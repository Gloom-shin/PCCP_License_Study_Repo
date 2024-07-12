package JUL.day12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Elise5 {
    static HashMap<Integer, Integer> combiMap = new HashMap<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 이미 수열안에 원소들도 포함되어있음
        // 작은 수 부터 조합으로 만들수 있는 숫자가 있으면, 그 숫자 제외
        // 남은 수를 비교하여, 모두 합한 값이  max값을 넘어가면 안댐. (애초에 작은 수부터 봤기때문에, 같아지면 순환끝임)
        int n = Integer.parseInt(br.readLine());
        int pow = (int)Math.pow(2 , n);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i< pow; i++){
            pq.add(Integer.parseInt(st.nextToken()));
        }

        ArrayList<Integer> result = new ArrayList<>();

        while(!pq.isEmpty()){
            int el =  pq.poll();
            if(el == 0) continue;
            // 조합으로 나온 수중에 있으면 제외
            if(combiMap.containsKey(el)){
                int cnt = combiMap.get(el) -1 ;
                combiMap.put(el, cnt);
                if(cnt ==0) combiMap.remove(el);
                continue;
            }
            // 없으면 추가
            result.add(el);
            if(result.size() == n){
                break;
            }
            // 조합 추가하기
            for(int target=2; target<= result.size(); target++){
                boolean[] visited = new boolean[target];
                combi(result, target, 0, 0, visited);
            }

        }
        StringBuilder sb = new StringBuilder();
        for(int a : result){
            sb.append(a).append(" ");
        }

        System.out.println(sb.toString());
    }

    private static void combi(ArrayList<Integer> list,int target, int cnt, int sum, boolean[] visited){
        if(cnt == target){ // 2개부터 ~ n개 까지
            if(combiMap.containsKey(sum)) return;
            combiMap.put(sum, 1);
            return;
        }
        for(int i=0; i < list.size(); i++){
            if(visited[i]) continue;
            visited[i] = true;
            combi(list, target, cnt+1, sum + list.get(i), visited);
            visited[i] = false;
        }

    }
}