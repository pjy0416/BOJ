package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Eureka_10448 {
    static int[] numArr;
    static int answer;
    static boolean isFound;

    public static void main(String[] args) throws IOException {
        solution(new BufferedReader(new InputStreamReader(System.in)));
    }

    private static void solution(BufferedReader br) throws IOException {
        final int MOVE = 3, FOUND = 1, NOTFOUND=0;
        final String NEWLINE = "\n";
        int testCase = Integer.parseInt(br.readLine());
        initNumArr();
        StringBuffer sb = new StringBuffer();

        for(int i=0; i<testCase; i++) {
            isFound =false;
            dfs(Integer.parseInt(br.readLine()), MOVE, 0);
            if(isFound) { sb.append(FOUND).append(NEWLINE); }
            else { sb.append(NOTFOUND).append(NEWLINE); }
        }
        System.out.println(sb.toString());

        br.close();
    }

    private static void initNumArr() {
        final int MAX = 1000;
        numArr = new int[MAX+1];
        for(int i=1; i<=MAX; i++) {
            numArr[i] = numArr[i-1]+i;
        }
    }

    private static void dfs(int n, int cnt, int sum) {
        if(isFound) {
            return;
        }
        if(cnt ==0) {
            if(sum == n) {
                answer++;
                isFound = true;
            }
            return;
        }
        for(int i=1; i<=n; i++) {
            dfs(n, cnt-1, sum+numArr[i]);
        }
    }
}
