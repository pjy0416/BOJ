package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MinSpanningTreeKruskal_1197 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        while(e >0) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new Edge(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
            e--;
        }

        solution(v, pq);
    }

    private static void solution(int v, PriorityQueue<Edge> pq) {
        int answer =0, remain =v;
        initParent(v+1);

        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            if(find(edge.left) != find(edge.right)) {
                answer += edge.weight;
                union(edge.left, edge.right);
                if(--remain ==0) {
                    break;
                }
            }
        }
        System.out.println(answer);
    }

    private static void initParent(int n) {
        parent = new int[n+1];
        for(int i=1; i<=n; i++) {
            parent[i] = i;
        }
    }

    private static int find(int index) {
        if(index == parent[index]) {
            return index;
        }
        return parent[index] = find(parent[index]);
    }

    private static void union(int left, int right) {
        left = find(left);
        right = find(right);
        if(left != right) {
            parent[right] = left;
        }
    }

    private static class Edge implements Comparable<Edge>{
        int left, right, weight;

        public Edge(int left, int right, int weight) {
            this.left = left;
            this.right = right;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.weight - edge.weight;
        }
    }
}
