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
    private static boolean[][] isVisited;
    private static String[][] map;
    private static Dot_3197[] swans;
    private static Queue<Dot_3197> queue;
    private static int step;
    private static int[] wayX = {1,-1,0,0};     // 4방향 증감 저장
    private static int[] wayY = {0,0,1,-1};

    private static void bfs(int maxX, int maxY) {
        isVisited = new boolean[maxX][maxY];
        queue = new LinkedList<>();
        step =1501;

        // Swan은 두마리기 때문에 두번 진행
        queue.offer(swans[0]);
        queue.offer(swans[1]);

        for(int i=0; i<2; i++) {    // 얼지 않은 곳을 찾아서 저장해줘야 함
            findPos(queue.poll(), maxX, maxY); // 백조 근처부터 탐색 시작
        }

        while(!queue.isEmpty()) {
            findWays(queue.poll(), maxX, maxY);
        }

        System.out.println(step);
    }

    private static void findPos(Dot_3197 dot, int maxX, int maxY) {
        isVisited[dot.x][dot.y] = true;
        for(int i=0; i<4; i++) {
            int posX = dot.x + wayX[i];
            int posY = dot.y + wayY[i];

            if(posX >=0 && posX<maxX && posY<maxY && posY>=0) {             // out of index 방지
                if(!isVisited[posX][posY]) {
                    if(map[posX][posY].equals(".")) {                   // 물길일 때
                        Dot_3197 tmp = new Dot_3197(posX, posY, dot.step);
                        queue.offer(tmp);
                        isVisited[posX][posY] = true;
                    }
                }
            }
        }
    }

    private static void findWays(Dot_3197 dot, int maxX, int maxY) {
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

                if(dot.step !=0 && posX == swans[1].x && posY == swans[1].y) {      // 문제점 : 길이 다 뚫리기 전에 step을 저장할 수 있음
                    step = Math.min(dot.step, step);
                }
                isVisited[posX][posY] = true;

            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        swans = new Dot_3197[2];                 // swans position
        int swanIdx =0;

        String lakeInfo = br.readLine();

        int maxX = Integer.parseInt(lakeInfo.split(" ")[0]);
        int maxY = Integer.parseInt(lakeInfo.split(" ")[1]);
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
