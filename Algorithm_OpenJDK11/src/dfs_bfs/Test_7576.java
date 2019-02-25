package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_7576 {
    private static String[][] map;                          // 토마토 상자 정보
    private static int maxX, maxY;                          // 박스 크기 정보
    private static boolean[][] isVisited;                   // 방문 기록
    private static int blank;                               // 비어있는 갯수
    private static int[] wayX = {-1, 1, 0, 0};                         // 좌 우
    private static int[] wayY = {0, 0, -1, 1};                         // 상 하

    private static LightQueue[] movePos;
    private static int step,cnt;


    private static void bfs(LightQueue ripeTmt) {
        step =0;                    // 확산 스텝 counting 변수
        cnt =0;                      // 변하는 갯수 counting 변수

        if((ripeTmt.getSize() == blank && ripeTmt.getSize() !=0) || ripeTmt.getSize() == maxX*maxY) {        // 상자에 이미 익은게 다 있으면 할 필요 없어
            printResult(step, cnt, ripeTmt.getSize());
            return;
        }

        if(ripeTmt.getSize() ==0) { // 익은게 아예 없으면
            printResult(0, 1,0);
            return;
        }

        int size = ripeTmt.getSize();
        movePos = new LightQueue[size];
        isVisited = initBool();

        for(int i=0; i<size; i++) {
            movePos[i] = new LightQueue();
            int[] pos = ripeTmt.pop();
            movePos[i].push(pos[0],pos[1]);
            isVisited[pos[1]][pos[0]] = true;
        }

        while(true) {
            if(movePos[0].getSize() ==0) {
                break;
            }
            boolean isDone = false;

            if( size ==1) {
                int posSize = movePos[0].getSize();
                for(int i =0; i<posSize; i++) {
                    search(0, movePos[0].pop());
                }
                step++;

            } else {
                for (int i = 0; i < size; i++) {
                    isDone = search(i, movePos[i].pop());
                }
                if (isDone) {
                    step++;
//                    System.out.println("Step ++ !!!!!!!!!!!!!!!!\t" + step);
                }
            }
        }

        printResult(step, cnt, size);
    }

    private static boolean search(int idx,int[] pos) {
//        System.out.println("\t" + pos[0] + "," + pos[1] + "에서 시작");
        boolean isDone =false;

        for(int direction=0; direction<4; direction++) {                // 4방향 검색
            int x = pos[0] + wayX[direction];
            int y = pos[1] + wayY[direction];

            if( x>=0 && x<maxX && y>=0 && y<maxY ) {                // out of index 방지
                if(!map[y][x].equals("-1") && !isVisited[y][x]) {       // 비어있는 공간이 아니면
//                    System.out.println(x + "," + y + "로 이동");
                    movePos[idx].push(x,y);
                    cnt++;
                    isDone = true;
                }
                isVisited[y][x] = true;
            }
        }
        return isDone;
    }

    private static void printResult(int step, int cnt, int ripeSize) {
        // 작업 후 변한 갯수가 토마토의 갯수와 같은지 아닌지 판단
        if(cnt ==0) {
            System.out.println("0");
        } else if(maxX*maxY == cnt +blank +ripeSize) {      // 전체 박스와 (익게 만든 것 + 빈 공간 + 익어 있던 것 갯수) 가 같으면
            if(ripeSize ==1) {
                System.out.println(step-1);
            } else {
                System.out.println(step);                       // 몇 번에 걸쳐서 익게했는지 print
            }
        } else {
            System.out.println("-1");                       // 다르면 -1 print
        }
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

        map = new String[maxY][maxX];

        for(int y=0; y<maxY; y++) {             // 여기서 초반의 맵에서 1인 부분을 모두 queue 에 집어 넣고 동시 다발적으로
            // 탐색을 시작하는 코드가 중요!
            String[] strArr = br.readLine().split(" ");
            for(int x=0; x<maxX; x++) {
                String info = strArr[x];
                map[y][x] = info;
                if(info.equals("1")) {              // 익은 토마토 좌표 저장
                    queue.push(x,y);
                } else if(info.equals("-1")) {      // 안익은 토마토 갯수 저장
                    blank++;
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

    int[] pop() {
        int[] result = new int[2];
        if(front == MAX_QUEUE_SIZE) {
            front =0;
        }

        result[0] = posX[front];
        result[1] = posY[front];
        front++;

        return result;
    }

    int[] peek() {
        int[] result = new int[2];
        result[0] =posX[front];
        result[1] = posY[front];

        return result;
    }

    int getSize() {
        return rear-front;
    }

    void init() {
        front =0;
        rear =0;
    }
}