package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class TakeALine_2252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] edgeArr = new int[n+1];

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            graph.get(left).add(right);
            edgeArr[right]++;
        }

        br.close();
        solution(n, graph, edgeArr);
    }

    private static void solution(int n, ArrayList<ArrayList<Integer>> graph, int[] edgeArr) {
        LinkedList<Integer> orderList = new LinkedList<>();
        final int zero =0;
        StringBuilder sb = new StringBuilder();
        final String space = " ";

        for(int i=1; i<=n; i++) {
            if(edgeArr[i] ==zero) {
                orderList.add(i);
            }
        }

        while(!orderList.isEmpty()) {
            int vertex = orderList.poll();
            sb.append(vertex).append(space);
            ArrayList<Integer> edgeList = graph.get(vertex);

            for(int nextVertex : edgeList) {
                if(--edgeArr[nextVertex] ==0) {
                    orderList.offer(nextVertex);
                }
            }
        }
        System.out.println(sb.toString());
    }
}
