package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CMD_1032 {
    final static char QUESTION_MARK = '?';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[][] chArr = new char[n][];

        for(int i=0; i<n; i++) {
            chArr[i] = br.readLine().toCharArray();
        }
        br.close();
        solution(n, chArr);
    }

    private static void solution(int n, char[][] chArr) {
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<chArr[0].length; i++) {
            char prev = chArr[0][i];
            for(int j=0; j<n; j++) {
                if(prev != chArr[j][i]) {
                    prev = QUESTION_MARK;
                    break;
                }
            }
            sb.append(prev);
        }
        System.out.println(sb.toString());
    }
}
