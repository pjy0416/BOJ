package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ATM_11399 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] lines = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            lines[i] = Integer.parseInt(st.nextToken());
        }
        br.close();
        solution(n,lines);
    }

    private static void solution(int n, int[] lines) {
        int answer=0;

        Arrays.sort(lines);
        for(int i=1; i<=n; i++) {
            lines[i] +=lines[i-1];
            answer += lines[i];
        }
        System.out.println(answer);
    }
}
