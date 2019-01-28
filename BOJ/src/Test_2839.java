import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_2839 {
//    Ref : https://zoonvivor.tistory.com/75
//    재귀함수로 시도했다가 실패...
    private static void printResult(int kg) {
        System.out.println(cal(kg));
    }

    private static int cal(int kg) {
        int cnt =0;
        while(kg%5 !=0 && kg>=0) {
            kg -=3;
            cnt++;
        }
        if(kg <0) {
            return -1;
        } else {
            return cnt + kg/5;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int kg = Integer.parseInt(br.readLine());

        printResult(kg);

        br.close();
    }
}
