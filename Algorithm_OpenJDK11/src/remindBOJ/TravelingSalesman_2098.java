package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TravelingSalesman_2098 {
  static final int MAX = 16 * 1000000 +1; // |v - 1| * maxLength

  static int n, ALL_VISIT;
  static int[][] map, dp;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    map = new int[n][n];

    for(int i=0; i<n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int j=0; j<n; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    br.close();
    solution();
  }

  private static void solution() {
    ALL_VISIT = (1 << n) -1;
    dp = new int[n][ALL_VISIT];
    for(int i=0; i<n; i++) {
      Arrays.fill(dp[i], MAX);
    }

    int answer = tsp(1,0);
    System.out.println(answer);
  }

  private static int tsp(int visit, int now) {
    if (visit == ALL_VISIT) { //모든 도시를 지난 경우 ( 1 + ... + n )
      return map[now][0] ==0 ? MAX : map[now][0]; //여행비용 반환
    }
    
    if (dp[now][visit] != MAX) { // 메모이제이션
      return dp[now][visit];
    }

    for (int i = 0; i < n; i++) {
      //now -> 아직 방문하지 않은 i번 도시 가는 경로가 있는 경우
      if (map[now][i] > 0 && (visit & (1<<i)) == 0) {
        int cost = tsp(visit | (1 << i), i) + map[now][i]; //최소 비용 갱신
        dp[now][visit] = Math.min(dp[now][visit], cost);
      }
    }
    return dp[now][visit];
  }
}
