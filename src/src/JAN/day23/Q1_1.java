package JAN.day23;

// bfs를 번갈아가며, 벽이라 간주하고 진행해야되지않나 싶다.
// 또한, 2개의 visited를 만들어서 번갈아가며 확인해주어야 한다.

import java.util.*;

class Q1_1 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public int solution(int[][] maze) {
        int[][] redVisited = new int[maze.length][maze[0].length];
        int[][] blueVisited = new int[maze.length][maze[0].length];


        Queue<int[]> redQueue = new LinkedList<>();
        Queue<int[]> blueQueue = new LinkedList<>();
        int redx = 0;
        int redy = 0;
        int bluex = 0;
        int bluey = 0;
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
                    bluex = j;
                }
                if (maze[i][j] == 3)
                    redEnd = new int[]{i, j};
                if (maze[i][j] == 4)
                    blueEnd = new int[]{i, j};
            }
        }

        redQueue.add(redStart);
        blueQueue.add(blueStart);

        while (!((redQueue.isEmpty()) || blueQueue.isEmpty())) {
            if (!redQueue.isEmpty()) {
                redx = redQueue.peek()[0];
                redy = redQueue.poll()[1];

                for (int i = 0; i < 4; i++) {
                    int redNextX = redx + dx[i];
                    int redNextY = redy + dy[i];

                    //영역 밖 제외
                    if (redNextX < 0 || redNextY < 0 || redNextX >= maze.length || redNextY >= maze[0].length) continue;
                    // 방문한곳 제외
                    if (redVisited[redNextX][redNextY] > 0) continue;
                    // 블루 있는 곳 제외
                    if (redNextX == bluex && redNextY == bluey) continue;
                    // 벽인 곳 제외
                    if (maze[redNextX][redNextY] == 5) continue;
                    // 갈 수 있는 곳
                    redVisited[redNextX][redNextY] = redVisited[redx][redy] + 1;
                    // 도착지
                    if (maze[redNextX][redNextY] == 3) {
                        redQueue = new LinkedList<>();
                        continue;
                    }
                    // 다음 이동 가능
                    redQueue.add(new int[]{redNextX, redNextY});

                }

            }
            if (!blueQueue.isEmpty()) {

                bluex = blueQueue.peek()[0];
                bluey = blueQueue.poll()[1];

                for (int i = 0; i < 4; i++) {
                    int blueNextX = bluex + dx[i];
                    int blueNextY = bluey + dy[i];

                    //영역 밖 제외
                    if (blueNextX < 0 || blueNextY < 0 || blueNextX >= maze.length || blueNextY >= maze[0].length)
                        continue;
                    // 방문한곳 제외
                    if (blueVisited[blueNextX][blueNextY] > 0) continue;
                    // 블루 있는 곳 제외
                    if (blueNextX == redx && blueNextY == redy) continue;
                    // 벽인 곳 제외
                    if (maze[blueNextX][blueNextY] == 5) continue;
                    // 갈 수 있는 곳
                    blueVisited[blueNextX][blueNextY] = blueVisited[bluex][bluey] + 1;
                    // 도착지
                    if (maze[blueNextX][blueNextY] == 4) {
                        blueQueue = new LinkedList<>();
                        continue;
                    }
                    // 다음 이동 가능
                    blueQueue.add(new int[]{blueNextX, blueNextY});
                }
            }
        }
        int answer = 0;
        answer = Math.max(redVisited[redEnd[0]][redEnd[1]], blueVisited[blueEnd[0]][blueEnd[1]]);
        if (redVisited[redEnd[0]][redEnd[1]] == 0 || blueVisited[blueEnd[0]][blueEnd[1]] == 0) {
            answer = 0;
        }
        return answer;
    }
}
