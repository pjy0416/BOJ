package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Pizza_3213 {
    final static String[] PIZZAS= {"1/4", "1/2", "3/4"};
    final static int SIZE = 3;
    static int[] PIZZACNT;

    public static void main(String[] args) throws IOException {
        System.out.println(solution(new BufferedReader(new InputStreamReader(System.in))));
    }

    private static int solution(BufferedReader br) throws IOException{
        int answer =0;
        HashMap<String, Integer> pizzaMap = new HashMap<>();
        PIZZACNT = new int[SIZE];

        int friends = Integer.parseInt(br.readLine());

        for(int i=0; i<friends; i++) {
            String pizza = br.readLine();
            if(pizzaMap.containsKey(pizza)) {
                pizzaMap.replace(pizza, pizzaMap.get(pizza)+1);
            } else {
                pizzaMap.put(pizza,1);
            }
        }
        for(int i=0; i<SIZE; i++) {
            String pizza = PIZZAS[i];
            if(pizzaMap.containsKey(pizza)) {
                PIZZACNT[i] = pizzaMap.get(pizza);
            }
        }
        answer += canCombine(0,2); // 1/4랑 3/4 같이 묶을 수 있는 경우, 먼저 처리
        answer += PIZZACNT[2];  // 남은 3/4짜리가 있다면, 한판으로 시켜야함
        answer += canCombine(0,1); // 1/4랑 1/2 같이 묶을 수 있는 경우, 처리
        answer += PIZZACNT[1]/2; // 1/2 2개당 한판
        answer += PIZZACNT[1]%2; // 1/2가 홀수면 한판 추가
        answer += PIZZACNT[0]/4; // 1/4 4개당 한판
        answer += PIZZACNT[0]%4; // 4개로 묶었을 때, 남는게 있으면

        br.close();

        return answer;
    }

    private static int canCombine(int idx1, int idx2) {
        int min;
        if(idx2==2) { // 1/4랑 3/4 컴바인
            min = Math.min(PIZZACNT[idx1], PIZZACNT[idx2]);
            PIZZACNT[idx1] -= min;
            PIZZACNT[idx2] -= min;
        } else { // 1/4랑 1/2 컴바인
            min = Math.min(PIZZACNT[idx1]/2, PIZZACNT[idx2]);
            PIZZACNT[idx1] -= min*2;
            PIZZACNT[idx2] -= min;
            if(PIZZACNT[idx1] !=0 && PIZZACNT[idx2] !=0) {    // 남은게 있네? ~> 하나씩 묶어주자
                PIZZACNT[idx1]--;
                PIZZACNT[idx2]--;
                min++;
            }
        }
        return min;
    }
}
/*
https://www.acmicpc.net/problem/3213

8
1/2
1/4
1/4
1/4
1/4
3/4
1/4
1/2
 */