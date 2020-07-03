package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BreakNMove_16946 {
    private static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    private static char WALL = '1', BLANK = '0';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int maxY = Integer.parseInt(st.nextToken());
        int maxX = Integer.parseInt(st.nextToken());

        char[][] map = new char[maxY][maxX];
        for(int i=0; i<maxY; i++) {
            map[i] = br.readLine().toCharArray();
        }

        solution(maxX, maxY, map);

        br.close();
    }

    private static void solution(int maxX, int maxY, char[][] map) {
        int[][] move = new int[maxY][maxX];

        LinkedList<Dot> moveList = new LinkedList<>();
        HashMap<Integer,Integer> groupCnt = new HashMap<>();
        int group =0;

        for(int y=0; y<maxY; y++) {
            for(int x=0; x<maxX; x++) {
                if(map[y][x] == BLANK && move[y][x] ==0) {
                    int cnt=0;
                    move[y][x] = ++group;
                    moveList.offer(new Dot(x,y));

                    while(!moveList.isEmpty()) {
                        Dot pos = moveList.poll();
                        cnt++;

                        for(int i=0; i<4; i++) {
                            int nx = pos.x+dx[i];
                            int ny = pos.y+dy[i];

                            if(isInArea(nx,ny,maxX,maxY)) {
                                if(move[ny][nx] ==0 && map[ny][nx] == BLANK) {
                                    move[ny][nx] = group;
                                    moveList.offer(new Dot(nx, ny));
                                }
                            }
                        }
                    }
                    groupCnt.put(group, cnt%10);
                }
            }
        }
        int mul = maxX*maxY;
        LinkedList<Integer> blankList = new LinkedList<>();
        for(int i=0; i<mul; i++) {
            int y = i/maxX;
            int x = i%maxX;
            if(map[y][x]==WALL) {
                for(int j=0; j<4; j++) {
                    int nx = x +dx[j];
                    int ny = y +dy[j];
                    if(isInArea(nx,ny,maxX,maxY)) {
                        if(map[ny][nx] == BLANK && !blankList.contains(move[ny][nx])) {
                            blankList.offer(move[ny][nx]);
                        }
                    }
                }
                while(!blankList.isEmpty()) {
                    move[y][x] += groupCnt.get(blankList.poll());
                }
                move[y][x] = (move[y][x]+1)%10;
            }
        }

        for(int y=0; y<maxY; y++) {
            for(int x=0; x<maxX; x++) {
                if(map[y][x] == WALL) {
                    System.out.print(move[y][x]);
                } else {
                    System.out.print(BLANK);
                }
            }
            System.out.println();
        }
    }

    private static boolean isInArea(int x, int y, int maxX, int maxY) {
        return x >=0 && x<maxX && y>=0 && y<maxY;
    }
}

class Dot {
    int x;
    int y;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }
}