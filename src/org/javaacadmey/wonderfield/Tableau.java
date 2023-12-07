package org.javaacadmey.wonderfield;

import java.util.Arrays;
import java.util.Locale;

public class Tableau {
    private String answer;
    private String[] lettersOfTableau;

    public String getAnswer() {
        return answer;
    }

    public void init(String answer) {
        lettersOfTableau = new String[answer.length()];
        Arrays.fill(lettersOfTableau, "_");
        this.answer = answer.toUpperCase();
    }

    public void showLettersOfTableau() {
        if (!answer.isEmpty()) {
            for (String letter : lettersOfTableau) {
                System.out.print(letter);
            }
            System.out.println();
        }
    }

    public boolean checkLetters(String letter) {
        boolean result = false;
        if (answer.contains(letter) & letter.length() == 1) {
            for (int i = 0; i < answer.length(); i++) {
                if (String.valueOf(answer.charAt(i)).equalsIgnoreCase(letter)) {
                    if (lettersOfTableau[i].equals("_")) {
                        lettersOfTableau[i] = letter.toUpperCase(Locale.ROOT);
                        result = true;
                    }
                }
            }
            return result;
        }
        return false;
    }

    public boolean checkWord(String word) {
        if (answer.equalsIgnoreCase(word)) {
            for (int i = 0; i < answer.length(); i++) {
                lettersOfTableau[i] = String.valueOf(answer.charAt(i)).toUpperCase(Locale.ROOT);
            }
            return true;
        }
        return false;
    }

    public boolean containsUnknownLetters() {
        for (String letter : lettersOfTableau) {
            if (letter.equals("_")) {
                return true;
            }
        }
        return false;
    }
}
