package programmers;

import java.util.Arrays;

public class NumberOf124Nation {
    static final int[] numArr = {1,2,4};

    private static String solution(int n) {
        StringBuffer sb = new StringBuffer();
        int[] info = getDigit(n);   // 0 : digit, 1 : start Num
        char[] numChArr = getChArr(info, n);

        for(char ch : numChArr) {
            sb.append(numArr[ch-48]);
        }
        return sb.toString();
    }

    private static char[] getChArr(int[] info, int num) {
        char[] result = new char[info[0]];
        Arrays.fill(result, '0');
        num -= info[1];
        int idx = info[0]-1;

        while(num !=0) {
            result[idx--] += num%3;
            num /= 3;
        }

        return result;
    }

    private static int[] getDigit(int num) {
        int end = 0;
        int digit = 0;
        int start = 0;

        for(int i=1; i<19; i++) {
//            System.out.println(start);
//            System.out.println(end);
            end += Math.pow(3,i);
            start += Math.pow(3,i-1);
            digit = i;
            if(num <= end) {
                break;
            }
        }
//        System.out.println(start);
//        System.out.println(end);
        int[] result = {digit, start};
        // digit이랑 start가 필요
        return result;
    }
    public static void main(String[] args) {
        int n = 39;
        System.out.println(solution(n));
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/12899
124 나라의 숫자

문제 설명
124 나라가 있습니다.
124 나라에서는 10진법이 아닌 다음과 같은 자신들만의 규칙으로 수를 표현합니다.

124 나라에는 자연수만 존재합니다.
124 나라에는 모든 수를 표현할 때 1, 2, 4만 사용합니다.
예를 들어서 124 나라에서 사용하는 숫자는 다음과 같이 변환됩니다.

10진법	 124 나라	10진법	  124 나라
  1	        1	      6	        14
  2	        2	      7	        21
  3	        4	      8	        22
  4	        11	      9	        24
  5	        12	      10	    41
자연수 n이 매개변수로 주어질 때, n을 124 나라에서 사용하는 숫자로 바꾼 값을 return 하도록 solution 함수를 완성해 주세요.

제한사항
n은 500,000,000이하의 자연수 입니다.
입출력 예
n	result
1	1
2	2
3	4
4	11
 */