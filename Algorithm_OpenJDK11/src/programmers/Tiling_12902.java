package programmers;

public class Tiling_12902 {
    public static void main(String[] args) {
        int n =5000;
        System.out.println(solution(n));
    }

    private static int solution(int n) {
        int answer =0;
        if(n ==2) {
            answer =3;
        } else if(n%2 ==0) {   // 짝수일 때만
            int div = 1000000007;
            long[] dp = new long[n+1];
            dp[1] = 2;
            dp[2] = 3;

            for(int i=4; i<=n; i+=2) {
                // 점화식
                dp[i] = dp[i-2]*3;
                dp[i-1] = dp[i-3] + dp[i-4]*2; // 홀수는 안쓰이기 때문에 add를 저장
                dp[i] += dp[i-1];
                dp[i] %= div;
            }
            answer = (int)dp[n];
        }

        return answer;
    }
}