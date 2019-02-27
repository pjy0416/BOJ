package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Test_7576 {
    private static int[][] map;                          // 토마토 상자 정보
    private static int maxX, maxY;                          // 박스 크기 정보
    private static boolean[][] isVisited;                   // 방문 기록
    private static int[] wayX = {-1, 1, 0, 0};                         // 좌 우
    private static int[] wayY = {0, 0, -1, 1};                         // 상 하

    private static Queue<Dot> movePos;

    private static void bfs(Queue<Dot> ripeTmt) {
        int size = ripeTmt.size();
        movePos = new LinkedList<>();
        isVisited = new boolean[maxY][maxX];

        for(int i=0; i<size; i++) {
            Dot pos = ripeTmt.poll();
            movePos.offer(pos);
            isVisited[pos.y][pos.x] = true;
        }

        while(!movePos.isEmpty()) {
            search(movePos);
        }

        printResult();
    }

    private static void search(Queue<Dot> queue) {
        Dot pos = queue.poll();

        for(int direction=0; direction<4; direction++) {                // 4방향 검색
            int x = pos.x + wayX[direction];
            int y = pos.y + wayY[direction];

            if( x>=0 && x<maxX && y>=0 && y<maxY ) {                // out of index 방지
                if(map[y][x] == 0 && !isVisited[y][x]) {       // 비어있는 공간이 아니면
                    movePos.offer(new Dot(x,y));
                    map[y][x] = map[pos.y][pos.x] +1;
                }
                isVisited[y][x] = true;
            }
        }
    }

    private static void printResult() {
        int day =1;
        for(int y=0; y<maxY; y++) {
            for(int x=0; x<maxX; x++) {
                day = Math.max(day, map[y][x]);
                if (map[y][x] == 0) {
                    System.out.println("-1");
                    return;
                }
            }
        }

        System.out.println(day-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        Queue<Dot> queue = new LinkedList<>();

        maxX = Integer.parseInt(str.split(" ")[0]);
        maxY = Integer.parseInt(str.split(" ")[1]);

        map = new int[maxY][maxX];

        for(int y=0; y<maxY; y++) {             // 여기서 초반의 맵에서 1인 부분을 모두 queue 에 집어 넣고 동시 다발적으로
            // 탐색을 시작하는 코드가 중요!
            String[] strArr = br.readLine().split(" ");
            for(int x=0; x<maxX; x++) {
                int info = Integer.parseInt(strArr[x]);
                map[y][x] = info;
                if(info == 1) {              // 익은 토마토 좌표 저장
                    queue.offer(new Dot(x,y));
                }
            }
        }

        bfs(queue);

        br.close();
    }

}

class Dot {
    int x;
    int y;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
