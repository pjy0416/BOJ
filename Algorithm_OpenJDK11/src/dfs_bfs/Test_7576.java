package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_7576 {
    private static int[][] map;                          // 토마토 상자 정보
    private static int maxX, maxY;                          // 박스 크기 정보
    private static boolean[][] isVisited;                   // 방문 기록
    private static int[] wayX = {-1, 1, 0, 0};                         // 좌 우
    private static int[] wayY = {0, 0, -1, 1};                         // 상 하

    private static LightQueue movePos;

    private static void bfs(LightQueue ripeTmt) {
        int size = ripeTmt.getSize();
        movePos = new LightQueue();
        isVisited = initBool();

        for(int i=0; i<size; i++) {
            movePos.push(ripeTmt.getX(), ripeTmt.getY());
            isVisited[ripeTmt.getY()][ripeTmt.getX()] = true;
            ripeTmt.pop();
        }

        while(!movePos.isEmpty()) {
            search(movePos);
        }

        printResult();
    }

    private static void search(LightQueue queue) {
        int posX = queue.getX();
        int posY = queue.getY();
        queue.pop();
        for(int direction=0; direction<4; direction++) {                // 4방향 검색
            int x = posX + wayX[direction];
            int y = posY + wayY[direction];

            if( x>=0 && x<maxX && y>=0 && y<maxY ) {                // out of index 방지
                if(map[y][x] == 0 && !isVisited[y][x]) {       // 비어있는 공간이 아니면
                    movePos.push(x,y);
                    map[y][x] = map[posY][posX] +1;
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

    private static boolean[][] initBool() {
        boolean[][] result = new boolean[maxY][maxX];
        for(int y=0; y<maxY; y++) {
            for(int x=0; x<maxX; x++) {
                result[y][x] = false;
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        LightQueue queue = new LightQueue();

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
                    queue.push(x,y);
                }
            }
        }

        bfs(queue);

        br.close();
    }

}

class LightQueue {
    private static final int MAX_QUEUE_SIZE = 128;
    int[] posX;
    int[] posY;
    int front;
    int rear;

    LightQueue() {
        posX = new int[MAX_QUEUE_SIZE];
        posY = new int[MAX_QUEUE_SIZE];
        front =0;
        rear =0;
    }

    void push(int x, int y) {
        if(rear == MAX_QUEUE_SIZE) {
            rear =0;
        }
        posX[rear] =x;
        posY[rear] =y;
        rear++;
    }

    void pop() {
        if(front == MAX_QUEUE_SIZE) {
            front =0;
        }
        front++;
    }

    int getX() {
        return this.posX[front];
    }

    int getY() {
        return this.posY[front];
    }

    int getSize() {
        return rear-front;
    }

    boolean isEmpty() {
        return getSize() ==0 ? true : false;
    }
}