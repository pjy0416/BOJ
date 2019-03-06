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
import java.util.LinkedList;
import java.util.Queue;

public class Test_3197 {
    private static Queue<Dot_3197> leftQ, rightQ;
    private static boolean[][] visitL, visitR;
    private static Dot_3197 swanL, swanR;
    private static String[][] map;
    private static int[][] mapStep;
    private static int[] wayX = {1, -1, 0, 0};
    private static int[] wayY = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        leftQ = new LinkedList<>();
        rightQ = new LinkedList<>();

        String lakeInfo = br.readLine();

        int maxX = Integer.parseInt(lakeInfo.split(" ")[0]);
        int maxY = Integer.parseInt(lakeInfo.split(" ")[1]);
        map = new String[maxX][maxY];
        mapStep = new int[maxX][maxY];
        visitL = new boolean[maxX][maxY];
        visitR = new boolean[maxX][maxY];
//        isVisited = new boolean[maxX][maxY];

        for(int x=0; x<maxX; x++) {
            String st = br.readLine();
            String[] stArr = st.split("");
            for(int y=0; y<maxY; y++) {
                map[x][y] = stArr[y];
                if(stArr[y].equals("L")) {
                    if(leftQ.isEmpty()) {
                        leftQ.offer(new Dot_3197(x,y, 0));
                    } else {
                        rightQ.offer(new Dot_3197(x,y,0));
                    }
                }
            }
        }

        bfs(maxX, maxY);

        br.close();
    }

    private static void bfs(int maxX, int maxY) {
        swanL = leftQ.poll();
        swanR = rightQ.poll();
        startSwan(maxX, maxY, swanL, swanR);
        setMaxStep(maxX, maxY);
        while(!leftQ.isEmpty() || !rightQ.isEmpty()) {       // 각각 swan~에서 한번씩 search 합시다
            if(!leftQ.isEmpty()) {
                searchPath(maxX, maxY, leftQ.poll(), 0);
            }
            if(!rightQ.isEmpty()) {
                searchPath(maxX, maxY, rightQ.poll(), 1);
            }
        }

        System.out.println(mapStep[swanL.x][swanL.y] + "\t\t" + mapStep[swanR.x][swanR.y]);
    }

    private static void searchPath(int maxX, int maxY, Dot_3197 dot, int direction) {
        for(int i=0; i<4; i++) {
            int posX = dot.x + wayX[i];
            int posY = dot.y + wayY[i];

            if (posX >= 0 && posX < maxX && posY >= 0 && posY < maxY) {
                int step = dot.step;
                String sign = map[posX][posY];

                if(sign.equals("X")) {   // 빙판이면 step 플러스
                    step += 1;
                    map[posX][posY] = ".";  // 물길로 만들기
                }

                if (direction == 0 ) { // 좌측 큐에서 빼온거면
                    if (!visitL[posX][posY]){                // 들른적 없어?
                        leftQ.offer(new Dot_3197(posX, posY, step));    // 그럼 큐에 넣어~
                    }

                    if(posX == swanR.x && posY == swanR.y) {    // 좌측 백조에서 우측 백조까지 갔어
                        mapStep[posX][posY] = step;             // 지금 스텝 넣어주고 끝내
                        return;
                    }

                    if(visitR[posX][posY]) {   // 반대에서 들른 적 있는 곳이야?
                        mapStep[posX][posY] = Math.min(mapStep[posX][posY], step);  // 더 작은 step 넣어
                    }


                    visitL[posX][posY] = true;

                } else if(direction == 1){         // 우측 큐에서 빼온거면
                    if(!visitR[posX][posY]) {       // 들른적 없으면 넣어
                        rightQ.offer(new Dot_3197(posX, posY, step));
                    }

                    if(posX == swanL.x && posY == swanL.y) {    // 좌측 백조에서 우측 백조까지 갔어
                        mapStep[posX][posY] = step;             // 지금 스텝 넣어주고 끝내
                        return;
                    }

                    if(visitL[posX][posY]) {   // 반대에서 들른 적 있는 곳이야?
                        mapStep[posX][posY] = Math.min(mapStep[posX][posY], step);  // 더 작은 step 넣어
                    }

                    visitR[posX][posY] = true;
                }

            }
        }
    }

    private static void setMaxStep(int maxX, int maxY) {
        for(int x=0; x<maxX; x++) {
            for(int y=0; y<maxY; y++) {
                mapStep[x][y] = 1500;
            }
        }
    }

    private static void startSwan(int maxX, int maxY, Dot_3197 swanL, Dot_3197 swanR) {
        visitL[swanL.x][swanL.y] = true;
        visitR[swanR.x][swanR.y] = true;

        for(int i=0; i<4; i++) {
            int lx = swanL.x + wayX[i];
            int ly = swanL.y + wayY[i];
            int rx = swanR.x + wayX[i];
            int ry = swanR.y + wayY[i];

            if(lx >=0 && lx <maxX && ly >=0 && ly <maxY) {
                String sign = map[lx][ly];
                if(sign.equals(".")) {      // 처음에 물이어야만 스텝에 맞게 탐색 가능
                    leftQ.offer(new Dot_3197(lx, ly, 0));
                    visitL[lx][ly] = true;
                }
            }
            if(rx >=0 && rx <maxX && ry >=0 && ry <maxY) {
                String sign = map[lx][ly];
                if(sign.equals(".")) {      // 처음에 물이어야만 스텝에 맞게 탐색 가능
                    visitR[rx][ry] = true;
                    rightQ.offer(new Dot_3197(rx, ry, 0));
                }
            }
        }
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
