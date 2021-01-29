package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Anagram_1919 {
    static final int ASCII_LOWERCASE_START = 97, MAX_LENGTH = 25;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] origin = getASCIICountArr(br.readLine().toCharArray());
        int[] target = getASCIICountArr(br.readLine().toCharArray());

        br.close();
        solution(origin, target);
    }

    private static void solution(int[] origin, int[] target) {
        int answer =0;
        for(int i=0; i<=MAX_LENGTH; i++) {
            answer += Math.abs(origin[i] - target[i]);
        }
        System.out.println(answer);
    }

    private static int[] getASCIICountArr(char[] chArr) {
        int[] asciiArr = new int[MAX_LENGTH+1];
        for(char ch : chArr) {
            asciiArr[ch-ASCII_LOWERCASE_START]++;
        }
        return asciiArr;
    }
}
