package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App_7579 {
  static final int MAX = 10000;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
    // n => 앱 개수 1 ~ 100, m => 메모리 1 ~ 10000000
    App[] app = new App[n];
    st = new StringTokenizer(br.readLine());

    for(int i=0; i<n; i++) {
      app[i] = new App(Integer.parseInt(st.nextToken()));
    }

    st = new StringTokenizer(br.readLine());
    for(int i=0; i<n; i++) {
      app[i].cost = Integer.parseInt(st.nextToken());
    }

    br.close();
    solution(n,m,app);
  }

  private static void solution(int n, int m,  App[] app) {
    int[] dp = initDP(n,app);

    int answer =0;
    for(int i=0; i<=MAX; i++) {
      if(dp[i] >= m) {
        answer = i;
        break;
      }
    }
    System.out.println(answer);
  }

  private static int[] initDP(int n, App[] app) {
    final int DEFAULT = 0;
    int[] dp = new int[MAX+1]; // m을 저장 m * cost 최대 => 10000, 인덱스는 cost
    dp[app[0].cost] = app[0].runningMemory;

    for(int i=1; i<n; i++) { // 100
      App now = app[i];
      for(int j=MAX-now.cost; j>=DEFAULT; j--) {
        if(dp[j] != DEFAULT) {
          dp[j + now.cost] = Math.max(dp[j + now.cost], dp[j] + now.runningMemory);
        }
      }
      dp[now.cost] = Math.max(dp[now.cost], now.runningMemory);
    }
    return dp;
  }

  private static class App {
    int runningMemory, cost;

    public App(int runningMemory) {
      this.runningMemory = runningMemory;
    }
  }
}
