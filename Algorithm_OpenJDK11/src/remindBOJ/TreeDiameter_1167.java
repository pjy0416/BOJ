package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TreeDiameter_1167 {
    static ArrayList<Node>[] tree;
    static int answer, maxIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int v = Integer.parseInt(br.readLine());
        final int END = -1;
        initTree(v);

        for(int i=0; i<v; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            while(st.hasMoreTokens()) {
                int right = Integer.parseInt(st.nextToken());
                if(right == END) {
                    break;
                }
                int weight = Integer.parseInt(st.nextToken());
                tree[left].add(new Node(right, weight));
            }
        }

        br.close();
        solution(v);
    }

    private static void initTree(int v) {
        tree = new ArrayList[v+1];
        for(int i=1; i<=v; i++) {
            tree[i] = new ArrayList<>();
        }
    }

    private static void solution(int v) {
        boolean[] visit = new boolean[v+1];
        visit[1] = true;
        dfs(1, visit, 0);
        visit[1] = false;
        visit[maxIdx] = true;
        dfs(maxIdx, visit, 0);

        System.out.println(answer);
    }

    private static void dfs(int idx, boolean[] visit, int weight) {
        for(Node node : tree[idx]) {
            if(!visit[node.vertex]) {
                visit[node.vertex] = true;
                dfs(node.vertex, visit, weight + node.weight);
                visit[node.vertex] = false;
            }
        }
        if(weight > answer) {
            answer = weight;
            maxIdx = idx;
        }
    }


    private static class Node {
        int vertex, weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}
