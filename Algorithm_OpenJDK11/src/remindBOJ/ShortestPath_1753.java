package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ShortestPath_1753 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        List<Node>[] edgeList = new ArrayList[v+1];
        for(int i=1; i<=v; i++) {
            edgeList[i] = new ArrayList<>();
        }

        for(int i=0; i<e; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList[left].add(new Node(right, weight));
        }

        br.close();
        solution(start, v, edgeList);
    }

    private static void solution(int start, int v, List<Node>[] edgeMap) {
        int[] distArr = getDistArray(start, v, edgeMap);
        printAnswer(v, distArr);
    }

    private static int[] getDistArray(int start, int v, List<Node>[] edgeMap) {
        int[] distArr = initDistArray(v, start);
        boolean[] isVisit = new boolean[v+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(!isVisit[now.vertex]) {
                isVisit[now.vertex] = true;

                for(Node next : edgeMap[now.vertex]) {
                    if(distArr[next.vertex] > distArr[now.vertex] + next.weight) {
                        distArr[next.vertex] = distArr[now.vertex] + next.weight;
                        pq.add(new Node(next.vertex, distArr[next.vertex]));
                    }
                }
            }
        }
        return distArr;
    }

    private static int[] initDistArray(int v, int start) {
        int[] answer = new int[v+1];
        Arrays.fill(answer, Integer.MAX_VALUE);
        answer[start] =0;

        return answer;
    }

    private static void printAnswer(int v, int[] distArr) {
        final String NO_PATH_STRING = "INF", NEW_LINE = "\n";
        StringBuilder sb = new StringBuilder();

        for(int i=1; i<=v; i++) {
            if(distArr[i] == Integer.MAX_VALUE) {
                sb.append(NO_PATH_STRING);
            } else  {
                sb.append(distArr[i]);
            }
            sb.append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static class Node implements Comparable<Node>{
        int vertex, weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            return this.weight - node.weight;
        }
    }
}
