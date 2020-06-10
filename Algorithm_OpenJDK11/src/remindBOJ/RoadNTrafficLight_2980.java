package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RoadNTrafficLight_2980 {
    public static void main(String[] args) throws IOException {
        System.out.println(solution(new BufferedReader(new InputStreamReader(System.in))));
    }

    private static int solution(BufferedReader br) throws IOException {
        String str = br.readLine();

        int N = Integer.parseInt(str.split(" ")[0]);
        int K = Integer.parseInt(str.split(" ")[1]);

        int answer =K;
        int waitTime =0;
        for(int i=0; i<N; i++) {
            String inputStr = br.readLine();
            String[] strArr = inputStr.split(" ");
            int time = waitTime + Integer.parseInt(strArr[0]);
            int R =  Integer.parseInt(strArr[1]);
            int G =  Integer.parseInt(strArr[2]);
            time %=(R+G); // 이후의 시간에 대해서도 쉽게 구하기 위해
            if(R - time >0) {
                waitTime+= R-time;
            }
        }

        br.close();
        return answer+waitTime;
    }
}
/*
https://www.acmicpc.net/problem/2980
 */
