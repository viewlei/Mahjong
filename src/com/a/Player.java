package com.a;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Player {
    public String name;
    public ArrayList<Card> cards;

    public Player() {
        cards = new ArrayList<Card>(14);
        name = "";
    }

    public boolean reveive(Card card) {
        cards.add(card); // ��������
        cards.sort(new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return (o1.color * 10 + o1.id) - (o2.color * 10 + o2.id);
            }
        }); // ������
        System.out.println(this.toString());
        return isVictory();
    }

    protected boolean isVictory() {
        // �޲Σ��ȷ���
        List<ArrayList<Card>> o = new ArrayList<ArrayList<Card>>();
        o.add(new ArrayList<Card>());
        o.add(new ArrayList<Card>());
        o.add(new ArrayList<Card>());
        for (Card tmp : cards) {
            o.get(tmp.color).add(tmp);
        }
        return isVictory(o);
    }

    private boolean isVictory(List<ArrayList<Card>> o) {
        // 0, 2, 3, 5, 6, 8, 9, 11, 12, 14
        int k[] = { 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1 };
        int tag = 0;
        for (int i = 0; i < o.size(); i++) {
            List<Card> l = o.get(i);
            if (k[l.size()] == 0) {
                continue;
            }
            if (l.size() == 0) {
                tag++;
                continue;
            }
            tag += isVictory(l.toArray());
        }
        return tag == o.size();
    }

    private int isVictory(Object[] o) {
        /*
         * ǰ������:O �Ѿ�����id�Ź���
         * 
         */
        Card[] c = new Card[o.length];
        for (int i = 0; i < o.length; i++) {
            c[i] = (Card) o[i];
        }
        if (c.length % 3 == 0) {
            for (int i = 0; i < c.length; i = i + 3) {
                if (c[i].id + 1 == c[i + 1].id && c[i].id + 2 == c[i + 2].id) {
                    // 1 2 3
                    continue;
                } else if (c[i].id == c[i + 1].id && c[i].id == c[i + 2].id) {
                    // 1 1 1
                    continue;
                } else {
                    return 0;
                }
            }
            return 1;
        } else {
            // �����н�
            Card suspect = null;
            for (int i = 0; i < c.length - 1; i++) {
                if (c[i].equals(c[i + 1])) {
                    suspect = c[i];
                }
                if (suspect == null) {
                    // ��ǰ��Ϊ��
                    continue;
                } else {
                    // ��ǰ���Դճ�һ�����ݹ��жϳ���֮���������Ƿ���Ժ�
                    Card[] res = new Card[c.length - 2];
                    int j = 0;  // j ָ��res
                    int k = 0;  // k ָ��c
                    while(j < res.length) {
                        if(c[k].equals(suspect)) {
                            k++;
                            continue;
                        }
                        res[j++] = c[k++];
                    }
                    if(isVictory(res)==1) {
                        // ��ǰȡ�Ľ�����ʹʣ�µ��ƺ�
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    public abstract Card move();

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(name + ": ");
        for (Card c : cards) {
            str.append(c.toString() + " ");
        }
        return str.toString();
    }

    public void add(Card c) {
        cards.add(c);
    }

//    public static void main(String[] args) {
//        Player test = new Wanjia("test");
//        test.add(new Card(0, 1));
//        test.add(new Card(0, 1));
//        test.add(new Card(0, 2));
//        test.add(new Card(0, 3));
//        test.add(new Card(0, 4));
//        test.add(new Card(0, 1));
//        test.add(new Card(0, 2));
//        test.add(new Card(0, 3));
//        test.add(new Card(0, 4));
//        test.add(new Card(1, 5));
//        test.add(new Card(1, 3));
//        test.add(new Card(1, 1));
//        test.add(new Card(2, 1));
//        boolean tag = test.reveive(new Card(2, 1));
//        // 11 123 444 555 999
//        System.out.println(tag);
//    }
}
