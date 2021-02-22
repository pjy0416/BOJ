package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 지금 코드 : Kruskal 알고리즘
 * 이전 코드 : Prim 알고리즘ㅎ
 */

public class CityPartitionPlan_1647 {
  static int[] parents;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
    PriorityQueue<Edge> edgePQ = new PriorityQueue<>();

    while(m-- >0) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken()), cost = Integer.parseInt(st.nextToken());
      edgePQ.offer(new Edge(start, end, cost));
    }

    br.close();
    solution(edgePQ,n);
  }

  private static void solution(PriorityQueue<Edge> edgePQ, int n) {
    initParents(n);
    int remain = n-2; // 1번과 모두 연결되는 경로 중 가장 큰 경로 제외
    int answer =0;

    while(remain >0) {
      Edge edge = edgePQ.poll();
      if(union(edge.start, edge.end)) {
        answer+= edge.cost;
        remain--;
      }
    }
    System.out.println(answer);
  }

  private static void initParents(int n) {
    parents = new int[n+1];
    for(int i=1; i<=n; i++) {
      parents[i] = i;
    }
  }

  private static int find(int x) {
    if(parents[x] == x) {
      return x;
    }
    return parents[x] = find(parents[x]);
  }

  private static boolean union(int val1, int val2) {
    val1 = find(val1);
    val2 = find(val2);
    if(val1 == val2) {
      return false;
    }
    parents[val1] = val2;
    return true;
  }

  private static class Edge implements Comparable<Edge> {
    int start, end, cost;

    public Edge(int start, int end, int cost) {
      this.start = start;
      this.end = end;
      this.cost = cost;
    }

    @Override
    public int compareTo(Edge edge) {
      return this.cost - edge.cost;
    }
  }
}
