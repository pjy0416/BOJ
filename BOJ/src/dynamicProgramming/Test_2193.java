package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_2193 {
    // 이번 문제 역시 점화식 문제
    // D[n] = D[n-1] + D[n-2] (단, n >=3)
    public static void main(String[] args) throws IOException {
        long dp[];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());
        dp = new long[size+1];

        if(size ==1) {
            System.out.println(1);
        } else {
            dp[0] = 0;
            dp[1] = 1;
            for(int i=2; i<=size; i++) {
                dp[i] = dp[i-1] + dp[i-2];
            }
            System.out.println(dp[size]);
        }

        br.close();
    }
}
