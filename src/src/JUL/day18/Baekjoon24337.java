package JUL.day18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Baekjoon24337 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        if (a + b > N + 1) {
            System.out.println(-1);
            return;
        }
        if (a == 1) {

        }
        int max = Math.max(a, b);

        Stack<Integer> gahui = new Stack<>();
        ArrayList<Integer> danbi = new ArrayList<>();


        for (int i = max - 1; i > 0; i--) {
            if (a > i) {
                gahui.push(i);
            }
            if (b > i) {
                danbi.add(i);
            }
        }
        int curLen = gahui.size() + danbi.size();
        while (N - 1 > curLen) {
            gahui.push(1);
            curLen++;
        }
        StringBuilder sb = new StringBuilder();
        if(a == 1) sb.append(max).append(" ");
        while (!gahui.isEmpty()) {
            int num = gahui.pop();
            sb.append(num).append(" ");
        }
        if(a != 1) sb.append(max).append(" ");
        for (int num : danbi) {
            sb.append(num).append(" ");
        }

        System.out.print(sb.toString().trim());
    }
}