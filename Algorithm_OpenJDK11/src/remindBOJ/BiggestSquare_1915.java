package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BiggestSquare_1915 {
    static int[] dx = {-1,-1,0}, dy = {-1,0,-1};

    final static int ZERO_Character = '0', ZERO = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n+1][m+1];

        for(int i=1; i<=n; i++) {
            char[] inputArr = br.readLine().toCharArray();
            for(int j=1; j<=m; j++) {
                map[i][j] = inputArr[j-1] - ZERO_Character;
            }
        }
        br.close();
        solution(n,m,map);
    }

    private static void solution(int n, int m, int[][] map) {
        int max = 1;
        for(int y=1; y<=n; y++) {
            for(int x=1; x<=m; x++) {
                if(map[y][x] != ZERO) {
                    int min = Integer.MAX_VALUE;
                    for (int i = 0; i < 3; i++) {
                        int nx = x + dx[i], ny = y + dy[i];
                        min = Math.min(map[ny][nx], min);
                    }
                    map[y][x] += min;
                    max = Math.max(max, map[y][x]);
                }
            }
        }
        System.out.println(max*max);
    }
}
