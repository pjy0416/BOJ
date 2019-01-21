import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_14489 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputStr = br.readLine();
        int balance = Integer.parseInt(inputStr.split(" ")[0]) + Integer.parseInt(inputStr.split(" ")[1]);
        int chickens = Integer.parseInt(br.readLine())*2;

        if(balance>= chickens) {
            balance -= chickens;
        }

        System.out.println(balance);

        br.close();
    }
}
