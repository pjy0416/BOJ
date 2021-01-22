package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DistributedProcessing_1009 {
    private final static String NEW_LINE = "\n";
    private final static int MOD = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCases = Integer.parseInt(br.readLine());

        while(testCases-- >0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(solution(a,b)).append(NEW_LINE);
        }
        br.close();
        System.out.println(sb.toString());
    }

    private static int solution(int a, int b) {
        int num =1;
        a %= MOD;

        for(int i=1; i<=b; i++) {
            num = (num * a) % MOD;
        }

        return num == 0 ? MOD : num;
    }
}
