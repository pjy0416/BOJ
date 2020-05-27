package programmers;

public class Navigation_1832 {
    static int MOD = 20170805;
    private static int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = inItDp(cityMap);
        dp = findRoutes(dp, cityMap);

        return dp[m][n][0];
    }

    private static int[][][] inItDp(int[][] cityMap) {
        int[][][] result = new int[cityMap.length+1][cityMap[0].length+1][2];
        result[1][1][0] =1;
        result[1][1][1] =1;

        return result;
    }

    private static int[][][] findRoutes(int[][][] dp, int[][] cityMap) {
        for(int y=1; y<dp.length; y++) {
            for(int x=1; x<dp[y].length; x++) {
                if(cityMap[y-1][x-1] ==0) {
                    dp[y][x][0] += (dp[y-1][x][0] + dp[y][x-1][1]) % MOD;
                    dp[y][x][1] += (dp[y-1][x][0] + dp[y][x-1][1]) % MOD;
                } else if(cityMap[y-1][x-1] ==1) {   // 통행 불가
                    dp[y][x][0] =0;
                    dp[y][x][1] =0;
                } else { // 좌 / 우회전 불가
                    dp[y][x][0] = dp[y-1][x][0];
                    dp[y][x][1] = dp[y][x-1][1];
                }
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        int m = 3;
        int n = 6;
        int[][] cityMap = {{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}};

        System.out.println(solution(m,n,cityMap));
    }
}
