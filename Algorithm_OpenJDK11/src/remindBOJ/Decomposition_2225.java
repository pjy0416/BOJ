package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Decomposition_2225 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        br.close();
        solution(n,k);
    }

    private static void solution(int n, int k) {
        int[][] dp = new int[k+1][n+1];
        final int MOD = 1000000000;

        for(int i=0; i<=n; i++) { dp[1][i] = 1; } // init DP

        for(int i=1; i<=k; i++) { // 2
            for(int j=0; j<=n; j++) { // 20
                for(int l=j; l>=0; l--) {
                    dp[i][j] = (dp[i][j] + dp[i-1][j-l]) %MOD;
                }
            }
        }
        System.out.println(dp[k][n]);
    }
}

// n = 1, k =3;
// 0 ,2
// 1
//