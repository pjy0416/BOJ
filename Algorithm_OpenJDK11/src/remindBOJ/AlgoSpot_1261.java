package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class AlgoSpot_1261 {
    final static char WALL = '1';
    final static int ADDITIONAL_NUM =1, ZERO =0;
    final static int[] dx = {0,0,1,-1}, dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] map = new char[m][n];

        for(int i=0; i<m; i++) {
            map[i] = br.readLine().toCharArray();
        }

        br.close();
        solution(n,m,map);
    }

    private static void solution(int n, int m, char[][] map) {
        int[][] dp = new int[m][n];
        for(int i=0; i<m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = ZERO;
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(0,0, dp[0][0]));

        while(!pq.isEmpty()) {
            Pos pos = pq.poll();

            for(int i=0; i<4; i++) {
                int nx = pos.x + dx[i];
                int ny = pos.y + dy[i];
                if(isInArea(nx,ny,n,m)) {
                    int addCount = map[ny][nx] == WALL ? ADDITIONAL_NUM : ZERO;
                    if(dp[ny][nx] > dp[pos.y][pos.x] + addCount) {
                        dp[ny][nx] = dp[pos.y][pos.x] + addCount;
                        pq.offer(new Pos(nx,ny, dp[ny][nx]));
                    }
                }
            }
        }

        System.out.println(dp[m-1][n-1]);
    }

    private static boolean isInArea(int x, int y, int n, int m) {
        return x>=0 && x<n && y>=0 && y<m;
    }

    private static class Pos implements Comparable<Pos>{
        int x,y,cost;

        public Pos(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pos pos) {
            return this.cost - pos.cost;
        }
    }
}
