import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_1924 {
    private final static String[] days = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
    private final static int[] monStart = {0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = br.readLine();
        int day = monStart[Integer.parseInt(inputStr.split(" ")[0])-1] + Integer.parseInt(inputStr.split(" ")[1])-1;

        System.out.println(days[day%7]);
        br.close();
    }
}
