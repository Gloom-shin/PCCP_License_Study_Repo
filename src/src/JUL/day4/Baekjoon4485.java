package JUL.day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon4485 {

// 상하좌우로 다시 갈 수 있다.
// 최소값을 유지하면된다. BFS

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = 1;
        int index = 0;
        while ((N = Integer.parseInt(br.readLine())) != 0) {
            index++;
            int[][] map = new int[N][N];
            int max = 0;
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int[][] visited = initVisited(N);
            bfs(N, visited, map);
            System.out.println("Problem " + index + ": " + visited[N - 1][N - 1]);

        }

    }
    private static int[][] initVisited(int n) {
        int[][] visited = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = Integer.MAX_VALUE;
            }
        }
        return visited;
    }
    // 무조건 출발은 0,0 끝은 n-1, n-1
    public static void bfs(int n, int[][] visited, int[][] map) {
        Queue<Integer> queX = new LinkedList<>();
        Queue<Integer> queY = new LinkedList<>();
        queX.add(0);
        queY.add(0);
        visited[0][0] = map[0][0];
        while (!queX.isEmpty()) {
            int x = queX.poll();
            int y = queY.poll();

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                    continue;
                }
                //다음 목적지가, 최소금액이 된다면 갱신
                if (visited[nx][ny] > visited[x][y] + map[nx][ny]) {
                    visited[nx][ny] = visited[x][y] + map[nx][ny];
                    queX.add(nx);
                    queY.add(ny);
                }
            }
        }


    }
}