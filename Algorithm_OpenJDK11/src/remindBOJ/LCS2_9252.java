package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LCS2_9252 {
//    private static class Pos {
//        int x, y, len;
//
//        public Pos(int x, int y, int len) {
//            this.x = x;
//            this.y = y;
//            this.len = len;
//        }
//
//        public void set(int x, int y, int len) {
//            this.x = x;
//            this.y = y;
//            this.len = len;
//        }
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] left = br.readLine().toCharArray();
        char[] right = br.readLine().toCharArray();

        br.close();
        solution(left,right);
    }

//    private static void solution(char[] left, char[] right) {
//        int n = left.length;
//        int m = right.length;
//        String[][] dp = new String[n+1][m+1];
//        Pos pos = new Pos(0,0,0);
//
//        for(int i=0; i<=n; i++) {
//            Arrays.fill(dp[i], "");
//        }
//
//        for(int i=1; i<=n; i++) {
//            for(int j=1; j<=m; j++) {
//                Pos max = new Pos(0,0,0);
//                if(left[i-1] == right[j-1]) {
//                    StringBuilder sb = new StringBuilder(dp[i-1][j-1]);
//                    sb.append(left[i-1]);
//                    dp[i][j] = sb.toString();
//                    max.set(i,j,dp[i][j].length());
//                } else {
//                    if(dp[i][j-1].length() > dp[i-1][j].length()) {
//                        dp[i][j] = dp[i][j-1];
//                    } else {
//                        dp[i][j] = dp[i-1][j];
//                    }
//                }
//                if(max.len > pos.len) {
//                    pos = max;
//                }
//            }
//        }
//
//        System.out.println(pos.len);
//        System.out.println(dp[pos.x][pos.y]);
//    }

    private static void solution(char[] left, char[] right) {
        StringBuilder sb = new StringBuilder();
        int n = left.length;
        int m = right.length;
        int[][] dp = new int[n+1][m+1];

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(left[i-1] == right[j-1]) {
                    dp[i][j] = dp[i-1][j-1]+1;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        System.out.println(dp[n][m]);

        while(n!=0 && m !=0) {
            if(left[n-1] == right[m-1]) {
                sb.append(left[--n]);
                m--;
            } else if(dp[n][m] == dp[n-1][m]) {
                n--;
            } else if(dp[n][m] == dp[n][m-1]) {
                m--;
            }
        }
        System.out.println(sb.reverse().toString());
    }
}
