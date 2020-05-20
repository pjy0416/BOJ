package programmers;

public class Fibonacci_12945 {
    final static int DIV_NUM = 1234567;
    private static int solution(int n) {
        int[] answer = new int[n+1];
        answer[0] =0;
        answer[1] = 1;
        for(int i=2; i<=n; i++) {
            answer[i] = (answer[i-1] + answer[i-2])%DIV_NUM ;
        }

        return answer[n];
    }
    public static void main(String[] args) {
        int n =5;
        System.out.println(solution(n));
    }
}
