package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Freshman_5557 {
    final static int MIN = 0, MAX =20, ZERO =0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] numArr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
        solution(n, numArr);
    }

    private static void solution(int n, int[] numArr) {
        long[][] dp = new long[n][MAX+1];
        final int lastIndex = n-1;
        dp[0][numArr[0]] = 1;
        for(int i=1; i<lastIndex; i++) {
            for(int j=0; j<=MAX; j++) {
                if(dp[i-1][j] == ZERO) {
                    continue;
                }
                int sum = j + numArr[i];
                int minus = j - numArr[i];
                if(isInRange(sum)) {
                    dp[i][sum]+= dp[i-1][j];
                }
                if(isInRange(minus)) {
                    dp[i][minus]+= dp[i-1][j];
                }
            }
        }
        System.out.println(dp[lastIndex-1][numArr[lastIndex]]);
    }

    private static boolean isInRange(int sum) {
        return sum >= MIN && sum <=MAX;
    }
}
