package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ConnectNetwork_1922 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        PriorityQueue<Network> pq = new PriorityQueue<>();

        while(m >0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.offer(new Network(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
            m--;
        }
        br.close();
        solution(n,pq);
    }

    private static void solution(int n, PriorityQueue<Network> pq) {
        int answer =0, remain =n;
        initParent(n);

        while(!pq.isEmpty()) {
            Network network = pq.poll();
            if(find(network.left) != find(network.right)) {
                answer+= network.weight;
                union(network.left, network.right);
                if(--remain ==0) {
                    return;
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

    private static class Network implements Comparable<Network> {
        int left, right, weight;

        public Network(int left, int right, int weight) {
            this.left = left;
            this.right = right;
            this.weight = weight;
        }

        @Override
        public int compareTo(Network network) {
            return this.weight - network.weight;
        }
    }
}
