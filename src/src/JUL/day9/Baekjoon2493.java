package JUL.day9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon2493 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] topArr = new int[N+1];
        int[] indexArr = new int[N + 1];
        topArr[1] = Integer.parseInt(st.nextToken());
        for(int i = 2; i<=N; i++){
            // 10^8이라 int로 가능
            int top = Integer.parseInt(st.nextToken());
            topArr[i] = top;
            int beforeIndex = i - 1;
            int beforeTop = topArr[beforeIndex];
            // 이전 top일 비교하여 해당값이 같거나 작으면 인덱스 넣기
            while(top > beforeTop){
                if(beforeIndex == 0) break; // 1이면 끝
                beforeIndex = indexArr[beforeIndex]; //앞썬 값으로 이동
                beforeTop = topArr[beforeIndex]; // 앞썬 탑 크기
            }
            indexArr[i] = beforeIndex; //인덱스 값 넣어주기
            // 단, 0인덱스 = 1 임으로
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<=N; i++){
            sb.append(indexArr[i]).append(" ");
        }
        System.out.print(sb.toString());
    }
}
