package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BreakWall_Move_2206 {
    private final static char MOVE = '0', WALL = '1';
    private final static int BREAK = 0, NO_BREAK = 1;
    private final static int[] dx = {1,-1,0,0}, dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][m];
        for(int i=0; i<n; i++) {
            map[i] = br.readLine().toCharArray();
        }
        br.close();

        solution(n,m,map);
    }

    private static void solution(int n, int m, char[][] map) {
        int answer =-1;
        LinkedList<Pos> moveList = new LinkedList<>();
        boolean[][][] visit = new boolean[n][m][2]; // 벽을 한 번 부순 경우와 부수지 않은 경우
        visit[0][0][1] = true; // 부수지 않은 채 이동
        moveList.offer(new Pos(0,0, 1,NO_BREAK));

        while(!moveList.isEmpty()) {
            Pos pos = moveList.poll();
            if(pos.x == m-1 && pos.y == n-1) {
                answer = pos.step;
                break;
            }
            int step = pos.step+1;
            int remain = pos.remain;

            for(int i=0; i<4; i++) {
                int nx = pos.x + dx[i];
                int ny = pos.y + dy[i];
                if(isInArea(nx,ny,m,n)) { // out of idx 방지
                    if(!visit[ny][nx][remain]) { // visit check
                        if (map[ny][nx] == MOVE) {
                            moveList.offer(new Pos(nx,ny,step, remain));
                            visit[ny][nx][remain] = true;
                        } else { // 벽의 경우
                            if (remain == NO_BREAK) { // 벽을 한번도 안부쉈다면
                                moveList.offer(new Pos(nx,ny,step, BREAK));
                                visit[ny][nx][BREAK] = true;
                            }
                        }

                    }
                }
            }
        }
        System.out.println(answer);
    }

    private static boolean isInArea(int x, int y, int m, int n) {
        return x>=0 && y>=0 && x<m && y<n;
    }

    private static class Pos {
        int x,y,step,remain;
        public Pos(int x, int y, int step, int remain) {
            this.x =x;
            this.y =y;
            this.step = step;
            this.remain =remain;
        }
    }
}
