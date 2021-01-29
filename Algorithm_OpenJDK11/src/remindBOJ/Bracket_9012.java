package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bracket_9012 {
    static final int DEFAULT_COUNT =0;
    static final char OPEN_PARENTHESIS = '(';
    static final String NEW_LINE = "\n", SUCCESS = "YES", FAIL = "NO";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<n; i++) {
            sb.append(solution(br.readLine().toCharArray())).append(NEW_LINE);
        }
        br.close();
        System.out.println(sb.toString());
    }

    private static String solution(char[] parenthesis) {
        int open =DEFAULT_COUNT;
        for(char ch : parenthesis) {
            if(ch == OPEN_PARENTHESIS) {
                open++;
            } else {
                if(open == DEFAULT_COUNT) {
                    return FAIL;
                }
                open--;
            }
        }
        return open == DEFAULT_COUNT ? SUCCESS : FAIL;
    }
}
