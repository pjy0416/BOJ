package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GoldBach_9020 {
    final static String SPACE= " ", NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()), max =0;
        int[] numArr = new int[n];

        for(int i=0; i<n; i++) {
            int num = Integer.parseInt(br.readLine());
            numArr[i] = num;
            max = Math.max(max, num);
        }

        br.close();
        solution(numArr, max);
    }

    private static void solution(int[] numArr, int max) {
        boolean[] prime = initPrimeArray(max);
        StringBuilder sb = new StringBuilder();

        for(int num : numArr) {
            int[] goldBach = getGoldBachPartitions(num, prime);
            sb.append(goldBach[0]).append(SPACE).append(goldBach[1]).append(NEW_LINE);
        }
        System.out.println(sb.toString());
    }

    private static int[] getGoldBachPartitions(int num, boolean[] prime) {
        int left = num/2, right = left;
        boolean isGoldBachPartitions = prime[left] && prime[right];

        while(!isGoldBachPartitions) {
            left--;
            right++;
            isGoldBachPartitions = prime[left] && prime[right];
        }

        return new int[] {left, right};
    }

    private static boolean[] initPrimeArray(int n) {
        boolean[] prime = new boolean[n];
        Arrays.fill(prime, true);

        for(int i=2; i<n; i++) {
            if(prime[i]) {
                for(int j= i*i; j<n; j+=i) {
                    prime[j] = false;
                }
            }
        }

        return prime;
    }
}
