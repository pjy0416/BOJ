package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FindPrimeNums_1929 {
    public static void main(String[] args) throws IOException { // 에라토네스의 채 이용하기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        solution(start, end);

        br.close();
    }

    private static void solution(int start, int end) {  // 2차 시도, 더 효율적
        boolean[] prime = new boolean[end+1];
        final String newLine = "\n";
        StringBuffer sb = new StringBuffer();
        for(int i= 2; i<=end; i++) {
            if(!prime[i]) {
                if(i >=start) {
                    sb.append(i).append(newLine);
                }
                for (int j = i*2; j <= end; j+=i) {
                    prime[j] = true;
                }
            }
        }
        System.out.println(sb.toString());
    }
    /*private static void solution(int start, int end) { // 1차 시도
        boolean[] prime = new boolean[end+1];
        prime[1] = true;
        for(int i= 2; i<=end; i++) {
            if(!prime[i]) {
                for (int j = i*2; j <= end; j+=i) {
                    prime[j] = true;
                }
            }
        }

        for(int i=start; i<=end; i++) {
            if(!prime[i]) {
                System.out.println(i);
            }
        }
    }*/
}
