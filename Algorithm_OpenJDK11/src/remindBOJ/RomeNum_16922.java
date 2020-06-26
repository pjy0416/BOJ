package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RomeNum_16922 {
    private final static int[] NUMARR = {1,5,10,50};
    private final static int SIZE = 4;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        solution(Integer.parseInt(br.readLine()));
    }

    private static void solution(int n) {
        answer=0;
        goSum(n,0, 0, new boolean[1001]);
        System.out.println(answer);
    }

    private static void goSum(int n, int sum, int idx, boolean[] visit) {
        if(n ==0) {
            if(!visit[sum]) {
                visit[sum] = true;
                answer++;
            }
            return;
        }
        for(int i=idx; i<SIZE; i++) {
            goSum(n-1, sum+NUMARR[i], i, visit);
        }
    }

}
