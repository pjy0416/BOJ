package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Test_1260 {
    public static void main(String[] args) throws IOException { //
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
    private LinkedList<Integer>[] adj;      // 인접리스트 Adjacency List
    private Boolean[] isVisited;            // 방문 기록 Array

    //Constructor
    Graph(int num) {
        nodeNum = num;
        adj = new LinkedList[num+1];        // index의 시작이 0이 아닌 1부터 시작하기 위해서
        isVisited = new Boolean[num+1];     // 같은 이유

        for(int i=1; i<=num; i++) {         // 초기화
            adj[i] = new LinkedList<Integer>();
            isVisited[i] = false;
        }
    }

    void add(int major, int target) {   // 양방향으로 간선 이어주기
        adj[major].add(target);
        adj[target].add(major);
    }

    void printDFS(int start) {          // DFS 실행 결과 print
        LinkedList<Integer>[] adjCopy = adjClone();
        dfs(start, adjCopy);
    }

    void printBFS(int start) {          // BFS 실행 결과 print
        System.out.println();       // DFS결과와 띄워주기 위한 프린트
        bfs(start);
    }

    private void dfs(int start, LinkedList<Integer>[] adjCopy) {            // DFS 탐색
        if(!adjCopy[start].isEmpty()) { // 리스트에 연결되어 있는 것이 있으면
            int num = adjCopy[start].pop();     //pop으로 꺼내주고

            if(!isVisited[start]) {             // 들린적이 없으면
                System.out.print(start + " ");  // print
            }

            if(isVisited[num]) {                // 이미 방문했었으면
                num = adjCopy[start].pop();     // 다시 pop~~
                dfs(num, adjCopy);
            }

            isVisited[start] = true;            // 들렸다는 표시
            dfs(num, adjCopy);                  // 꺼낸 index로 들어감
        }
    }

    private void bfs(int start) {                                           // BFS 탐색
        for(int i=1; i<=nodeNum; i++) { //  방문기록 초기화
            isVisited[i] = false;
        }

        int[] queue = new int[1001];    // queue 생성
        int top =0;                     // 위치한 곳을 표시할 top
        int size =0;                    // 전체 Size

        System.out.print(start + " ");
        isVisited[start] = true;
        while(!adj[start].isEmpty()) {
            int num = adj[start].pop();
            queue[size++] = num;

            if(!isVisited[num]) {
                System.out.print(num + " ");
            }

            isVisited[num] =true;

            if(adj[start].isEmpty()) {
                start = queue[top++];
            }

            if(top >= nodeNum) {
                break;
            }
        }

    }

    LinkedList<Integer>[] adjClone() {      // 인접 리스트 복사 메소드
        LinkedList<Integer>[] result= new LinkedList[nodeNum+1];

        for(int i=1; i<=nodeNum; i++) {
            result[i] = new LinkedList<Integer>();
            result[i] = (LinkedList<Integer>) adj[i].clone();
        }

        return result;
    }

}
