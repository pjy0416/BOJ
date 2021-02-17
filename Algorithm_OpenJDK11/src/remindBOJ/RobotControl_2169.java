package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RobotControl_2169 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());

    int[][] map = new int[n+1][m+1];

    for(int i=1; i<=n; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=1; j<=m; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    br.close();
    solution(n,m,map);
  }

  private static void solution(int n, int m, int[][] map) {
    int[][] dp = initDP(n,m,map);

    for(int y=2; y<=n; y++) {
      int[] left = getLeftDirection(y,m,dp,map); // 우측에서 좌측으로 진행하는 경우 left <-- right
      int[] right = getRightDirection(y,m,dp,map); // 좌측에서 우측으로 진행하는 경우 left --> right
      
      for(int x=1; x<=m; x++) { // dp
        dp[y][x] = Math.max(left[x], right[x]);
      }
    }
    System.out.println(dp[n][m]);
  }

  private static int[] getLeftDirection(int y, int m, int[][] dp, int[][] map) {
    int[] left = new int[m+2];
    left[m+1] = dp[y-1][m]; // 위에서 내려온 값을 0에 저장
    for(int x=m; x>0; x--) {
      left[x] = Math.max(left[x+1], dp[y-1][x]) + map[y][x];
    }

    return left;
  }

  private static int[] getRightDirection(int y, int m, int[][] dp, int[][] map) {
    int[] right = new int[m+1];
    right[0] = dp[y-1][1]; // 위에서 내려온 값을 0에 저장
    for(int x=1; x<=m; x++) {
      right[x] = Math.max(right[x-1], dp[y-1][x]) + map[y][x];
    }
    return right;
  }

  private static int[][] initDP(int n, int m, int[][] map) {
    int[][] dp = new int[n+1][m+1];
    dp[1][1] = map[1][1];
    for(int x=1; x<=m; x++) {
      dp[1][x] = map[1][x] + dp[1][x-1];
    }

    return dp;
  }
}

/*
Reference
https://m.blog.naver.com/PostView.nhn?blogId=occidere&logNo=220808155184&proxyReferer=https:%2F%2Fwww.google.com%2F
 */