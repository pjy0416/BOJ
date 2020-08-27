package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Alphabet_1987 {
    static int r,c,move,answer;
    static int[] dx = {1,-1,0,0}, dy={0,0,1,-1};
    static char A = 'A';
    static char[][] map;
    static boolean[] visit = new boolean[26];   // alphabet 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];

        for(int y=0; y<r; y++) {
            map[y] = br.readLine().toCharArray();
        }
        br.close();
        solution();
    }

    private static void solution() {
        move =1;
        answer =1;
        final int start =0;
        dfs(start,start); // 처음 칸도 포함해야하니까 start 에 +1
        System.out.println(answer);
    }

    private static void dfs(int x, int y) {
        int now = map[y][x] - A;
        visit[now] = true;

        for(int i=0; i<4; i++) {
            int nx=x+dx[i];
            int ny=y+dy[i];
            if(isInArea(nx,ny)) {
                int next = map[ny][nx] -A;
                if(!visit[next]) {
                    move++;
                    answer = Math.max(answer,move);
                    dfs(nx,ny);
                }
            }
        }
        visit[now] = false; // 백트랙킹
        move--;
    }

    private static boolean isInArea(int x, int y) {
        return x>=0 && x<c && y>=0 && y<r;
    }
}

