package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PermutationCycle_10451 {
  static int[] parents;
  static boolean[] visit;

  public static void main(String[] args) throws IOException {
    final String NEW_LINE = "\n";
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int testCases = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();

    while(testCases-- >0) {
      int n = Integer.parseInt(br.readLine());
      init(n, br.readLine());
      sb.append(solution(n)).append(NEW_LINE);
    }

    br.close();
    System.out.println(sb.toString());
  }

  private static int solution(int n) {
    int answer =0;
    for(int i=1; i<=n; i++) {
      if(!visit[i]) {
        if(visit[parents[i]]) {
          visit[i] = true;
        } else {
          if (isCycle(i)) {
            answer++;
          }
        }
      }
    }
    return answer;
  }

  private static void init(int n, String graphStr) {
    StringTokenizer st = new StringTokenizer(graphStr);
    parents = new int[n+1];
    for(int i=1; i<=n; i++) {
      parents[i] = Integer.parseInt(st.nextToken());
    }
    visit = new boolean[n+1];
  }

  private static boolean isCycle(int val) {
    if(!visit[val]) {
      visit[val] = true;
      return visit[parents[val]] ? true : isCycle(parents[val]);
    }
    return false;
  }
}
/*
1
5
2 3 4 1 4
 */