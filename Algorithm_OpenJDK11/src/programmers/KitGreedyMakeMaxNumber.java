package programmers;

public class KitGreedyMakeMaxNumber {
    private static String solution(String number, int k) {
        String answer = "";
        int len = number.length();

        StringBuffer sb = new StringBuffer();

        int count = len -k;
        char max = number.charAt(0);
        int maxIdx=0, lastIdx = len, start =0;

        while(count >0) {
            for(int i=start; i<lastIdx; i++) {
                char ch = number.charAt(i);
                if(max < ch) {
                    max = ch;
                    maxIdx = i;
                    if(max =='9') {
                        break;
                    }
                }
            }

            if(len-maxIdx < count) {    // 앞에서 지울 수 있는 범위를 넘어가는 수가 MAX일 경우 재설정
                lastIdx = maxIdx;
                max = number.charAt(start);
                maxIdx = start;
            } else {
                sb.append(max);
                if(maxIdx < len-1) {    // out of index 방지
                    maxIdx++;           // 최고값 다음 수부터 탐색
                    max = number.charAt(maxIdx);
                    start = maxIdx;
                    lastIdx = len;
                }
                count--;
            }
        }
        answer = sb.toString();

        return answer;
    }
    /*
    private static String solution(String number, int k) {
        return getAnswer(number, k);
    }

    private static String getAnswer(String number, int k) {
        StringBuffer sb = new StringBuffer();
        int len = number.length();
        int idx =0;
        for(int i=0; i<len-k; i++) {
            char max = ZERO;
            for(int j=idx; j<=i+k; j++) {
                char ch = number.charAt(j);
                if(max < ch) {
                    max = ch;
                    idx = j+1;
                }
            }
            sb.append(max);
        }
        return sb.toString();
    }
     */


    public static void main(String[] args) {
//        String number = "1924";
//        int k = 2;
        String number = "1231234";
        int k = 3;
//        String number = "4177252841";
//        int k = 4;
//        String number = "1111";
//        int k = 2;
        System.out.println(solution(number, k));
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/42883

큰 수 만들기
문제 설명
어떤 숫자에서 k개의 수를 제거했을 때 얻을 수 있는 가장 큰 숫자를 구하려 합니다.

예를 들어, 숫자 1924에서 수 두 개를 제거하면
[19, 12, 14, 92, 94, 24] 를 만들 수 있습니다. 이 중 가장 큰 숫자는 94 입니다.

문자열 형식으로 숫자 number와 제거할 수의 개수 k가 solution 함수의 매개변수로 주어집니다.
number에서 k 개의 수를 제거했을 때 만들 수 있는 수 중 가장 큰 숫자를 문자열 형태로 return 하도록 solution 함수를 완성하세요.

제한 조건
number는 1자리 이상, 1,000,000자리 이하인 숫자입니다.
k는 1 이상 number의 자릿수 미만인 자연수입니다.

입출력 예
number	        k	       return
1924	        2	        94
1231234	        3	        3234
4177252841	    4	        775841
10-7 =3 ~> count => 10-4 =6
출처
 */