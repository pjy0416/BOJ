package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CandyMove_11048 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n+1][m+1];

        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();
        solution(n,m,map);
    }

    private static void solution(int n, int m, int[][] map) {
        int dp[][] = new int[n+1][m+1];
        for(int y=1; y<=n; y++) {
            for(int x=1; x<=m; x++) {
                dp[y][x] = map[y][x] + Math.max(dp[y-1][x], dp[y][x-1]);
            }
        }
        System.out.println(dp[n][m]);
    }
}
