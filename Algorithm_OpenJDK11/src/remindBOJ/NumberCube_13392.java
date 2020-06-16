package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class NumberCube_13392 {
    final static int[] screw = {0,1,2,3,4,5,6,7,8,9};
    final static int ZERO = 48, MAX = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());
        int[] cube = new int[size+1];
        int[] target = new int[size+1];

        String str1 = br.readLine();
        String str2 = br.readLine();

        for(int i=1; i<=size; i++) {
            cube[i] = str1.charAt(i-1)-ZERO;
            target[i] = str2.charAt(i-1)-ZERO;
        }
        br.close();

        System.out.println(solution(cube,target));
    }

    private static int solution(int[] cube, int[] target) {
        int[][] dp = new int[cube.length][10];
        for(int i=0; i<cube.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for(int i=0; i<MAX; i++) {
            dp[0][i] = i;
        }
        for(int i=1; i<cube.length; i++) {
            for(int j=0; j<MAX; j++) {
                int left = (target[i] - cube[i] -j +20)%10;
                int right = 10-left;
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + right);
                dp[i][(j+left)%MAX] = Math.min(dp[i][(j+left)%MAX], dp[i-1][j] + left);
            }
        }
        int answer = dp[cube.length-1][0];

        for(int i=1; i<MAX; i++) {
            answer = Math.min(answer, dp[cube.length-1][i]);
        }
        return answer;
    }
}

/*
3
328
479

7이 나와야하는데, 위의 코드는 9가 나옴
 */
