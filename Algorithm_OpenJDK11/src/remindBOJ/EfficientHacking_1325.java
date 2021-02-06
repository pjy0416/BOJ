package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class EfficientHacking_1325 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
    List<Integer>[] connections = initConnections(n);

    while(m-- >0) {
      st = new StringTokenizer(br.readLine());
      int right = Integer.parseInt(st.nextToken()), left = Integer.parseInt(st.nextToken());
      connections[left].add(right);
    }

    br.close();
    solution(n, connections);
  }

  private static void solution(int n, List<Integer>[] connections) {
    int[] counts = getCounts(n, connections);
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

  private static int[] getCounts(int n, List<Integer>[] connections) {
    int[] counts = new int[n+1];
    for(int i=1; i<=n; i++) {
      counts[i] = getConnectionCount(i, n, connections);
    }
    return counts;
  }

  private static int getConnectionCount(int start, int n, List<Integer>[] connections) {
    boolean[] visit = new boolean[n+1];
    visit[start] = true;
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(start);
    int count=0;

    while(!queue.isEmpty()) {
      int now = queue.poll();

      for(int next : connections[now]) {
        if(!visit[next]) {
          visit[next] = true;
          queue.offer(next);
          count++;
        }
      }
    }
    return count;
  }

  private static List<Integer>[] initConnections(int n) {
    List<Integer>[] connections = new ArrayList[n+1];
    for(int i=1; i<=n; i++) {
      connections[i] = new ArrayList<>();
    }
    return connections;
  }
}
