package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindPrimeNums_4948 {
    final static String END = "0";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = "";

        while(!(inputStr = br.readLine()).equals(END)) {
            int n = Integer.parseInt(inputStr);
            System.out.println(getCnt(n));
        }
    }

    private static int getCnt(int n) {
        int cnt =0;
        boolean[] prime = new boolean[n*2+1];

        for(int i=2; i<=2*n; i++) {
            if(!prime[i]) {
                if(i >n) {
                    cnt++;
                }
                for(int j=i*2; j<=2*n; j+=i) {
                    prime[j] = true;
                }
            }
        }
        return cnt;
    }
}
// https://www.acmicpc.net/problem/4948