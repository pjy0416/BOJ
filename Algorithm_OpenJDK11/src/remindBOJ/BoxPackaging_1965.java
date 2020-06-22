package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BoxPackaging_1965 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] numArr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(numArr,n));

        br.close();
    }

    private static int solution(int[] numArr, int n) {
        int answer =0;
        int[] dp = new int[n];

        for(int i=0; i<n; i++) {
            for(int j =0; j<i; j++) {
                if(numArr[i] > numArr[j]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            answer = Math.max(answer, ++dp[i]);
        }

        return answer;
    }
}
//https://www.acmicpc.net/problem/1965