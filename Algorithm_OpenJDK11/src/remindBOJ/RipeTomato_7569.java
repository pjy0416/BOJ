package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class RipeTomato_7569 {
    private final static int RIPE = 1, UNRIPE =0, EMPTY = -1, WAYS =6;
    private final static int[] dx = {1,-1,0,0,0,0}, dy = {0,0,1,-1,0,0}, dz = {0,0,0,0,1,-1};
    private static int m,n,h,unRipeCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        int[][][] tomatoBox = new int[h][n][m];
        LinkedList<Pos> ripeList = new LinkedList<>();

        for(int i=0; i<h; i++) {
            for(int j=0; j<n; j++) {
                st = new StringTokenizer(br.readLine());
                for(int k=0; k<m; k++) {
                    int status = Integer.parseInt(st.nextToken());
                    tomatoBox[i][j][k] = status;
                    if(status == RIPE) {
                        ripeList.offer(new Pos(k,j,i,0));
                    } else if(status == UNRIPE) {
                        unRipeCnt++;
                    }
                }
            }
        }

        solution(tomatoBox, ripeList);
        br.close();
    }

    private static void solution(int[][][] tomatoBox, LinkedList<Pos> ripeList) {
        int[][][] ripeDays = new int[h][n][m];
        int day =0;

        while(!ripeList.isEmpty()) {
            Pos pos = ripeList.poll();
            for(int i=0; i<WAYS; i++) {
                int nx = pos.x+dx[i];
                int ny = pos.y+dy[i];
                int nz = pos.z+dz[i];
                int step = pos.step+RIPE; // RIPE는 익는데 걸리는 시간 1 증가의 의미로 사용

                if(isInArea(nx,ny,nz)) {
                    if(tomatoBox[nz][ny][nx] == UNRIPE && ripeDays[nz][ny][nx] == UNRIPE) { // ripeday의 UNRIPE는 아직 익지 않았다의 0으로 사용
                        ripeDays[nz][ny][nx] = step;
                        ripeList.offer(new Pos(nx,ny,nz,step));
                        day = Math.max(day, step);
                        unRipeCnt--;
                    }
                }
            }
        }
        if(unRipeCnt !=0) {
            day = EMPTY;
        }
        System.out.println(day);
    }

    private static boolean isInArea(int x, int y, int z) {
        return x>=0 && y>=0 && z>=0 && x<m && y<n && z<h;
    }

    private static class Pos {
        int x,y,z,step;
        public Pos(int x, int y, int z, int step) {
            this.x =x;
            this.y =y;
            this.z =z;
            this.step = step;
        }
    }
}
