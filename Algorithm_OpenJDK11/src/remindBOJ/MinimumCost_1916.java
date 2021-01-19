package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MinimumCost_1916 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] graph = new int[n+1][n+1];

        for(int i=1; i<=n; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
        }

        int m = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while(m-- >0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start][end] = Math.min(cost, graph[start][end]);
        }

        st = new StringTokenizer(br.readLine());
        br.close();

        solution(graph, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

    }

    private static void solution(int[][] graph, int start, int end) {
        int len = graph.length;
        int[] costArr = new int[len];
        Arrays.fill(costArr, Integer.MAX_VALUE);
        costArr[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            for(int i=1; i<len; i++) {
                if(graph[now.vertex][i] != Integer.MAX_VALUE) {
                    if(costArr[i] > costArr[now.vertex] + graph[now.vertex][i]) {
                        costArr[i] = costArr[now.vertex] + graph[now.vertex][i];
                        pq.offer(new Node(i, costArr[i]));
                    }
                }
            }
        }
        System.out.println(costArr[end]);
    }

    private static class Node implements Comparable<Node>{
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
/*
7
12
1 2 7
1 5 3
1 6 10
5 2 2
2 6 6
2 3 4
2 4 10
5 7 5
5 4 11
3 4 2
6 4 9
7 4 4

1 4 -> 11
1 2 -> 5
1 3 -> 9

3
4
1 2 3
1 2 10
1 3 1
2 3 1

1 2 -> 3
1 3 -> 1
 */