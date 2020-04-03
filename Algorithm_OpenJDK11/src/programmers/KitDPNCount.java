package programmers;

import java.util.ArrayList;
import java.util.Collections;

public class KitDPNCount {
    static ArrayList<Integer> cntList;

    private static int solution(int N, int number) {
        cntList = new ArrayList<>();
        dfs(0, 0, N, number);
        Collections.sort(cntList);

        int answer = cntList.get(0);

        return answer > 8 ? -1 :answer;
    }

    private static void dfs(int cnt, int number, int N, int target) {
        if(cnt >8) {    // Breaking Point
            return;
        }
        if(number == target) {  // 숫자가 만들어질 때 count 입력
            cntList.add(cnt);
            return;
        }

        for(int i=1; i<=8-cnt; i++) {   // N, NN, NNN, ... 사칙연산 Looping
            dfs(cnt + i, calculator(0, number, increaseNum(i,N)), N, target);
            dfs(cnt + i, calculator(1, number, increaseNum(i,N)), N, target);
            dfs(cnt + i, calculator(2, number, increaseNum(i,N)), N, target);
            dfs(cnt + i, calculator(3, number, increaseNum(i,N)), N, target);
        }
    }

    private static int increaseNum(int digit, int N) {  // 입력된 자릿수 만큼 digit개로 이루어진 N을 반환
        int result = 0;
        for(int i=0; i<digit; i++) {
            result += Math.pow(10,i)*N;
        }
        return result;
    }

    private static int calculator(int cmd, int left, int right) {
        int result =0;

        switch (cmd) {
            case 0: // 덧셈
                result = left + right;
                break;
            case 1: // 뺄셈
                result = left - right;
                break;
            case 2: // 곱셈
                result = left * right;
                break;
            case 3: // 나눗셈
                result = right ==0 ? 0 :left / right;
                break;
            default:
                break;
        }

        return result;
    }

    public static void main(String[] args) {
        int N = 5, number = 12;
//        int N = 2, number = 11;
//        int N = 5, number = 31168;
//        int N = 9, number = 13;
        System.out.println(solution(N, number));
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/42895
문제 설명
아래와 같이 5와 사칙연산만으로 12를 표현할 수 있습니다.

12 = 5 + 5 + (5 / 5) + (5 / 5)
12 = 55 / 5 + 5 / 5
12 = (55 + 5) / 5

5를 사용한 횟수는 각각 6,5,4 입니다. 그리고 이중 가장 작은 경우는 4입니다.
이처럼 숫자 N과 number가 주어질 때,
N과 사칙연산만 사용해서 표현 할 수 있는 방법 중 N 사용횟수의 최솟값을 return 하도록 solution 함수를 작성하세요.

제한사항
N은 1 이상 9 이하입니다.
number는 1 이상 32,000 이하입니다.
수식에는 괄호와 사칙연산만 가능하며 나누기 연산에서 나머지는 무시합니다.
최솟값이 8보다 크면 -1을 return 합니다.
입출력 예

N	number	return
5	  12	  4
2	  11	  3

입출력 예 설명

예제 #1
문제에 나온 예와 같습니다.

예제 #2
11 = 22 / 2와 같이 2를 3번만 사용하여 표현할 수 있습니다.
 */
