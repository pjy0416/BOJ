package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PlumTree_2240 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int t = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken());
    int[] treeArr = new int[t];
    for(int i=0; i<t; i++) {
      treeArr[i] = Integer.parseInt(br.readLine());
    }

    br.close();
    solution(treeArr, t, w);
  }

  private static void solution(int[] treeArr, int t, int w) {
    int[][] dp = new int[t+1][w+1];
    final int STANDARD = 1;

    for(int i=0; i<t; i++) { // 초
      int now = i+1;
      if(treeArr[i] == STANDARD) { // 짝수 이동 횟수 됨
        dp[now][0] = dp[i][0] +1;
        for(int j=2; j<=w; j+=2) {
          dp[now][j] = Math.max(dp[i][j-1],dp[i][j]) +1;
        }
      } else { // 홀수 이동 횟수 됨
        for(int j=1; j<=w; j+=2) {
          dp[now][j] = Math.max(dp[i][j-1],dp[i][j]) +1;
        }
      }
      for(int j=0; j<=w; j++) { // 이전 기록 저장
        dp[now][j] = Math.max(dp[i][j], dp[now][j]);
      }
    }

    int answer =0;
    for(int i=0; i<=w; i++) {
      answer = Math.max(dp[t][i], answer);
    }
    System.out.println(answer);
  }
}