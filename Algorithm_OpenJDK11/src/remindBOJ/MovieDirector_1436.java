package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MovieDirector_1436 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        br.close();

        solution(n);
    }

    private static void solution(int n) {
        final String DOOMSDAY = "666";
        int series = 666;
        String answer ="";

        while(n !=0) {
            String numString = String.valueOf(series);
            if(numString.contains(DOOMSDAY)) {
                n--;
                answer = numString;
            }
            series++;
        }
        System.out.println(answer);
    }
}
