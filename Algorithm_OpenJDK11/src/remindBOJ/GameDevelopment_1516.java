package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GameDevelopment_1516 {
    public static void main(String[] args) throws IOException {
        final int END = -1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] costArr = new int[n+1];
        int[] edgeArr = new int[n+1];
        List<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=1; i<=n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            costArr[i] = Integer.parseInt(st.nextToken());
            while(st.hasMoreTokens()) {
                int index = Integer.parseInt(st.nextToken()); // 부모
                if(index != END) {
                    edgeArr[i]++;
                    graph.get(index).add(i);
                }
            }
        }

        br.close();
        solution(costArr, edgeArr, graph, n);
    }

    private static void solution(int[] costArr, int[] edgeArr, List<ArrayList<Integer>> graph, int n) {
        int[] result = getResult(costArr, edgeArr, graph, n);
        printAnswer(result, n);
    }

    private static void printAnswer(int[] result, int n) {
        StringBuilder sb = new StringBuilder();
        final String NEW_LINE = "\n";
        for(int i=1; i<=n; i++) {
            sb.append(result[i]).append(NEW_LINE);
        }
        System.out.println(sb.toString());
    }

    private static int[] getResult(int[] costArr, int[] edgeArr, List<ArrayList<Integer>> graph, int n) {
        final int ZERO =0;
        PriorityQueue<Building> buildingList = new PriorityQueue<>();
        int[] result = new int[n+1];
        for(int i=1; i<=n; i++) {
            if(edgeArr[i] == ZERO) {
                buildingList.offer(new Building(i, costArr[i]));
            }
        }

        while(!buildingList.isEmpty()) {
            Building now = buildingList.poll();
            result[now.index] = Math.max(result[now.index], now.buildTime);
            ArrayList<Integer> childList = graph.get(now.index);
            for(int child : childList) {
                if(--edgeArr[child] ==0) {
                    buildingList.offer(new Building(child, now.buildTime + costArr[child]));
                }
            }
        }
        return result;
    }

    private static class Building implements Comparable<Building>{
        int index, buildTime;

        public Building(int index, int buildTime) {
            this.index = index;
            this.buildTime = buildTime;
        }

        @Override
        public int compareTo(Building building) {
            return this.buildTime - building.buildTime;
        }
    }
}
/*
4
1 4 3 2 -1
2 4 -1
1 4 -1
1 -1
*/