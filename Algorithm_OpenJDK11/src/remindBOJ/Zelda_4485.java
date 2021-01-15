package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Zelda_4485 {
    final static String PREFIX ="Problem ", COLON =": ", NEW_LINE ="\n";
    final static int END = 0;
    final static int[] dx = {0,0,1,-1}, dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n =0;
        int count =1;
        while( (n = Integer.parseInt(br.readLine())) !=END ) {
            int[][] map = new int[n][n];
            for(int i=0; i<n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            sb.append(PREFIX).append(count++).append(COLON).append(dijkstra(map,n)).append(NEW_LINE);
        }
        br.close();
        System.out.println(sb.toString());
    }
    private static int dijkstra(int[][] map, int n) {
        int[][] dp = new int[n][n];
        for(int i=0; i<n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = map[0][0];
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(0,0,map[0][0]));

        while(!pq.isEmpty()) {
            Pos pos = pq.poll();

            for(int i=0; i<4; i++) {
                int nx = pos.x + dx[i];
                int ny = pos.y + dy[i];
                if(isInArea(nx,ny,n)) {
                    if(dp[ny][nx] > dp[pos.y][pos.x] + map[ny][nx]) {
                        dp[ny][nx] = dp[pos.y][pos.x] + map[ny][nx];
                        pq.offer(new Pos(nx,ny, dp[ny][nx]));
                    }
                }
            }
        }
        return dp[n-1][n-1];
    }

    private static boolean isInArea(int x, int y, int n) {
        return x>=0 && y>=0 && x<n && y<n;
    }

    private static class Pos implements Comparable<Pos>{
        int x, y, cost;
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
