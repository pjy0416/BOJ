package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LIS_11053 {
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
        int[] dp = new int[n];
        int answer =0;
        for(int i=0; i<numArr.length; i++) dp[i] = 1;

        for(int i=0; i<numArr.length; i++) {
            for(int j=i+1; j<numArr.length; j++) {
                if(numArr[i] <numArr[j])  /* then */ dp[j] = Math.max(dp[j], dp[i]+1);
            }
            answer = Math.max(dp[i],answer);
        }

        System.out.println(answer);
    }
}