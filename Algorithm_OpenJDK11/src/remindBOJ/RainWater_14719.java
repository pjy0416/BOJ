package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RainWater_14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String size = br.readLine();
        int x = Integer.parseInt(size.split(" ")[1]);
        int[] blockArr = new int[x];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<x; i++) {
            blockArr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(blockArr, x));
        br.close();
    }

    private static int solution(int[] blockArr, int x) {
        int answer =0;

        for(int i=1; i<x-1; i++) {
            int leftMax = getLeftMax(blockArr, i);
            int rightMax = getRightMax(blockArr, i);

            int height = Math.min(leftMax, rightMax);

            if(height > blockArr[i]) {
                answer += height - blockArr[i];
            }
        }
        return answer;
    }

    private static int getLeftMax(int[] blockArr, int i) {
        int result =0;
        for(int idx = i-1; idx>=0; idx--) {
            result = Math.max(result, blockArr[idx]);
        }
        return result;
    }

    private static int getRightMax(int[] blockArr, int i) {
        int result =0;
        for(int idx = i+1; idx<blockArr.length; idx++) {
            result = Math.max(result, blockArr[idx]);
        }
        return result;
    }
}
/*
https://www.acmicpc.net/problem/14719
 */