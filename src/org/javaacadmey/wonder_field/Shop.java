package org.javaacadmey.wonder_field;

import org.javaacadmey.wonder_field.player.Player;

public class Shop {
    private String[] items = {"Чашка", "Часы", "Самокат", "Телефон", "Ноутбук", "Компьюетр", "Путевка на море", "Мотоцикл", "Автомобиль"};
    private int[] prices = {100, 500, 700, 1000, 2000, 3000, 4000, 6000, 12000};

    private String[] superItems = {"Квартира", "Дом", "Феррари"};
    private int indexSuperItem;

    public void showItems() {
        System.out.println("Список товаров:");
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                System.out.printf("%d - %s: %d \n", i + 1, items[i], prices[i]);
            }
        }
    }

    public boolean sellItem(int numItem, Player player) {
        if (numItem == 0) {
            return false;
        }
        if (items[numItem - 1] == null) {
            System.out.println("Данный товар куплен, выберите другой!");
            return true;
        } else if (player.getPoints() < prices[numItem - 1]) {
            System.out.println("У Вас недостаточно средств!");
            return true;
        } else {
            System.out.printf("Игрок выбрал %s \n", items[numItem - 1]);
            player.setPoints(player.getPoints() - prices[numItem - 1]);
            player.setItems(items[numItem - 1]);
            items[numItem - 1] = null;
            System.out.printf("Остаток средств составляет: %d \n", player.getPoints());
            return true;
        }
    }
    public String getSuperItem(){
        return superItems[indexSuperItem];
    }

    public String createSuperItem() {
        indexSuperItem = Game.random.nextInt(3);
        return superItems[indexSuperItem];
    }
}
