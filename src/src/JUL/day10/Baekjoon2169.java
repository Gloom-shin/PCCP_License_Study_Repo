package JUL.day10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon2169 {
// 최적화 할수 있나 생각해봤는데, DFS돌리는 방법으로 완전탐색이 제일 좋은것 같다.
// 1000* 1000 = 10^6 이며, --> 완전탐색 가능
//그리고 각각의 숫자가 최대 100, 즉 최대값이 10^6*10^2 = 10^8  --> int범위로 처리가능
    static int[] dx = {0, 1, 0};
    static int[] dy = {1, 0, -1};
    static int max = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        max = 0;
        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;
        dfs(0, 0, n - 1, m - 1, map, visited, map[0][0]);

        System.out.print(max);
    }

    private static void dfs(int x, int y, int endX, int endY, int[][] map, boolean[][] visited, int value) {
        if (x == endX && y == endY) {
            // 가치가 최대 값인지 확인
            max = Math.max(max, value);
            return;
        }
        // 이동
        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx > endX || ny > endY) {
                continue; //범위밖
            }
            if (visited[nx][ny]) continue; // 이미 방문
            visited[nx][ny] = true;
            value += map[nx][ny];
            dfs(nx, ny, endX, endY, map, visited, value);
            visited[nx][ny] = false;
            value -= map[nx][ny];
        }

    }
}