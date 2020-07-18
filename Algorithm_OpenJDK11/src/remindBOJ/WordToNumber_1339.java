package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class WordToNumber_1339 {
    final static int MAX = 10, MAX_ALPHABET = 26;
    final static char A = 'A';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] alphabets = new int[MAX_ALPHABET];

        for(int i=0; i<n; i++) {
            char[] wordArr = br.readLine().toCharArray();
            int len = wordArr.length;
            for(int j=0; j<len; j++) {
                alphabets[wordArr[j]-A] += Math.pow(MAX, len-j-1);
            }
        }
        br.close();
        solution(alphabets);
    }

    private static void solution(int[] wordArr) {
        int answer =0;
        Arrays.sort(wordArr);
        int idx = MAX_ALPHABET-1;
        for(int i=MAX-1; i>=0; i--) {
            answer += wordArr[idx--] * i;
        }
        System.out.println(answer);
    }
}
