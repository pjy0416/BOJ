package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SemiconductorDesign_2352 {     // LIS로 풀면 된다... ㅠ
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] semiArr = new int[n];

        for(int i=0; i<n; i++)  {
            int num = Integer.parseInt(st.nextToken());
            semiArr[i] = num;
        }
        br.close();
        solution(n,semiArr);
    }

    private static void solution(int n, int[] semiArr) {
        int answer =0;
        int[] dp = new int[n];
        for(int i=0; i<n; i++) {
            dp[i] = Math.max(dp[i],1);
            for(int j=i+1; j<n; j++) {
                if(semiArr[i] < semiArr[j])    dp[j] = Math.max(dp[j], dp[i]+1);
            }
            answer = Math.max(answer,dp[i]);
        }
        System.out.println(answer);
    }
}
