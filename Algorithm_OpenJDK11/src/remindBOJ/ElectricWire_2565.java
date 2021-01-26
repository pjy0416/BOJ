package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ElectricWire_2565 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Wire[] wires = new Wire[n];
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            wires[i] = new Wire(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        br.close();
        Arrays.sort(wires);
        solution(wires, n);
    }

    private static void solution(Wire[] wires, int n) {
        int[] dp = new int[n];
        int maxLine = 0;

        for(int i=0; i<n; i++) {
            dp[i] =1;
            for(int j=0; j<i; j++) {
                if(wires[i].right > wires[j].right) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            maxLine = Math.max(dp[i], maxLine);
        }

        System.out.println((n-maxLine));
    }

    static class Wire implements Comparable<Wire> {
        int left, right;

        public Wire(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Wire w) {
            return this.left - w.left;
        }
    }
}
