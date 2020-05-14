package programmers;

public class Tiling_12900 {
    private static int solution(int n) {
        int answer =0;
        if(n ==1 || n==2) {
            answer = n;
        } else {
            long[] dp = new long[n + 1];
            dp[1] =1;
            dp[2] =2;

            for(int i=3; i<=n; i++) {
                dp[i] = dp[i-1]%1000000007 + dp[i-2]%1000000007;
            }
            answer = (int)(dp[n]%1000000007);
        }
        return answer;
    }

    public static void main(String[] args) {
        int n =5;
        System.out.println(solution(n));
    }
}
