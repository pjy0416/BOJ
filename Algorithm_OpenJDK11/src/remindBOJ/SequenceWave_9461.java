package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SequenceWave_9461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCases = parseInt(br.readLine());
        int[] indexArr = new int[testCases];
        int max =0;

        for(int i=0; i<testCases; i++) {
            int num = parseInt(br.readLine());
            indexArr[i] = num;
            max = Math.max(num, max);
        }
        br.close();
        solution(indexArr, max);
    }

    private static void solution(int[] indexArr, int maxIndex) {
        long[] dp = initDP(maxIndex);
        final String newLine = "\n";
        StringBuilder sb = new StringBuilder();
        Arrays.stream(indexArr).forEach(index -> sb.append(dp[index]).append(newLine)); // 람다 표현식이 for문보다 효율이 훨씬 안좋기는 하다..
        System.out.println(sb.toString());
    }

    private static long[] initDP(int maxNum) {
        final int minNum = 4;
        maxNum = Math.max(maxNum, minNum);
        long[] dp = new long[maxNum+1];
        dp[1] =1;
        dp[2] =1;
        dp[3] =1;

        for(int i=minNum; i<=maxNum; i++) {
            dp[i] = dp[i-2] + dp[i-3];
        }
        return dp;
    }

    private static int parseInt(String str) {
        return Integer.parseInt(str);
    }
}