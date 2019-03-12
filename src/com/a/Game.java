package com.a;

import java.util.Collections;
import java.util.LinkedList;

public class Game {
    public LinkedList<Player> players;
    public LinkedList<Card> card_pool; // �ƿ�
    public LinkedList<Card> discard_pool; // ���ƶ�
    public boolean hasFinished;

    public Game() {
        // ��ʼ���ƿ�
        players = new LinkedList<Player>();
        card_pool = new LinkedList<Card>();
        discard_pool = new LinkedList<Card>();
        initCards();
        hasFinished = false;
    }

    public void initCards() {
        // ��ʼ���ƿ�
        for (int i = 1; i < 30; i++) {
            if (i % 10 == 0)
                continue;
            for (int j = 0; j < 4; j++) {
                card_pool.add(new Card(i / 10, i % 10));
            }
        }
        // ϴ��
        Collections.shuffle(card_pool);
    }

    public void join(Player e) {
        for (int i = 0; i < 13; i++) {
            e.cards.add(card_pool.remove());
        }
        players.add(e);
    }

    public void displayCards() {
        for (Card c : card_pool) {
            System.out.println(c.toString());
        }
    }

    public void run() {
        while (!card_pool.isEmpty()) {
            Player curr_player = players.remove(); // player����
            Card curr_card = card_pool.remove();
            System.out.println("���ڻغ�Ϊ��" + curr_player.name + ", ��ӵ��ˣ�" + curr_card.toString());
            boolean tag = curr_player.reveive(curr_card);// ���ƣ��ж��Ƿ����
            if (tag) {
                System.out.println("��ʤ��Ϊ:" + curr_player.toString());
                break;
            }
            curr_card = curr_player.move(); // ����
            System.out.println(curr_player.name + "����ˣ�" + curr_card.toString());
            discard_pool.add(curr_card); // �������ƶ�
            players.add(curr_player); // �Ⱥ�
        }
        System.out.println("�ƿ���ˣ�");
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.join(new Wanjia("lei"));
        g.run();
    }
}
