/**              3197번 백조의 호수 (Swan Lake)
 *                호수의 크기 : 가로 R * 세로 C
 *                백조 두 마리, 만나는 길이 물이어야함 (빙판 X)
 *                얼어있는 구간이 있는데 물과 인접해 있으면 하루만에 녹음
 *                . : 물공간,   X : 빙판 공간, L : 백조가 있는 공간
 */

/* Hint
*  1. 하루동안 물 근처 빙하 녹이고 다음날로 진입
*  2. 백조 만날수 있는지 검증
*  1과 2 반복하며 만나면 끝내는
*  코드가 많더라...
*  근데 그럼 너무 오래걸리지 않을까
*/
package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_3197 {
    private static boolean[][] isVisited;
    private static Dot_3197 swanL, swanR;
    private static Queue_3197 wayQueue, nextQueue;
    private static Queue_3197 checkQueue;
    private static String[][] map;
    private static int[] wayX = {1, -1, 0, 0};
    private static int[] wayY = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String lakeInfo = br.readLine();

        int maxX = Integer.parseInt(lakeInfo.split(" ")[0]);
        int maxY = Integer.parseInt(lakeInfo.split(" ")[1]);
        map = new String[maxX][maxY];
        wayQueue = new Queue_3197();
        nextQueue = new Queue_3197();

        boolean isAdd = false;
        for(int x=0; x<maxX; x++) {
            String st = br.readLine();
            String[] stArr = st.split("");
            for(int y=0; y<maxY; y++) {
                map[x][y] = stArr[y];
                if(stArr[y].equals("L")) {
                    if(!isAdd) {
                        swanL = new Dot_3197(x, y, 0);
                        isAdd = true;
                    } else {
                        swanR = new Dot_3197(x,y,0);
                    }
                }else if(stArr[y].equals(".")) {
//                    nextQueue.offer(new Dot_3197(x, y, 0));      //물길 저장
                    wayQueue.offer(new Dot_3197(x,y,0));       // 물길 저장
                }
            }
        }

        bfs(maxX, maxY);

        br.close();
    }

    private static void bfs(int maxX, int maxY) {
        int step =0;
        isVisited = new boolean[maxX][maxY];        // 방문기록 초기화

        while(!meetEach(maxX, maxY)) {
            step++;
            meltICE(maxX, maxY);
        }

        System.out.println(step);
    }

    private static void meltICE(int maxX, int maxY) {
        while(!wayQueue.isEmpty()) {
            Dot_3197 dot = wayQueue.poll();    // 물인 곳 get
            isVisited[dot.x][dot.y] = true;     // 방문 체크

            for(int di =0; di <4; di++) {   // 4방향 검증
                int posX = dot.x + wayX[di];
                int posY = dot.y + wayY[di];

                if(posX >=0&& posX <maxX && posY >=0 && posY <maxY) {   // out of index 방지
                    String sign = map[posX][posY];

                    if(!isVisited[posX][posY] && sign.equals("X")) {    // 얼음이면 queue에 넣자
                        nextQueue.offer(new Dot_3197(posX, posY, 0));   // queue에 집어넣어서 다음 검증에 쓰자
                        map[posX][posY] = ".";          // 물로 녹이기
                    }
                    isVisited[posX][posY] = true;
                }
            }

        }
        wayQueue = nextQueue;
    }

    private static boolean meetEach(int maxX, int maxY) {
        checkQueue = new Queue_3197();
        checkQueue.offer(swanL);
        boolean[][] check = new boolean[maxX][maxY];

        check[swanL.x][swanL.y] = true;

        while(!checkQueue.isEmpty()) {
            Dot_3197 dot = checkQueue.poll();
            for(int i=0; i<4; i++) {
                int posX = dot.x + wayX[i];
                int posY = dot.y + wayY[i];
                if(posX >=0 && posX <maxX && posY >=0 && posY <maxY) {
                    String sign = map[posX][posY];

                    if(sign.equals(".") && !check[posX][posY]) {
                        checkQueue.offer(new Dot_3197(posX, posY, 0));
                    }

                    if(posX == swanR.x && posY == swanR.y) {
                        return true;
                    }
                    check[posX][posY] = true;
                }
            }
        }

        return false;
    }

}

class Dot_3197 {
    int x;
    int y;
    int step;

    Dot_3197(int x, int y, int step) {
        this.x = x;
        this.y = y;
        this.step = step;
    }
}

class Queue_3197 {
    private static final int MAX_QUEUE_SIZE = 1501;
    private int front;
    private int rear;
    private Dot_3197[] dot;

    Queue_3197() {
        front =0;
        rear =0;
        dot = new Dot_3197[MAX_QUEUE_SIZE];
    }

    public void offer(Dot_3197 point) {
        if(rear == MAX_QUEUE_SIZE) {
            rear =0;
        }
        dot[rear++] = point;
    }

    public Dot_3197 poll() {
        if(front ==MAX_QUEUE_SIZE) {
            front =0;
        }

        return dot[front++];
    }

    public boolean isEmpty() {
        if(front == rear) {
            return true;
        }
        return false;
    }

    public int getSize() {
        return rear-front;
    }
}
/*
8 17
...XXXXXX..XX.XXX
....XXXXXXXXX.XXX
...XXXXXXXXXXXX..
..XXXXX.LXXXXXX..
.XXXXXX..XXXXXX..
XXXXXXX...XXXX...
..XXXXX...XXX....
....XXXXX.XXXL...
*/
