package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PoliceCar_2618 {
  static final String NEW_LINE = "\n", A = "1", B = "2";

  static Pos[] policeA, policeB;
  static int[][] dp;
  static int w;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    w = Integer.parseInt(br.readLine());
    init(n);

    for(int i=1; i<=w; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int y = Integer.parseInt(st.nextToken()), x = Integer.parseInt(st.nextToken());
      policeA[i] = policeB[i] = new Pos(x,y);
    }

    br.close();
    solution();
  }

  private static void init(int n) {
    policeA = new Pos[w+1];
    policeB = new Pos[w+1];
    policeA[0] = new Pos(1,1);
    policeB[0] = new Pos(n,n);

    dp = new int[w+1][w+1];
    for(int i=0; i<=w; i++) {
      Arrays.fill(dp[i], Integer.MIN_VALUE);
    }
  }

  private static void solution() {
    sb.append(getMaxDist(0,0)).append(NEW_LINE);
    find(0,0);
    System.out.println(sb.toString());
  }

  private static int getMaxDist(int a, int b) {
    if(a == w || b == w) { // 모든 사건 종료
      return 0;
    }
    if(dp[a][b] != Integer.MIN_VALUE) {
      return dp[a][b];
    }

    int next = Math.max(a,b) +1;
    int distA = getMaxDist(next, b) + getDist(policeA[a], policeA[next]); // 현재 위치에서 A가 사건 해결
    int distB = getMaxDist(a, next) + getDist(policeB[b], policeB[next]); // 현재 위치에서 B가 사건 해결

    return dp[a][b] = Math.min(distA, distB);
  }

  private static int getDist(Pos now, Pos next) {
    return Math.abs(next.x-now.x) + Math.abs(next.y-now.y);
  }

  private static void find(int a, int b) {
    if(a == w || b == w) {
      return;
    }
    int next = Math.max(a,b) +1;
    int distA = getMaxDist(next, b) + getDist(policeA[a], policeA[next]); // 현재 위치에서 A가 사건 해결
    int distB = getMaxDist(a, next) + getDist(policeB[b], policeB[next]); // 현재 위치에서 B가 사건 해결

    if(distA < distB) { // B가 더 먼 거리
      sb.append(A).append(NEW_LINE);
      find(next, b);
    } else {
      sb.append(B).append(NEW_LINE);
      find(a, next);
    }
  }

  private static class Pos {
    int x, y;

    public Pos(int x, int y) {
      this.x =x;
      this.y =y;
    }
  }
}

/*
Reference
https://injae-kim.github.io/problem_solving/2020/03/11/baekjoon-2618.html
 */