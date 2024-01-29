package JAN.day29;

public class Q1 {
    // 자연수 n =  집합안에 들어가는 수 (10^4이하)
    // s = > 합쳐서 만들어야되는 수 (10^8)
    // 수 중 원소끼리 곱한 수가 큰 수 -> int의 범위를 넘어감.
    // 수중 곱한 값이 커지려면, 평균값과 근사치일때, 가장 커질 수 있다.
    // 3, 8 => 2,3,3
    class Solution {
        public int[] solution(int n, int s) {

            int avg = s / n;
            int remain = s % n;

            if (avg == 0) {
                return new int[]{-1};
            }
            int[] answer = new int[n];


            for (int i = 0; i < n; i++) {
                answer[i] = avg;
                if (i >= n - remain) {
                    answer[i]++;
                }
            }
            return answer;
        }
    }
}
