package org.javaacadmey.wonder_field;

import org.javaacadmey.wonder_field.player.Player;
import org.javaacadmey.wonder_field.player.PlayerAnswer;

public class Yakubovich {
    private int numberRound= 0;

    public void sayStartShow(){
        System.out.println("Якубович: Здравствуйте, уважаемые дамы и господа! Пятница! В эфире капитал-шоу \"Поле чудес\"!");
    }

    public void sayEndShow(){
        System.out.println("Якубович: Мы прощаемся с вами ровно на одну неделю! Здоровья вам, до встречи!");
    }

    public void greetingPlayers(String[] namesPlayers, boolean finalRound){
        if (finalRound){
            System.out.println("Якубович: приглашаю победителей групповых этапов: ");
        } else {
            System.out.print("Якубович: приглашаю " + ++numberRound + " тройку игроков: ");
        }
            for (int i=0; i < namesPlayers.length; i++){
                System.out.print(namesPlayers[i]);
                if (i < (namesPlayers.length - 1)){
                    System.out.print(", ");
                }
            }
            System.out.println();
    }

    public void askQuestion(String question){
        System.out.println("Якубович: Внимание вопрос! \n" + question);
    }

    public void sayWinner(String name, String city,boolean finalRound){
        if (finalRound){
            System.out.println("Якубович: И перед нами победитель Капитал шоу поле чудес! Это " + name + " из " + city);
            return;
        }
        System.out.println("Якубович: Молодец! " + name + " из " + city + " проходит в финал!");
    }

    public boolean checkAnswer(Player player, PlayerAnswer playerAnswer, Tableau tableau, boolean finalRound){
        if (playerAnswer.getTypeAnswerPlayer().equals("буква")){
            return checkAnswerLetter(playerAnswer.getAnswerPlayer(),tableau);
        } else if (playerAnswer.getTypeAnswerPlayer().equals("слово")) {
            return checkAnswerWord(player, playerAnswer.getAnswerPlayer(), tableau, finalRound);
        }
        return false;
    }

    private boolean checkAnswerLetter(String answerPlayer, Tableau tableau){
        if (tableau.checkLetters(answerPlayer)){
            System.out.println("Якубович: Есть такая буква, откройте ее!");
            tableau.showLettersOfTableau();
            return true;
        } else {
            System.out.println("Якубович: Нет такой буквы! Следующий игрок, крутите барабан!");
            System.out.println("_________________________________");
            return false;
        }
    }

    private boolean checkAnswerWord(Player player, String answerPlayer, Tableau tableau, boolean finalRound){
        if (tableau.checkWord(answerPlayer)){
            System.out.println("Якубович: " + answerPlayer + " Абсолютно верно!");
            sayWinner(player.getName(), player.getCity(), finalRound);
            return true;
        }
        System.out.println("Якубович: Неверно! Следующий игрок!");
        return false;
    }
}
