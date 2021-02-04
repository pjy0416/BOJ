package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GridPath_10164 {
  static final int[] dx = {0,-1}, dy = {-1,0};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), mark = Integer.parseInt(st.nextToken());

    br.close();
    solution(n,m,mark);
  }

  private static void solution(int n, int m, int mark) {
    int[][] dp = new int[n+1][m+1];
    dp[0][1] =1;
    int[] markPos = getMarkedPosition(n,m,mark);

    setPathCount(dp, 1,1, markPos[0], markPos[1]);
    setPathCount(dp, markPos[0], markPos[1], n, m);
    System.out.println(dp[n][m]/2);
  }

  private static int[] getMarkedPosition(int n, int m, int mark) {
    int divN = mark/m, divM = mark%m;
    if(isNoMarked(divN, divM)) {
      divM++;
    }
    if(divM ==0) {
      divM = m;
    } else {
      divN++;
    }
    return new int[] {divN, divM};
  }

  private static boolean isNoMarked(int divN, int divM) {
    return divN ==0 && divM ==0;
  }

  private static void setPathCount(int[][] dp, int startY, int startX, int n, int m) {
    for(int y=startY; y<=n; y++) {
      for(int x=startX; x<=m; x++) {
        for(int i=0; i<2; i++) {
          int nx = x + dx[i];
          int ny = y + dy[i];
          dp[y][x] += dp[ny][nx];
        }
      }
    }
  }
}
