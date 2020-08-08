package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Yutnori_2490 {
    final static int cases = 3;
    final static String flat = "0";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] cntArr = new int[cases];
        for(int i=0; i<cases; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cnt =0;
            while(st.hasMoreTokens()) {
                if(st.nextToken().equals(flat))     cnt++;
            }
            cntArr[i] = cnt;
        }
        br.close();
        solution(cntArr);
    }

    private static void solution(int[] cntArr) {
        int n =0;
        char[] yutArr = {'E', 'A', 'B', 'C', 'D'};
        StringBuffer sb = new StringBuffer();
        for(int cnt : cntArr) {
            sb.append(yutArr[cnt] + "\n");
        }
        System.out.println(sb.toString());
    }
}
