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

    private static LightQueue movePos;
    private static int step,cnt;


    private static void bfs(LightQueue ripeTmt) {
        step =-1;                    // 확산 스텝 counting 변수
        cnt =0;                     // 변하는 갯수 counting 변수

        if(ripeTmt.getSize() == blank) {        // 상자에 이미 익은게 다 있으면 할 필요 없어
            System.out.println("0");
            return;
        }

        movePos = new LightQueue();
        isVisited = initBool();
        int[] ripePos = ripeTmt.pop();

        movePos.push(ripePos[0], ripePos[1]);
        isVisited[ripePos[1]][ripePos[0]] = true;

        while(true) {
            int[] pos = new int[2];
            if(movePos.getSize() ==0) {         // 저장되있는 토마토를 다 탐색하면
                if(ripeTmt.getSize() !=0) {     // 다른 파티션 되어있는 공간에 익은 토마토가 있을 수 있으니깐
                    pos = ripeTmt.pop();
                    search(pos[0], pos[1]);
                } else {                        // 익은것도 다 썼으면
                    break;
                }
            } else {                            // 안익은 토마토 좌표 가져오기
                int size = movePos.getSize();

                for(int i=0; i<size; i++) {
                    pos = movePos.pop();
                    search(pos[0],pos[1]);
                }
            }
            step++;
        }
        printResult(step, cnt);
    }

    private static void search(int posX, int posY) {
//        System.out.println("\t" + posX + "," + posY + "에서 시작");

        for(int direction=0; direction<4; direction++) {
            int x = posX + wayX[direction];
            int y = posY + wayY[direction];

            if( (x>=0 && x<maxX) && (y>=0 && y<maxY) ) {                // out of index 방지
                if(!map[y][x].equals("-1") && !isVisited[y][x]) {       // 비어있는 공간이 아니면
                    if(map[y][x].equals("1")) {                         // 익어있는 토마토가 있을 때는
                        step = roundDiv(step);
                    } else {                                            // 안익어있는게 있으면
//                        System.out.println(x + "," + y + "로 이동");
                        movePos.push(x,y);
                    }
                    cnt++;
                }
                isVisited[y][x] = true;
            }
        }
    }

    private static int roundDiv(int target) {
        if(target % 2 == 1) {
            return target/2 +1;
        }
        return target/2;
    }

    private static void printResult(int step, int cnt) {
        // 작업 후 변한 갯수가 토마토의 갯수와 같은지 아닌지 판단
        if(maxX*maxY-blank != cnt+1) {              // 자기 자신을 포함해야하기 때문에 count에 +1
            System.out.println("-1");               // 모두 익지 않을 경우는 -1 return
        } else {
            System.out.println(step);               // 모두 익었을 시는 step return
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

    int getSize() {
        return rear-front;
    }

    void init() {
        front =0;
        rear =0;
    }
}