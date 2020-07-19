package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LostBracket_1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        solution(br.readLine());
        br.close();
    }

    private static void solution(String arithmeticStr) {
        String[] minusArr = arithmeticStr.split("\\-");
        int answer =0;
        for(int i=0; i<minusArr.length; i++) {
            int partSum = getCalNum(minusArr[i]);
            if(i ==0) { partSum *= -1;}
            answer -= partSum;
        }
        System.out.println(answer);
    }

    private static int getCalNum(String str) {
        String[] numArr = str.split("\\+");
        int result =0;
        for(String numStr : numArr) {
            result += Integer.parseInt(numStr);
        }
        return result;
    }
}
