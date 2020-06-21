package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ChickenCoupon_1673 {
    public static void main(String[] args) throws IOException {
        solution(new BufferedReader(new InputStreamReader(System.in)));
    }

    private static void solution(BufferedReader br) throws IOException {
        String inputStr = "";
        while((inputStr = br.readLine()) != null) {
            System.out.println(getChickens(new StringTokenizer(inputStr)));
        }

        br.close();
    }

    private static long getChickens(StringTokenizer st) {
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int result =n;
        while(n/k !=0) {
            result += n/k;
            n = n/k + n%k;
        }
        return result;
    }
}
