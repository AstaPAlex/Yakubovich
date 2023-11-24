package org.javaacadmey.wonder_field;
import org.javaacadmey.wonder_field.player.Player;
import java.util.Scanner;

public class Game {
    private static final int NUMBER_PLAYERS = 3;
    private static final int NUMBER_ROUNDS = 4;
    private static final int NUMBER_GROUP_ROUNDS = 3;
    private static final int INDEX_FINAL_ROUND = 3;
    public static Scanner scanner = new Scanner(System.in);
    private final String[] question = new String[NUMBER_ROUNDS];
    private final String[] answer = new String[NUMBER_ROUNDS];
    private Yakubovich yakubovich;
    private final Player[] winners = new Player[INDEX_FINAL_ROUND];
    private Player[] players;
    private Tableau tableau;
    private boolean readyFinalRound;

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
        yakubovich.sayStartShow();
        readyFinalRound = playGroupRounds();
        playFinalRound(readyFinalRound);
        yakubovich.sayEndShow();
    }

    private Player[] createPlayers() {
        Player[] players = new Player[NUMBER_PLAYERS];
        for (int i = 0 ; i < NUMBER_PLAYERS; i++){
            System.out.println("Игрок №" + (i + 1) + " представьтесь: имя,город. Например: Иван,Москва");
            String[] nameAndCity = scanner.nextLine().split(",");
            players[i] = new Player(nameAndCity[0], nameAndCity[1]);
        }
        return players;
    }

    private boolean playGroupRounds() {
        tableau = new Tableau();
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
                boolean result = true;
                while (result){
                    result = yakubovich.checkAnswer(player, player.movePlayer(), tableau, readyFinalRound);
                    if (!tableau.containsUnknownLetters()){
                        return player;
                    }
                }
            }
        }
    }

    private void playFinalRound(boolean readyFinalRound) {
        yakubovich.greetingPlayers(getPlayersName(winners), readyFinalRound, 0);
        yakubovich.askQuestion(question[INDEX_FINAL_ROUND]);
        tableau.init(answer[INDEX_FINAL_ROUND]);
        tableau.showLettersOfTableau();
        startPlayRound(winners, readyFinalRound);
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
    }

    private void sleepGame(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

}
