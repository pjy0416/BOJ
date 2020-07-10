package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Rope_2217 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] ropes = new int[n];

        for(int i=0; i<n; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }
        br.close();
        solution(n,ropes);
    }

    private static void solution(int n, int[] ropes) {
        int answer = 0;
        Arrays.sort(ropes);

        for(int i=n-1; i>=0; i--) {
            int k = n-i;
            int w = ropes[i];
            answer = Math.max(answer, w*k);
        }
        System.out.println(answer);
    }
}
