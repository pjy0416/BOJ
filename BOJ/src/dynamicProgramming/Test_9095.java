package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_9095 {
    /** Ref : https://hrothgar.tistory.com/33
     *  case에 수 하나만 들어가도 되는지 몰랐음... ex) n=1 -> 1이 되기때문에 case가 하나 존재..!
    */
    private static int getResult(int num) {
        if(num ==1) {
            return 1;
        } else if(num ==2) {
            return 2;
        } else if(num ==3) {
            return 4;
        }

        int[] caseNum = new int[11];
        caseNum[0] =0;
        caseNum[1] =1;
        caseNum[2] =2;
        caseNum[3] =4;

        for(int i =4; i<=num; i++) {
            caseNum[i] = caseNum[i-3] + caseNum[i-2] + caseNum[i-1];
        }

        return caseNum[num];
    }

    private static void printCase(int num) {
        System.out.println(getResult(num));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        for(int i=0; i<testCase; i++) {
            int num = Integer.parseInt(br.readLine());
            printCase(num);
        }

        br.close();
    }
}
