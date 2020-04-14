package programmers;

public class KitDPThievery {
    private static int solution(int[] money) {
        //이 마을에 있는 집은 3개 이상 1,000,000개 이하입니다.
        int len = money.length;
        int[] answer = new int[len+1];

        answer[0] = money[len-1];
        answer[1] = money[0];
        answer[2] = money[1] + answer[0];

        for (int i = 3; i <= len; i++) {
            answer[i] = answer[i-2] + money[i-1] > answer[i-1] ? answer[i-2] + money[i-1] : answer[i-1];
        }

/*
        for(int num : answer) {
            System.out.println(num);
        }
*/

        /*if(money[0] < money[money.length-1]) {
            answer[money.length-1] -= money[0];
        } else {
            answer[money.length-1] -= money[money.length-1];
        }
*/
        return answer[money.length-1];
    }

    public static void main(String[] args) {
        int[] money = {1, 2, 3, 1};

        System.out.println(solution(money));
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/42897
도둑질

문제 설명
도둑이 어느 마을을 털 계획을 하고 있습니다. 이 마을의 모든 집들은 아래 그림과 같이 동그랗게 배치되어 있습니다.

각 집들은 서로 인접한 집들과 방범장치가 연결되어 있기 때문에 인접한 두 집을 털면 경보가 울립니다.

각 집에 있는 돈이 담긴 배열 money가 주어질 때, 도둑이 훔칠 수 있는 돈의 최댓값을 return 하도록 solution 함수를 작성하세요.

제한사항
이 마을에 있는 집은 3개 이상 1,000,000개 이하입니다.
money 배열의 각 원소는 0 이상 1,000 이하인 정수입니다.

입출력 예

    money	        return
[1, 2, 3, 1]          4

 */
