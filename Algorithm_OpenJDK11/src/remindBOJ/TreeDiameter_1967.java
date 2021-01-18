package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class TreeDiameter_1967 {
    static ArrayList<Node>[] tree;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        initTree(n);
        for(int i=1; i<n; i++) { // n-1번 간선 정보
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            tree[parent].add(new Node(child, weight));
        }

        br.close();
        solution(n);
    }

    private static void solution(int n) {
        int answer =0;
        initDP(n);
        PriorityQueue<Integer> pq;

        for(int i=1; i<=n; i++) {
            int weight = 0;
            pq = new PriorityQueue<>(Collections.reverseOrder());
            for(Node child : tree[i]) { // 이진트리가 아닐 수 있음
                pq.offer(dp[child.vertex] + child.weight);  // child가 가진 최대 거리 + child와 연결되는 거리
            }
            for(int j=0; j<2; j++) {
                if(!pq.isEmpty()) {
                    weight += pq.poll();
                }
            }
            answer = Math.max(answer, weight);
        }
        System.out.println(answer);
    }

    private static void initDP(int n) {
        dp = new int[n+1];
        final int ZERO =0;
        for(int i=1; i<=n; i++) {
            if(dp[i] == ZERO) { // ZERO가 아닌 경우는 이미 탐색해서 저장됐기 때문에 의미 X
                find(i);
            }
        }
    }

    private static void find(int parent) {
        int max =0;
        for(Node node : tree[parent]) {
            if(dp[node.vertex] == 0) { // 자식 node에 저장된 값이 없다면 다시 진행
                find(node.vertex);
            }
            max = Math.max(dp[node.vertex] + node.weight, max); // 자식의 max 거리와 자식의 거리를 더한 값
        }
        dp[parent] = Math.max(dp[parent],max); // dp 정보 수정
    }

    private static ArrayList[] initTree(int n) {
        tree = new ArrayList[n+1];
        for(int i=1; i<=n; i++) {
            tree[i] = new ArrayList<>();
        }
        return tree;
    }

    private static class Node {
        int vertex, weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}
/*
5
1 2 33
2 3 34
1 4 22
1 5 10
 */