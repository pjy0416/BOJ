package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_2156 {
    /**2579번 문제인 계단 오르기 최대합과 똑같이 접근 했었다.
     * 해당 코드로 하면 최댓값이 안나오는데
     * 그 이유는 구간의 차이다
     * 검색하는 구간이 최대 값이 없는 구간이었기 때문
     * 따라서 이번 코드는 그 구간을 더 늘려주었을 뿐.
     * 어렵다.....
     * Ref : https://wootool.tistory.com/94
     */
    public static void main(String[] args) throws IOException {
        int[] dp;
        int[] glass;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int glassNum = Integer.parseInt(br.readLine());

        dp = new int[10001];
        glass = new int[10001];

        for(int i=1; i<=glassNum; i++) {
            glass[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = glass[1];
        dp[2] = glass[1] + glass[2];

        for(int i=3; i<=glassNum; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2]+glass[i]);
            dp[i] = Math.max(dp[i], glass[i]+glass[i-1]+dp[i-3]);
        }

        System.out.println(dp[glassNum]);

        br.close();
    }
}
