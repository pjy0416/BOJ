import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_1110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        printResult(num);

        br.close();
    }

    private static void printResult(int num) {
        int tmp = num;
        int cnt =0;

        while(true) {
            if(tmp/10 >0) {  // 두자리 수 일 때,
                int first = tmp/10;
                int second = tmp%10;
                tmp = second*10 + (first+second)%10;
            } else { // 한자리 수 일 때,
                tmp = tmp*10 +tmp;
            }
            cnt++;
            if(num == tmp) {
                break;
            }
        }
        System.out.println(cnt);
    }
}
