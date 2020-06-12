package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class SwanLake_3197 {
    final static char SWAN = 'L', ICE = 'X', WATER = '.';
    final static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
    final static int directions = 4;
    static int visit[][];
    static LinkedList<WaterPos> swanList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sizeStr = br.readLine();
        int y= Integer.parseInt(sizeStr.split(" ")[0]);
        char[][] map = new char[y][];

        for(int i=0; i<y; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(solution(map));

        br.close();
    }

    private static int solution(char[][] map) {
        int answer =0;
        int maxX = map[0].length;
        int maxY = map.length;

        visit = new int[maxY][maxX];
        LinkedList<WaterPos> waterList = new LinkedList<>();
        swanList = new LinkedList<>();
        WaterPos[] swans = new WaterPos[2];

        int idx=0;
        for(int y=0; y<maxY; y++) {
            for(int x=0; x<maxX; x++) {
                if(map[y][x] ==WATER || map[y][x] == SWAN) {
                    WaterPos pos = new WaterPos(x,y);
                    waterList.offer(pos);
                    if(map[y][x] == SWAN) {
                        swans[idx++] = pos;
                    }
                }
            }
        }
        swanList.offer(swans[0]);
        visit[swans[0].y][swans[0].x] =1;

        while(!isMeetSwans(swans,map)) {
            int size = waterList.size();
            for(int i=0; i<size; i++) {
                WaterPos pos = waterList.poll();
                for(int j=0; j<directions; j++) {
                    int ny = pos.y + dy[j];
                    int nx = pos.x + dx[j];

                    if(isInArea(nx,ny,maxX,maxY)) {
                        if(map[ny][nx] == ICE) {
                            map[ny][nx] = WATER;
                            waterList.offer(new WaterPos(nx,ny));
                        }
                    }
                }
            }
            answer++;
        }

        return answer;
    }

    private static boolean isMeetSwans(WaterPos[] swans, char[][] map) {
        boolean result = false;

        LinkedList<WaterPos> moveList = new LinkedList<>();
        int maxX = map[0].length;
        int maxY = map.length;

        while(!swanList.isEmpty()) {
            WaterPos pos = swanList.poll();
            if(pos.x == swans[1].x && pos.y == swans[1].y) {
                result = true;
                break;
            }
            for(int i=0; i<directions; i++) {
                int ny = pos.y + dy[i];
                int nx = pos.x + dx[i];
                if(isInArea(nx,ny,maxX,maxY)) {
                    if(visit[ny][nx] ==0) {
                        if(map[ny][nx] == ICE) {
                            moveList.offer(new WaterPos(nx,ny));
                        } else {
                            swanList.offer(new WaterPos(nx,ny));
                        }
                        visit[ny][nx] = 1;
                    }
                }
            }
        }

        if(visit[swans[1].y][swans[1].x] ==1) {
            result = true;
        }
        swanList = moveList;

        return result;
    }

    private static boolean isInArea(int x, int y, int maxX, int maxY) {
        return x>=0 && x<maxX && y>=0 && y<maxY ? true : false;
    }
}

class WaterPos {
    int x;
    int y;

    public WaterPos(int x, int y) {
        this.x = x;
        this.y =y;
    }
}
/*
https://www.acmicpc.net/problem/3197
 */