package step5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class NumOfNumeric {     // 0~9 까지 숫자 몇개 나오는지 count
    public static int A,B,C;
    public static int[] cnts;

    private static void getResult() {
        int multi = A*B*C;

        while(multi!=0) {
            cnts[multi%10]++;
            multi /=10;
        }
    }

    private static void printResult() {
        getResult();

        for(int i=0; i<10; i++) {
            System.out.println(cnts[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        A = Integer.parseInt(br.readLine());
        B = Integer.parseInt(br.readLine());
        C = Integer.parseInt(br.readLine());

        cnts = new int[10];
        printResult();

        br.close();
    }
}
