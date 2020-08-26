package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class NumberOfIsland_4963 {
    final static String END = "0 0", NEWLINE = "\n";
    final static int LAND = 1;
    final static int[] dx = {0,1,1,1,0,-1,-1,-1}, dy = {-1,-1,0,1,1,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = "";
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        while( !(inputStr = br.readLine()).equals(END)) {
            st = new StringTokenizer(inputStr);
            int w = Integer.parseInt(st.nextToken()), h = Integer.parseInt(st.nextToken());
            int[][] map = new int[h+2][w+2];
            for(int y=1; y<=h; y++) {
                st = new StringTokenizer(br.readLine());
                for(int x=1; x<=w; x++) {
                    map[y][x] = Integer.parseInt(st.nextToken());
                }
            }
            sb.append(getIslands(w,h,map)).append(NEWLINE);
        }
        System.out.println(sb.toString());
    }

    private static int getIslands(int w, int h, int[][] map) {  // 0 : 땅, -1 : 물
        int groupID =1;
        for(int y=1; y<=h; y++) {
            for(int x=1; x<=w; x++) {
                if(map[y][x] ==LAND) {
                    map[y][x] = ++groupID;
                    map = grouping(new Pos(x,y),map, groupID);
                }
            }
        }
        return groupID-1;
    }

    private static int[][] grouping(Pos start, int[][] map, int groupID) {
        LinkedList<Pos> moveList = new LinkedList<>();
        moveList.offer(start);
        while(!moveList.isEmpty()) {
            Pos pos = moveList.poll();
            for(int i=0; i<8; i++) {
                int nx = pos.x+dx[i], ny = pos.y+dy[i];
                if(map[ny][nx] == LAND) {
                    map[ny][nx] = groupID;
                    moveList.offer(new Pos(nx,ny));
                }
            }
        }
        return map;
    }

    private static class Pos {
        int x,y;
        public Pos(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }
}
