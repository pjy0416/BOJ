package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BookLoan_16497 {
    public static void main(String[] args) throws IOException {
        System.out.println(solution(new BufferedReader(new InputStreamReader(System.in))));
    }

    private static int solution(BufferedReader br) throws IOException {
        int answer =0;
        int[] dateArr = new int[32];
        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start =Integer.parseInt(st.nextToken());
            int end =Integer.parseInt(st.nextToken());
            for(int idx = start; idx<end; idx++) {
                answer = Math.max(++dateArr[idx],answer);
            }
        }
        int K = Integer.parseInt(br.readLine());

        return answer <=K ? 1 : 0;
    }
}
