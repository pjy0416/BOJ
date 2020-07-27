package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Coin2_2294 {
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
        solution(k,coinArr);
    }

    private static void solution(int k, int[] coinArr) {
        int[] dp = new int[k+1];
        dp[k] = -1;
        Arrays.sort(coinArr);
        for(int coin : coinArr) {
            if(coin >k) { break; }
            dp[coin] =1;
            for(int i=1; i+coin<=k; i++) {
                if(dp[i] >0) {
                    dp[i + coin] = Math.min(dp[i + coin], dp[i] + 1);
                    if (dp[i + coin] <= 0) dp[i + coin] = dp[i] + 1;
                }
            }
        }
        System.out.println(dp[k]);
    }
}

