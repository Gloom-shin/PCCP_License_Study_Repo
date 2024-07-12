package JUL.day11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Elise4 {
    static List<List<Integer>> tree;
    static int[] scores;
    static int[][] dp;
    static boolean[][] calculated;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 말이 하나라는 점! 이게 없어서 헤깔렷다.
        // 즉,시작지점에서 부터 점수 계산해야됨.
        int N = Integer.parseInt(br.readLine());

        tree = new ArrayList<>();
        dp = new int[N + 1][2];
        calculated = new boolean[N + 1][2];
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int max = Math.max(n1, n2);
            int min = Math.min(n1, n2);
            tree.get(min).add(max);
        }

        for (int i = 1; i <= N; i++) {
            if (solve(i, 0) >= solve(i, 1)) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }

    }
    static int solve(int node, int turn) {
        if (calculated[node][turn]) {
            return dp[node][turn];
        }
        calculated[node][turn] = true;

        int bestScore;
        if (turn == 0) { // First player's turn
            bestScore = Integer.MIN_VALUE;
            for (int child : tree.get(node)) {
                bestScore = Math.max(bestScore, node + solve(child, 1));
            }
            if (tree.get(node).isEmpty()) {
                bestScore = node;
            }
        } else { // Second player's turn
            bestScore = Integer.MAX_VALUE;
            for (int child : tree.get(node)) {
                bestScore = Math.min(bestScore, solve(child, 0));
            }
            if (tree.get(node).isEmpty()) {
                bestScore = 0;
            }
        }
        System.out.println("node = " + node + ", turn = " + turn);
        dp[node][turn] = bestScore;
        System.out.println("bestScore = " + bestScore);
        return bestScore;
    }


}
