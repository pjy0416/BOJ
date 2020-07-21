package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class StringDifference_1120 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        solution(st.nextToken().toCharArray(), st.nextToken().toCharArray());
    }

    private static void solution(char[] left, char[] right) {
        int answer = right.length;
        int leftLen = left.length;
        int diff = answer-leftLen;
        for(int i=0; i<=diff; i++) {
            int cnt =0;
            for(int j=0; j<leftLen; j++) {
                if(left[j] != right[j+i]) { cnt++; }
            }
            answer = Math.min(cnt,answer);
        }
        System.out.println(answer);
    }

}
