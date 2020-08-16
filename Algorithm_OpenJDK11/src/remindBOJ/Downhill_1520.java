package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Downhill_1520 {
    static int n, m;
    static int[] dx = {1,-1,0,0}, dy = {0,0,1,-1};
    static int[][] map, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        dp = new int[n][m];

        for(int y=0; y<n; y++) {
            st = new StringTokenizer(br.readLine());
            for(int x=0; x<m; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                dp[y][x] = -1;
            }
        }
        br.close();
        solution();
    }

    private static void solution() {
        int result = move(0,0);
        System.out.println(result);
    }

    private static int move(int y, int x) {
        if(x == m-1 && y == n-1)    return 1;
        if(dp[y][x] != -1)   return dp[y][x];
        dp[y][x] =0;

        for(int i=0; i<4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(isInArea(nx,ny)) {
                if(map[y][x] > map[ny][nx])     dp[y][x] += move(ny,nx);
            }
        }
        return dp[y][x];
    }
    private static boolean isInArea(int x, int y) {
        return x>=0 && x<m && y>=0 && y< n;
    }
}
