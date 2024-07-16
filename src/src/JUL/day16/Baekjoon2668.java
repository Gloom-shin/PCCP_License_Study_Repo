package JUL.day16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Baekjoon2668 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] parent = new int[N + 1];
        Set<Integer> answerSet = new HashSet<>();
        int answer = 0;
        for(int i=1; i<= N; i++){
            int number = Integer.parseInt(br.readLine());
            parent[i] = number;
            if(i == number){
                answerSet.add(number);
                answer++;
            }
        }//무식한 방법으로 100번씩 확인 (처음골랐던 자신이 안나오면, 순환이 아님)
        int start;
        int next;
        Set<Integer> set = new HashSet<>();
        int cnt; // 100까지
        int max = 0;
        Set<Integer> maxSet  = new HashSet<>();
        for(int i=1; i<= N; i++){
            if(answerSet.contains(i)) continue;
            start = i;
            next = i;
            set = new HashSet<>();
            set.add(start);
            cnt = 1;
            while(cnt <= 100){
                next = parent[next];
                if(next == start){
                    //처음으로 돌아옴
                    answerSet.addAll(set);
                    answer += set.size();
                    break;
                }
                set.add(next);
                cnt++;
            }
        }
        ArrayList<Integer> list =new ArrayList<>(answerSet);
        Collections.sort(list);
        System.out.println(answer);
        for(int i : list){
            System.out.println(i);
        }
    }
}
