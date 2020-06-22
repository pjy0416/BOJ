package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RoomNumber_1475 {
    final static int MAX = 10, SIX = 6, NINE =9;
    final static int ZERO = 48;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String numStr = br.readLine();
        System.out.println(solution(numStr));

        br.close();
    }

    private static int solution(String numStr) {
        int[] numArr = new int[MAX];

        for(int i=0; i<numStr.length(); i++) {
            numArr[numStr.charAt(i)-ZERO]++;
        }

        if(numArr[SIX] > numArr[NINE]) {
            numArr = makeShare(SIX, NINE, numArr);
        } else if(numArr[NINE] > numArr[SIX]) {
            numArr = makeShare(NINE, SIX, numArr);
        }

        int answer =numArr[0];
        for(int i=1; i<MAX; i++) {
            answer = Math.max(answer, numArr[i]);
        }
        return answer;
    }

    private static int[] makeShare(int idx1, int idx2, int[] numArr) {
        int share = (numArr[idx1] - numArr[idx2])/2;
        numArr[idx1] -= share;
        numArr[idx2] += share;

        return numArr;
    }
}
//https://www.acmicpc.net/problem/1475