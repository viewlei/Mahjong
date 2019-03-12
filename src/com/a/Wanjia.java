package com.a;

import java.util.Scanner;

public class Wanjia extends Player {

    public Wanjia(String name) {
        super();
        this.name = name;
    }

    @Override
    public Card move() {
        Scanner cin = new Scanner(System.in);
        Card tmp = null;
        outer: while (tmp == null) {
            System.out.print("��������Ҫ�����: ");
            String msg = cin.next();
            for (Card c : cards) {
                if (c.toString().equals(msg)) {
                    tmp = c;
                    break outer;
                }
            }
            System.out.println("û�������ƣ�����������:");
        }
        cards.remove(tmp);
        return tmp;
    }
}
