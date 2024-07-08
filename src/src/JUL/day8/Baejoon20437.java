package JUL.day8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Baejoon20437 {
//hashmap으로 해당 알파벳의 인덱스를 배열로 저장
// 배열의 길이가 k보다 같거나 크면 순회를 진행
// 문자열의 길이는 (index + k) - (index) + 1 = 문자열의 길이
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i=0;i<N; i++){
            String str =br.readLine();
            int K = Integer.parseInt(br.readLine());
            stringGame(str, K);
        }
    }
    private static void stringGame(String str, int K){
        HashMap<Character, List<Integer>>  map = new HashMap<>();

        for(int i=0; i< str.length(); i++){
            char c = str.charAt(i);
            if(!map.containsKey(c)){
                ArrayList<Integer> list= new ArrayList<>();
                list.add(i); // 인덱스 저장
                map.put(c,list);
            }else{
                map.get(c).add(i); // 인덱스 저장
            }
        }
        int minLen = Integer.MAX_VALUE;
        int maxLen = -1;

        for(char c : map.keySet()){
            List<Integer> l = map.get(c);
            if(l.size() >= K){
                for(int j =0; j <= l.size() - K ; j++){
                    int len = l.get(j+K -1) - l.get(j) + 1;
                    minLen = Math.min(minLen, len);
                    maxLen = Math.max(maxLen, len);
                }
            }
        }
        if(maxLen == -1){
            System.out.println("-1");
        }else{
            System.out.println(minLen + " " + maxLen);
        }
    }
}