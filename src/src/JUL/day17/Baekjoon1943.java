package JUL.day17;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Baekjoon1943 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int N = scanner.nextInt();
            int[] coinValue = new int[N];
            int[] coinCnt = new int[N];
            int totalValue = 0;

            for (int i = 0; i < N; i++) {
                coinValue[i] = scanner.nextInt();
                coinCnt[i] = scanner.nextInt();
                totalValue += (coinValue[i] * coinCnt[i]);
            }

            if (totalValue % 2 != 0) {
                System.out.println(0);
                continue;
            }

            int halfValue = totalValue / 2;
            boolean[] dp = new boolean[halfValue + 1];
            dp[0] = true;

            for (int i = 0; i < N; i++) {
                int value = coinValue[i];
                int cnt = coinCnt[i];

                for (int j = halfValue; j >= 0; j--) {
                    if (dp[j]) {
                        for (int k = 1; k <= cnt && j + k * value <= halfValue; k++) {
                            dp[j + k * value] = true;
                        }
                    }
                }
            }

            System.out.println(dp[halfValue] ? 1 : 0);
        }

        scanner.close();
    }
}
