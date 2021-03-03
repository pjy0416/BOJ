package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class History_1613 {
  static final String FRONT = "-1\n", BACK = "1\n", NO_IDEA = "0\n";

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
    boolean[][] map = new boolean[n+1][n+1];
    while(k-- >0) {
      st = new StringTokenizer(br.readLine());
      int prev = Integer.parseInt(st.nextToken()), follow = Integer.parseInt(st.nextToken());
      map[prev][follow] = true; // 앞이 뒤보다 먼저 일어났다
    }
    floydWarshall(n,map);
    
    int s = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();

    while(s-- >0) {
      st = new StringTokenizer(br.readLine());
      sb.append(solution(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), map));
    }

    br.close();
    System.out.println(sb.toString());
  }

  private static String solution(int left, int right, boolean[][] map) {
    if(map[left][right]) {
      return FRONT;
    } else if(map[right][left]) {
      return BACK;
    }
    return NO_IDEA;
  }

  private static void floydWarshall(int n, boolean[][] map) {
    for(int k=1; k<=n; k++) {
      for(int i=1; i<=n; i++) {
        for(int j=1; j<=n; j++) {
          if(i != j && map[i][k] && map[k][j]) { // i가 k보다 먼저 일어났고, k가 j보다 먼저 일어났으면
            map[i][j] = true; // i는 j보다 먼저 일어남
          }
        }
      }
    }
  }
}
