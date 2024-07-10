package JUL.day10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon14719 {

// 시작이 0보다 크면 물을 담는다.
// 그다음 높이가 크면 새로 물을 담는 칸
//            작으면 차감만큼 물을 담는다.
// 끝에서도 똑같이 시작한다. (단, 시작과 끝을 비교해 낮은곳부터 움직임)
// 끝에서 물을 담는 칸보다 높아지면, 저장하고 그동안 담은 물을 저장하고
// 다시 갱신 이렇게 시작과 끝이 만나면 종료

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] block = new int[W];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < W; i++) {
            block[i] = Integer.parseInt(st.nextToken());
        }
        // 시작과 끝이 0이 아닌 곳을 찾는다.
        int start = 0;
        int end = W - 1;

        while (block[start] == 0 && start < end) {
            start++;
        }
        while (block[end] == 0 && end > start) {
            end--;
        }


        int rainTotal = 0;
        int startMax = 0;
        int endMax = 0;
        int save = 0;
        while (start <= end) {
            if (block[start] < block[end]) {
                // 더큰 높이
                if (startMax < block[start]) {
                    // 저장해놓은 빗물이 있으면 저장
                    if (save > 0) {
                        rainTotal += save;
                        save= 0;
                    }
                    startMax = block[start];
                }
                // 낮은 높이
                else {
                    save += (startMax - block[start]);
                }
                start++;
            } else {
                if (endMax < block[end]) {
                    if (save > 0) {
                        rainTotal += save;
                        save = 0;
                    }
                    endMax = block[end];
                } else {
                    save += (endMax - block[end]);
                }
                end--;
            }
        }
//        if(save > 0){
//            rainTotal += save;
//        }
        System.out.print(rainTotal);

    }
}