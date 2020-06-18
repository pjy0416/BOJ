package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MaxContest_12915 {
    final static int SIZE = 5;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] probArr = new int[SIZE];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<SIZE; i++) {
            probArr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(probArr));
        
        br.close();
    }

    private static int solution(int[] probArr) {
        int answer =0;
        while(true) {
            if(probArr[0] >0) { // Easy
                probArr[0]--;
            } else if(probArr[1] >0) {
                probArr[1]--;
            }
            else {
                break;
            }

            if(probArr[2] >0) {
                probArr[2]--;
            } else if(probArr[1] >0 || probArr[3] >0) {
                if(probArr[1] >= probArr[3]) {
                    probArr[1]--;
                } else {
                    probArr[3]--;
                }
            } else {
                break;
            }

            if(probArr[4] >0) {
                probArr[4]--;
            } else if(probArr[3] >0) {
                probArr[3]--;
            } else {
                break;
            }
            answer++;
        }
        return answer;
    }
}
// https://www.acmicpc.net/problem/12915