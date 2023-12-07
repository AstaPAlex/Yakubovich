package org.javaacadmey.wonderfield;

import java.util.Random;
import java.util.Scanner;
import org.javaacadmey.wonderfield.player.Player;

public class Game {
    private static final int NUMBER_PLAYERS = 3;
    private static final int NUMBER_ROUNDS = 5;
    private static final int NUMBER_GROUP_ROUNDS = 3;
    private static final int INDEX_FINAL_ROUND = 3;
    private static final int INDEX_SUPER_ROUND = 4;
    public static final Scanner scanner = new Scanner(System.in);
    public static final Random random = new Random();
    private Yakubovich yakubovich;
    private final Player[] winners = new Player[INDEX_FINAL_ROUND];
    private Player[] players;
    private Tableau tableau;
    private boolean readyFinalRound;
    private Wheel wheel;
    private Shop shop;
    Question question;


    public void init() {
        System.out.println("Запуск игры \"Поле чудес\" - подготовка к игре."
                + " Вам нужно ввести вопросы и ответы для игры.");
        question = new Question(NUMBER_ROUNDS);
        question.createQuestionAndAnswerAuto();
        //question.createQuestionAndAnswer();
        System.out.println("Иницализация закончена, игра начнется через 5 секунд");
        sleepGame();
        System.out.println("\n".repeat(50));
    }

    public void start() {
        yakubovich = new Yakubovich();
        tableau = new Tableau();
        wheel = new Wheel();
        shop = new Shop();
        yakubovich.sayStartShow();
        readyFinalRound = playGroupRounds();
        Player superWinner = playFinalRound(readyFinalRound);
        playSuperRound(superWinner);
        yakubovich.sayEndShow();
    }

    private Player[] createPlayers() {
        Player[] players = new Player[NUMBER_PLAYERS];
        for (int i = 0; i < NUMBER_PLAYERS; i++) {
            System.out.printf("Игрок №%d представьтесь: имя,город. Например: Иван,Москва\n", i + 1);
            String[] nameAndCity = scanner.nextLine().split(",");
            players[i] = new Player(nameAndCity[0], nameAndCity[1]);
        }
        return players;
    }

    private boolean playGroupRounds() {
        for (int i = 0; i < NUMBER_GROUP_ROUNDS; i++) {
            players = createPlayers();
            yakubovich.greetingPlayers(getPlayersName(players), readyFinalRound, i);
            yakubovich.askQuestion(question.getQuestion(i));
            tableau.init(question.getAnswer(i));
            tableau.showLettersOfTableau();
            winners[i] = startPlayRound(players, readyFinalRound);
        }
        return true;
    }

    private String[] getPlayersName(Player[] players) {
        String[] namesPlayers = new String[NUMBER_PLAYERS];
        for (int i = 0; i < NUMBER_PLAYERS; i++) {
            namesPlayers[i] = players[i].getName();
        }
        return namesPlayers;
    }

    private Player startPlayRound(Player[] players, boolean readyFinalRound) {
        while (true) {
            for (Player player : players) {
                boolean result = false;
                do {
                    if (!tableau.containsUnknownLetters()) {
                        yakubovich.sayWinner(player, readyFinalRound);
                        return player;
                    }
                    checkBoxes(player, result);
                    if (!yakubovich.checkWheel(player, wheel)) {
                        break;
                    }
                    result = yakubovich.checkAnswer(player.movePlayer(), tableau);
                } while (result);
            }
        }
    }

    private Player playFinalRound(boolean readyFinalRound) {
        yakubovich.greetingPlayers(getPlayersName(winners), readyFinalRound, 0);
        yakubovich.askQuestion(question.getQuestion(INDEX_FINAL_ROUND));
        tableau.init(question.getAnswer(INDEX_FINAL_ROUND));
        tableau.showLettersOfTableau();
        return startPlayRound(winners, readyFinalRound);
    }

    private void sleepGame() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());


        }
    }

    public void checkBoxes(Player player, boolean result) {
        if (result) {
            player.setCountRightAnswer(1);
        } else {
            player.setCountRightAnswer(0);
            return;
        }
        if (player.getCountRightAnswer() == 3) {
            yakubovich.startGameCheckBox(player);
            player.setCountRightAnswer(0);
        }
    }

    public void playSuperRound(Player player) {
        yakubovich.greetingSuperWinner(player);
        shopping(player);
        if (yakubovich.sayStartSuperGame(player)) {
            String superItem = shop.createSuperItem();
            yakubovich.askQuestion(question.getQuestion(INDEX_SUPER_ROUND));
            tableau.init(question.getAnswer(INDEX_SUPER_ROUND));
            player.sayThreeLetters(tableau);
            yakubovich.sayGuessWord();
            boolean winPlayerSuperGame = tableau.checkWord(player.sayWord());
            if (winPlayerSuperGame) {
                yakubovich.rightAnswer();
                player.addItems(superItem);
            }
            yakubovich.saySuperWinner(player, winPlayerSuperGame, tableau, shop);
        } else {
            yakubovich.saySuperWinner(player, true, tableau, shop);
        }

    }

    public void shopping(Player player) {
        boolean result;
        do {
            shop.showItems();
            result = shop.sellItem(player.buyItems(), player);
        } while (result);
    }

}
