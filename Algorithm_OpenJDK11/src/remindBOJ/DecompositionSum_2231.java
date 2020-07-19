package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DecompositionSum_2231 {
    final static int MAX_NUM = 9, DIGIT_SCALE = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = br.readLine();
        int n = Integer.parseInt(inputStr);
        int len = inputStr.length();
        br.close();
        solution(n,len);
    }

    private static void solution(int n, int len) {
        int answer = 0;
        for(int i=n-len*MAX_NUM; i<n; i++) {
            if(getSum(i) == n) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }

    private static int getSum(int n) {
        int tmp =n;
        while(tmp !=0) {
            n += tmp%DIGIT_SCALE;
            tmp /=DIGIT_SCALE;
        }
        return n;
    }
}
