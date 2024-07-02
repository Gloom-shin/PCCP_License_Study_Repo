package JUL.day2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Baekjoon12865{
    static class Jewel implements Comparable<Jewel>{
        int weight;
        int value;

        public Jewel(int w, int v){
            this.weight = w;
            this.value = v;
        }
        @Override
        public int compareTo(Jewel other){
            if(this.weight == other.weight){
                return other.value - this.value;  // 내림차순
            }
            return this.weight - other.weight;
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st =new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        // 보석의 갯수 100개
        Jewel[] jList = new Jewel[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jList[i] = new Jewel(w,v);
        }
        Arrays.sort(jList);

        int[][] ns = new int[N+1][W+1];

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <=W; j++){
                for(int k = 0; k < N; k++){ // 보석 순회
                    int weight = jList[k].weight;
                    int value = jList[k].value;
                    if( j < weight){
                        ns[i][j] = ns[i-1][j];
                    }
                    else{
                        ns[i][j] = Math.max(ns[i-1][j - weight] + value, ns[i-1][j]);
                    }
                }
            }
        }
        System.out.println(ns[N][W]);

    }
}
