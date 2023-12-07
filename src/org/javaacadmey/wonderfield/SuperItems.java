package org.javaacadmey.wonderfield;

public enum SuperItems {
    SUPER_ITEM1("Квартира"), SUPER_ITEM2("Дом"), SUPER_ITEM3("Феррари");
    private final String name;

    SuperItems(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
