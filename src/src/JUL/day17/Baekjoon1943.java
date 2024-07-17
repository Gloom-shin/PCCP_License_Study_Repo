package JUL.day17;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Baekjoon1943 {
    static class Coin implements Comparable<Coin> {
        private int value;
        private int cnt;

        Coin(int v, int c) {
            this.value = v;
            this.cnt = c;
        }

        public void useCoin() {
            this.cnt--;
        }

        public int getCnt() {
            return this.cnt;
        }

        public int getValue() {
            return this.value;
        }

        @Override
        public int compareTo(Coin other) { // value로 내림 정렬
            return other.getValue() - this.getValue();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int read = 0;
        int type = 0;
        StringTokenizer st;
        ArrayList<Coin> list;
        while (read < 3) {
            read++;
            type = Integer.parseInt(br.readLine());
            list = new ArrayList<>();
            for (int i = 0; i < type; i++) {
                st = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                list.add(new Coin(v, c));
            }
            Collections.sort(list);
            int yunhwa = 0;
            int junhee = 0;
            for (Coin c : list) {
                while (c.getCnt() > 0) {
                    c.useCoin();
                    if (yunhwa <= junhee) {
                        yunhwa += c.getValue();
                    } else {
                        junhee += c.getValue();
                    }
                }
            }
            if (yunhwa == junhee) System.out.println(1);
            else System.out.println(0);
        }

    }

}