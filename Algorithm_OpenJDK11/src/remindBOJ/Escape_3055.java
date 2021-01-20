package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
개선 사항
메모리 효율이 좋지 않고, 탐색에 시간이 조금 걸림
 */

public class Escape_3055 {
    final static int MAX_TIME =2500;
    final static int[] dx = {0,0,1,-1}, dy = {1,-1,0,0};
    final static char STONE ='X', ROAD = '.', WATER = '*', HEDGEHOG = 'S', BEAVER = 'D', DEFAULT = '\u0000';
    final static String FAILED = "KAKTUS";

    static char[][][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        map = new char[MAX_TIME][r][c];
        Pos start = null;

        for(int y=0; y<r; y++) {
            char[] inputs = br.readLine().toCharArray();
            map[0][y] = inputs;
            for(int x=0; x<c; x++) {
                if(inputs[x] == HEDGEHOG) {
                    start = new Pos(x,y);
                    map[0][y][x] = ROAD;
                }
            }
        }

        br.close();
        solution(start, r, c);
    }

    private static void solution(Pos start, int r, int c) {
        Queue<HedgeHog> queue = new LinkedList<>();
        queue.offer(new HedgeHog(start.x, start.y, 0));
        int step =0;
        boolean found = false;
        boolean[][] visit = new boolean[r][c];

        while(!queue.isEmpty()) {
            HedgeHog now = queue.poll();
            // step 검사해서 update해주는거
            if(step == now.step) {
                updateMap(step++, r, c);
            }

            if(map[step][now.pos.y][now.pos.x] == BEAVER) {
                found = true;
                break;
            }
            visit[now.pos.y][now.pos.x] = true;

            int nextStep = now.step +1;
            for(int i=0; i<4; i++) {
                int nx = now.pos.x +dx[i];
                int ny = now.pos.y +dy[i];
                if(isInRange(nx,ny,c,r) && !visit[ny][nx] && (map[nextStep][ny][nx] == ROAD || map[nextStep][ny][nx] == BEAVER)) {
                    visit[ny][nx] = true;
                    queue.offer(new HedgeHog(nx,ny,nextStep));
                }
            }
        }
        step--; // step이 하나 증가해있음
        
        System.out.println(found? step : FAILED);
    }

    private static void updateMap(int step, int r, int c) {
        int nextStep = step+1;
        for(int y=0; y<r; y++) {
            for(int x=0; x<c; x++) {
                if(map[nextStep][y][x] == DEFAULT) {
                    map[nextStep][y][x] = map[step][y][x];
                    if(map[nextStep][y][x] == WATER) {
                        for(int i=0; i<4; i++) {
                            int nx = x + dx[i];
                            int ny = y + dy[i];
                            if(isInRange(nx,ny,c,r) && map[step][ny][nx] == ROAD) {
                                map[nextStep][ny][nx] = WATER;
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean isInRange(int x, int y, int maxX, int maxY) {
        return x>=0 && y>=0 && x<maxX && y<maxY;
    }

    private static class HedgeHog {
        Pos pos;
        int step;

        public HedgeHog(int x, int y, int step) {
            this.pos = new Pos(x,y);
            this.step = step;
        }
    }

    private static class Pos {
        int x,y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
