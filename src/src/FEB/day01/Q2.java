package FEB.day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 입력값 예시1
 **/
//4 2
//20 26 185 80
//100 20 25 80
//20 20 88 99
//15 32 44 50
//1 2
//2 3

//4 1
//20 26 185 80
//100 40 25 80
//100 20 88 99
//15 32 44 50
//1 2



public class Q2 {
    // dfs 3번 번갈아 가면서 돌기(동적 1~3) , 서로 겹칠 수 있기때문에 순서는 상관 x
    // visited공유해야함.
    // 다 더한 값의 max값 출력 필요
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int totalFriend;
    static int MAX;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");
        int N = Integer.parseInt(nm[0]);
        int M = Integer.parseInt(nm[1]);
        totalFriend = M;
        MAX = 0;
        int[][] map = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        int[] curX = new int[M];
        int[] curY = new int[M];

        for (int i = 0; i < N; i++) {
            String[] arr = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(arr[j]);
            }
        }
        int sum = 0;
        for (int i = 0; i < M; i++) {
            String[] friendStr = br.readLine().split(" ");
            curX[i] = Integer.parseInt(friendStr[0]) - 1;
            curY[i] = Integer.parseInt(friendStr[1]) - 1;
            visited[curX[i]][curY[i]] = true;
            sum += map[curX[i]][curY[i]];
        }



        dfs(map, visited, sum, curX, curY, new int[M], 0);

        System.out.println(MAX);

    }


    public static void dfs(int[][] map, boolean[][] visited, int sum, int[] curX, int[] curY, int[] move, int curFriend) {

        if (curFriend == totalFriend) {
            if(move[curFriend-1] == 3){
                MAX = Math.max(sum, MAX);
                return;
            }
            curFriend = 0;
        }
        // 4방향 움직이기
        int x = curX[curFriend];
        int y = curY[curFriend];
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            // map 바깥이면 제외
            if (nextX < 0 || nextY < 0 || nextX >= map.length || nextY >= map[0].length) continue;
            // 그외는 움직일 수 있음
            move[curFriend]++;
            // 방문했던 곳이 아니라면 ++
            if (!visited[nextX][nextY]) {
                sum += map[nextX][nextY];
                visited[nextX][nextY] = true;
                curX[curFriend] = nextX;
                curY[curFriend] = nextY;
                // 움직일 수 있으면 다음 친구 움직이기
                dfs(map, visited, sum, curX, curY, move, curFriend + 1);
                curX[curFriend] = x;
                curY[curFriend] = y;
                visited[nextX][nextY] = false;
                sum -= map[nextX][nextY];
            }
            else {
                // 움직일 수 있으면 다음 친구 움직이기
                curX[curFriend] = nextX;
                curY[curFriend] = nextY;
                dfs(map, visited, sum, curX, curY, move, curFriend + 1);
                curX[curFriend] = x;
                curY[curFriend] = y;

            }
            move[curFriend]--;

        }
    }
}
