package org.javaacadmey.wonderfield;

import org.javaacadmey.wonderfield.player.Player;
import org.javaacadmey.wonderfield.player.PlayerAnswer;

public class Yakubovich {
    public void sayStartShow() {
        System.out.println("Якубович: Здравствуйте, уважаемые дамы и господа! "
                + "Пятница! В эфире капитал-шоу \"Поле чудес\"!");
    }

    public void sayEndShow() {
        System.out.println("Якубович: Мы прощаемся с вами ровно на одну неделю! "
               + " Здоровья вам, до встречи!");
    }

    public void greetingPlayers(String[] namesPlayers, boolean readyFinalRound, int numberRound) {
        if (readyFinalRound) {
            System.out.println("Якубович: приглашаю победителей групповых этапов: ");
        } else {
            System.out.printf("Якубович: приглашаю %d тройку игроков: ", numberRound + 1);
        }
        namesPlayers(namesPlayers);
    }

    private void namesPlayers(String[] namesPlayers) {
        for (int i = 0; i < namesPlayers.length; i++) {
            System.out.print(namesPlayers[i]);
            if (i < (namesPlayers.length - 1)) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public void askQuestion(String question) {
        System.out.println("Якубович: Внимание вопрос! \n" + question);
    }

    public void sayWinner(Player player, boolean readyFinalRound) {
        if (readyFinalRound) {
            System.out.printf("Якубович: И перед нами победитель Капитал шоу поле чудес!"
                   + " Это %s из %s\n", player.getName(), player.getCity());
            System.out.printf("Якубович: Сумма его очков равна %d, \n", player.getPoints());
            if (player.getMoney() != 0) {
                System.out.printf("Якубович: А так же он выиграл %d рублей! \n", player.getMoney());
            }
            return;
        }
        System.out.printf("Якубович: Молодец! %s из %s проходит в финал!\n",
                player.getName(), player.getCity());
    }

    public boolean checkAnswer(PlayerAnswer playerAnswer, Tableau tableau) {
        if (playerAnswer.getTypeAnswerPlayer().equals("буква")) {
            return checkAnswerLetter(playerAnswer.getAnswerPlayer(), tableau);
        } else if (playerAnswer.getTypeAnswerPlayer().equals("слово")) {
            return checkAnswerWord(playerAnswer.getAnswerPlayer(),
                    tableau);
        }
        return false;
    }

    private boolean checkAnswerLetter(String answerPlayer, Tableau tableau) {
        if (tableau.checkLetters(answerPlayer)) {
            System.out.println("Якубович: Есть такая буква, откройте ее!");
            tableau.showLettersOfTableau();
            return true;
        } else {
            System.out.println("Якубович: Нет такой буквы! Следующий игрок, крутите барабан!");
            System.out.println("_________________________________");
            return false;
        }
    }

    private boolean checkAnswerWord(String answerPlayer, Tableau tableau) {
        if (tableau.checkWord(answerPlayer)) {
            System.out.printf("Якубович: %s. Абсолютно верно!\n", answerPlayer);

            return true;
        }
        System.out.println("Якубович: Неверно! Следующий игрок!\n");
        return false;
    }

    public boolean checkWheel(Player player, Wheel wheel) {
        String result = wheel.getPoints(player);
        System.out.printf("Якубович: Барабан показал: %s! \n", result);
        if (result.equals("ПРОПУСК ХОДА")) {
            System.out.println("Якубович: Следующий игрок, крутите барабан!");
            System.out.println("_________________________________");
            return false;
        }
        System.out.printf("Якубович: %s общая сумма Ваших очков %d! \n",
                player.getName(), player.getPoints());
        return true;
    }

    public void startGameCheckBox(Player player) {
        System.out.printf("Якубович: игрок %s, вы отгадали 3 буквы подряд! Коробки в студию! \n",
                player.getName());
        System.out.println("Якубович: в одной из коробок находятся 20 тысяч рублей, "
               + "Вам необходимо выбрать одну из коробок!");
        int selectPlayer = player.selectBox();
        System.out.printf("Якубович: игрок %s выбрал коробку №%d! \n",
                player.getName(), selectPlayer);
        if (selectPlayer == (Game.random.nextInt(2) + 1)) {
            System.out.printf("Якубович: игрок %s выигрывает 20 тысяч рублей! Поздравляю! \n",
                    player.getName());
            player.setMoney(player.getMoney() + 20000);
        } else {
            System.out.printf("Якубович: к сожалению игрок %s выбрал пустую коробку! \n",
                    player.getName());
        }
    }

    public void greetingSuperWinner(Player player) {
        System.out.printf("Якубович: Игрок %s, перед Вами магазин с призами, "
               + "Вы можете купить за очки любой приз! \n", player.getName());
    }

    public boolean sayStartSuperGame(Player player) {
        System.out.print("Якубович: Покупки окончены! И теперь начинаем СУПЕР ИГРУ!!! \n");
        System.out.printf("Якубович: Игрок %s вы согласны на СУПЕР ИГРУ? \n", player.getName());
        if (!player.sayWantSuperGame()) {
            return false;
        }
        System.out.println("Якубович: Я Вам задам вопрос и Вы можете отгадать 3 буквы!");
        System.out.println("Якубович: После этого вы обязаны назвать слово целиком! Удачи!");
        return true;
    }

    public void sayGuessWord() {
        System.out.println("Якубович: Теперь назовите слово!");
    }

    public void saySuperWinner(Player player, boolean winGame, Tableau tableau, Shop shop) {
        if (winGame) {
            System.out.printf("Якубович: И победителем Камитал шоу \"Поле Чудес\" "
                   + "становится %s из %s! Призы в студию! \n", player.getName(), player.getCity());
            System.out.printf("Якубов: А супер приз сегодня был: %s! \n", shop.getSuperItem());
            System.out.println("Якубович: Призы в студию!");
            player.showPrizes();
        } else {
            System.out.printf("Якубович: К сожалению не правильно! Правильный ответ '%s' \n",
                    tableau.getAnswer());
        }
    }

    public void rightAnswer() {
        System.out.println("Якубович: Абсолютно верно!");
    }
}
