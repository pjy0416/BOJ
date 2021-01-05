package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LongestBitonicSequence_11054 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] sequence = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }
        br.close();
        solution(n, sequence);
    }

    private static void solution(int n, int[] sequence) {
        int[] lis = getLIS(n,sequence);
        int[] lds = getLDS(n,sequence);

        int answer =0;
        for(int i=0; i<n; i++) {
            answer = Math.max(answer, lis[i]+lds[i]);
        }

        System.out.println(answer-1);
    }

    private static int[] getLIS(int n, int[] sequence) { // 최장 증가 부분 수열
        int[] lis = new int[n];
        for(int i=0; i<n; i++) {
            lis[i] = 1;
            for(int j=0; j<i; j++) {
                // j번째 원소가 i번째 원소보다 작고 dp[i]가 dp[j] +1 값보다 작은경우
                if(sequence[j] < sequence[i] && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                }
            }
        }
        return lis;
    }

    private static int[] getLDS(int n, int[] sequence) { // 최장 감소 부분 수열
        int[] lds = new int[n];
        for(int i=n-1; i>=0; i--) {
            lds[i] =1;
            for(int j=n-1; j>i; j--) {
                if(sequence[j] < sequence[i] && lds[i] < lds[j] +1) {
                    lds[i] = lds[j]+1;
                }
            }
        }
        return lds;
    }
}

/* Reference
 * https://st-lab.tistory.com/136
 */
