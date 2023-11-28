package org.javaacadmey.wonder_field;
import org.javaacadmey.wonder_field.player.Player;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private static final int NUMBER_PLAYERS = 3;
    private static final int NUMBER_ROUNDS = 5;
    private static final int NUMBER_GROUP_ROUNDS = 3;
    private static final int INDEX_FINAL_ROUND = 3;
    private static final int INDEX_SUPER_ROUND = 4;
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();
    private final String[] question = new String[NUMBER_ROUNDS];
    private final String[] answer = new String[NUMBER_ROUNDS];
    private Yakubovich yakubovich;
    private final Player[] winners = new Player[INDEX_FINAL_ROUND];
    private Player[] players;
    private Tableau tableau;
    private boolean readyFinalRound;
    private Wheel wheel;
    private int countRightAnswer;
    private Player superWinner;
    private Shop shop;
    private boolean winPlayerSuperGame;


    public void init(){
        System.out.println("Запуск игры \"Поле чудес\" - подготовка к игре. Вам нужно ввести вопросы и ответы для игры.");
        //createQuestionAndAnswer();
        createQuestionAndAnswerAuto();
        System.out.println("Иницализация закончена, игра начнется через 5 секунд");
        sleepGame(5000);
        System.out.println("\n".repeat(50));
    }

    public void start() {
        yakubovich = new Yakubovich();
        tableau = new Tableau();
        wheel = new Wheel();
        shop = new Shop();
        yakubovich.sayStartShow();
        readyFinalRound = playGroupRounds();
        superWinner = playFinalRound(readyFinalRound);
        playSuperRound(superWinner);
        yakubovich.sayEndShow();
    }

    private Player[] createPlayers() {
        Player[] players = new Player[NUMBER_PLAYERS];
        for (int i = 0 ; i < NUMBER_PLAYERS; i++){
            System.out.printf("Игрок №%d представьтесь: имя,город. Например: Иван,Москва\n", i + 1);
            String[] nameAndCity = scanner.nextLine().split(",");
            players[i] = new Player(nameAndCity[0], nameAndCity[1]);
        }
        return players;
    }

    private boolean playGroupRounds() {
        for (int i = 0; i < NUMBER_GROUP_ROUNDS; i++){
            players = createPlayers();
            yakubovich.greetingPlayers(getPlayersName(players), readyFinalRound, i);
            yakubovich.askQuestion(question[i]);
            tableau.init(answer[i]);
            tableau.showLettersOfTableau();
            winners[i] = startPlayRound(players, readyFinalRound);
        }
        return true;
    }

    private String[] getPlayersName(Player[] players) {
        String[] namesPlayers = new String[NUMBER_PLAYERS];
        for (int i = 0; i < NUMBER_PLAYERS; i++){
            namesPlayers[i] = players[i].getName();
        }
        return namesPlayers;
    }

    private Player startPlayRound(Player[] players, boolean readyFinalRound) {
        while (true){
            for (Player player : players){
                boolean result = false;
                do {
                    if (!tableau.containsUnknownLetters()){
                        yakubovich.sayWinner(player, readyFinalRound);
                        return player;
                    }
                    checkBoxes(player, result);
                    if (!yakubovich.checkWheel(player, wheel)) {
                        break;
                    }
                    result = yakubovich.checkAnswer(player, player.movePlayer(), tableau, readyFinalRound);

                } while (result);
            }
        }
    }

    private Player playFinalRound(boolean readyFinalRound) {
        yakubovich.greetingPlayers(getPlayersName(winners), readyFinalRound, 0);
        yakubovich.askQuestion(question[INDEX_FINAL_ROUND]);
        tableau.init(answer[INDEX_FINAL_ROUND]);
        tableau.showLettersOfTableau();
        return startPlayRound(winners, readyFinalRound);
    }

    private void createQuestionAndAnswer() {
        for (int i = 0; i < NUMBER_ROUNDS; i++) {
            System.out.println("Введите вопрос #" + (i + 1));
            question[i] = scanner.nextLine();
            System.out.println("Введите ответ #" + (i + 1));
            answer[i] = scanner.nextLine();
        }
    }

    private void createQuestionAndAnswerAuto() {
        question[0] = "Какое существо может задержать дыхание на 6 суток?";
        answer[0] = "Скорпион";
        question[1] = "Какое живое существо может спать, не просыпаясь 3 года?";
        answer[1] = "Улитка";
        question[2] = "У какого живого существа кровь синего цвета?";
        answer[2] = "Осьминог";
        question[3] = "Какая страна мира имеет две столицы?";
        answer[3] = "Боливия";
        question[4] = "Как у западных и южных славян назывались селение, деревня, курень?";
        answer[4] = "Жупа";
    }

    private void sleepGame(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void checkBoxes(Player player, boolean result){
        if (result) {
            countRightAnswer++;
        } else {
            countRightAnswer = 0;
            return;
        }
        if (countRightAnswer == 3) {
            yakubovich.startGameCheckBox(player);
            countRightAnswer = 0;
        }
    }

    public void playSuperRound(Player player){
        yakubovich.greetingSuperWinner(player);
        shopping(player);
        if (yakubovich.sayStartSuperGame(player)) {
            String superItem = shop.createSuperItem();
            yakubovich.askQuestion(question[INDEX_SUPER_ROUND]);
            tableau.init(answer[INDEX_SUPER_ROUND]);
            player.sayThreeLetters(tableau);
            yakubovich.sayGuessWord();
            winPlayerSuperGame = tableau.checkWord(player.sayWord());
            if (winPlayerSuperGame) {
                player.setItems(superItem);
            }
            yakubovich.saySuperWinner(player, winPlayerSuperGame, tableau, shop);
        } else {
            yakubovich.saySuperWinner(player, true, tableau, shop);
        }

    }

    public void shopping(Player player){
        boolean result;
        do {
            shop.showItems();
            result = shop.sellItem(player.buyItem(), player);
        } while (result);
    }

}
