package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EscapeMaze_17090 {
    final static int[] dx = {0,1,0,-1}, dy = {-1,0,1,0};
    final static char[] cmdArr = {'U','R','D','L'};
    final static int DEFALUT =0, POSSIBLE =1, IMPOSSIBLE=2;

    static int N,M,visit[][];
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visit = new int[N][M];

        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        br.close();
        solution();
    }

    private static void solution() {
        int answer =0;
        for(int y=0; y<N; y++) {    //500
            for(int x=0; x<M; x++) {    //500
                if(visit[y][x] ==DEFALUT) {
                    setVisit(x,y);
                }
                if(visit[y][x] ==POSSIBLE) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

    private static int setVisit(int x, int y) {
        if(visit[y][x] != 0) {
            return visit[y][x];
        }

        int status =IMPOSSIBLE;
        visit[y][x] = status;

        for(int i=0; i<4; i++) {
            if(map[y][x] == cmdArr[i]) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(isInArea(nx,ny)) {
                    status = setVisit(nx,ny);
                } else {
                    status =POSSIBLE;
                }
                break;
            }
        }
        visit[y][x] = status;
        return visit[y][x];
    }

    private static boolean isInArea(int x, int y) {
        return x>=0 && x<M && y >=0 && y <N;
    }
}
