package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Test_1149 {
    /**
     *   Ref : https://stack07142.tistory.com/99
     *   지금 까지의 결과 주로 DP 문제는 점화식과 그것을 구현하는 문제.
     */
    static final int RED =0;
    static final int GREEN =1;
    static final int BLUE =2;

    public static void main(String[] args) throws IOException {
        int size;
        int[][] color;
        int[][] dp;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        size = Integer.parseInt(br.readLine());
        color = new int [size][3];
        dp = new int [size][3];

        for(int i=0; i<3; i++) {
            Arrays.fill(dp[i], 0);
        }

        for(int i=0; i<size; i++) {
            String rgbStr = br.readLine();
            color[i][0] = Integer.parseInt(rgbStr.split(" ")[0]);
            color[i][1] = Integer.parseInt(rgbStr.split(" ")[1]);
            color[i][2] = Integer.parseInt(rgbStr.split(" ")[2]);
        }

        dp[0][RED] = color[0][RED];
        dp[0][GREEN] = color[0][GREEN];
        dp[0][BLUE] = color[0][BLUE];

        for(int i=1; i<size; i++) {
            for(int j=0; j<3; j++) {
                dp[i][j] = getMin(dp, i-1, j) + color[i][j];    // 자기 자신을 제외한 제일 싼 색상과 자신을 더함 (연달아 색칠 방지)
            }
        }
        System.out.println(Math.min(dp[size-1][RED], Math.min(dp[size-1][GREEN], dp[size-1][BLUE]) ));  // 위의 식대로 계속 색칠해온 애들 중 가장 최소인 애

        br.close();
    }

    private static int getMin(int[][] dp, int house, int color) {
        switch (color) {    // 각 색상에 대해서 자신을 제외한 최소 가격인 것을 리턴
            case RED :
                return Math.min(dp[house][GREEN],dp[house][BLUE]);
            case GREEN :
                return Math.min(dp[house][RED],dp[house][BLUE]);
            case BLUE :
                return Math.min(dp[house][GREEN],dp[house][RED]);
            default :
                return 55;
        }
    }
}
