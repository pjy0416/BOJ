package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MakeBridge_1010 {
    private final static int MAX = 29;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        int[][] dp = getAllCases();
        for(int i=0; i<testCase; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            System.out.println(dp[right][left]);
        }

        br.close();
    }

    private static int[][] getAllCases() {
        int[][] result = new int[MAX+1][MAX+1];
        result[1][0] = 1;
        result[1][1] =1;

        for(int n=2; n<=MAX; n++) { // nCr = n-1Cr-1 + n-1Cr
            result[n][0] =1;
            for(int r=1; r<=n; r++) {
                result[n][r] = result[n-1][r-1] + result[n-1][r];
            }
        }
        return result;
    }
}
