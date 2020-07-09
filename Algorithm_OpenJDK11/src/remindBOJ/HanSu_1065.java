package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HanSu_1065 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        solution(Integer.parseInt(br.readLine()));
        br.close();
    }

    private static void solution(int n) {
        int[] dp = new int[n+1];
        dp[1] =1;

        for(int i=2; i<=n; i++) {
            dp[i] += dp[i-1] + isHanSu(i);
        }
        System.out.println(dp[n]);
    }

    private static int isHanSu(int num) {
        if(num /100 ==0) {
            return 1;
        }
        String numStr= String.valueOf(num);
        char prev = numStr.charAt(1);
        int diff = numStr.charAt(0)-prev;

        for(int i=2; i<numStr.length(); i++) {
            char ch = numStr.charAt(i);
            if(prev-ch != diff) {
                return 0;
            }
            prev = ch;
        }
        return 1;
    }
}
