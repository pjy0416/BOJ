package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sticker_9465 {
    final static int lines = 2;

    public static void main(String[] args) throws IOException {
        solution(new BufferedReader(new InputStreamReader(System.in)));
    }

    private static void solution(BufferedReader br) throws IOException {
        int testCase = Integer.parseInt(br.readLine());
        for(int i=0; i<testCase; i++) {
            int n = Integer.parseInt(br.readLine());
            int[][] map = new int[lines][n+1];

            for(int j=0; j<lines; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int k=1; k<=n; k++) {
                    map[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            System.out.println(getMaxScore(map,n));
        }
        br.close();
    }

    private static int getMaxScore(int[][] map, int n) {
        int[][] dp = new int[lines][n+1];
        dp[0][1] = map[0][1];
        dp[1][1] = map[1][1];

        for(int x=2; x<=n; x++) {
            dp[0][x] = Math.max(dp[1][x-1], dp[1][x-2]) + map[0][x];
            dp[1][x] = Math.max(dp[0][x-1], dp[0][x-2]) + map[1][x];
        }

        return Math.max(dp[1][n],dp[0][n]);
    }
}
