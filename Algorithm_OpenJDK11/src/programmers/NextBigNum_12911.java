package programmers;

public class NextBigNum_12911 {
    private static int solution(int n) {
        int cnt = getBinaryCnt(n);

        boolean isDone = false;
        int answer = n;
        while(!isDone) {
            if(getBinaryCnt(++answer) == cnt) {
                isDone = true;
            }
        }
        return answer;
    }

    private static int getBinaryCnt(int num) {
        int result =0;
        String str = Integer.toBinaryString(num);
        str = str.replaceAll("[0]", "");

        for(int i =0; i< str.length(); i++) {
            if(str.charAt(i) =='1') {
                result++;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int n = 78;
        System.out.println(solution(n));
    }
}
/*
https://programmers.co.kr/learn/courses/30/lessons/12911
다음 큰 숫자
문제 설명
자연수 n이 주어졌을 때, n의 다음 큰 숫자는 다음과 같이 정의 합니다.

조건 1. n의 다음 큰 숫자는 n보다 큰 자연수 입니다.
조건 2. n의 다음 큰 숫자와 n은 2진수로 변환했을 때 1의 갯수가 같습니다.
조건 3. n의 다음 큰 숫자는 조건 1, 2를 만족하는 수 중 가장 작은 수 입니다.
예를 들어서 78(1001110)의 다음 큰 숫자는 83(1010011)입니다.

자연수 n이 매개변수로 주어질 때, n의 다음 큰 숫자를 return 하는 solution 함수를 완성해주세요.

제한 사항
n은 1,000,000 이하의 자연수 입니다.
입출력 예
n	result
78	83
15	23
입출력 예 설명
입출력 예#1
문제 예시와 같습니다.
입출력 예#2
15(1111)의 다음 큰 숫자는 23(10111)입니다.
 */
