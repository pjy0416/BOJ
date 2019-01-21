import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_5543 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int bugger = 2000;
        int drink = 2000;

        for(int i=0; i<3; i++) {
            int tmp =Integer.parseInt(br.readLine());
            if(tmp < bugger) {
                bugger = tmp;
            }
        }
        for(int i=0; i<2; i++) {
            int tmp =Integer.parseInt(br.readLine());
            if(tmp < drink) {
                drink = tmp;
            }
        }

        System.out.println(bugger+drink-50);


        br.close();
    }
}
