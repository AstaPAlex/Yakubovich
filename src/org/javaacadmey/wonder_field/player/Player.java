package org.javaacadmey.wonder_field.player;
import org.javaacadmey.wonder_field.Game;
import org.javaacadmey.wonder_field.Tableau;

import java.util.Locale;

public class Player {
    private final String name;
    private final String city;
    private int money;
    private int points;
    private final String[] items;
    private int countItems;

    public Player(String name, String city) {
        this.name = name;
        this.city = city;
        points = 0;
        items = new String[8];
        countItems = 0;
    }

    public void setItems(String item) {
        items[countItems] = item;
        countItems++;
    }

    public void showPrizes() {
        System.out.println("На сцену выносят:");
        if (getMoney() != 0) {
            System.out.printf("Денежные средства: %d  рублей!\n", getMoney());
        }
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null){
                return;
            }
            System.out.printf("%d - %s \n", i + 1, items[i]);
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
            if (letter.length() == 1) {
                char ch = letter.charAt(0);
                if (ch >= 'А' & ch <= 'я'){
                    System.out.printf("Игрок %s: буква %s \n", name, letter);
                    return letter.toUpperCase(Locale.ROOT);
                } else {
                    {
                        System.out.println("Ошибка! это не русская буква, введите русскую букву!");
                    }
                }
            } else {
                System.out.println("Ошибка! Введи одну букву!");
            }
        }
    }

    public String sayWord() {
        String word = Game.scanner.nextLine();
        System.out.printf("Игрок %s: слово %s \n", name, word);
        return word;
    }

    public PlayerAnswer movePlayer() {
        System.out.printf("Ход игрока %s, %s \n", name, city);
        System.out.println("Если хотите букву нажмите 'б' и enter, если хотите слово нажмите 'c' и enter");
        while (true) {
            String move = Game.scanner.nextLine();
            if (move.equals("б")) {
                return new PlayerAnswer(sayLetter(), "буква");
            } else if (move.equals("с")) {
                return new PlayerAnswer(sayWord(), "слово");
            } else {
                System.out.println("Некорректное значение, введите 'б' или 'с'");
            }
        }
    }

    public int selectBox(){
        System.out.printf("Ход игрока %s из %s \n", name, city);
        while (true) {
            System.out.println("Чтобы выбрать одну из коробок введите '1' или '2'");
            String select = Game.scanner.nextLine();
            if (!select.equals("1") && !select.equals("2")) {
                System.out.println("Некорректное значение, введите '1' или '2'");
            } else {
                return Integer.parseInt(select);
            }
        }
    }

    public int buyItem() {
        System.out.printf("Ход игрока %s из %s \n", name, city);
        while (true) {
            System.out.println("Выберите приз, введите число от '1' до '9'. Чтобы закончить покупки введите 'в'");
            String select = Game.scanner.nextLine();
            try {
                if (select.equalsIgnoreCase("В")) {
                    return 0;
                } else if (Integer.parseInt(select) <= 9 && Integer.parseInt(select) > 0) {
                    return Integer.parseInt(select);
                } else {
                    System.out.println("Игрок, вы ввели не правильное число!");
                }
            } catch (NumberFormatException e){
                System.out.println("Вы ввели не число, повторите ввод");
            }
        }
    }

    public void sayThreeLetters(Tableau tableau) {
        for (int i = 1; i < 4; i++) {
            tableau.checkLetters(sayLetter());
            tableau.showLettersOfTableau();
        }
    }

}
