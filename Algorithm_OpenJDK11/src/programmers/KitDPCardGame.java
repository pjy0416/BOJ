package programmers;

public class KitDPCardGame {
    static int score;
    private static int solution(int[] left, int[] right) {
        score =0;
        dp(left,right,0,0,0);

        return score;
    }

    private static void dp(int[] left, int[] right, int leftIdx, int rightIdx, int sum) {
        if(leftIdx == left.length || rightIdx == right.length) {
            score = score < sum ? sum : score;
            return;
        }

//        1. 언제든지 왼쪽 카드만 통에 버릴 수도 있고 왼쪽 카드와 오른쪽 카드를 둘 다 통에 버릴 수도 있다. 이때 얻는 점수는 없다.
//        2. 오른쪽 카드에 적힌 수가 왼쪽 카드에 적힌 수보다 작은 경우에는 오른쪽 카드만 통에 버릴 수도 있다.
//        오른쪽 카드만 버리는 경우에는 오른쪽 카드에 적힌 수만큼 점수를 얻는다.
        // 2번 조건
        if(left[leftIdx] > right[rightIdx]) {
            dp(left,right,leftIdx,rightIdx+1, sum+right[rightIdx]);
        }
        // 1번 조건
        // 1-1
        dp(left,right,leftIdx+1,rightIdx,sum);
        // 1-2
        dp(left,right,leftIdx+1,rightIdx+1,sum);
    }

    public static void main(String[] args) {
        int[] left = {3,2,5};
        int[] right = {2,4,1};

        System.out.println(solution(left,right));
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/42896

카드 게임

문제 설명
카드게임이 있다. 게임에 사용하는 각 카드에는 양의 정수 하나가 적혀있고 같은 숫자가 적힌 카드는 여러 장 있을 수 있다.
게임방법은 우선 짝수개의 카드를 무작위로 섞은 뒤 같은 개수의 두 더미로 나누어 하나는 왼쪽에 다른 하나는 오른쪽에 둔다.

각 더미의 제일 위에 있는 카드끼리 서로 비교하며 게임을 한다.
게임 규칙은 다음과 같다.
지금부터 왼쪽 더미의 제일 위 카드를 왼쪽 카드로, 오른쪽 더미의 제일 위 카드를 오른쪽 카드로 부르겠다.

1. 언제든지 왼쪽 카드만 통에 버릴 수도 있고 왼쪽 카드와 오른쪽 카드를 둘 다 통에 버릴 수도 있다. 이때 얻는 점수는 없다.
2. 오른쪽 카드에 적힌 수가 왼쪽 카드에 적힌 수보다 작은 경우에는 오른쪽 카드만 통에 버릴 수도 있다.
   오른쪽 카드만 버리는 경우에는 오른쪽 카드에 적힌 수만큼 점수를 얻는다.
3. (1)과 (2)의 규칙에 따라 게임을 진행하다가 어느 쪽 더미든 남은 카드가 없다면 게임이 끝나며 그때까지 얻은 점수의 합이 최종 점수가 된다.
왼쪽 더미의 카드에 적힌 정수가 담긴 배열 left와 오른쪽 더미의 카드에 적힌 정수가 담긴 배열 right가 매개변수로 주어질 때,
얻을 수 있는 최종 점수의 최대값을 return 하도록 solution 함수를 작성하시오.

제한 사항

한 더미에는 1장 이상 2,000장 이하의 카드가 있다.

각 카드에 적힌 정수는 1 이상 2,000 이하이다.

입출력 예
left	      right	    return
[3, 2, 5]	[2, 4, 1]	  7

입출력 예 설명
먼저 오른쪽 카드를 버려서 2점을 획득한다.
그 뒤 왼쪽 카드를 두 장 버리고 오른쪽 카드를 버려서 4점을 획득한다.
마지막으로 오른쪽 카드를 버려서 1점을 획득한다.
총 얻을 수 있는 점수는 7점이다.
 */
