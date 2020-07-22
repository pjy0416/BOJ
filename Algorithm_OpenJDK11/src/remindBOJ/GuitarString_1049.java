package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GuitarString_1049 {
    private final static int STRING_PACKAGE = 6, ONE_STRING = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int packageCost = Integer.MAX_VALUE;
        int stringCost = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            packageCost = Math.min(packageCost, Integer.parseInt(st.nextToken()));
            stringCost = Math.min(stringCost, Integer.parseInt(st.nextToken()));
        }
        br.close();
        solution(n, packageCost, stringCost);
    }

    private static void solution(int n, int packageCost, int stringCost) {
        int answer =0;
        packageCost = Math.min(packageCost, stringCost*STRING_PACKAGE);
        while(n>0) {
            if(packageCost < stringCost*n) {
                answer += packageCost;
                n -= STRING_PACKAGE;
            } else {
                answer += stringCost*n;
                n = 0;
            }
        }
        System.out.println(answer);
    }
}