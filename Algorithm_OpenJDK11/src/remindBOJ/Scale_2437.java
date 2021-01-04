package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Scale_2437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] scaleArray = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            scaleArray[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
        solution(scaleArray);
    }

    private static void solution(int[] scaleArray) {
        Arrays.sort(scaleArray);
        int answer =1;

        for(int scale : scaleArray) {
            if(answer < scale) {
                break;
            }
            answer += scale;
        }
        System.out.println(answer);
    }
}
