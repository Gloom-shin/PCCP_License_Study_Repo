package JUL.day7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Baekjoon20055 {

//start, end 인덱스를 만들어 컨테이너 밸트가 어떻게 움직이는 지 관리

// 로봇의 경우 올린 지점에 매번 올리고, 내린지점에 가면 내리므로, 별도의 []로 관리
// 이걸 순회하면서, 내구도가 0이 되면 카운트
// K 만큼 도달하면 현재 반복지점을 리턴한다. 처음이 1단계

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] belt = new int[2 * N];
        // 밸트위에 로봇은 최대 200개까지 올라갈수있으니, Queue로 관리 -> 인덱스를 넣어 관리하자
        Queue<Integer> robotQueue = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }
        int start = 0;
        int end = 2 * N;
        int step = 0;
        boolean[] visited = new boolean[2 * N];
        // 단계 진행
        while (K > 0) {
            step++;
            // 1번
            start = (start - 1 + end) % end;

            //2번 로봇이동
            // 로봇의 위치가 2N - 1까지 가면 내린다.
            int robotCnt = robotQueue.size();
            for (int i = 0; i < robotCnt; i++) {
                int index = robotQueue.poll();
                visited[index] = false;
                index++; // 밸트이동 로봇에도 적용
                if (index == N - 1) continue; // 내리는 위치면 내림
                // 이동할곳 탐색(밸트 내구도와 로봇유무파악필요)
                int beltIndex = (index + start + 1) % end;
                // 가고자 하는 곳이 내구도가 있고, 로봇이 없다면?
                if (belt[beltIndex] > 0 && !visited[index + 1]) {
                    //이동하고 내구도 차감
                    belt[beltIndex]--;
                    if (belt[beltIndex] == 0) K--;
                    index++;
                }
                if (index == N - 1) continue; // 내림
                visited[index] = true;
                robotQueue.add(index);
            }
            //3번 처음위치에 로봇올리기
            if (belt[start] > 0) {
                robotQueue.add(0);
                belt[start]--;
                visited[0] = true;
                if (belt[start] == 0) K--;
            }
            //4번은 while로

        }
        System.out.print(step);
    }
}
