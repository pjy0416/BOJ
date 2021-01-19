package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PrefixSum_11660 {
    private final static String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n+1][n+1];

        for(int y=1; y<=n; y++) {
            st = new StringTokenizer(br.readLine());
            for(int x=1; x<=n; x++) {
                map[y][x] = Integer.parseInt(st.nextToken()) + map[y][x-1];
            }
        }

        StringBuilder sb = new StringBuilder();
        while(m-- >0) {
            st = new StringTokenizer(br.readLine());
            Pos start = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            Pos end = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            sb.append(solution(map, start, end)).append(NEW_LINE);
        }

        br.close();
        System.out.println(sb.toString());
    }

    private static int solution(int[][] map, Pos start, Pos end) {
        int answer =0;

        if(start.x == end.x) {
            answer = map[start.x][end.y] - map[start.x][start.y-1];
        } else {
            for(int x= start.x; x<=end.x; x++) {
                answer += map[x][end.y] - map[x][start.y-1];
            }
        }
        return answer;
    }

    private static class Pos {
        int x,y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
