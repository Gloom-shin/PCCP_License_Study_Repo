package JUL.day10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon2169 {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        int[][] dp = new int[n][m];
        int[][] leftDp  = new int[n][m];
        int[][] rightDp  = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if (i == 0) {
                    dp[i][j] = temp + (j == 0 ? 0 : dp[i][j - 1]); // 위쪽 채우기
                    leftDp[i][j] = dp[i][j];
                    rightDp[i][j] = dp[i][j];
                }
                // 위쪽만 채워넣으면 된다.
                map[i][j] = temp;
            }
        }

        // dp를 한줄씩 내려가면서 3방향을 비교해 가장 높은 값을 비교하자
        for(int i =1 ; i < n ; i++){
            //위로 못움직이니 왼쪽과 오른쪽을 비교해서 쭈욱 만들자
            //왼쪽
            leftDp[i][0] = dp[i-1][0] + map[i][0]; //왼쪽 끝 추가
            for(int j = 1; j < m; j++){
                leftDp[i][j] = Math.max(dp[i-1][j], leftDp[i][j-1]) + map[i][j];
            }

            // 오른쪽
            rightDp[i][m-1] = dp[i-1][m-1] + map[i][m-1]; //오른쪽 끝 추가
            for(int j = m-2; j >= 0; j--){
                rightDp[i][j] = Math.max(dp[i-1][j], rightDp[i][j+1]) + map[i][j];
            }

            //이제 다시 순환하면서, 오른쪽dp와 왼쪽dp중에 최대값을 넣어주면된다.
            for(int j = 0; j < m; j++){
                dp[i][j] = Math.max(leftDp[i][j], rightDp[i][j]);
            }

        }
        System.out.print(dp[n-1][m-1]);
    }
}