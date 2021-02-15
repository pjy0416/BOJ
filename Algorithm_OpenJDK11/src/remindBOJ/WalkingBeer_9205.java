package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class WalkingBeer_9205 {
  static final String SUCCESS = "happy", FAIL = "sad", NEW_LINE = "\n";
  static final int HOUSE = 0, MAX_DIST = 1000;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();

    while(t-- >0) {
      int n = parseInt(br.readLine())+2;
      Pos[] spots = new Pos[n];

      for(int i=0; i<n; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        spots[i] = new Pos(tokenParseInt(st), tokenParseInt(st));
      }

      sb.append(solution(n, spots)).append(NEW_LINE);
    }
    br.close();
    System.out.println(sb.toString());
  }

  private static String solution(int n, Pos[] spots) {
    // 0 : 집, 1 ~ n-2 편의점, n-1 : 페스티벌 장소
    boolean[][] connection = initConnection(n,spots);
    for(int stopOver=0; stopOver<n; stopOver++) {
      for(int start=0; start<n; start++) {
        for(int dest=0; dest<n; dest++) {
          if(start != dest && connection[start][stopOver] && connection[stopOver][dest]) {
            connection[start][dest] = true;
          }
        }
      }
    }

    return connection[HOUSE][n-1] ? SUCCESS : FAIL;
  }

  private static boolean[][] initConnection(int n, Pos[] spots) {
    boolean[][] connection = new boolean[n][n];
    for(int i=0; i<n; i++) {
      for(int j=i+1; j<n; j++) {
        if(i!=j && isNearBy(spots[i], spots[j])) {
          connection[i][j] = true;
          connection[j][i] = true;
        }
      }
    }
    return connection;
  }

  private static boolean isNearBy(Pos start, Pos dest) {
    return Math.abs(start.x - dest.x) + Math.abs(start.y - dest.y) <= MAX_DIST;
  }

  private static int tokenParseInt(StringTokenizer st) {
    return parseInt(st.nextToken());
  }

  private static int parseInt(String str) {
    return Integer.parseInt(str);
  }

  private static class Pos {
    int x, y;

    public Pos(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
