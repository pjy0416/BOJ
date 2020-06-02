package programmers;

import java.util.Stack;

public class KitStackQueueStockPrice {

    private static int[] solution(int[] prices) {   // 첫번째 푼 풀이
        int len = prices.length;
        int[] answer = new int[len];

        Stack<int[]> stack = new Stack<>();

        // 주가 하락시점 저장
        for(int i=0; i<len; i++) {
//            System.out.println(i + "번 쨰 도는 중 ##########################");
            int[] tmp = {i, prices[i]};
            if(stack.isEmpty()) {
                stack.add(tmp);
//                System.out.println(tmp[1] + "스택에 추가 \t\t pirce는 " + prices[i]);
            } else {
//                 주가가 하락시점에서 스택 비교
                while(stack.peek()[1] > prices[i]) {
                    int[] popStock = stack.pop();
//                    System.out.println("들어오는 시점 : " + i + "\t\t" + popStock[1] + "보다 " + prices[i] + "가 더 작아서");
//                    System.out.println(popStock[0] + "인덱스 빠짐");
                    answer[popStock[0]] = i - popStock[0];

                    if(stack.isEmpty()) {   // Stack Null Checking
                        break;
                    }
                }
                stack.add(tmp);
//                System.out.println(tmp[1] + "스택에 추가");
            }
        }

        while(!stack.isEmpty()) {
            int[] tmp = stack.pop();
//            System.out.println("스택처리 : " + tmp[0]);
            answer[tmp[0]] = len - tmp[0] -1;
        }

        answer[len-1] =0;
        return answer;
    }

    /*
    public int[] solution(int[] prices) {   // 2020.06.02 다시 품
        int[] answer = new int [prices.length];
        for(int i=0; i<prices.length-1; i++) {
            int prev = prices[i];
            for(int j=i; j<prices.length; j++) {
                if(i!=j) {
                    answer[i]++;
                    if(prev > prices[j]) {
                        break;
                    }
                }
            }
        }

        return answer;
    }
     */

    public static void main(String[] args) {
        int[] prices = {5, 6, 3, 7, 9, 2};

        int[] result = solution(prices);

        for(int num : result) {
            System.out.print(num + " ");
        }

    }
}
// 21 22 40 100 200 107 99 11 12 13 14 25

/*
문제 설명
초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때, 가격이 떨어지지 않은 기간은 몇 초인지를 return 하도록 solution 함수를 완성하세요.

제한사항
prices의 각 가격은 1 이상 10,000 이하인 자연수입니다.
prices의 길이는 2 이상 100,000 이하입니다.
입출력 예
prices	return
[1, 2, 3, 2, 3]	[4, 3, 1, 1, 0]
입출력 예 설명
1초 시점의 ₩1은 끝까지 가격이 떨어지지 않았습니다.
2초 시점의 ₩2은 끝까지 가격이 떨어지지 않았습니다.
3초 시점의 ₩3은 1초뒤에 가격이 떨어집니다. 따라서 1초간 가격이 떨어지지 않은 것으로 봅니다.
4초 시점의 ₩2은 1초간 가격이 떨어지지 않았습니다.
5초 시점의 ₩3은 0초간 가격이 떨어지지 않았습니다.
※ 공지 - 2019년 2월 28일 지문이 리뉴얼되었습니다.
 */
