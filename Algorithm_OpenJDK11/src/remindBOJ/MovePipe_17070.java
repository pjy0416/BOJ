package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MovePipe_17070 {
    final static int[] dx = {1,0,1}, dy = {0,1,1};
    final static int WALL =1;

    static int answer, n, map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solution();
        br.close();
    }

    private static void solution() {
        answer =0;
        dfs(new PipePos(1,0,0)); // 끝 부분으로 시작하기
        System.out.println(answer);
    }

    private static void dfs(PipePos pipePos) {
        if(pipePos.x ==n-1 && pipePos.y == n-1) {
            answer++;
            return;
        }
        int dir = pipePos.direction;
        int x = pipePos.x;
        int y = pipePos.y;
        switch (dir) {
            case 0: // 가로, 대각선 가능
                for(int i=0; i<3; i++) {
                    if(i != 1) {
                        if(canMove(x,y,i)) { dfs(new PipePos(x+dx[i], y+dy[i], i));}
                    }
                }
                break;
            case 1: // 세로, 대각선 가능
                for(int i=1; i<3; i++) {
                    if(canMove(x,y,i)) { dfs(new PipePos(x+dx[i], y+dy[i], i)); }
                }
                break;
            case 2: // 가로, 세로, 대각선 가능
                for(int i=0; i<3; i++) {
                    if(canMove(x,y,i)) { dfs(new PipePos(x+dx[i], y+dy[i], i));}
                }
                break;
            default:
                break;
        }
    }

    private static boolean canMove(int x, int y, int dir) {
        int nx = x+dx[dir], ny = y+dy[dir];
        if(nx >=n || ny >=n) { return false; }
        if(map[ny][nx] == WALL) { return false; }
        if(dir ==2) {
            if(map[ny][x] == WALL || map[y][nx] == WALL) { return false; }
        }
        return true;
    }
}

class PipePos {
    int x;
    int y;
    int direction;  // 0 : 가로, 1 : 세로, 2 : 대각선

    public PipePos(int x, int y, int direction) {
        this.x =x;
        this.y =y;
        this.direction = direction;
    }
}