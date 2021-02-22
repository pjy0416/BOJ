package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CityPartitionPlan_1647 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
    List<Route>[] routes = new ArrayList[n+1];
    for(int i=1; i<=n; i++) {
      routes[i] = new ArrayList<>();
    }

    while(m-- >0) {
      st = new StringTokenizer(br.readLine());
      int left = Integer.parseInt(st.nextToken()), right = Integer.parseInt(st.nextToken()), cost = Integer.parseInt(st.nextToken());
      routes[left].add(new Route(right,cost));
      routes[right].add(new Route(left, cost));
    }

    br.close();
    solution(routes,n);
  }

  private static void solution(List<Route>[] routes, int n) {
    int answer = 0, max =0;
    PriorityQueue<Route> pq = new PriorityQueue<>();
    boolean[] visit = new boolean[n+1];
    for(Route route : routes[1]) {
      pq.offer(route);
    }
    visit[1] = true;

    while(!pq.isEmpty()) {
      Route now = pq.poll();
      if(visit[now.vertex]) {
        continue;
      }
      answer += now.cost;
      max = Math.max(now.cost, max);
      visit[now.vertex] = true;
      List<Route> nextCities = routes[now.vertex];
      for(Route next : nextCities) {
        if(!visit[next.vertex]) { // 이동
          pq.offer(next);
        }
      }
    }
    System.out.println(answer-max);
  }

  private static class Route implements Comparable<Route> {
    int vertex, cost;

    public Route(int vertex, int cost) {
      this.vertex = vertex;
      this.cost = cost;
    }

    @Override
    public int compareTo(Route route) {
      return this.cost - route.cost;
    }
  }
}
