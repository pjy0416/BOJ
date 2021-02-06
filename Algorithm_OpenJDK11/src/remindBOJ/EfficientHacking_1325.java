package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
  DFS와 BFS 모두 시간 복잡도가 좋지 못함
  메모이제이션을 더 활용하면 좋을 것 같음
 */
public class EfficientHacking_1325 {
  static List<Integer>[] connections;
  static int n;
  static boolean[] visit;

  static final int ZERO =0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    initConnections();

    while(m-- >ZERO) {
      st = new StringTokenizer(br.readLine());
      int right = Integer.parseInt(st.nextToken()), left = Integer.parseInt(st.nextToken());
      connections[left].add(right);
    }

    br.close();
    solution();
  }

  private static void solution() {
    int[] counts = new int[n+1];
    for(int i=1; i<=n; i++) {
      visit = new boolean[n+1];
      counts[i] = setCounts(i);
    }

    Queue<Integer> answerQueue = new LinkedList<>();
    int max=0;
    for(int i=1; i<=n; i++) {
      if(max < counts[i]) {
        answerQueue.clear();
        max = counts[i];
        answerQueue.offer(i);
      } else if(max == counts[i]) {
        answerQueue.offer(i);
      }
    }

    StringBuilder sb = new StringBuilder();
    final char SPACE =' ';
    while(!answerQueue.isEmpty()) {
      sb.append(answerQueue.poll()).append(SPACE);
    }
    System.out.println(sb.toString());
  }

  private static int setCounts(int now) {
    visit[now] = true;
    int count =1;
    for(int next : connections[now]) {
      if(!visit[next]) {
        count += setCounts(next);
      }
    }
    return count;
  }

  private static void initConnections() {
    connections = new ArrayList[n+1];
    for(int i=1; i<=n; i++) {
      connections[i] = new ArrayList<>();
    }
  }
}
