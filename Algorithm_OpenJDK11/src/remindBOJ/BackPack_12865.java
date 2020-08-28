package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BackPack_12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        int[][] baggageArr = new int[n][2];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<2; j++)  baggageArr[i][j] = Integer.parseInt(st.nextToken());
        }
        br.close();
        solution(k,baggageArr);
    }

    private static void solution(int k, int[][] baggageArr) {
        int[] dp = new int[k+1];
        for(int[] baggage : baggageArr) {
            int w = baggage[0], v = baggage[1];
            for(int i =k; i>=w; i--)    dp[i] = Math.max(dp[i], dp[i-w] +v);
        }
        System.out.println(dp[k]);
    }
}
