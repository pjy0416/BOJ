package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MinSpanningTree_1197{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        ArrayList<Node>[] edgeList = new ArrayList[v+1];
        for(int i=0; i<=v; i++) {
            edgeList[i] = new ArrayList<>();
        }

        while(e >0) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList[left].add(new Node(right, weight));
            edgeList[right].add(new Node(left,weight));

            e--;
        }

        solution(v, edgeList);
    }

    private static void solution(int v, ArrayList<Node>[] edgeList) {
        int answer =0, remain =v;
        boolean[] visit = new boolean[v+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1,0));

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(!visit[now.vertex]) {
                visit[now.vertex] = true;
                answer += now.weight;
                remain--;
                ArrayList<Node> nextNodes = edgeList[now.vertex];
                for(Node next : nextNodes) {
                    if(!visit[next.vertex]) {
                        pq.offer(next);
                    }
                }
            }
            if(remain == 0) {
                break;
            }
        }
        System.out.println(answer);
    }

    private static class Node implements Comparable<Node> {
        int vertex, weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node tree) {
            return this.weight - tree.weight;
        }
    }
}

/* Minimum Spanning Tree (MST, 최소 스패닝 트리 === 최소 신장 트리)
 * 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 그 가중치의 합이 최소인 트리
 *
 */

/*
6 6
1 2 1
1 3 6
2 3 2
3 4 4
3 5 5
4 5 3
ans => 10
 */
