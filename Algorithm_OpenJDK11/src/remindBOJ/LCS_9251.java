package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LCS_9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] left = br.readLine().toCharArray();
        char[] right = br.readLine().toCharArray();
        br.close();

        solution(left,right);
    }

    private static void solution(char[] left, char[] right) {
        int answer =0;
        int n = left.length;
        int m = right.length;
        int[][] dp = new int[n+1][m+1];

        for(int i=1; i<=n; i++) {
            int maxLen =0;
            for(int j=1; j<=m; j++) {
                if(left[i-1] == right[j-1]) {
                    dp[i][j] = dp[i-1][j-1]+1;
                    maxLen = dp[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
                answer = Math.max(answer,maxLen);
            }
        }
        System.out.println(dp[n][m]);
        System.out.println(answer);
    }
}

/*
LSC 알고리즘
str1 str2 에 대해서 각각의 길이 +1을 한 DP[][] 를 생성

1부터 str1의 길이까지 DP 인덱스를 도는 i 인덱스,
1부터 str2의 길이까지 DP 인덱스를 도는 j 인덱스

str1.charAt(i-1) == str2.charAt(j-1) 라면
dp[i][j] = dp[i-1][j-1] +1;

아니라면,
dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
 */