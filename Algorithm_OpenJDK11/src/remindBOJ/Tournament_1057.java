package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tournament_1057 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int left = Integer.parseInt(st.nextToken());
        int right = Integer.parseInt(st.nextToken());

        int answer =0;
        while(left != right) {
            answer++;
            left =(left+1)/2;
            right =(right+1)/2;
        }
        System.out.println(answer);
//        System.out.println(Integer.toBinaryString((left-1)^(right-1)).length());
        br.close();
    }
}
