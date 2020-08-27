package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WeirdMultiply_1225 {
    final static char ZERO = '0';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] numStrArr = br.readLine().split(" ");
        br.close();
        solution(numStrArr[0].toCharArray(), numStrArr[1].toCharArray());
    }

    private static void solution(char[] chArr_A, char[] chArr_B) {
        long answer =0;
        for(char a : chArr_A) {
            long numA = a-ZERO;
            for(char b : chArr_B) answer += numA*(b-ZERO);
        }
        System.out.println(answer);
    }
}
