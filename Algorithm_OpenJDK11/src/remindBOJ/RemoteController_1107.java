package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RemoteController_1107 {
    final static int MAX = 9, MAX_CHANEL=999999, ZERO =0, BASIC = 100;
    static boolean[] brokenBtn;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int chanel = Integer.parseInt(br.readLine());
        brokenBtn = new boolean[MAX+1];
        int n = Integer.parseInt(br.readLine());
        if(n !=ZERO) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = ZERO; i < n; i++) {
                brokenBtn[Integer.parseInt(st.nextToken())] = true;
            }
        }
        br.close();

        solution(chanel);
    }

    private static void solution(int chanel) {
        int answer = Math.abs(BASIC-chanel);

        for(int i=ZERO; i<=MAX_CHANEL; i++) {
            int clickBtn = getClick(i);
            if(clickBtn >ZERO) {
                int upDown = Math.abs(i-chanel);
                answer = Math.min(answer, clickBtn+upDown);
            }
        }
        System.out.println(answer);
    }

    private static int getClick(int num) {
        int result =ZERO;
        if(num ==ZERO) {
            if(!brokenBtn[ZERO]) { result =1; }
        } else {
            while(num >ZERO) {
                if(brokenBtn[num%10]) {
                    result =ZERO;
                    break;
                }
                result++;
                num/=10;
            }
        }
        return result;
    }
}
