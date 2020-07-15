package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CoinZero_11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n];
        for(int i=0; i<n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        br.close();
        solution(n,k,coins);
    }

    private static void solution(int n, int k, int[] coins) {
        int cnt =0;
        for(int i=n-1; i>=0; i--) {
            if(k==0) {
                break;
            }
            cnt += k/coins[i];
            k %= coins[i];
        }
        System.out.println(cnt);
    }
}