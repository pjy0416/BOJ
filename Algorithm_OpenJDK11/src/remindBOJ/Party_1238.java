package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Party_1238 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[][] graph = new int[n+1][n+1];
        for(int i=1; i<=n; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
        }
        while(m-- >0) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[left][right] = Math.min(graph[left][right], cost);
        }

        br.close();
        solution(graph, n, x);
    }

    private static void solution(int[][] graph, int n, int dest) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int answer =0;

        for(int i=1; i<=n; i++) {
            if(i != dest) {
                pq.offer(new Node(i, 0));
                int go = dijkstra(graph, n, pq, dest);
                pq.poll();
                pq.offer(new Node(dest, 0));
                int back = dijkstra(graph, n, pq, i);
                pq.poll();
                answer = Math.max(answer, go+back);
            }
        }
        System.out.println(answer);
    }

    private static int dijkstra(int[][] graph, int n, PriorityQueue<Node> pq, int dest) {
        int[] costArr = new int[n+1];
        Arrays.fill(costArr, Integer.MAX_VALUE);
        costArr[pq.peek().vertex] =0;

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            for(int i=1; i<=n; i++) {
                if(graph[now.vertex][i] != Integer.MAX_VALUE) {
                    if (costArr[i] > costArr[now.vertex] + graph[now.vertex][i]) {
                        costArr[i] = costArr[now.vertex] + graph[now.vertex][i];
                        pq.offer(new Node(i, costArr[i]));
                    }
                }
            }
        }
        return costArr[dest];
    }

    private static class Node implements Comparable<Node> {
        int vertex, cost;

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return this.cost - node.cost;
        }
    }
}
