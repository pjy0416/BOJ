package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StairNumber_10844 {
    final static int NUMSIZE = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        solution(Integer.parseInt(br.readLine()));

        br.close();
    }

    private static void solution(int n) {
        int[][] dp = new int[n+1][NUMSIZE];
        final int MOD = 1000000000;

        for(int i=1; i<NUMSIZE; i++) {
            dp[1][i] = 1;
        }
        for(int i=2; i<=n; i++) {
            for (int j = 0; j < NUMSIZE; j++) {
                if(j-1 >=0) {
                    dp[i][j] = (dp[i][j]+dp[i-1][j-1])%MOD;
                }
                if(j+1 <NUMSIZE) {
                    dp[i][j] = (dp[i][j]+dp[i-1][j+1])%MOD;
                }
            }
        }

        int num = 0;
        for(int i=0; i<NUMSIZE; i++) {
            num = (num+dp[n][i])%MOD;
        }
        System.out.println(num);
    }
}
