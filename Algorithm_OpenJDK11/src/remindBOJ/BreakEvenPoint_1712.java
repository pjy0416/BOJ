package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BreakEvenPoint_1712 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int fixed = Integer.parseInt(st.nextToken());
        int variable = Integer.parseInt(st.nextToken());
        int price = Integer.parseInt(st.nextToken());

        solution(fixed,variable,price);

        br.close();
    }

    private static void solution(int fixed, int variable, int price) {
        if(price-variable >0) {
            System.out.println((fixed/(price-variable)+1));
        } else {
            System.out.println(-1);
        }
    }
}
