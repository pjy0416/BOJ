package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SnailClimbing_2869 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long v = Long.parseLong(st.nextToken());
        br.close();

        solution(a,b,v);
    }

    private static void solution(long a, long b, long v) { // a : 낮에 오르는 미터, 밤에 흐르는 미터, 목표 v
        long answer =0;
        long left =1, right = v; // a-b의 최소값은 1이기 때문에, 최대 v일이 걸림

        while(left <= right) {
            long mid = (left+right)/2; // 날짜
            if(canClimb(a, b, v, mid)) {
                right = mid-1;
                answer = mid;
            } else {
                left = mid+1;
            }
        }
        System.out.println(answer);
    }

    private static boolean canClimb(long a, long b, long v, long days) {
        long height = a*days - b*(days-1);
        return height >=v;
    }
}
