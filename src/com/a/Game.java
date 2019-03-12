package com.a;

import java.util.Collections;
import java.util.LinkedList;

public class Game {
    public LinkedList<Player> players;
    public LinkedList<Card> card_pool; // 牌库
    public LinkedList<Card> discard_pool; // 弃牌堆
    public boolean hasFinished;

    public Game() {
        // 初始化牌库
        players = new LinkedList<Player>();
        card_pool = new LinkedList<Card>();
        discard_pool = new LinkedList<Card>();
        initCards();
        hasFinished = false;
    }

    public void initCards() {
        // 初始化牌库
        for (int i = 1; i < 30; i++) {
            if (i % 10 == 0)
                continue;
            for (int j = 0; j < 4; j++) {
                card_pool.add(new Card(i / 10, i % 10));
            }
        }
        // 洗牌
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
            Player curr_player = players.remove(); // player出队
            Card curr_card = card_pool.remove();
            System.out.println("现在回合为：" + curr_player.name + ", 你接到了：" + curr_card.toString());
            boolean tag = curr_player.reveive(curr_card);// 接牌，判断是否胡牌
            if (tag) {
                System.out.println("获胜者为:" + curr_player.toString());
                break;
            }
            curr_card = curr_player.move(); // 打牌
            System.out.println(curr_player.name + "打出了：" + curr_card.toString());
            discard_pool.add(curr_card); // 加入弃牌堆
            players.add(curr_player); // 等候
        }
        System.out.println("牌库空了！");
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.join(new Wanjia("lei"));
        g.run();
    }
}
