package programmers;

public class KitDPThievery {
    private static int solution(int[] money) {
        //이 마을에 있는 집은 3개 이상 1,000,000개 이하입니다.
        int len = money.length;
        int[] dp1 = new int[len], dp2 = new int[len];

        dp1[0] = money[0];  // 첫번째 돈 포함
        dp1[1] = money[0];  // 두번째 돈은 포함을 안할거니까 첫번째 애 입력
        dp2[1] = money[1];  // 두번째 돈 포함

        for (int i = 2; i <len; i++) {  // 돈은 하나 당겨서 생각
            dp1[i] = Math.max(dp1[i-2]+money[i], dp1[i-1]);
            dp2[i] = Math.max(dp2[i-2]+money[i], dp2[i-1]);
        }

        return Math.max(dp1[len-2], dp2[len-1]);    // dp1에는 첫번째 집이 포함됐으니까 최대 길이 하나 전 IDX를 return
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
