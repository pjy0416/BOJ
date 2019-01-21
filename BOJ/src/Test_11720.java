import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_11720 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

/*        // 풀이 1
        int line = Integer.parseInt(br.readLine());

        int sum =0;
        for(int i=0; i<line; i++) {
            sum += br.read() - '0';
        }*/

        // 풀이 2

        int line = Integer.parseInt(br.readLine());
        String inputStr = br.readLine();
        int sum =0;
        for(int i=0; i<line; i++) {
            sum += inputStr.charAt(i)-'0';
        }
        System.out.println(sum);
        br.close();
    }
}
