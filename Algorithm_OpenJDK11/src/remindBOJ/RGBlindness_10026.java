package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class RGBlindness_10026 {
    final static int[] dx = {1,-1,0,0}, dy = {0,0,1,-1};
    final static char RED = 'R', GREEN = 'G', BLUE = 'B';
    final static int NOGROUP =0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[][] map = new char[n][n];
        for(int i=0; i<n; i++) {
            map[i] = br.readLine().toCharArray();
        }
        solution(n,map);
        br.close();
    }

    private static void solution(int n, char[][] map) {
        StringBuffer sb = new StringBuffer();
        sb.append(getNormal(n,map)).append(" ");
        sb.append(getRedGreen(n,map));
        System.out.println(sb.toString());
    }

    private static int getNormal(int n, char[][] map) {
        int groupIdx =0;
        int[][] group = new int[n][n];
        LinkedList<Pos> moveList = new LinkedList<>();

        for(int y=0; y<n; y++) {
            for(int x=0; x<n; x++) {
                if(group[y][x] == NOGROUP) {
                    moveList.offer(new Pos(x,y));
                    group[y][x] = ++groupIdx;
                    char origin = map[y][x];

                    while(!moveList.isEmpty()) {
                        Pos pos = moveList.poll();
                        for(int i=0; i<4; i++) {
                            int nx = pos.x +dx[i];
                            int ny = pos.y +dy[i];
                            if(isInArea(nx,ny,n)) {
                                if(map[ny][nx] == origin && group[ny][nx] ==NOGROUP) {
                                    group[ny][nx] = groupIdx;
                                    moveList.offer(new Pos(nx,ny));
                                }
                            }
                        }
                    }
                }
            }
        }
        return groupIdx;
    }

    private static int getRedGreen(int n, char[][] map) {
        int groupIdx =0;
        int[][] group = new int[n][n];
        LinkedList<Pos> moveList = new LinkedList<>();

        for(int y=0; y<n; y++) {
            for(int x=0; x<n; x++) {
                if(group[y][x] == NOGROUP) {
                    moveList.offer(new Pos(x,y));
                    group[y][x] = ++groupIdx;
                    char origin = map[y][x];

                    while(!moveList.isEmpty()) {
                        Pos pos = moveList.poll();
                        for(int i=0; i<4; i++) {
                            int nx = pos.x +dx[i];
                            int ny = pos.y +dy[i];
                            if(isInArea(nx,ny,n)) {
                                if(origin == BLUE) {
                                    if(map[ny][nx] == BLUE && group[ny][nx] == NOGROUP) {
                                        group[ny][nx] = groupIdx;
                                        moveList.offer(new Pos(nx,ny));
                                    }
                                } else {    // RED GREN
                                    if(map[ny][nx] != BLUE && group[ny][nx] == NOGROUP) {
                                        group[ny][nx] = groupIdx;
                                        moveList.offer(new Pos(nx,ny));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return groupIdx;
    }

    private static boolean isInArea(int x, int y, int n) {
        return x>=0 && x<n && y>=0 && y<n;
    }

    private static class Pos {
        int x,y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
