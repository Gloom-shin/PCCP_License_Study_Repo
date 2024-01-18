package JAN.day18;

class Q1 {
    //dp문제?

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        // 조건 온도와 시간에 따른 전력량  -> 2차배열로 가능
        // dp[시간][온도] = 전력량
        // dp[0][0] = 0;
        // onboard[0] 0 일때는 관계없지만, 1일때는    t1 ~ t2 범위 안에 들어야한다.
        // -10~40 까지니 +10 해준다.
        temperature += 10;
        t1 += 10;
        t2 += 10;
        int time = onboard.length;
        // dp 안에 max값으로 초기화
        int[][] dp = new int[time][51];
        for (int i = 0; i < time; i++) {
            for (int j = 0; j < 51; j++) {
                dp[i][j] = time * (a+b);
            }
        }


        // 갱신해주는 법  j-1 온도 변화
        // dp[i-1][j]  => dp[i][j-1] = dp[i-1][j] + a;
        // dp[i-1][j]  => dp[i][j+1] = dp[i-1][j] + a;
        // 유지
        // dp[i-1][j]  => dp[i][j] = dp[i-1][j] + b;
        dp[0][temperature] = 0;
        int start = temperature;
        int end = temperature;
        int minTemp = temperature-1;
        int maxTemp = temperature+1;
        int up = 0;
        int down = 0;
        // 실외온도가 높으면, -1 이 +a , 실외온도가 낮으면 +1이 +a
        if(temperature > t2){
            down = a;
        }
        if(temperature < t1){
            up = a;
        }

        for (int t = 1; t < time; t++) {
            if (onboard[t] == 1) {
              start = t1;
              end = t2;
            }
            else {
                start = minTemp;
                if(start < 0) start = 0;
                end = maxTemp;
                if(end > 50) end = 50;
            }
            // 온도에 맞춰 값 변경
            for (int i = start; i <= end; i++) {
                // 1도 변경

                if(i+1 <= 50)
                    dp[t][i] = Math.min(dp[t][i], dp[t-1][i+1] +down);
                if(i-1 >= 0)
                    dp[t][i] = Math.min(dp[t][i], dp[t-1][i-1] + up);
                //실외온도랑 같으면, 0으로 변경
                if(i == temperature)
                    dp[t][i] = Math.min(dp[t][i], dp[t-1][i]);
                // 그래도 유지
                dp[t][i] = Math.min(dp[t][i], dp[t-1][i] + b);

                // maxTemp = Math.max(maxTemp, i);
            }
            minTemp = start - 1;
            maxTemp = end + 1;

        }
        int answer= Integer.MAX_VALUE;
        for (int i = 1; i <51; i++) {
            answer = Math.min(answer, dp[time-1][i]);
        }


        return answer;
    }
}