package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RepairMan_1449 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int len = Integer.parseInt(st.nextToken());
        final int tape = Integer.parseInt(st.nextToken());
        int[] holes = new int[len];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<len; i++) {
            holes[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        solution(len, holes, tape);
    }

    private static void solution(int len, int[] holes, int tape) {
        final double interval =0.5;
        double prev =0.0;
        int count =0;

        Arrays.sort(holes);

        for(int i=0; i<len; i++) {
            double now = holes[i];
            if(prev < now + interval) {
                count++;
                prev = now +tape - interval;
            }
        }
        System.out.println(count);;
    }
}

/*
2 3
1 1000
2

4 3
1 2 3 4
2

2 1
1 3
4

 */