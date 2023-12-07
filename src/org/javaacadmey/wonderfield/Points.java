package org.javaacadmey.wonderfield;

public enum Points {
    HUNDRED("100"), TWO_HUNDRED("200"), THREE_HUNDRED("300"), FOUR_HUNDRED("400"),
    FIVE_HUNDRED("500"), SIX_HUNDRED("600"), SEVEN_HUNDRED("700"), EIGHT_HUNDRED("800"),
    NINE_HUNDRED("900"), TEN_HUNDRED("1000"), ELEVEN_HUNDRED("1100"), TWELVE_HUNDRED("1200"),
    SKIPP_MOVE("ПРОПУСК ХОДА"), DOUBLING("УДВОЕНИЕ ОЧКОВ");

    private final String point;

    Points(String point) {
        this.point = point;
    }

    public String getPoints() {
        return point;
    }
}
