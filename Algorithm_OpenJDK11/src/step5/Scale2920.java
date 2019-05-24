package step5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Scale2920 {
    static String scaleStr;
    static String[] results = {"ascending", "descending", "mixed"};

    private static String getResult() {
        int firstNum = scaleStr.charAt(0)-48;
        String[] scales = scaleStr.split(" ");
        switch(firstNum) {
            case 1:
                int num = firstNum;
                for(String numStr : scales) {
                    int tmp = Integer.parseInt(numStr);
                    if(tmp == num) {
                        num++;
                    } else {
                        return results[2];
                    }
                }
                return results[0];

            case 8:
                num = firstNum;
                for(String numStr : scales) {
                    int tmp = Integer.parseInt(numStr);
                    if(tmp == num) {
                        num--;
                    } else {
                        return results[2];
                    }
                }
                return results[1];

            default :
                return results[2];
        }
    }

    private static void printResult() {
        System.out.println(getResult());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        scaleStr = br.readLine();

        printResult();

        br.close();
    }
}
