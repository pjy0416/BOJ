package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class OvenClock_2525 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Time oven = new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        oven.addMinutes(Integer.parseInt(br.readLine()));

        br.close();
        oven.printTime();
    }

    private static class Time {
        private final int MAX_HOUR = 24, MAX_MIN = 60;
        private final char SPACE = ' ';
        int hour, min;

        public Time(int hour, int min) {
            this.hour = hour;
            this.min = min;
        }

        public void addMinutes(int extraMinutes) {
            this.min += extraMinutes;
            int extraHours = this.min / this.MAX_MIN;
            this.min %= this.MAX_MIN;
            this.hour = (extraHours + this.hour) % this.MAX_HOUR;
        }

        public void printTime() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.hour).append(this.SPACE).append(this.min);
            System.out.println(sb.toString());
        }
    }
}
