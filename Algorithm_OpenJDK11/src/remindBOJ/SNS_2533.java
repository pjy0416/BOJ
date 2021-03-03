package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SNS_2533 {
  static final int LENGTH =2, EARLY_ADAPTOR =1, LATE_ADAPTOR =0; // dp의 길이, 얼리 어답터 인덱스, 레이트 어답터 인덱스
  static int[][] dp; // 자신이 얼리 어답터일 때와 레이트 어답터일 때의 값을 저장
  static boolean[] visit; // 방문 체크
  static List<Integer>[] graph; // 연결 그래프

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    init(n); // init graph, dp, visit

    for(int i=1; i<n; i++) { // set graph
      StringTokenizer st = new StringTokenizer(br.readLine());
      int left = Integer.parseInt(st.nextToken()), right = Integer.parseInt(st.nextToken());
      graph[left].add(right);
      graph[right].add(left);
    }

    br.close();
    solution();
  }

  private static void init(int n) {
    graph = new ArrayList[n+1];
    for(int i=1; i<=n; i++) {
      graph[i] = new ArrayList<>();
    }
    dp = new int[n+1][LENGTH];
    visit = new boolean[n+1];
  }

  private static void solution() {
    final int STARTING_INDEX =1;
    find(STARTING_INDEX);
    System.out.println(Math.min(dp[STARTING_INDEX][EARLY_ADAPTOR], dp[STARTING_INDEX][LATE_ADAPTOR]));
  }

  private static void find(int idx) {
    visit[idx] = true;
    dp[idx][LATE_ADAPTOR] = LATE_ADAPTOR;
    dp[idx][EARLY_ADAPTOR] = EARLY_ADAPTOR;
    for(int next : graph[idx]) {
      if(visit[next]) {
        continue;
      }
      find(next);
      dp[idx][LATE_ADAPTOR] += dp[next][EARLY_ADAPTOR]; // 자신이 레이트 어답터면, 친구들 모두 얼리 어답터.
      dp[idx][EARLY_ADAPTOR] += Math.min(dp[next][LATE_ADAPTOR], dp[next][EARLY_ADAPTOR]); // 자신이 얼리 어답터면 뭐든 OK
    }
  }
}

/*
Reference
https://comyoung.tistory.com/41
 */