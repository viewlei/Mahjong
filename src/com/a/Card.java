package com.a;

public class Card {
    public int color;
    public int id;
    public static String[] color_map = { "万", "条", "筒" };
    public static String[] id_map = { "一", "二", "三", "四", "五", "六", "七", "八", "九" };

    public Card(int color, int id) {
        this.color = color;
        this.id = id;
    }

    @Override
    public String toString() {
        return id_map[id - 1] + color_map[color];
    }

    public boolean equals(Card o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (color == o.color && id == o.id) {
            return true;
        } else {
            return false;
        }
    }
}
