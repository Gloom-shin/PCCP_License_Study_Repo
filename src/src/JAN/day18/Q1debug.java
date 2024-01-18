package JAN.day18;

public class Q1debug {
    public static void main(String[] args) {
        Q1 q1 = new Q1();
        //28, 18, 26, 10, 8, [0, 0, 1, 1, 1, 1, 1]
        int temperature = 28;
        int t1 = 18;
        int t2 = 26;
        int a = 10;
        int b = 8;
        // [0, 0, 1, 1, 1, 1, 1]
        int[] onboard = {0, 0, 1, 1, 1, 1, 1};
        int answer = q1.solution(temperature, t1, t2, a, b, onboard);
        System.out.println(answer);

        //-10, -5, 5, 5, 1, [0, 0, 0, 0, 0, 1, 0]
        temperature = -10;
        t1 = -5;
        t2 = 5;
        a = 5;
        b = 1;
        onboard = new int[]{0, 0, 0, 0, 0, 1, 0};
        int answer2 = q1.solution(temperature, t1, t2, a, b, onboard);
        System.out.println(answer2);

        // 11, 8, 10, 10, 1, [0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1]
        temperature = 11;
        t1 = 8;
        t2 = 10;
        a = 10;
        b = 1;
        onboard = new int[]{0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1};
        int answer3 = q1.solution(temperature, t1, t2, a, b, onboard);
        System.out.println(answer3);

        //11, 8, 10, 10, 100, [0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1]
        temperature = 11;
        t1 = 8;
        t2 = 10;
        a = 10;
        b = 100;
        onboard = new int[]{0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1};
        //                  11 10 9  10 9  10 9  10 11 11 10 9
        int answer4 = q1.solution(temperature, t1, t2, a, b, onboard);
        System.out.println(answer4); // 50

    }

}
