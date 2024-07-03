package JUL.day3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Baekjoon12919 {

//S -> T로 가려면 완전 탐색 해야되는데 최대 2^49가 나와 힘듬/
// 그럼 T -> S로 거꾸로 가자
// T 에서 S길이만큼 제거해보고 T==S 같은 지로 구분해보자
// 먼저 T의 맨뒤가 A여야 A를 제거 가능  str.substring(0, str.length() - 1);
//     T의 맨앞이 B여야 B를 제거 가능  str.substring(1);
// 뒤집어지는데 이때, A가 맨뒤면 더이상 소거가 불가능하다.

// 만약 T.length == S.length+1 일때는 우선순위 상관없으므로 위 과정을 S의 길이 + 1만큼만 반복
    //반례
    //AB
    //BAABAAAAAB
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        int sLen = S.length();
        int tLen = T.length();

        boolean index = true;
        int start = 0;
        int end = 0;
        int result = 0;
        while (sLen + 1 < tLen) {
            start = 0;
            end = T.length() - 1;
            if (!index) { // 역순일 경우
                start = end;
                end = 0;
            }

            if (T.charAt(end) == 'A') {
                if (index) {
                    T = T.substring(0, end);
                } else {
                    T = T.substring(1);
                }
                tLen--;
                continue;
            }
            if (T.charAt(start) == 'B') {
                if (index) {
                    T = T.substring(1);
                } else {
                    T = T.substring(0, start);
                }
                tLen--;
                index = !index; // 역순으로 변경
                continue;
            }
            //더이상 줄일수가 없음
            break;
        }
        // A 를 제거해보기

        if (tLen == sLen + 1) {


            start = 0;
            end = T.length() - 1;

            if (!index) {
                start = end;
                end = 0;
            }
            if (T.charAt(end) == 'A') {
                if (index) {
                    if (T.substring(0, end).equals(S)) {
                        result = 1;
                    }
                } else {
                    //여기도 역순으로
                    StringBuilder sb = new StringBuilder(T.substring(1));
                    String str = sb.reverse().toString();
                    if (str.equals(S)) {
                        result = 1;
                    }
                }

            }
            if (T.charAt(start) == 'B') {
                if (index) {
                    // 만약 B를 지우면 역순으로
                    StringBuilder sb = new StringBuilder(T.substring(1));
                    String str = sb.reverse().toString();
                    if (str.equals(S)) {
                        result = 1;
                    }
                } else {
                    // 이땐 원래대로
                    if (T.substring(0, start).equals(S)) {
                        result = 1;
                    }
                }

            }
        }
        System.out.print(result);
    }
}


