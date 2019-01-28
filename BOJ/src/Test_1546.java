import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_1546 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sum =0;
        int max =0;
        int all = Integer.parseInt(br.readLine());
        String inputStr = br.readLine();
        String[] examArr = inputStr.split(" ");

        for(String examStr : examArr) {
            int exam = Integer.parseInt(examStr);
            sum += exam;
            max = Math.max(exam, max);
        }

//        float result = ((float)sum/max)*(float)100/all;
//        System.out.println(String.format("%.2f",result));
//        casting 하면서 계산하고 String.format으로 출력하는 것 보다 Math.round를 통해서 반올림 하는것이 더 빠름 (8ms) (메모리 효율도 아주 조금 티 안날만큼 좋음..)
        System.out.println(Math.round(sum*10000.0 /max /all)/100.0);


        br.close();
    }
}
