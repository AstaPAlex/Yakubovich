package org.javaacadmey.wonderfield;

import org.javaacadmey.wonderfield.player.Player;

public class Shop {
    private int indexSuperItem;
    private final SuperItems[] superItems;

    private final Items[] items;

    public Shop() {
        superItems = SuperItems.values();
        items = Items.values();
    }

    public void showItems() {
        System.out.println("Список товаров:");
        for (Items item : items) {
            if (item != null) {
                item.showInfo();
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
        } else if (player.getPoints() < items[numItem - 1].getPrice()) {
            System.out.println("У Вас недостаточно средств!");
            return true;
        } else {
            System.out.printf("Игрок выбрал %s \n", items[numItem - 1].getName());
            player.setPoints(player.getPoints() - items[numItem - 1].getPrice());
            player.addItems(items[numItem - 1].getName());
            items[numItem - 1] = null;
            System.out.printf("Остаток средств составляет: %d \n", player.getPoints());
            return true;
        }
    }

    public String getSuperItem() {
        return superItems[indexSuperItem].getName();
    }

    public String createSuperItem() {
        indexSuperItem = Game.random.nextInt(superItems.length);
        return superItems[indexSuperItem].getName();
    }
}
