package JAN.day18;

class Q1 {
    //dp문제?
    // 하나하나 보자,
    // 매번 선택지는 3가지이다.
    //  1. 희망온도로 맞춘다.
    //  2. 온도를 유지한다.
    //  3. 에어컨을 끈다.
    // 그리고 선택지에 따라 변화는 다음과 같다.
    // 1.  1도 변화한다.  a 만큼 소비
    // 2.  변화 없다. b반큼 소비
    // 3. 처음온도 방향으로 1도 돌아간다. 소비 x
    // 최적의 방법은 소비x 하는 방법을 선택하는 것!

    // 일단 승객이 없을때는, 승객이 있을 때를 대비하여, 실내온도를 맞추면 된다.
    // 1이 나올때 까지 카운트
    // 1일 경우 최적의 연산이 필요!
    // 처음엔 무조건 희망온도에 맞추기위한 계산을 한다.
    // 그리고 1의 갯수만큼 유지, 0의 갯수 /2 만큼 변화를 대비한다.
    // 단 0의 갯수가 3개 일땐, 변화 +1 유지-1 이다.
    // 1의 변화 횟수 한번에 , 유지 횟수 2번을 차감할 수 있다.

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int consum = 0;  // 소비 전력
        // 희망온도와 실외온도 차이 계산
        // int maxDiff = Math.max(Math.abs(temperature- t1) ,Math.abs(temperature- t2));
        int minDiff = Math.min(Math.abs(temperature - t1), Math.abs(temperature - t2));
        // 초반 1이 나올 때까지
        // 뒤로 0인 부분 제거
        int len = onboard.length -1;
        while (onboard[len] != 1) {
            len--;
        }

        int index = 0;
        while (onboard[index] != 1) {
            index++;
        }
        consum = minDiff * a;

        // 이제 1의 갯수랑 0의 갯수 카운트

        // 만약 a가 2*b보다 크면, 유지비용으로 0,1 구분없이 유지하는 편이 더 이득이다.

        // 하지만, a가 2*b보다 작거나 같으면, 유지비용으로 0,1 구분해서 변화를 더 하는 편이 더 이득이다.

        //그외의 경우 아래와 같이 연산을 진행해도 된다.
        int ONE = -1;
        int ZERO = 0;
        int change = 0;
        int stay = 0;
        if (a <= 2 * b) { // change 중심
            while (index <= len) {
                if (onboard[index] == 0) {
                    if(ONE != 0) {
                        if(ONE%2 == 1)ONE--; // 하나 없어도 됨.
                        stay += ONE;
                        ONE = 0;
                    }
                    ZERO++;
                } else {
                    if(ZERO != 0) {
                        stay += ZERO;
                        ZERO = 0;
                    }
                    ONE++;
                }
                index++;
            }
            if(ONE != 0) {
                if(ONE%2 == 1)ONE--; // 하나 없어도 됨.
                stay += ONE;
            }
        } else {
            //ONE 갯수세기
            stay = len - index;
            while (index <= len) {
                if (onboard[index] == 1) {
                    ONE++;
                }
                index++;
            }
        }
        if (ONE == 0) {
            stay = 0;
        }
        int min = Integer.MAX_VALUE;

        while (stay >= 0) {
            min = Math.min(change * a + stay * b, min);
            change++;
            stay -= 2;
            if (stay == -1) stay = 0;
        }
        if (min == Integer.MAX_VALUE) min = 0;
        int answer = min + consum;
        return answer;
    }
}