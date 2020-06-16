package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HighWay_1884 {
    public static void main(String[] args) throws IOException { // 메모리초과..
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int budget = Integer.parseInt(br.readLine());
        int home = Integer.parseInt(br.readLine());
        int roads = Integer.parseInt(br.readLine());

        int[][] costArr = new int[home+1][home+1];
        int[][] distArr = new int[home+1][home+1];
        for(int i=1; i<=home; i++) {  // 0 : cost, 1 : dist
            Arrays.fill(costArr[i],-1); // cost만 초기화해줘도 괜찮음.
        }

        for(int i=0; i<roads; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());

            distArr[start][dest] = Integer.parseInt(st.nextToken());    // 거리
            costArr[start][dest] = Integer.parseInt(st.nextToken());    // 통행료
        }

        System.out.println(solution(budget, home, distArr, costArr));

        br.close();
    }

    private static int solution(int budget, int home, int[][] distArr, int[][] costArr) {
        int answer =Integer.MAX_VALUE;
        PriorityQueue<Route_1884> moveList = new PriorityQueue<>();
        moveList.offer(new Route_1884(1,0,0));

        while(!moveList.isEmpty()) {
            Route_1884 route = moveList.poll();
            if(route.city == home) {
                if(route.cost <= budget) {
                    answer = Math.min(answer, route.dist);
                }
            } else {
                if (route.cost <= budget) {
                    for (int i = 1; i <= home; i++) {
                        if (costArr[route.city][i] != -1) {
                            int city = i;
                            int cost = route.cost + costArr[route.city][i];
                            int dist = route.dist + distArr[route.city][i];
                            moveList.offer(new Route_1884(city, cost, dist));
                        }
                    }
                }else {
                }
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}
class Route_1884 implements Comparable<Route_1884>{
    int city;
    int cost;
    int dist;

    public Route_1884(int city, int cost, int dist) {
        this.city = city;
        this.cost = cost;
        this.dist = dist;
    }

    @Override
    public int compareTo(Route_1884 r) {
        if(this.dist < r.dist) {
            return 1;
        } else if(this.dist == r.dist) {
            return this.cost < r.cost? 1 : -1;
        }
        return -1;
    }
}