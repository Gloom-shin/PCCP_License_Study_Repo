package FEB.day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * 입력예시1
 * 6
 * 2 4 6 9 12 18
 */
public class Q1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();
        String[] arr = s.split(" ");

        HashMap<Integer, Integer> map = new HashMap<>();

        // 배열 돌기
        for (int i = 0; i < n; i++) {
            int Num =  Integer.parseInt(arr[i]);
            saveDivisor(Num, map);
        }
        //HashMap value중 가장 큰 값 추출
        int max = 0;
        for (int key : map.keySet()) {
            max = Math.max(max, map.get(key));
        }
    }

    // 약수 저장
    private static void saveDivisor(int num, HashMap<Integer, Integer> map){
        for (int i = 2; i <= num; i++) {
            if(num % i == 0){
                map.put(i, map.getOrDefault(i, 0)+1);
            }
        }
    }

}
