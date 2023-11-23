package org.javaacadmey.wonder_field;
import org.javaacadmey.wonder_field.player.Player;
import java.util.Scanner;

public class Game {
    static final private int NUMBER_PLAYERS = 3;
    static final private int NUMBER_ROUNDS = 4;
    static final private int NUMBER_GROUP_ROUNDS = 3;
    static final private int INDEX_FINAL_ROUND = 3;
    static public Scanner scanner = new Scanner(System.in);
    final private String[] question = new String[NUMBER_ROUNDS];
    final private String[] answer = new String[NUMBER_ROUNDS];
    private Yakubovich yakubovich;
    final private Player[] winners = new Player[INDEX_FINAL_ROUND];
    private Player[] players;
    private Tableau tableau;
    private boolean readyFinalRound;

    public void init(){
        System.out.println("Запуск игры \"Поле чудес\" - подготовка к игре. Вам нужно ввести вопросы и ответы для игры.");
        //createQuestionAndAnswer();
        createQuestionAndAnswerAuto();
        System.out.println("Иницализация закончена, игра начнется через 5 секунд");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
        start();
    }

    private void start(){
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
            String nameAndCity = scanner.nextLine();
            players[i] = new Player(nameAndCity.split(","));
        }
        return players;
    }

    private boolean playGroupRounds() {
        tableau = new Tableau();
        for (int i = 0; i < NUMBER_GROUP_ROUNDS; i++){
            players = createPlayers();
            yakubovich.greetingPlayers(getPlayersName(players), readyFinalRound);
            yakubovich.askQuestion(question[i]);
            tableau.init(answer[i]);
            tableau.showLettersOfTableau();
            winners[i] = startPlayRound(players, tableau, readyFinalRound);
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

    private Player startPlayRound(Player[] players, Tableau tableau, boolean readyFinalRound) {
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
        yakubovich.greetingPlayers(getPlayersName(winners), readyFinalRound);
        yakubovich.askQuestion(question[INDEX_FINAL_ROUND]);
        tableau.init(answer[INDEX_FINAL_ROUND]);
        tableau.showLettersOfTableau();
        startPlayRound(winners, tableau, readyFinalRound);
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

}
