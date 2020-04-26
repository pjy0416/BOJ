package swMaestro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SecondTest1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] mandarins = new int[n];
        String inputStr = br.readLine();
        StringTokenizer st = new StringTokenizer(inputStr);

        for(int i=0; i<n; i++) {
            int num = Integer.parseInt(st.nextToken());
            mandarins[i] = num;
        }

        long k = solution(n, mandarins);

        System.out.println(k);
    }

    private static long solution(int n, int[] mandarins) {
        return getMax(n, mandarins);
    }
    private static long getMax(int n, int[] mandarins) {
        long partialSum =mandarins[0];
        long max =mandarins[0];

        for(int i=1; i<n; i++) {
            partialSum = Math.max(0, partialSum) + mandarins[i];
            max = partialSum > max ? partialSum : max;
        }

        return max;
    }
}

