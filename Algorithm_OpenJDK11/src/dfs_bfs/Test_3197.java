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
    private static boolean[][] isVisited;
    private static Dot_3197 swanL, swanR;
    private static Queue<Dot_3197> wayStack;
    private static Queue<Dot_3197> queue;
    private static String[][] map;
    private static int[] wayX = {1, -1, 0, 0};
    private static int[] wayY = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        queue = new LinkedList<>();

        String lakeInfo = br.readLine();

        int maxX = Integer.parseInt(lakeInfo.split(" ")[0]);
        int maxY = Integer.parseInt(lakeInfo.split(" ")[1]);
        map = new String[maxX][maxY];

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
                }
            }
        }

        bfs(maxX, maxY);

        br.close();
    }

    private static void bfs(int maxX, int maxY) {
        int step =0;

        boolean canMeet = meetEach(maxX, maxY);
        while(!canMeet) {
            step++;
            meltICE(maxX, maxY);
            canMeet = meetEach(maxX, maxY);
        }
        System.out.println(step);
    }

    private static void meltICE(int maxX, int maxY) {
        isVisited = new boolean[maxX][maxY];

        for(int x=0; x<maxX; x++) {
            for(int y=0; y<maxY; y++) {
                String sign = map[x][y];

                for(int i=0; i<4; i++) {
                    int posX = x+ wayX[i];
                    int posY = y+ wayY[i];

                    if(posX >=0 && posX <maxX && posY >=0 && posY <maxY) {
                        if(!isVisited[x][y] && sign.equals(".") ) { // 해당 위치에 들린적 없고, 물이면
                            String around = map[posX][posY];        // 주변 좌표

                            if(around.equals("X")) {                // 주변이 얼음이면
                                map[posX][posY] = ".";              // 녹여
//                                System.out.println(posX + "," + posY + "녹였어");
                            }
                            isVisited[posX][posY] = true;           // 녹인 곳은 방문체크
                        }
                    }
                }
                isVisited[x][y] = true;                             // 물이었던 곳 방문체크
            }
        }

    }

    private static boolean meetEach(int maxX, int maxY) {
        wayStack = new LinkedList<>();
        wayStack.offer(swanL);
        boolean[][] check = new boolean[maxX][maxY];
        check[swanL.x][swanL.y] = true;

        while(!wayStack.isEmpty()) {
            Dot_3197 dot = wayStack.poll();
//            System.out.println(dot.x + "," + dot.y + " 시작");
            for(int i=0; i<4; i++) {
                int posX = dot.x + wayX[i];
                int posY = dot.y + wayY[i];
                if(posX >=0 && posX <maxX && posY >=0 && posY <maxY) {
                    String sign = map[posX][posY];

                    if(sign.equals(".") && !check[posX][posY]) {
                        wayStack.offer(new Dot_3197(posX, posY, 0));
//                        System.out.println("\t" + posX + "," + posY);
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
