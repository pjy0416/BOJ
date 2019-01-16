import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_8595 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int line = Integer.parseInt(br.readLine());     // String line size
        String str = br.readLine();

        long sum =0;
        long prev =0;
        for(int i=0; i<line; i++) {
            char ch = str.charAt(i);
            if(ch >=48 && ch <=57) {
                prev = prev*10 + (ch -'0');
            } else {
                sum += prev;
                prev =0;
            }
        }
        sum += prev;

        System.out.println(sum);

        br.close();
    }
}
