package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_11726 {
    static int[] ways = new int[1001];

    private static void printWays(int num) {
        if(num ==1) {
            System.out.println(1);
        } else {
            ways[0] =1;
            ways[1] =1;
            for(int i=2; i<=num; i++) {
                ways[i] = (ways[i-1] + ways[i-2])%10007;
            }
            System.out.println(ways[num]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());

        printWays(num);

        br.close();
    }
}
