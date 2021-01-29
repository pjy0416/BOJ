package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MinimumRoute_1504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n+1][n+1];

        while(e-- >0) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken()), right = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[left][right] = weight;
            graph[right][left] = weight;
        }

        st = new StringTokenizer(br.readLine());
        int[] necessary = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

        br.close();
        solution(n, graph, necessary);
    }

    private static void solution(int n, int[][] graph, int[] necessary) {
        final int start = 1;
        long max = Integer.MAX_VALUE *3 -1;
        long firstCase = dijkstra(start,necessary[0],n,graph) + dijkstra(necessary[0],necessary[1],n,graph) + dijkstra(necessary[1],n,n,graph);
        long secondCase = dijkstra(start,necessary[1],n,graph) + dijkstra(necessary[1],necessary[0],n,graph) + dijkstra(necessary[0],n,n,graph);
        long answer = Math.min(firstCase, secondCase);
        
        System.out.println(answer > max ? -1 : answer);
    }

    private static long dijkstra(int start, int dest, int n, int[][] graph) {
        long[] dp = initWeights(n, start);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            for(int i=1; i<=n; i++) {
                if(graph[now.vertex][i] !=0 && dp[i] > dp[now.vertex] + graph[now.vertex][i]) {
                    dp[i] = dp[now.vertex] + graph[now.vertex][i];
                    pq.offer(new Node(i, dp[i]));
                }
            }
        }
        return dp[dest];
    }

    private static long[] initWeights(int n, int start) {
        long[] dp = new long[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[start] =0;
        return dp;
    }

    private static class Node implements Comparable<Node> {
        int vertex;
        long weight;

        public Node(int vertex, long weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            return Long.compare(this.weight, node.weight);
        }
    }
}
