package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class CrocoCity_5822 { // 메모리 초과 ~> N <= 100,000 ~> int[][], boolean[][] 둘 다 초과
    // https://www.acmicpc.net/problem/5822
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] infoArr = br.readLine().split(" ");

        int N = Integer.parseInt(infoArr[0]);
        int M = Integer.parseInt(infoArr[1]);
        int K = Integer.parseInt(infoArr[2]);

        ArrayList<HashMap<Integer,Integer>> costList = inItList(N);
        int[] minRoute = new int[N];
        Arrays.fill(minRoute,-1);

        for(int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            costList.get(node1).put(node2, cost);
            costList.get(node2).put(node1, cost);

            if(minRoute[node1] ==-1) {
                minRoute[node1] = cost;
            } else {
                minRoute[node1] = Math.min(minRoute[node1], cost);
            }
            if(minRoute[node2] ==-1) {
                minRoute[node2] = cost;
            } else {
                minRoute[node2] = Math.min(minRoute[node2], cost);
            }
        }

        int[] escapeRooms = new int[N];
        for(int i=0; i<K; i++) {
            escapeRooms[Integer.parseInt(br.readLine())] =1;
        }

        br.close();

        System.out.println(travel_plan(N, minRoute, escapeRooms, costList));
    }

    private static ArrayList<HashMap<Integer, Integer>> inItList(int n) {
        ArrayList<HashMap<Integer,Integer>> result = new ArrayList<>();
        for(int i=0; i<n; i++) {
            HashMap<Integer,Integer> tmp = new HashMap<>();
            result.add(tmp);
        }
        return result;
    }

    private static long travel_plan(int N, int[] minRoute, int[] escapeRooms, ArrayList<HashMap<Integer,Integer>> costList) {
        long answer =0;

        for(int i=0; i<N; i++) {
            int min = Integer.MAX_VALUE;
            int idx =0;
            HashMap<Integer, Integer> costMap = costList.get(i);

            for(int j=1; j<N; j++) {
                if(costMap.containsKey(j)) {
                    int val = costMap.get(j);
                    if(val !=-1 && minRoute[i] != val) {
                        if(min > val) {
                            min = val;
                            idx = j;
                        }
                    }
                }
            }
            if(min != Integer.MAX_VALUE) {
                answer+= min;
            }
            if(escapeRooms[idx] ==1) {
                break;
            }
        }

        return answer;
    }
}

/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException { // 메모리 초과 코드
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] infoArr = br.readLine().split(" ");

        int N = Integer.parseInt(infoArr[0]);
        int M = Integer.parseInt(infoArr[1]);
        int K = Integer.parseInt(infoArr[2]);

        int[][] map = inItMap(N);

        for(int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            map[node1][node2] = cost;
            map[node2][node1] = cost;
        }

        int[] escapeRooms = new int[N];
        for(int i=0; i<K; i++) {
            escapeRooms[Integer.parseInt(br.readLine())] =1;
        }

        br.close();

        System.out.println(travel_plan(N, map, escapeRooms));
    }

    private static int[][] inItMap(int n) {
        int[][] result = new int[n][n];
        for(int i=0; i<n; i++) {
            Arrays.fill(result[i],-1);
        }
        return result;
    }

    private static long travel_plan(int N, int[][] map, int[] escapeRooms) {
        long answer =Long.MAX_VALUE;

        PriorityQueue<CrocoNode> moveQueue = new PriorityQueue<>();
        moveQueue.offer(new CrocoNode(0,0,0));

        while(!moveQueue.isEmpty()) {
            CrocoNode node = moveQueue.poll();
            if(escapeRooms[node.idx] ==1) {
                answer = Math.min(answer, node.cost);
            } else {
                for(int i=1; i<N; i++) {
                    int step = node.step+1;
                    if(map[node.idx][i] != -1) {
                        long cost = node.cost + map[node.idx][i];
                        moveQueue.offer(new CrocoNode(i,cost,step));
                    }
                }
                if(!moveQueue.isEmpty()) {
                    moveQueue.poll();
                }
            }
        }
        return answer;
    }
}

class CrocoNode implements Comparable<CrocoNode>{
    int idx;
    long cost;
    int step;

    public CrocoNode(int idx, long cost, int step) {
        this.idx = idx;
        this.cost = cost;
        this.step = step;
    }

    @Override
    public int compareTo(CrocoNode c) {
        if(this.step < c.step) {
            return 1;
        } else if(this.step == c.step){
            return this.cost > c.cost ? 1 : -1;
        }
        return -1;
    }
}
 */

/*
5 7 2
0 2 4
0 3 3
3 2 2
2 1 10
0 1 100
0 4 7
3 4 9
1
3
 */