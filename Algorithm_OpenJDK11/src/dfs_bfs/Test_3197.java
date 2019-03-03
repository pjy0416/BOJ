/**              3197번 백조의 호수 (Swan Lake)
*                호수의 크기 : 가로 R * 세로 C
*                백조 두 마리, 만나는 길이 물이어야함 (빙판 X)
*                얼어있는 구간이 있는데 물과 인접해 있으면 하루만에 녹음
*                . : 물공간,   X : 빙판 공간, L : 백조가 있는 공간
*/
package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Test_3197 {
    private static boolean[][] isVisitedL;
    private static boolean[][] isVisitedR;
    private static String[][] map;
    private static Dot_3197[] swans;
    private static Queue<Dot_3197> queueL;
    private static Queue<Dot_3197> queueR;
    private static int step;
    private static int maxX;
    private static int maxY;
    private static int[] wayX = {1,-1,0,0,0};     // 4방향 증감 저장  + 자기 자신
    private static int[] wayY = {0,0,1,-1,0};

    private static void bfs(int maxX, int maxY) {
        isVisitedL = new boolean[maxX][maxY];
        isVisitedR = new boolean[maxX][maxY];
        queueL = new LinkedList<>();
        queueR = new LinkedList<>();

        findPos();

        boolean isEnd = false;

        while(true) {

            if(!queueL.isEmpty()) {
                Dot_3197 posL = queueL.poll();
                isEnd = findWays(posL, maxX, maxY, "left");
            }
            if(!queueR.isEmpty()) {
                Dot_3197 posR = queueR.poll();
                isEnd = findWays(posR, maxX, maxY, "right");
            }

            if(isEnd) {
                break;
            }

        }
        System.out.println(step);
    }

    private static void findPos() {         // 4가지 경우
        Dot_3197 pos1 = swans[0];
        Dot_3197 pos2 = swans[1];

        Dot_3197 casePosL = selectCase(pos1, pos2, 1);
        Dot_3197 casePosR = selectCase(casePosL, pos2, 0);

        queueL.offer(casePosL);
        queueR.offer(casePosR);

        isVisitedL[casePosL.x][casePosL.y] = true;
        isVisitedR[casePosR.x][casePosR.y] = true;
    }

    private static Dot_3197 selectCase(Dot_3197 pos1, Dot_3197 pos2, int stand) {
        Dot_3197 closePos = new Dot_3197(1501, 1501, 1501);

        if(pos1.x >= pos2.x && pos1.y >= pos2.y) {        // 첫 번째 경우, pos1 이 pos2보다 우하 위치
            closePos = findPoint(pos2.x, pos1.x, pos2.y, pos1.y, stand);
        } else if(pos1.x >= pos2.x && pos1.y <= pos2.y) {        // 두 번째 경우, pos1 이 pos2보다 좌하 위치
            closePos = findPoint(pos2.x, pos1.x, pos1.y, pos2.y, stand);
        } else if(pos1.x <= pos2.x && pos1.y >= pos2.y) {        // 세 번째 경우, pos1 이 pos2보다 우상 위치
            closePos = findPoint(pos1.x, pos2.x, pos2.y, pos1.y,stand );
        } else if(pos1.x <= pos2.x && pos1.y <= pos2.y) {        // 네 번째 경우, pos1 이 pos2보다 좌상 위치
            closePos = findPoint(pos1.x, pos2.x, pos1.y, pos2.y,stand );
        }

        closePos.step =0;

        return closePos;
    }

    private static Dot_3197 findPoint(int pos2_X, int pos1_X, int pos2_Y, int pos1_Y, int stand) {
        Dot_3197 result = new Dot_3197(1501, 1501, 1501);
        for(int x = pos2_X; x<= pos1_X; x++) {
            for(int y=pos2_Y; y<= pos1_Y; y++) {
                for(int i=0; i<5; i++) {        // 4방향
                    int posX = x + wayX[i];
                    int posY = y + wayY[i];

                    if(posX >=0 && posX < maxX && posY >=0 && posY < maxY) {
                        if(map[posX][posY].equals(".")) {
                            int dist = pos1_X - posX + pos1_Y - posY;
                            if (result.step > dist && dist > stand) {
                                result.x = posX;
                                result.y = posY;
                                result.step = dist;
                            }
                        }
                    }
                }

            }
        }

        return result;
    }

    private static boolean findWays(Dot_3197 dot, int maxX, int maxY, String key) {
        boolean[][] isVisited;
        Queue<Dot_3197> queue;
        if(key.equals("left")) {
            isVisited = isVisitedL.clone();
            queue = queueL;
        } else {
            isVisited = isVisitedR.clone();
            queue = queueR;
        }
        for(int i=0; i<4; i++) {
            int posX = dot.x + wayX[i];
            int posY = dot.y + wayY[i];

            if(posX >=0 && posX<maxX && posY<maxY && posY>=0) {             // out of index 방지
                if(!isVisited[posX][posY]) {
                    if(map[posX][posY].equals(".")) {                   // 물길일 때
                        Dot_3197 tmp = new Dot_3197(posX, posY, dot.step);
                        queue.offer(tmp);
                    } else if(map[posX][posY].equals("X")) {            // 빙판일 때
                        Dot_3197 tmp = new Dot_3197(posX, posY, dot.step+1);
                        map[posX][posY] = ".";
                        queue.offer(tmp);
                    }
                }

                if(key.equals("left")) {
                    if(isVisitedR[posX][posY]) {
                        step = dot.step;
                        return true;
                    }
                } else {
                    if(isVisitedL[posX][posY]) {
                        step = dot.step;
                        return true;
                    }
                }

                isVisited[posX][posY] = true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        swans = new Dot_3197[2];                 // swans position
        int swanIdx =0;

        String lakeInfo = br.readLine();

        maxX = Integer.parseInt(lakeInfo.split(" ")[0]);
        maxY = Integer.parseInt(lakeInfo.split(" ")[1]);
        map = new String[maxX][maxY];


        for(int x=0; x<maxX; x++) {
            String st = br.readLine();
            String[] stArr = st.split("");
            for(int y=0; y<maxY; y++) {
                map[x][y] = stArr[y];
                if(stArr[y].equals("L")) {
                    swans[swanIdx++] = new Dot_3197(x, y, 0);
                }
            }
        }

        bfs(maxX, maxY);

        br.close();
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
