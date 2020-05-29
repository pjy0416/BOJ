package programmers;

public class Changes_12907 {
    final static int MOD = 1000000007;
    private static int solution(int n, int[] money) {
        int[] dp = new int[n+1];
        dp[0] =1;

        for(int i=0; i<money.length; i++) { // 돈 종류 idx
            for(int j=money[i]; j<=n; j++) { // 돈 종류 ~ 거슬러 줘야하는 금액 N
                dp[j] += dp[j-money[i]];    //
            }
        }
        return dp[n]%MOD;
    }

    public static void main(String[] args) {
        int n =5;
        int[] money = {1,2,4};
        System.out.println(solution(n,money));
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/12907#
 */