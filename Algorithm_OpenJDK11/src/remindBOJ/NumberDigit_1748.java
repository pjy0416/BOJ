package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NumberDigit_1748 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String numStr = br.readLine();
        int digit = numStr.length();
        int n = Integer.parseInt(numStr);
        br.close();

        solution(n,digit);
    }

    private static void solution(int n,int digit) {
        int div = (int) Math.pow(10,digit-1);
        int answer = (n-div +1)*digit;
        for(int i=digit-1; i>0; i--) {
            answer += 9*Math.pow(10, i-1)*i;
        }
        System.out.println(answer);
    }
}
