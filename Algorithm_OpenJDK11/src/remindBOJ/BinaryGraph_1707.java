package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// grouping으로 풀자.. ( 다른 사람들은 그래프를 색칠해서 구했다고 함)
public class BinaryGraph_1707 {
  static final String SUCCESS = "YES", FAIL = "NO", NEW_LINE = "\n";
  static final int ZERO =0;

  static List<Integer>[] graph;
  static int[] groups;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();

    while(t-- >ZERO) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());
      initGraph(n);
      while(e-- >ZERO) {
        st = new StringTokenizer(br.readLine());
        int left = Integer.parseInt(st.nextToken());
        int right = Integer.parseInt(st.nextToken());
        graph[left].add(right);
        graph[right].add(left);
      }
      sb.append(solution(n)).append(NEW_LINE);
    }
    br.close();

    System.out.println(sb.toString());
  }

  private static void initGraph(int n) {
    graph = new ArrayList[n+1];
    groups = new int[n+1];
    for(int i=1; i<=n; i++) {
      graph[i] = new ArrayList<>();
    }
  }

  private static String solution(int n) {
    for(int i=1; i<=n; i++) {
      if(groups[i] == ZERO) { // grouping이 안된 (색칠이 안된) node grouping 
        grouping(i, 1);
      }
    }
    return isBinaryGraph(n) ? SUCCESS : FAIL;
  }

  private static void grouping(int idx, int groupIdx) {
    List<Integer> children = graph[idx];
    groups[idx] = groupIdx;

    for(int child : children) {
      if(groups[child] == ZERO) {
        grouping(child, 3-groupIdx); // group 1, group 2 반복해서 색칠 
      }
    }
  }

  private static boolean isBinaryGraph(int n) {
    for(int i =1; i<=n; i++) {
      List<Integer> children = graph[i];
      for(int child : children) {
        if(groups[i] == groups[child]) {
          return false;
        }
      }
    }
    return true;
  }
}
/*
1
5 4
1 2
3 4
3 5
4 5
NO

1
4 2
1 2
3 4
YES

1
6 6
1 3
3 4
4 2
2 5
5 6
6 1
NO

2
3 2
3 1
3 2
3 2
1 3
2 3
YES
YES

1
3 1
1 2
YES

1
5 2
1 2
2 3
NO

1
6 6
1 2
2 3
3 1
4 5
5 6
6 4
YES

1
6 4
1 2
3 4
3 5
4 6
 */