package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Alarm_2884 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int hour = Integer.parseInt(st.nextToken());
        int min = Integer.parseInt(st.nextToken());

        solution(hour, min);
    }

    private static void solution(int hour, int min) {
        final int before = 45, maxMin = 60, maxHour=24;
        min -= before;
        if(min <0) {
            min += maxMin;
            hour--;
        }
        if(hour<0) { hour+= maxHour; }

        StringBuffer sb = new StringBuffer();
        sb.append(hour).append(" ").append(min);

        System.out.println(sb.toString());
    }
}
