package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CardCost_11052 {
    public static void main(String[] args) throws IOException {
        solution(new BufferedReader(new InputStreamReader(System.in)));
    }

    private static void solution(BufferedReader br) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] cards = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=1; i<=n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<=n; i++) {
            int mid = i/2;
            if(i%2 ==0) {
                cards[i] = Math.max(cards[i], cards[mid]*2);
                mid++;
            }
            for(int j=mid; j<i; j++) {
                cards[i] = Math.max(cards[j]+cards[i-j], cards[i]);
            }
        }

        System.out.println(cards[n]);
        br.close();
    }
}
