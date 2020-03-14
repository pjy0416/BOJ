package swMaestro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        int cnt = Integer.parseInt(input);
        String answer ="";

        if(cnt ==2) {
            answer = "1";
        } else if (cnt ==3) {
            answer = "7";
        } else {
            int mod = cnt%2;
            if(mod !=0) {
                answer +="7";
                cnt -=3;
            }
            for(int i=0; i<cnt/2; i++) {
                answer+="1";
            }
        }

        System.out.println(answer);
    }

}
