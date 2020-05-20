package programmers;

import java.util.StringTokenizer;

public class MaxAndMinValue_12939 {
    private static String solution(String s) {
        StringBuffer answer = new StringBuffer();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        /*String[] strArr = s.split(" ");
        for(String str : strArr) {
            int num = Integer.parseInt(str);
            max = Math.max(max, num);
            min = Math.min(min, num);
        }*/

        StringTokenizer st = new StringTokenizer(s);
        while(st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        answer.append(min).append(" ").append(max);

        return answer.toString();
    }

    public static void main(String[] args) {
        String s = "-1 -2 -3 -4";
        System.out.println(solution(s));
    }
}
