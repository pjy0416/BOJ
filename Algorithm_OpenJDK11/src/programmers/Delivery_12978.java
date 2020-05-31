package programmers;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Delivery_12978 {
    final static int ROOT = 1, MAX = 500001;
    private static int solution(int N, int[][] road, int K) {
        int answer = 0;
        int[] costArr = inItCost(N);
        int[][] edge = getEdge(road, N);
        return getAnswer(costArr, edge, N, K);
    }

    private static int[] inItCost(int n) {
        int[] result = new int[n+1];
        Arrays.fill(result,MAX);
        result[1] = 0;
        return result;
    }

    private static int[][] getEdge(int[][] road, int n) {
        int[][] result = new int[n+1][n+1];
        for(int i=1; i<=n; i++) {
            Arrays.fill(result[i], MAX);
        }
        for(int[] route : road) {
            int cost = Math.min(result[route[0]][route[1]], route[2]);
            result[route[0]][route[1]] = result[route[1]][route[0]] = cost;
        }
        return result;
    }

    private static int[] dijkstra(int[] costArr, int[][] edge, int N) {
        PriorityQueue<Road> roadQ = new PriorityQueue<>();
        roadQ.offer(new Road(1,0));

        while(!roadQ.isEmpty()) {
            Road route = roadQ.poll();
            for(int i=1; i<=N; i++) {
                if(edge[route.city][i] != MAX) {    // city랑 연결된 곳
                    if(costArr[i] > costArr[route.city] + edge[route.city][i]) {
                        costArr[i] = costArr[route.city] + edge[route.city][i];
                        roadQ.offer(new Road(i, costArr[i]));
                    }
                }
            }
        }
        return costArr;
    }
    private static int getAnswer(int[] costArr, int[][] edge, int N, int K) {
        costArr = dijkstra(costArr, edge, N);
        int result =0;
        for(int i=1; i<=N; i++) {
            if(costArr[i] <=K) {
                result++;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int N =5;
        int[][] road = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
        int K = 3;
        System.out.println(solution(N,road,K));
    }
}

class Road implements Comparable<Road>{
    int city;
    int cost;

    public Road(int city, int cost) {
        this.city = city;
        this.cost = cost;
    }

    @Override
    public int compareTo(Road r) {
        return this.cost > r.cost ? 1:-1;
    }
}
/*
https://programmers.co.kr/learn/courses/30/lessons/12978#
 */