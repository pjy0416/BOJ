package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_1003 {
    /** 재귀함수로 돌리면 시간초과가 뜸
     *  따라서 관계식을 찾아야 함
     *  점화식 D[n] = D[n-1] + D[n-2] 라는 결과가 나온다.
     *  Ref : http://gnujoow.github.io/boj/2016/05/16/BOJ2-1003/
    */
    static int[] result = new int[40];

    private static int fibonacci(int n) {
        if (n <= 1) {
            return result[n];
        } else {
            if(result[n] >0) {
                return result[n];
            }
        }
        return result[n] = fibonacci(n-1) + fibonacci(n-2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        result[0] = 1;
        result[1] = 1;

        for(int i=0; i<testCase; i++) {
            int num =Integer.parseInt(br.readLine());
            if(num ==0) {
                System.out.println("1 0");
            } else if(num ==1) {
                System.out.println("0 1");
            } else {
                fibonacci(num);
                System.out.println(result[num-2] + " " + result[num-1]);
            }
        }

        br.close();
    }
}
