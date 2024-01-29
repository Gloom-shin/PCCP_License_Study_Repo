package JAN.day26;


// bfs를 번갈아가며, 벽이라 간주하고 진행해야되지않나 싶다.
// 또한, 2개의 visited를 만들어서 번갈아가며 확인해주어야 한다.

import java.util.*;

public class Q1 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int redx = 0;
    static int redy = 0;
    static int bluex = 0;
    static int bluey = 0;
    static int min;
    public int solution(int[][] maze) {
        boolean[][] redVisited = new boolean[maze.length][maze[0].length];
        boolean[][] blueVisited = new boolean[maze.length][maze[0].length];
        min = Integer.MAX_VALUE;
        int[] redStart = {};
        int[] redEnd = {};
        int[] blueStart = {};
        int[] blueEnd = {};

        // 시작칸과 도착칸 찾기
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 1) {
                    redStart = new int[]{i, j};
                    redx = i;
                    redy = j;
                }
                if (maze[i][j] == 2) {
                    blueStart = new int[]{i, j};
                    bluex = i;
                    bluey = j;
                }
                if (maze[i][j] == 3)
                    redEnd = new int[]{i, j};
                if (maze[i][j] == 4)
                    blueEnd = new int[]{i, j};
            }
        }
        redVisited[redStart[0]][redStart[1]] = true;
        blueVisited[blueStart[0]][blueStart[1]] = true;
        redDfs(maze, redVisited, blueVisited, redStart, redEnd, blueStart, blueEnd, 0, 0);
        blueDfs(maze, redVisited, blueVisited, redStart, redEnd, blueStart, blueEnd, 0, 0);


        int answer = min;
        if(min == Integer.MAX_VALUE)
            answer = 0;
        return answer;
    }

    // 블루 수레 위치, 레드 수레 위치, 미로, 블루 방문, 레드 방문
    private void redDfs(int[][] maze, boolean[][] redVisited, boolean[][] blueVisited, int[] red, int[] redEnd, int[] blue, int[] blueEnd, int redMove, int blueMove) {

        if (redEnd[0] == red[0] && redEnd[1] == red[1]) { // 종점이면 끝
            if (blueEnd[0] == blue[0] && blueEnd[1] == blue[1]) {
                if(redMove ==0 || blueMove == 0) return;
                int max = Math.max(redMove, blueMove);
                min = Math.min(min, max);
                return;
            }
            blueDfs(maze, redVisited, blueVisited, red, redEnd, blue, blueEnd, redMove, blueMove);
        }
        for (int i = 0; i < 4; i++) { // 4방향
            int redNextX = red[0] + dx[i];
            int redNextY = red[1] + dy[i];

            // 영역밖 제외
            if (redNextX < 0 || redNextY < 0 || redNextX >= maze.length || redNextY >= maze[0].length) continue;
            if (redVisited[redNextX][redNextY]) continue; // 방문한 곳 제외
            if (redNextX == blue[0] && redNextY == blue[1]) continue; // 블루 있는 곳 제외
            if (maze[redNextX][redNextY] == 5) continue; // 벽인 곳 제외
            // 갈 수 있는 곳
            redVisited[redNextX][redNextY] = true; // 갈 수 있는 곳
            redMove++;
            // 다음 이동 가능
            blueDfs(maze, redVisited, blueVisited, new int[]{redNextX, redNextY}, redEnd, blue, blueEnd, redMove, blueMove);
            redMove--;
            redVisited[redNextX][redNextY] = false;

        }
        // 아무것도 없어도 끝

    }

    private void blueDfs(int[][] maze, boolean[][] redVisited, boolean[][] blueVisited, int[] red, int[] redEnd, int[] blue, int[] blueEnd, int redMove, int blueMove) {
        if (blueEnd[0] == blue[0] && blueEnd[1] == blue[1]) {
            if (redEnd[0] == red[0] && redEnd[1] == red[1]) {
                if(redMove ==0 || blueMove == 0) return;
                int max = Math.max(redMove, blueMove);
                min = Math.min(min, max);
                return;
            }
            redDfs(maze, redVisited, blueVisited, red, redEnd, blue, blueEnd, redMove, blueMove);
        }
        for (int i = 0; i < 4; i++) {
            int blueNextX = blue[0] + dx[i];
            int blueNextY = blue[1] + dy[i];

            // 영역밖 제외
            if (blueNextX < 0 || blueNextY < 0 || blueNextX >= maze.length || blueNextY >= maze[0].length) continue;
            if (blueVisited[blueNextX][blueNextY]) continue; // 방문한 곳 제외
            if (blueNextX == red[0] && blueNextY == red[1]) continue; // 레드 있는 곳 제외
            if (maze[blueNextX][blueNextY] == 5) continue; // 벽인 곳 제외
            // 갈 수 있는 곳
            blueVisited[blueNextX][blueNextY] = true; // 갈 수 있는 곳
            // 다음 이동 가능
            blueMove++;
            redDfs(maze, redVisited, blueVisited, red, redEnd, new int[]{blueNextX, blueNextY}, blueEnd, redMove, blueMove);
            blueMove--;
            blueVisited[blueNextX][blueNextY] = false;
        }

    }
}
