package FEB.day02;

import java.io.*;
import java.util.*;

//4 6
//.....D
//......
//.GN#..
//G.....
public class Q1 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N = 0;
    static int M = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        N = Integer.parseInt(nm[0]);
        M = Integer.parseInt(nm[1]);
        //공간 0, 벽 1
        int[] start = new int[2];
        int[] end = new int[2];
        int[][] map = new int[N][M];
        ArrayList<int[]> glist = new ArrayList<>();
        int ghostMin = 2000;
        for(int i = 0; i < N; i++){
            String[] strArr = br.readLine().split("");
            for(int j = 0; j < M; j++){
                if(strArr[j].equals(".")){
                    map[i][j] = 0;
                }
                if(strArr[j].equals("#")){
                    map[i][j] = 1;
                }
                if(strArr[j].equals("N")){
                    start[0] = i;
                    start[1] = j;
                }
                if(strArr[j].equals("D")){ // 출구
                    end[0] = i;
                    end[1] = j;
                }
                if(strArr[j].equals("G")){
                    glist.add(new int[]{i,j});
                }
            }
        }

        // 고스트 출구까지 최단 거리
        for(int i = 0; i< glist.size(); i++){
            int d = Math.abs(end[0] - glist.get(i)[0]) + Math.abs(end[1] - glist.get(i)[1]);
            ghostMin = Math.min(ghostMin, d);
        }
        //이제 bfs로 남우가 탈출할 수 있는지 보면된다.

        String answer = "No";
        if (bfs(map, new int[N][M], start, end, ghostMin)){
            answer="Yes";
        }
        System.out.print(answer);
    }
    private static boolean bfs(int[][] map, int[][] visited, int[] start, int[] end, int ghostMin){

        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);

        while(!queue.isEmpty()){
            int x = queue.peek()[0];
            int y = queue.poll()[1];
            // 4방향 이동
            for(int i=0; i< 4; i++){
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M)continue;
                if(visited[nextX][nextY] != 0 ) continue;
                if(map[nextX][nextY] == 1)continue;
                visited[nextX][nextY] = visited[x][y] + 1;
                if(visited[nextX][nextY] >= ghostMin) continue;
                if(nextX == end[0] && nextY == end[1]) {
                    return true;
                }
                queue.add(new int[]{nextX, nextY});
            }
        }
        return false;
    }
}
