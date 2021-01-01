package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Camping_4796 {
    static String PRINT_FORMAT = "Case %d: %d", newLine = "\n";
    static StringBuilder sb = new StringBuilder();
    static int caseCount =1;

    public static void main(String[] args) throws IOException {
        final String endInput = "0 0 0";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        while(!(input = br.readLine()).equals(endInput)) {
            solution(input);
            caseCount++;
        }

        br.close();
        System.out.println(sb.toString());
    }

    private static void solution(String input) {
        StringTokenizer st = new StringTokenizer(input);
        int L = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        int answer = (V/P)*L;
        V %= P;
        answer += (V > L ? L : V);

        sb.append(String.format(PRINT_FORMAT, caseCount, answer)).append(newLine);
    }
}
