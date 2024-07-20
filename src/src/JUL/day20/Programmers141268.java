package JUL.day20;

public class Programmers141268 {
    public static void main(String[] args) {
        Programmers141268 sol = new Programmers141268();
//        int solution = sol.solution(0, 0, 0, 23, 59, 59);

        int solution13 = sol.solution(11, 59, 30, 12, 0, 0);
        System.out.println("solution13 = " + solution13);
    }

    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        // 1초 움직일때
        // 초침 6도 움직임
        // 분침 1/10 각도 움직임 60분  3600초 360도
        // 시침 1/120  각도 움직임 12시간 = 720분 = 43200초에 360도

        // 끝 시간 - 시작 시간 = 걸리는 시간 = 초로 환산 가능
        // 360 * 120 = 43200
        int circle = 43200;
        //시작 시간의 시 분초 침의 각도 계산
        int start = transSecond(h1, m1, s1);
        int end = transSecond(h2, m2, s2);
        int term = end - start;

        //시작 각도에서 term동안 초침이 시침,분침을 지나간 시간
        int alarm = 0;
        boolean hCheck = false;
        boolean mCheck = false;
        int s = start * 120 * 6 % circle;
        int m = start * 12 % circle;
        int h = start % circle;

        if (h == 0 && m == 0 && s == 0) alarm--;
        if (h < s) hCheck = true;
        if (m < s) mCheck = true;


        for (int i = 1; i <= term; i++) {
            h += 1;
            m += 12;
            s += 720;

            if (h < 3600) {
                if (s == circle) s = 0;
            }
            if (s > circle) s %= circle;
            if (h == circle) {
                h = 0;
                alarm--;
            }
            if (m == circle) {
                m %= circle;
            }

            if (h >= s) hCheck = false; // 시침 전으로 돌아왔을때,
            if (m >= s) mCheck = false; // 분침 전으로 돌아왔을때.

            if (!hCheck) {
                if (h < s) {// 시침 지날때
                    alarm++;
                    hCheck = true;
                }
            }
            if (!mCheck) {
                if (m < s) { // 분침 지날때
                    alarm++;
                    mCheck = true;
                }
            }

        }

        return alarm;
    }

    private int transSecond(int h, int m, int s) {
        int total = s;
        total += m * 60;
        total += h * 3600;
        return total;
    }
}
