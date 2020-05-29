package programmers;

public class StandingLongJump_12914 {
    final static int MOD = 1234567;
    private static long solution(int n) {   // 배열 dp ~> 변수 세개
        long answer = 0;
        long left =1;
        long right =2;
        if(n ==1) {
            answer = left;
        } else if (n==2) {
            answer = right;
        } else {
            for(int i=2; i<n; i++) {
                answer = (left + right)%MOD;
                left = right;
                right = answer;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(4));
    }
}
/*
https://programmers.co.kr/learn/courses/30/lessons/12914
 */