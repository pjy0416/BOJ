package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ContinueSum_1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int total = Integer.parseInt(br.readLine());
        String inputStr = br.readLine();
        br.close();

        System.out.println(solution(total,inputStr));
    }

    private static int solution(int total, String inputStr) {

        StringTokenizer st = new StringTokenizer(inputStr);
        int max = Integer.parseInt(st.nextToken());
        int partialSum =max;

        for(int i=1; i<total; i++) {
            int num = Integer.parseInt(st.nextToken());
            partialSum = Math.max(0, partialSum) + num;
            max = Math.max(max, partialSum);
        }

        return max;
    }
}
