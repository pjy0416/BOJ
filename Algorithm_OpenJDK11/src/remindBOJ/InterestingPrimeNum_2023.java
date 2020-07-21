package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InterestingPrimeNum_2023 {
    final static String NEWLINE = "\n";
    final static int[] firstPrime = {2,3,5,7}, nextPrime = {1,3,7,9};
    final static int STANDARD = 10;
    static StringBuffer sb;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = br.read()-48;
        br.close();
        solution();
    }

    private static void solution() {
        sb = new StringBuffer();
        makePrimeNum(0, new StringBuffer());
        System.out.println(sb.toString());
    }

    private static void makePrimeNum(int cnt, StringBuffer listBuffer) {
        if(cnt >0 && listBuffer.length() >0) {
            if(!isPrime(listBuffer.toString())) { return; }
        }
        if(cnt == n) {
            if(listBuffer.length() == n) { sb.append(listBuffer.toString()).append(NEWLINE); }
            return;
        }
        if(cnt ==0) {
            for(int num : firstPrime) {
                listBuffer.append(num);
                makePrimeNum(cnt+1, listBuffer);
                listBuffer.deleteCharAt(listBuffer.length()-1);
            }
        } else {
            for(int num : nextPrime) {
                listBuffer.append(num);
                makePrimeNum(cnt+1, listBuffer);
                listBuffer.deleteCharAt(listBuffer.length()-1);
            }
        }
    }

    private static boolean isPrime(String numStr) {
        int num = Integer.parseInt(numStr);
        while(num /STANDARD !=0) {
            if(!isAnswer(num)) { return false ;}
            num /= STANDARD;
        }
        return true;
    }

    private static boolean isAnswer(int num) {
        for(int i=2; i*i<=num; i++) {
            if(num %i ==0) { return false; }
        }
        return true;
    }
}
