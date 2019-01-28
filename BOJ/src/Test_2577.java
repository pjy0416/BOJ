import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_2577 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());

        int tmp = a*b*c;
        int[] cnt = new int[10];

        for(int i=0; i<10; i++) {       // 초기화가 있느냐 없느냐에 따라 8ms 차이가 존재, 초기화 해주는게 더 빠름
            cnt[i] =0;
        }

        while(tmp !=0) {
            cnt[tmp%10]++;
            tmp /=10;
        }

        for(int num : cnt) {
            System.out.println(num);
        }

        br.close();
    }
}
