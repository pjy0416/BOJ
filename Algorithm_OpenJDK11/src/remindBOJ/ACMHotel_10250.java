package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ACMHotel_10250 {
    final static String newLine = "\n", ZERO = "0";

    public static void main(String[] args) throws IOException {
        solution(new BufferedReader(new InputStreamReader(System.in)));
    }

    private static void solution(BufferedReader br) throws IOException {
        int testCase = parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();

        for(int i=0; i<testCase; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = parseInt(st.nextToken());   // 층수
            int w = parseInt(st.nextToken());   // 한 층당 호수
            int n = parseInt(st.nextToken());   // n번째 손님
            sb.append(getRoomNumber(h,w,n)).append(newLine);
        }
        br.close();
        System.out.println(sb.toString());
    }

    private static String getRoomNumber(int h, int w, int n) {
        StringBuffer sb = new StringBuffer();
        int floor = n % h;
        if(floor ==0)   floor = h;

        int order = n / h;
        if(floor != h)  order++;

        sb.append(floor);
        if(order/10 ==0)    sb.append(ZERO);
        sb.append(order);

        return sb.toString();
    }

    private static int parseInt(String numStr) {
        return Integer.parseInt(numStr);
    }
}

