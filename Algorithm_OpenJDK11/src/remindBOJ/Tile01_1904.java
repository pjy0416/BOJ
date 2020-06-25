package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tile01_1904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        solution(n);
        br.close();
    }

    private static void solution(int n) {   // fibonacci...
        if(n <=3) {
            System.out.println(n);
        } else {
            int left=1,right=2,now=3;
            final int mod = 15746;
            for(int i=4; i<=n; i++) {
                left = right;
                right = now;
                now = (left+right)%mod;
            }
            System.out.println(now);
        }
    }
}
