package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AilingKnight_1783 {
    public static void main(String[] args) throws IOException { // brute force
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());
        br.close();
        solution(n,m);
    }

    private static void solution(long n, long m) {
        StringBuffer sb = new StringBuffer();
        if(n ==1)       sb.append(n);                       // 세로가 1인 경우, 1칸만 가능
        else if(n ==2)  sb.append(Math.min(4, (m+1)/2));    // 세로가 2인 경우, (m+1)/2 칸 가능 (4와 Math.min 하는 이유는, 이동 범위가 5칸 이상인 경우에는 4방향 다 가야하는데, n=2라 4방향을 다 못가서
        else if(m <7)   sb.append(Math.min(4,m));           // 여기서도 위와 마찬가지로 m>5인 경우 이동 가능 범위때문에 Math.min 
        else            sb.append(m-2);                     // 자유롭게 가는 경우
        System.out.println(sb.toString());
    }
}
