package org.javaacadmey.wonder_field.player;
import org.javaacadmey.wonder_field.Game;
import java.util.Locale;

public class Player {
    private final String name;
    private final String city;

    public Player(String name, String city) {
        this.name = name;
        this.city = city;
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
                    System.out.println("Игрок "+ name +": буква " + letter);
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
        System.out.println("Игрок " + name + ": слово " + word);
        return word;
    }

    public PlayerAnswer movePlayer() {
        System.out.println("Ход игрока " + name + ", " + city);
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
}
