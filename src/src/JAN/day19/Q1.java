package JAN.day19;

// 총 주사위 갯수 2, 4, 6, 8, 10개
// 각 주사위에 적힌 숫자는 1~100이고 총 6개이고
// 반반씩 가져간다 햇을때,
// 완전탐색? (5) (5)  -> 6^5 * 6^5 = 6^10 안됨
// 예시 1이 2보다 큰 경우의 수 3*4개 +2*2개  = 16
//     1이 3보다 큰수 5개 + 2*3개 + 3* 2개 = 17
//     1이 4보다 큰수 2*5개 + 2*2개 + 2* 1개 = 16
//  = 49
// 예시 2이 1보다 큰 경우의 수 6*2개 + 2개  = 14
//     2이 3보다 큰수       6개 + 2*2개  = 10
//     2이 4보다 큰수       2*6개       = 12
//  = 36
// 예시 3이 1보다 큰 경우의 수 5*2 + 3  = 13
//     3이 2보다 큰수       4 * 3개   = 12
//     3이 4보다 큰수       2*5개     = 10
// = 35

// 예시 4이 1보다 큰 경우의 수 3*4 + 2  = 14
//     4이 2보다 큰수       4*4 * 2*2개 = 20
//     4이 3보다 큰수       3*4 + 3*2 = 18
// = 52
// 이래서 최동은 1,4

// 각 주사위가 다른 주사위 이기는 경우의 수를 계산
import java.util.*;
class Q1 {
    public int[] solution(int[][] dice) {


        int diceCnt = dice.length;
        Dice[] dices = new Dice[diceCnt];
        int cnt = 0;
        for(int i=0; i < diceCnt; i++){
            cnt = 0;
            for(int other = 0; other < diceCnt; other++){
                if(other == i) continue;
                // 승리하는 경우의 수 계산
                cnt+= victoryCount(dice[i], dice[other]);
            }
            System.out.println(i + " : " + cnt);

            dices[i] = new Dice(i, cnt);
        }
        Arrays.sort(dices);
        int[] answer = new int[diceCnt/2];
        for(int i=0; i < answer.length; i++){
            answer[i] = dices[i].index + 1 ;
        }
        Arrays.sort(answer);
        return answer;
    }
    private int victoryCount(int[] curDice, int[] otherDice){
        int cnt = 0;
        for(int i=0; i< 6; i++){
            for(int j=0; j< 6; j++){
                if(curDice[i] >  otherDice[j]){
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
class Dice implements Comparable<Dice> {
    int index;
    int winCnt;

    Dice(int index,int winCnt){
        this.index = index;
        this.winCnt = winCnt;
    }
    public int compareTo(Dice obj){ // 크면 앞으로
        return   obj.winCnt - this.winCnt;
    }
}
