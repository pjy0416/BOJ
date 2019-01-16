import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_1463 {            // dynamic programming
                                    // 출처 : https://github.com/stack07142/BOJ/blob/master/BOJ%231463_SetTo1/src/Main.java
                                    // 참고 : http://stack07142.tistory.com/39
                                    // https://blockdmask.tistory.com/254
                                    // 1등 코드 : https://www.acmicpc.net/source/6082035
    static int[] dp = new int[1000001];

    static int go(int n) {

        if (n == 1) {
            return 0;
        }

        if (dp[n] > 0) {
            return dp[n];
        }

        // -1
        dp[n] = go(n - 1) + 1;

        // /3
        if (n % 3 == 0) {
            int temp;
            temp = go(n/3) + 1;

            if (dp[n] > temp) {
                dp[n] = temp;
            }
        }

        // /2
        if (n % 2 == 0) {
            int temp;
            temp = go(n / 2) + 1;

            if (dp[n] > temp) {
                dp[n] = temp;
            }
        }

        return dp[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        System.out.println(go(num));
        br.close();
    }
}
