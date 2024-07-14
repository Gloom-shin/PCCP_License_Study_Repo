package JUL.day14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Baejoon24042 {
    // 최단 거리를 기억하는 min[]
// 각 노드에서 이동할 수 있는 값 넣기(중복될 수 있으니, 중복 가능)
//    만약 현재 값보다 낮은 이동값이라면 + 주기 크기만큼 업 시켜주기
// 이런식으로 1에서 부터 모든 노드로 가는 최적의 방법 구하기
// -> 모두 순환했다면, 종료
    public static class Node {
        int next;
        int time;

        Node(int next, int time) {
            this.next = next;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList[] nodesInfo = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            nodesInfo[i] = new ArrayList<Node>();
        }

        for (int t = 1; t <= M; t++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            nodesInfo[a].add(new Node(b, t));
            nodesInfo[b].add(new Node(a, t));
        }
        long[] minTime = new long[N + 1];
        minTime[1] = 0; // 1번 노드는 0
        for (int i = 2; i <= N; i++) {
            minTime[i] = Long.MAX_VALUE;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1); // 1번 노드부터 시작 N까지 가는 최단거리를 구해야함

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = 0; i < nodesInfo[cur].size(); i++) {
                Node next = (Node) nodesInfo[cur].get(i);
                int nextNode = next.next;
                long nextTime = next.time;
                // 현재 시간이 다음 시간보다 크다면 + 주기만큼 더해주기
                if(minTime[cur] > nextTime) {
                    long cnt = (minTime[cur]- nextTime)/M + 1;
                    nextTime += (M*cnt);
                }
                if (minTime[nextNode] >  nextTime) {
                    minTime[nextNode] = nextTime;
                    queue.add(nextNode);
                }
            }
        }
        System.out.println(minTime[N]);
    }
}

