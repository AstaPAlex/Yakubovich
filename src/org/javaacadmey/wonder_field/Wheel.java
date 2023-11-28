package org.javaacadmey.wonder_field;

import org.javaacadmey.wonder_field.player.Player;

import java.util.Random;

public class Wheel {
    public String getPoints(Player player){
        int result = Game.random.nextInt(14) * 100;
        if (result == 0) {
            return "ПРОПУСК ХОДА";
        } else if (result == 1300) {
            player.setPoints(player.getPoints() * 2);
            return "УДВОЕНИЕ ОЧКОВ";
        } else {
            player.setPoints(player.getPoints() + result);
            return String.valueOf(result);
        }
    }
}
