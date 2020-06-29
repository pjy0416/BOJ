package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class DropCoins_16197 {
    private final static int[] dx ={1,0,-1,0}, dy ={0,1,0,-1};
    private final static char COINCH = 'o', WALLCH = '#';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        char[][] map = new char[y][x];
        int[][] coinsPos = new int[2][2];
        int idx =0;

        for(int i=0; i<y; i++) {
            String inputStr = br.readLine();
            for(int j=0; j<x; j++) {
                char ch = inputStr.charAt(j);
                map[i][j] = ch;
                if(ch == COINCH) {
                    coinsPos[idx][0] = j; //x
                    coinsPos[idx++][1] = i; //y
                }
            }
        }

        solution(x,y,map,coinsPos);

        br.close();
    }

    private static void solution(int x, int y, char[][] map, int[][] coinsPos) {
        LinkedList<CoinInfo> moveList = new LinkedList<>();
        moveList.offer(new CoinInfo(coinsPos,0,2));

        while (!moveList.isEmpty()) {
            CoinInfo coins = moveList.poll();

            int step = coins.step;
            if(step >10) {
                System.out.println(-1);
                break;
            }
            if(coins.remains ==1) {
                System.out.println(step);
                break;
            }

            step++;

            if(coins.remains ==2) {
                for(int i=0; i<4; i++) {
                    int x1 = coins.coins[0][0] + dx[i];
                    int y1 = coins.coins[0][1] + dy[i];
                    int x2 = coins.coins[1][0] + dx[i];
                    int y2 = coins.coins[1][1] + dy[i];

                    if(isInArea(x1,y1,x,y) && isInArea(x2,y2,x,y)) {    // 둘 다 범위 안에 있으면
                        if(map[y1][x1] == WALLCH) { // 벽이면 이동 못함
                            y1 -= dy[i];
                            x1 -= dx[i];
                        }
                        if(map[y2][x2] == WALLCH) { // 벽이면 이동 못함
                            y2 -= dy[i];
                            x2 -= dx[i];
                        }
                        moveList.offer(new CoinInfo(new int[][] {{x1,y1},{x2,y2}}, step, 2));
                    } else if(isInArea(x1,y1,x,y) || isInArea(x2,y2,x,y)) { // 하나만 나가는 상황
                        moveList.offer(new CoinInfo(new int[2][2],step,1));
                    } // 둘 다 나가는 상황은 체크 안함
                }
            }
        }
    }

    private static boolean isInArea(int x, int y, int maxX, int maxY) {
        return x >=0 && x<maxX && y>=0 && y<maxY ? true : false;
    }
}

class CoinInfo {
    int[][] coins;
    int step;
    int remains;

    public CoinInfo(int[][] coins, int step, int remains) {
        this.coins = coins;
        this.step = step;
        this.remains = remains;
    }
}