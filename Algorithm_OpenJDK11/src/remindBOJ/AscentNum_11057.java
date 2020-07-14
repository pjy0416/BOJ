package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AscentNum_11057 {
    private final static int MAX = 10;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        br.close();

        solution(n);
    }

    private static void solution(int n) {
        int[][] map = new int[n+1][MAX];
        final int MOD = 10007;
        int answer =0;

        for(int i=0; i<MAX; i++) {
            map[1][i] =1;
        }

        for(int i=2; i<=n; i++) {
            for(int j=0; j<MAX; j++) {
                for(int k=j; k<MAX; k++) {
                    map[i][j] = (map[i][j] + map[i - 1][k]) % MOD;
                }
            }
        }
        for(int i=0; i<MAX; i++) {
            answer = (answer+map[n][i])%MOD;
        }
        System.out.println(answer);
    }
}
