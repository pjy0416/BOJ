package steps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class OXQuiz8958 {
    public static String[] results;

    private static void getResult() {
        for(String result : results) {
            printResult(result);
        }
    }

    private static void printResult(String result) {
        int len = result.length();
        int total =0;
        int addScore =0;
        for(int i=0; i<len; i++) {
            char ch = result.charAt(i);
            if(ch =='O') {
                addScore++;
            } else {
                addScore =0;
            }
            total +=addScore;
        }

        System.out.println(total);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        results = new String[testCase];

        for (int i = 0; i < testCase; i++) {
            results[i] = br.readLine();
        }

        getResult();

        br.close();
    }
}
