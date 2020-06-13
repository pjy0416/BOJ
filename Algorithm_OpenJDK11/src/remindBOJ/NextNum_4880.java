package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NextNum_4880 {
    final static String END = "0 0 0";
    final static int SIZE = 3;

    public static void main(String[] args) throws IOException {
        solution(new BufferedReader(new InputStreamReader(System.in)));
    }

    private static void solution(BufferedReader br) throws IOException{
        while(true) {
            String inputStr = br.readLine();

            if(inputStr.equals(END)) {
                break;
            }
            int[] numArr = new int[SIZE];
            StringTokenizer st = new StringTokenizer(inputStr);

            for(int i=0; i<SIZE; i++) {
                numArr[i] = Integer.parseInt(st.nextToken());
            }

            isAP(numArr);
            isGP(numArr);
        }
        br.close();
    }
    private static void isAP(int[] numArr) {
        StringBuffer sb = new StringBuffer("AP ");
        if(numArr[1] - numArr[0] == numArr[2] - numArr[1]) {
            int num = numArr[2] +numArr[1] - numArr[0];
            sb.append(num);
            System.out.println(sb.toString());
        }
    }

    private static void isGP(int[] numArr) {
        StringBuffer sb = new StringBuffer("GP ");
        if((double)numArr[1] / numArr[0] == (double)numArr[2] / numArr[1]) {
            int num = numArr[2] *(numArr[1] / numArr[0]);
            sb.append(num);
            System.out.println(sb.toString());
        }
    }
}
/*
https://www.acmicpc.net/problem/4880
 */