package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test_2573 {
    private static Queue_2573 queue;
    private static boolean[][] isVisited;
    private static int[][] map;
    private static int maxX, maxY;
    private static boolean isMeltedAll;
    private static int[] wayX = {1, -1, 0, 0}, wayY = {0, 0, 1, -1};        // 방향 좌표 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputStr = br.readLine();
        maxY = Integer.parseInt(inputStr.split(" ")[0]);
        maxX = Integer.parseInt(inputStr.split(" ")[1]);

        map = new int[maxY][maxX];
        queue = new Queue_2573();

        for(int y=0; y< maxY; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int x=0; x< maxX; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());   // 저장
            }
        }

        bfs();

        br.close();
    }

    private static void bfs() {
        int year =0;
        boolean isEnd =false;
        isMeltedAll = false;

        while(!isEnd) {
            meltIce();      // 얼음 녹이기
            isEnd = isParted(); // 두동강 났는지 확인
            year++;
            if(isMeltedAll) {
                year =0;
                break;
            }
        }

        System.out.println(year);
    }

    private static boolean isParted() {
        int part =0;
        isVisited = new boolean[maxY][maxX];

        for(int y=1; y <maxY-1; y++) {           //  테두리 = 물
            for(int x=1; x <maxX-1; x++) {       //  테두리 전만 체크
                if(map[y][x] != 0 && !isVisited[y][x]) {    // 물 아니고 방문한 적 없으면
                    queue.offer(new Dot_2573(x, y));
                    if(searchParts()) {
                        part++;
                    }
                }
            }
        }
        if(part >=2) {
            return true;
        }

        return false;
    }

    private static boolean searchParts() {
        boolean result = false;
        while(!queue.isEmpty()) {
            result = true;
            Dot_2573 dot = queue.poll();
            isVisited[dot.y][dot.x] = true;     // 시작점 방문 체크

            for(int i=0; i<4; i++) {    // 4방향 검증
                int posX = dot.x + wayX[i];
                int posY = dot.y + wayY[i];

                if(map[posY][posX] !=0 && !isVisited[posY][posX]) {
                    queue.offer(new Dot_2573(posX, posY));
                    isVisited[posY][posX] = true;       // 들른 곳 방문 체크
                }
            }
        }

        return result;
    }

    private static void meltIce() {         // 녹여~~
        isVisited = new boolean[maxY][maxX];    // 초기화
        int cnt =0;
        for(int y=1; y<maxY-1; y++) {       // 테두리는 다 물이기 때문에
            for(int x=1; x<maxX-1; x++) {   // 테두리는 안들어가도 됨
                int info = map[y][x];
                if(info !=0) {  // 빙산이면
                    isVisited[y][x] = true; // 빙산 방문체크
                    map[y][x] = meltedSpot(info, x, y); // 녹여서 저장
                } else {
                    cnt++;                  // 맵 전체가 0이면?
                }
            }
        }
        if(cnt == (maxX-1) * (maxY-1)) {
            isMeltedAll = true;
        }
    }

    private static int meltedSpot(final int info, final int x, final int y) {
        int cnt =0;

        for(int i=0; i<4; i++) {
            int posX = x + wayX[i];
            int posY = y + wayY[i];

            if(map[posY][posX] ==0 && !isVisited[posY][posX]) {   // 주변이 원래 물이면 cnt
                cnt++;
            }
        }

        return info-cnt >=0 ? info-cnt : 0;
    }

    private static boolean isInRange(int x, int y) {
        return x>=0 && x<maxX && y>=0 && y<maxY ? true : false;
    }
}

class Queue_2573 {
    private static final int MAX_QUEUE_SIZE = 10001;
    private Dot_2573[] queue;
    private int rear;
    private int front;

    Queue_2573() {
        queue = new Dot_2573[MAX_QUEUE_SIZE];
        rear =0;
        front =0;
    }

    void offer(Dot_2573 dot) {
        if(rear == MAX_QUEUE_SIZE) {
            rear =0;
        }
        queue[rear++] = dot;
    }

    Dot_2573 poll() {
        if(front == MAX_QUEUE_SIZE) {
            front =0;
        }
        return queue[front++];
    }

    boolean isEmpty() {
        return rear == front ? true : false;
    }

    void initalize() {
        front =0;
        rear =0;
    }
}

class Dot_2573 {
    int x;
    int y;

    Dot_2573(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

