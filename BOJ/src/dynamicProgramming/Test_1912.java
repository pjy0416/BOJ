package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_1912 {        // wrong code
    public static void main(String[] args) throws IOException {
        int[][] dp;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());
        dp = new int[size+1][2];        // - 더하는 경우와 안하는 경우     0: - 포함, 1: 미포함

        String inputStr = br.readLine();
        String[] numArr = inputStr.split(" ");

        int zeroMax =0;
        int oneMax =0;

        for(int i=1; i<=size; i++) {
            int num = Integer.parseInt(numArr[i-1]);
            dp[i][0] = dp[i-1][0] + num;
            if(num >0) {
                dp[i][1] = dp[i-1][1] + num;
                oneMax = Math.max(oneMax, dp[i][1]);
            }
            dp[i][0] = Math.max(dp[i][0], dp[i][1]);
            zeroMax = Math.max(dp[i][0], zeroMax);
        }

        System.out.println(Math.max(zeroMax, oneMax));

        br.close();
    }
}
