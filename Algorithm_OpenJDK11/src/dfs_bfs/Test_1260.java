package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Test_1260 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String infoStr = br.readLine();

        Graph gr = new Graph(Integer.parseInt(infoStr.split(" ")[0]));
        int size = Integer.parseInt(infoStr.split(" ")[1]);
        int start = Integer.parseInt(infoStr.split(" ")[2]);

        for(int i=0; i<size; i++) {
            String inputStr = br.readLine();
            gr.add(Integer.parseInt(inputStr.split(" ")[0]), Integer.parseInt(inputStr.split(" ")[1]));
        }

        gr.printDFS(start);
        gr.printBFS(start);

        br.close();
    }
}

class Graph {
    private int nodeNum;                    // 노드의 갯수
    private int[][] adj;

    //Constructor
    Graph(int num) {
        nodeNum = num;
        adj = new int[num+1][num+1];        // index의 시작이 0이 아닌 1부터 시작하기 위해서
    }

    void add(int major, int target) {   // 양방향으로 간선 이어주기
        adj[major][target] =1;
        adj[target][major] =1;
    }

    void printDFS(int start) {          // DFS 실행 결과 print
        boolean[] isVisited = initBool();

        dfs(start, isVisited);
    }

    void printBFS(int start) {          // BFS 실행 결과 print
        System.out.println();       // DFS결과와 띄워주기 위한 프린트
        boolean[] isVisited = initBool();
        bfs(start, isVisited);
    }

    private void dfs(int start, boolean[] isVisited) {            // DFS 탐색
        System.out.print(start + " ");
        
        isVisited[start] = true;                                  // 방문기록 작성
        for(int i=1; i<=nodeNum; i++) {                           // loop 돌면서
            if(adj[start][i] ==1) {                               // 현재 들어와있는 Node에 간선 연결된 곳이 있을 때
                if(!isVisited[i]) {                               // 방문한 적이 없으면
                    dfs(i,isVisited);                             // 들어가즈아~
                }
            }
        }
    }

    private void bfs(int start, boolean[] isVisited) {                                           // BFS 탐색
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        isVisited[start] = true;

        while(!q.isEmpty()) {
            int num = q.poll();
            System.out.print(num + " ");
            for(int i=1; i<=nodeNum; i++) {
                if(adj[num][i] == 1) {
                    if(!isVisited[i]) {
                        q.add(i);
                        isVisited[i] = true;
                    }
                }
            }
        }
    }

    private boolean[] initBool() {
        boolean[] isVisited = new boolean[nodeNum+1];

        for(int i=1; i<=nodeNum; i++) {
            isVisited[i] = false;
        }

        return isVisited;
    }
}
