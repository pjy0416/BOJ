package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class StairNumber_1562 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());

    br.close();
    solution(n);
  }

  private static void solution(int n) {
    final int MAX_NUMBER =9, MOD = 1000000000, ALL_VISIT = (1<<10) -1; // 0 ~ 10 비트마스크
    int[][][] dp = new int[n+1][MAX_NUMBER+2][ALL_VISIT+1];
    for(int i=1; i<=MAX_NUMBER; i++) {
      dp[1][i][1 << i] = 1;
    }

    for(int i=2; i<=n; i++) {
      for(int j=0; j<=MAX_NUMBER; j++) {
        for(int k=0; k<=ALL_VISIT; k++) {
          dp[i][j][k | 1 << j] = (dp[i][j][k | 1 << j] + dp[i-1][j+1][k]) % MOD;
          if(j !=0) { // 0인 경우 out of index 방지
            dp[i][j][k | (1 << j)] = (dp[i][j][k | (1 << j)] + dp[i - 1][j - 1][k]) % MOD;
          }
        }
      }
    }

    int answer =0;
    for(int i=0; i<=MAX_NUMBER; i++) {
      answer = (answer + dp[n][i][ALL_VISIT]) % MOD;
    }
    System.out.println(answer);
  }
}
