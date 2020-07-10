package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Thirty_10610 {
    final static char ZERO = '0';
    final static String FAIL = "-1";
    private static int THIRTY = 30, MAX_VALUE = 9, DIV = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String numStr = br.readLine();
        br.close();

        solution(numStr);
    }

    private static void solution(String numStr) {
        int[] numArr = new int[MAX_VALUE+1];
        int sum = 0;
        for(int i=0; i<numStr.length(); i++) {
            int num = numStr.charAt(i)-ZERO;
            numArr[num]++;
            sum = (sum+num)%DIV;
        }
        if(numArr[0] == 0 || sum != 0) {
            System.out.println(FAIL);
        } else {
            StringBuffer sb = new StringBuffer();
            for(int i=MAX_VALUE; i>=0; i--) {
                for(int j=0; j<numArr[i]; j++) {
                    sb.append(i);
                }
            }
            System.out.println(sb.toString());
        }
    }
}
