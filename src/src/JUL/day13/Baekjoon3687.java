package JUL.day13;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baekjoon3687 {
    // 문자열 형태의 규칙이다.
// 최대값은 짝수면 1, 홀수면 7로 커버 가능  -> 최대 50자리
// 최솟값은 몇가지 규칙이 있다.
// 2개 -> 1, 3개 -> 7 , 4개 -> 4, 5개 ->2, 6개 -> 6또는 0 , 7개 -> 8
// 7개가 넘어가면 한자리가 추가된다 보면 된다.
// 7의배수 +1, +2 일 경우 1로 시작가능하고  +1 : 10888.. , +2 : 18888..
// 7의 배수  +3, +4, +5 2로 시작 가능하다. +3 : 22888... +4 : 208888.. , +5 : ;288888...
// +6은 6으로 시작하며,  68888...
// 7의 배수는 8로 이루어진다.  8888....
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int cnt = Integer.parseInt(br.readLine());
            StringBuilder max = new StringBuilder();
            StringBuilder min = new StringBuilder();
            int maxIndex = cnt % 2;
            if (maxIndex == 1) max.append("7");
            if (maxIndex == 0) max.append("1");
            maxIndex += 2;

            while (cnt != maxIndex) {
                max.append("1");
                maxIndex += 2;
            }
            long[] dp = new long[101];
            dp[1] = 10;
            dp[2] = 1;
            dp[3] = 7;
            dp[4] = 4;
            dp[5] = 2;
            dp[6] = 6;
            dp[7] = 8;
            for (int j = 8; j <= 100; j++) {
                dp[j] = Long.MAX_VALUE;
                for (int k = 2; k <= 7; k++) {
                    long add = dp[k];
                    if(k == 6) add = 0;
                    dp[j] = Math.min(dp[j], dp[j - k] * 10 + add);
                }
            }
            min.append(dp[cnt]);
            System.out.println(min.append(" ").append(max));
        }

    }
}
