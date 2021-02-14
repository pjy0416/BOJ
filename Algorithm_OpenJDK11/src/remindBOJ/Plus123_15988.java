package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Plus123_15988 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[] dp = initDp();
    StringBuilder sb = new StringBuilder();
    final String NEW_LINE = "\n";

    int testCases = Integer.parseInt(br.readLine());
    while(testCases-- >0) {
      int n = Integer.parseInt(br.readLine());
      sb.append(dp[n]).append(NEW_LINE);
    }

    br.close();
    System.out.println(sb.toString());
  }

  private static int[] initDp() {
    final int MAX = 1000000, MOD = 1000000009;
    int[] dp = new int[MAX+1];
    dp[1] = 1;
    dp[2] = 2; // 1 + 1, 2
    dp[3] = 4; // 1+1+1, 2+1, 1+2, 3
    dp[4] = 7;
    for(int i=5; i<=MAX; i++) {
      for(int j=1; j<=3; j++) {
        dp[i] = (dp[i] + dp[i-j]) % MOD;
      }
    }
    return dp;
  }
}
