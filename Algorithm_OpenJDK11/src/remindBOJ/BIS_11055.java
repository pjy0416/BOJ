package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BIS_11055 { // LIC(Longest Increasing Subsequence) 에서 착안하여 BIC로 네이밍
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] numArr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }
        br.close();
        solution(n, numArr);
    }

    private static void solution(int n, int[] numArr) {
        int answer =0;
        int[] dp = new int[n];
        for(int i=0; i<n; i++) {
            dp[i] = numArr[i];
            for(int j=0; j<i; j++) {
                if(numArr[i] > numArr[j]) {
                    dp[i] = Math.max(dp[j] + numArr[i], dp[i]);
                }
            }
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(answer);
    }
}