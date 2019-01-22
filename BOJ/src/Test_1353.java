import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_1353 {
    // Ref : https://github.com/kks227/BOJ/blob/master/1300/1353.cpp
    private static int myFunc(int sum, int multi) {
        if (sum == multi) {
            return 1;
        }

        double prev = -1;
        for (int div = 2; ; div++) {        // 2부터 div 로 나누는 것은 생각했었음
            double tmp = Math.pow(1.0 * sum / div, div);    // 합을 div로 나눠서 갯수만큼 곱하는 것 까지는 내코드
            if (prev > tmp) {               // prev 설정과 prev가 tmp 보다 커지는 것 부분 참고
                return -1;
            }
            if (tmp >= multi) {
                return div;
            }
            prev = tmp;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputStr = br.readLine();

        int sum = Integer.parseInt(inputStr.split(" ")[0]);
        int multi = Integer.parseInt(inputStr.split(" ")[1]);

        System.out.println(myFunc(sum, multi));

        br.close();
    }
}