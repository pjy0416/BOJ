package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SumGCD_9613 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        final String NEW_LINE = "\n";

        while(t-- >0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] numArr = initNumArray(n, st);
            sb.append(solution(n, numArr)).append(NEW_LINE);
        }
        br.close();
        System.out.println(sb.toString());
    }

    private static int[] initNumArray(int n, StringTokenizer st) {
        int[] numArr = new int[n];
        for(int i=0; i<n; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }
        return numArr;
    }

    private static long solution(int n, int[] numArr) {
        long answer =0;
        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                answer += gcd(numArr[i], numArr[j]);
            }
        }
        return answer;
    }

    private static int gcd(int left, int right) {
        while(right !=0) {
            int target = left;
            left = right;
            right = target % right;
        }
        return left;
    }
}
