package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GasStation_13305 {
    static final int SIZE =2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] cities = new int[n][SIZE];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<n; i++) {
            cities[i][0] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            cities[i][1] = Integer.parseInt(st.nextToken());
        }

        br.close();
        solution(cities, n);
    }

    private static void solution(int[][] cities, int n) {
        long answer =0;
        int cost = cities[0][1];
        for(int i=1; i<n; i++) {
            // 다음 도시까지 가는데 무조건 기름을 넣어야한다.
            answer += (long)cities[i][0] * cost; // dist * cost
            if(cost > cities[i][1]) { // 더 싼 가격 저장
                cost = cities[i][1];
            }
        }

        System.out.println(answer);
    }
}
/*
4
3 3 4
2 1 3 4
 */
