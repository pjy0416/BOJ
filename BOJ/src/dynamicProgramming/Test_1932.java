package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Test_1932 {
    // n이 3 이상일 경우부터
    // 자기 자신의 위치에서
    // 자신의 부모가 될 수 있는 인덱스 중 최댓값과 자기 자신의 인덱스에 있는 수를 더해준다.
    // 마지막 열을 오름차순으로 정렬해주고
    // 마지막 인덱스에 있는 것을 가져오면 그게 최댓값이 됨
    public static void main(String[] args) throws IOException {
        int[][] dp;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int line = Integer.parseInt(br.readLine());
        dp = new int[line+1][line+1];
        for(int i=1; i<=line; i++) {
            String inputStr = br.readLine();
            String[] numArr = inputStr.split(" ");
            if(i ==1) {
                dp[1][1] = Integer.parseInt(inputStr);
                continue;
            } else if(i ==2) {
                dp[2][1] = dp[1][1] + Integer.parseInt(numArr[0]);
                dp[2][2] = dp[1][1] + Integer.parseInt(numArr[1]);
                continue;
            } else {   // 3부터
                dp[i][1] = dp[i - 1][1] + Integer.parseInt(numArr[0]);
                dp[i][i] = dp[i - 1][i - 1] + Integer.parseInt(numArr[i - 1]);
                for (int j = 2; j < i; j++) {
                    dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + Integer.parseInt(numArr[j - 1]);
                }
            }
        }
        Arrays.sort(dp[line]);
        System.out.println(dp[line][line]);



        br.close();
    }
}
