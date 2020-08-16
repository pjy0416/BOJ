package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class NeverSeenNHeard_1764 {
    public static void main(String[] args) throws IOException {
        solution(new BufferedReader(new InputStreamReader(System.in)));
    }

    private static void solution(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int seeCnt = Integer.parseInt(st.nextToken());
        int hearCnt = Integer.parseInt(st.nextToken());
        HashSet<String> neverSeenSet = new HashSet<>();
        ArrayList<String> result = new ArrayList<>();

        for(int i=0; i<seeCnt; i++)             neverSeenSet.add(br.readLine());
        for(int i=0; i<hearCnt; i++) {
            String name = br.readLine();
            if(neverSeenSet.contains(name))     result.add(name);
        }
        br.close();

        printResult(result);
    }

    private static void printResult(ArrayList<String> result) {
        final String newLine = "\n";
        Collections.sort(result);
        StringBuffer sb = new StringBuffer();
        sb.append(result.size()).append(newLine);
        for(String name : result)   sb.append(name).append(newLine);

        System.out.println(sb.toString());
    }
}
