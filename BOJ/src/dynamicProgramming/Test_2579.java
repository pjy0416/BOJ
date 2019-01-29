package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_2579 {
    // 코드 : https://nackwon.tistory.com/67
    // https://2youngjae.tistory.com/38

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int steps = Integer.parseInt(br.readLine());

        int[] stepArr = new int[301];
        int[] choice = new int[301];

        for(int i=1; i<=steps; i++) {
            stepArr[i] = Integer.parseInt(br.readLine());
        }

        choice[1] = stepArr[1];
        choice[2] = stepArr[1] + stepArr[2];
        choice[3] = stepArr[3] + Math.max(stepArr[1], stepArr[2]);

        for(int i=4; i<=steps; i++) {
            choice[i] = stepArr[i] +Math.max(choice[i-2], choice[i-3]+stepArr[i-1]);
        }

        System.out.println(choice[steps]);

        /**
         * Ref : https://barefoot-coder.tistory.com/56
         * 개인적으로 이 코드가 더 좋은거 같음
         * 결과는 속도가 같고, 이 코드가 메모리 4kb 더 차지함
         * 짧은 경우의 수라면 이 코드가 수월
        int[] tmp = new int[3];
        for(int i=0; i<steps; i++) {
            int num = Integer.parseInt(br.readLine());
            int max = Math.max(tmp[1], tmp[2]);
            tmp[2] = tmp[1] + num;
            tmp[1] = tmp[0] + num;
            tmp[0] = max;
        }

        System.out.println(Math.max(tmp[1],tmp[2]));
        */
        br.close();
    }
}
