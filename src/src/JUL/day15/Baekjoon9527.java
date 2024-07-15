package JUL.day15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon9527 {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        long A = scanner.nextLong();
        long B = scanner.nextLong();
        scanner.close();

        long result = countBitsUpTo(B) - countBitsUpTo(A - 1);
        System.out.println(result);
    }

    // x까지의 모든 숫자들의 1의 개수를 세는 함수
    private static long countBitsUpTo(long x) {
        if (x < 0) return 0;

        long count = 0;
        long powerOf2 = 1;

        while (powerOf2 <= x) {
            long totalPairs = (x + 1) / (powerOf2 * 2);
            long remainder = (x + 1) % (powerOf2 * 2);

            count += totalPairs * powerOf2;
            if (remainder > powerOf2) {
                count += remainder - powerOf2;
            }

            powerOf2 *= 2;
        }

        return count;
    }
}