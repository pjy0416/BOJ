package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LDS_11722 {    // Longest Decreasing subsequence
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numArr = new int[n];

        for(int i=0; i<n; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }
        br.close();
        solution(n,numArr);
    }

    private static void solution(int n, int[] numArr) {
        int[] dp = new int[n];
        int answer =0;

        for(int i=0; i<n; i++) {
            dp[i] = Math.max(dp[i], 1);
            for(int j = i+1; j<n; j++) {
                if(numArr[i] > numArr[j])   dp[j] = Math.max(dp[j], dp[i]+1);
            }
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(answer);
    }
}
