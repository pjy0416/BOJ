package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_2579 {
    // 코드 : https://nackwon.tistory.com/67 이 사람꺼
    // 푼게 아님..
    // dp문제 다른거 풀고 풀자
    // 보류
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int steps = Integer.parseInt(br.readLine());
        int[] step = new int[steps+1];
        int[][] result = new int[steps+1][2];

        for(int i=1; i<=steps; i++) {
            step[i] = Integer.parseInt(br.readLine());
        }

        result[1][0] = result[1][1] = step[1];

        for(int i=2; i<=steps; i++) {
            result[i][0] = result[i-1][1] + step[i];
            result[i][1] = Math.max(result[i-2][0], result[i-2][1]) + step[i];
        }

        System.out.println(Math.max(result[steps][0], result[steps][1]));

        br.close();
    }
}
