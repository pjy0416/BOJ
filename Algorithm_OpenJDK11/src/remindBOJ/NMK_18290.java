package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NMK_18290 {
    final static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] map = new int[n+2][m+2];

        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution(n,m,k,map);

        br.close();
    }

    private static void solution(int n, int m, int k, int[][] map) {
        answer =Integer.MIN_VALUE;  // 0으로 초기화 했어서 틀림,, 답이 -인 경우도 생각하자
        getMaxNum(n,m,k,map,new boolean[n+2][m+2], 0);
        System.out.println(answer);
    }

    private static void getMaxNum(int n, int m, int k, int[][] map, boolean[][] visit, int sum) {
        if(k ==0) {
            answer = Math.max(answer,sum);
            return;
        }
        for(int y=1; y<=n; y++) {
            for(int x=1; x<=m; x++) {
                if(!visit[y][x]) {  // 방문한 적 없으면
                    if(canPickNum(x,y,visit)) { // 고를 수 있으면
                        visit[y][x] = true; // 방문 체크
                        getMaxNum(n, m, k - 1, map, visit, sum + map[y][x]);
                        visit[y][x] = false;    // 방문 해제
                    }
                }
            }
        }
    }

    private static boolean canPickNum(int x, int y, boolean[][] visit) {
        boolean result = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(visit[ny][nx]) {
                result = false;
            }
        }
        return result;
    }
}
