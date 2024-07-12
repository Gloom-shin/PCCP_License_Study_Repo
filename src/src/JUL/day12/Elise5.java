package JUL.day12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
//5
//0 1 2 3 3 4 4 5 5 6 6 7 7 8 9 10 10 11 12 13 13 14 14 15 15 16 16 17 17 18 19 20
public class Elise5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 이미 수열안에 원소들도 포함되어있음
        // 작은 수 부터 조합으로 만들수 있는 숫자가 있으면, 그 숫자 제외
        // 남은 수를 비교하여, 모두 합한 값이  max값을 넘어가면 안댐. (애초에 작은 수부터 봤기때문에, 같아지면 순환끝임)
        int n = Integer.parseInt(br.readLine());
        int pow = (int) Math.pow(2, n);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < pow; i++) {
            pq.add(Integer.parseInt(st.nextToken()));
        }
        HashMap<Integer, Integer> makeMap = new HashMap<>(); // 만든 조합 저장
        HashMap<Integer, Integer> map = new HashMap<>(); // 기존에 주어지는 값 저장
        ArrayList<Integer> result = new ArrayList<>(); // 원소 저장하는 곳
        ArrayList<Integer> combiList = new ArrayList<>(); // 조합 저장하는 곳
        combiList.add(0);
        makeMap.put(0, 1);
        map.put(0, 1);
        while (!pq.isEmpty()) {
            int el = pq.poll();
            if (el == 0) continue;
            map.put(el, map.getOrDefault(el, 0) + 1);
            // 조합으로 만든 값들이 주어지는 값을 포함하면 카운트하고 패스
            if (makeMap.containsKey(el) && makeMap.get(el) >= map.get(el)) {
                continue;
            }
            // 없으면 추가
            result.add(el);
            if (result.size() == n) {
                break;
            }
            int size = combiList.size();
            for (int i = 0; i < size; i++) {
                int addCombi = combiList.get(i) + el;
                combiList.add(addCombi);
                makeMap.put(addCombi, makeMap.getOrDefault(addCombi, 0) + 1);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int a : result) {
            sb.append(a).append(" ");
        }

        System.out.println(sb.toString());
    }
}
