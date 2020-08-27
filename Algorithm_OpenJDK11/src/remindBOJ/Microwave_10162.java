package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Microwave_10162 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int time = Integer.parseInt(br.readLine());
        br.close();
        solution(time);
    }

    private static void solution(int time) {
        final int[] buttons = {300, 60, 10};
        final String SPACE = " ", FAIL = "-1";
        StringBuffer sb = new StringBuffer();
        for(int button : buttons) {
            sb.append(time/button).append(SPACE);
            time %= button;
        }
        if(time !=0) {
            sb = new StringBuffer();
            sb.append(FAIL);
        }
        System.out.println(sb.toString());
    }
}
