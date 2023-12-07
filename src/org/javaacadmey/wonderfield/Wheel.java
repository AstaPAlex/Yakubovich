package org.javaacadmey.wonderfield;

import org.javaacadmey.wonderfield.player.Player;

public class Wheel {
    public String getPoints(Player player) {
        Points[] points = Points.values();
        Points point = points[Game.random.nextInt(points.length)];
        switch (point) {
            case SKIPP_MOVE -> {
                return Points.SKIPP_MOVE.getPoints();
            }
            case DOUBLING -> {
                player.sumPoints(point.getPoints());
                return Points.DOUBLING.getPoints();
            }
            default -> {
                player.sumPoints(point.getPoints());
                return point.getPoints();
            }
        }
    }
}
