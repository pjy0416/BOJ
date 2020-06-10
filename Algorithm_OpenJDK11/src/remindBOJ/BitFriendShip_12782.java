package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BitFriendShip_12782 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        String[] caseArr = new String[len];
        for(int i=0; i<len; i++) {
            caseArr[i] = br.readLine();
        }

        solution(caseArr);

        br.close();
    }

    private static void solution(String[] caseArr) {
        for(String str : caseArr) {
            char[] left = str.split(" ")[0].toCharArray();
            char[] right = str.split(" ")[1].toCharArray();

            int leftCnt =0;
            int rightCnt =0;
            int diff =0;
            for(int i=0; i<left.length; i++) {
                if(left[i] =='1') {
                    leftCnt++;
                }
                if(right[i] =='1') {
                    rightCnt++;
                }
                if(left[i] != right[i]) {
                    diff++;
                }
            }

            if(leftCnt == rightCnt) {
                System.out.println(diff/2);
            } else {
                int change = Math.abs(leftCnt-rightCnt);
                System.out.println(((diff-change)/2+ change));
            }
        }
    }
}
/*
https://www.acmicpc.net/problem/12782
 */