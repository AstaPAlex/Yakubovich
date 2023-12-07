package org.javaacadmey.wonderfield.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.javaacadmey.wonderfield.Game;
import org.javaacadmey.wonderfield.Tableau;


public class Player {
    private final String name;
    private final String city;
    private int money;
    private int points;
    private final List<String> items;
    private static final String ERROR_CHECK_BOX = "Некорректное значение, введите '1' или '2'";
    private static final String ERROR_IS_EMPTY = "Пустое значение, попробуйте еще раз!";
    private int countRightAnswer;

    public Player(String name, String city) {
        this.name = name;
        this.city = city;
        points = 0;
        items = new ArrayList<>();
    }

    public int getCountRightAnswer() {
        return countRightAnswer;
    }

    public void setCountRightAnswer(int countRightAnswer) {
        if (countRightAnswer == 0) {
            this.countRightAnswer = countRightAnswer;
        } else {
            this.countRightAnswer++;
        }
    }

    public void addItems(String item) {
        items.add(item);
    }

    public void showPrizes() {
        System.out.println("На сцену выносят:");
        if (getMoney() != 0) {
            System.out.printf("Денежные средства: %d  рублей!\n", getMoney());
        }
        for (var item : items) {
            System.out.printf("%s \n", item);
        }
    }

    public int getPoints() {
        return points;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String sayLetter() {
        while (true) {
            String letter = Game.scanner.nextLine();
            try {
                isEmpty(letter);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(ERROR_IS_EMPTY);
                continue;
            }
            char ch = letter.charAt(0);
            if (letter.length() != 1) {
                System.out.println("Ошибка! Введи одну букву!");
            } else if (ch >= 'А' & ch <= 'я') {
                System.out.printf("Игрок %s: буква %s \n", name, letter);
                return letter.toUpperCase(Locale.ROOT);
            } else {
                System.out.println("Ошибка! это не русская буква, введите русскую букву!");
            }
        }
    }

    public String sayWord() {
        while (true) {
            String word = Game.scanner.nextLine();
            try {
                isEmpty(word);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(ERROR_IS_EMPTY);
                continue;
            }
            System.out.printf("Игрок %s: слово %s \n", name, word);
            return word;
        }
    }

    public PlayerAnswer movePlayer() {
        System.out.printf("Ход игрока %s, %s \n", name, city);
        System.out.println("Если хотите букву нажмите 'б' и enter, "
               + "если хотите слово нажмите 'c' и enter");
        while (true) {
            String move = Game.scanner.nextLine();
            try {
                isEmpty(move);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(ERROR_IS_EMPTY);
                continue;
            }
            if (move.equals("б")) {
                return new PlayerAnswer(sayLetter(), "буква");
            } else if (move.equals("с")) {
                return new PlayerAnswer(sayWord(), "слово");
            } else {
                System.out.println("Некорректное значение, введите 'б' или 'с'");
            }
        }
    }

    public int selectBox() {
        System.out.printf("Ход игрока %s из %s \n", name, city);
        System.out.println("Чтобы выбрать одну из коробок введите '1' или '2'");
        while (true) {
            String select = Game.scanner.nextLine();
            try {
                isEmpty(select);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(ERROR_IS_EMPTY);
                continue;
            }
            if (select.equals("1") || select.equals("2")) {
                return Integer.parseInt(select);
            } else {
                System.out.println(ERROR_CHECK_BOX);
            }
        }
    }

    public int buyItems() {
        System.out.printf("Ход игрока %s из %s \n", name, city);
        while (true) {
            System.out.println("Выберите приз, введите число от '1' до '9'. "
                    + "Чтобы закончить покупки введите 'в'");
            String select = Game.scanner.nextLine();
            try {
                isEmpty(select);
                if (select.equalsIgnoreCase("В")) {
                    return 0;
                } else if (Integer.parseInt(select) <= 9 && Integer.parseInt(select) > 0) {
                    return Integer.parseInt(select);
                } else {
                    System.out.println("Игрок, вы ввели не правильное число!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели не число, повторите ввод");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(ERROR_IS_EMPTY);
            }
        }
    }

    public void sayThreeLetters(Tableau tableau) {
        for (int i = 1; i < 4; i++) {
            tableau.checkLetters(sayLetter());
            tableau.showLettersOfTableau();
        }
    }

    public boolean sayWantSuperGame() {
        while (true) {
            System.out.println("Если Вы согласны на супер игру введите 'д', "
                   + "если вы отказываетесь от супер игры введите 'н'");
            String select = Game.scanner.nextLine();
            try {
                isEmpty(select);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(ERROR_IS_EMPTY);
                continue;
            }
            if (select.equalsIgnoreCase("д")) {
                return true;
            } else if (select.equalsIgnoreCase("н")) {
                return false;
            } else {
                System.out.println("Вы ввели не правильное значение!");
            }
        }
    }

    public void sumPoints(String points) {
        if (points.equals("УДВОЕНИЕ ОЧКОВ")) {
            this.points *= 2;
        } else {
            this.points += Integer.parseInt(points);
        }
    }

    public void isEmpty(String str) throws StringIndexOutOfBoundsException {
        if (str.isEmpty()) {
            throw new StringIndexOutOfBoundsException();
        }
    }


}
