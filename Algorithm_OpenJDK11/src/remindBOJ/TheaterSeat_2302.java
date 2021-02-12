package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TheaterSeat_2302 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine()), m = Integer.parseInt(br.readLine());
    int[] fixedSeat = new int[m];
    for(int i=0; i<m; i++) {
      fixedSeat[i] = Integer.parseInt(br.readLine());
    }

    br.close();
    solution(n, fixedSeat);
  }

  private static void solution(int n, int[] fixedSeat) {
    int[] dp = initDp(n);
    int answer =1;
    int prev =0;
    for(int seat : fixedSeat) {
      answer *= dp[seat-prev-1];
      prev = seat;
    }
    answer *= dp[n-prev];

    System.out.println(answer);
  }

  private static int[] initDp(int n) {
    int[] dp = new int[n+1];
    dp[0] =1;
    dp[1] =1;
    for(int i=2; i<=n; i++) {
      dp[i] = dp[i-1] +dp[i-2];
    }
    return dp;
  }
}
/*
3
3
1
2
3
 */