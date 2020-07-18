package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Coin1_2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coinArr = new int[n];
        for(int i=0; i<n; i++) {
            coinArr[i] = Integer.parseInt(br.readLine());
        }
        br.close();
        solution(n,k,coinArr);
    }

    private static void solution(int n, int k, int[] coinArr) {
        int[] dp = new int[k+1];
        dp[0] =1;
        for(int i=0 ;i<n; i++) {
            int coin = coinArr[i];
            for(int j=coin; j<=k; j++) {
                if(j-coin >=0) {
                    dp[j] += dp[j - coin];
                }
            }
        }
        System.out.println(dp[k]);
    }
}