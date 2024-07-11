package JUL.day11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon5972 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList[] nodes = new ArrayList[N+1];
        //인접 리스트 초기화
        for (int i = 0; i < N+1; i++) {
            nodes[i] = new ArrayList<Node>();
        }
        int[] minDist = new int[N+1];
        // 최대값으로 초기화
        for (int i = 2; i < N+1; i++) {
            minDist[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cow = Integer.parseInt(st.nextToken());
            Node anode = new Node(cow, b);
            Node bnode = new Node(cow, a);
            nodes[a].add(anode);
            nodes[b].add(bnode);
        }
        //다익스트라 알고리즘
        //1부터 이동
        Queue<Integer> q = new LinkedList<>();
        q.add(1);

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 0; i < nodes[cur].size(); i++) {
                Node next = (Node) nodes[cur].get(i);
                int nextNode = next.getNextNode();
                int cowCnt = next.getCowCnt();
                if (minDist[nextNode] > minDist[cur] + cowCnt) {
                    minDist[nextNode] = minDist[cur] + cowCnt;
                    q.add(nextNode);
                }
            }
        }
        System.out.println(minDist[N]);



    }

    static class Node{ ;
        private final int cowCnt;
        private final int nextNode;

        public Node(int cowCnt, int nextNode) {
            this.cowCnt = cowCnt;
            this.nextNode = nextNode;
        }

        public int getCowCnt() {
            return this.cowCnt;
        }

        public int getNextNode() {
            return this.nextNode;
        }
    }

}
