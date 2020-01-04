package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Command17838 {
    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int caseNum = br.read() -48;
        br.readLine();

        for(int i=0; i<caseNum; i++) {
            int result =0;

            String inputStr = br.readLine();
            if(check1st(inputStr) && check2nd(inputStr)) {
                result =1;
            }
            System.out.println(result);
        }

        br.close();
    }

    static boolean check1st(String command) {
        return command.length() == 7 ? true : false;
    }

    static boolean check2nd(String command) {
        boolean result =false;

        char ch1, ch2;
        ch1 = command.charAt(0);
        ch2 = command.charAt(2);

        if(ch1 == command.charAt(1) && ch2 == command.charAt(3)) {  // 첫번째 조건
            if(ch1 == command.charAt(4) && (command.charAt(5) == command.charAt(6) && command.charAt(6) == ch2) ) {
                result = true;
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {

        solution();
    }
}
