package programmers;

import java.util.ArrayList;
import java.util.Collections;

public class KitDPNCount {
    static ArrayList<Integer> cntList;

    private static int solution(int N, int number) {
        int digit = getDigit(number);   // Digit 범위 => 1 ~ 5
        int[] info = optimaCondition(digit, N, number); // 0 : cnt, 1: div, 2: mod  메소드를 돌고나면 1 Idx는 필요 없어지고 2자리 수 이하가 mod에 남음

        digit = getDigit(info[2]);  // 자리수를 다시 구해줌

        cntList = new ArrayList<>();
        dfs(info[0]-1,0,N,number);  // 처음 들어갈 때, 0에서 연산이 시작됨으로 첫 수를 넣는 카운트 1 뺴줌
        Collections.sort(cntList);

        int answer = cntList.get(0);

        return answer > 8 ? -1 :answer;
    }

    private static void dfs(int cnt, int number, int N, int target) {
        if(cnt >8) {
            return;
        }
        if(number == target) {
            cntList.add(cnt);
            return;
        }
        dfs(cnt+1, calculator(0,number,N), N, target);
        dfs(cnt+1, calculator(1,number,N), N, target);
        dfs(cnt+1, calculator(2,number,N), N, target);
        dfs(cnt+1, calculator(3,number,N), N, target);
        dfs(cnt+2, calculator(4,number,N), N, target);
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
            case 4: // 자기 자신 두자릿수
                result = left*10 + left;
                break;
            default:
                break;
        }

        return result;
    }

    private static int getDigit(int num) {  // 자리 수 구하기
        int cnt =1;

        while(num /10 !=0) {
            cnt++;
            num /=10;
        }
        return cnt;
    }

    private static int[] optimaCondition(int digit, int N, int num) {   // 속도 향상을 위해 최적화 시키기
        int[] info = {0,0,num};   // 0 : Cnt , 1 : div, 2 : mod

        for(int i=0; i<digit; i++) {
            int tmp =0;
            for(int j=i; j<digit; j++) {
                tmp +=Math.pow(10, j) * N;
            }
            while(num/tmp !=0) {
                info[0] += info[1]*(digit-i);
                info[1] = num/tmp;
                info[2] = num%tmp;
            }
        }

        return info;
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
