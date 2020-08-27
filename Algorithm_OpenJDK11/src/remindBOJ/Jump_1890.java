package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jump_1890 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];

        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)  map[i][j] = Integer.parseInt(st.nextToken());
        }
        br.close();
        solution(n,map);
    }

    private static void solution(int n, int[][] map) {
        long[][] dp = new long[n][n];
        dp[0][0] =1;
        for(int y=0; y<n; y++) {
            for(int x=0; x<n; x++) {
                if(dp[y][x] !=0 && map[y][x] !=0) { // 왜 이렇게 되는지 생각..
                    int step = map[y][x];
                    if(isInArea(x+step,y,n))    dp[y][x+step] += dp[y][x];
                    if(isInArea(x,y+step,n))    dp[y+step][x] += dp[y][x];
                }
            }
        }
        System.out.println(dp[n-1][n-1]);
    }

    private static boolean isInArea(int x,int y, int n) {
        return x<n && y<n;
    }
}
