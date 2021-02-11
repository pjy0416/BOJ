package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JumpJump_11060 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] numArr = new int[n];
    for(int i=0; i<n; i++) {
      numArr[i] = Integer.parseInt(st.nextToken());
    }

    br.close();
    solution(n, numArr);
  }

  private static void solution(int n, int[] numArr) {
    final int MAX = 10000;
    int[] dp = new int[n];
    Arrays.fill(dp, MAX);
    dp[0] =0;

    for(int now=0; now<n; now++) {
      for(int next=now+1; next<=now+numArr[now] && next<n; next++) {
        dp[next] = Math.min(dp[now]+1, dp[next]);
      }
    }
    if(dp[n-1] >= MAX) {
      dp[n-1] = -1;
    }

    System.out.println(dp[n-1]);
  }
}

/*
10
1 2 0 1 3 0 0 0 0 0

5
0 0 0 1 0
 */
