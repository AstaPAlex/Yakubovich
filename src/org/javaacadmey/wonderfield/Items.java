package org.javaacadmey.wonderfield;

public enum Items {
    PRIZE1("Чашка", 100, 1), PRIZE2("Часы", 500, 2), PRIZE3("Самокат", 700, 3),
    PRIZE4("Телефон", 1000, 4), PRIZE5("Ноутбук", 2000, 5), PRIZE6("Компьютер", 3000, 6),
    PRIZE7("Путевка на море", 4000, 7), PRIZE8("Мотоцикл", 6000, 8), PRIZE9("Автомобиль", 12000, 9);

    private final String name;
    private final int price;
    private final int number;

    Items(String name, int price, int number) {
        this.name = name;
        this.price = price;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public void showInfo() {
        System.out.printf("%d - %s: %d \n", getNumber(), getName(), getPrice());
    }
}
