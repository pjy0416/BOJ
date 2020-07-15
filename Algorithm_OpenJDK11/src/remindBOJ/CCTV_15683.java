package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CCTV_15683 {
    final static char WALL='6', WATCH='#', EMPTY ='0', CCTV1='1',CCTV2='2',CCTV3='3',CCTV4='4',CCTV5='5';
    final static int DIRECTION = 4, dr[] = {0,1,1,1}, NORTH =0, EAST =1, SOUTH =2, WEST=3;
    static int n,m,answer;
    static ArrayList<CCTVInfo> cctvList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        char[][] map = new char[n][m];
        cctvList = new ArrayList<>();

        for(int y=0; y<n; y++) {
            st = new StringTokenizer(br.readLine());
            for(int x=0; x<m; x++) {
                char ch = st.nextToken().charAt(0);
                map[y][x] = ch;
                if(ch != WALL && ch != EMPTY) {
                    cctvList.add(new CCTVInfo(ch,x,y));
                }
            }
        }
        br.close();
        solution(map);
    }

    private static void solution(char[][] map) {
        answer = Integer.MAX_VALUE;
        dfs(0, map, cctvList.size());
        System.out.println(answer);
    }

    private static void dfs(int idx, char[][] map, int end) {
        if(idx == end) {
            answer = Math.min(answer, getBlindSpots(map));
            return;
        }
        CCTVInfo cctv = cctvList.get(idx);
        char id = cctv.id;
        int x = cctv.x;
        int y = cctv.y;

        for(int i=0; i<DIRECTION; i++) {
            dfs(idx + 1, getMap(id,x,y,i, map), end);
        }
    }

    private static char[][] getMap(char id, int x, int y, int direction, char[][] map) {
        char[][] result = new char[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                result[i][j] = map[i][j];
            }
        }
        if(id == CCTV1) {
            if(direction == NORTH) { result = monitorNorth(x,y,result); }
            else if(direction == EAST) { result = monitorEast(x,y,result); }
            else if(direction == SOUTH) { result = monitorSouth(x,y,result); }
            else { result = monitorWest(x,y,result); } // WEST
        } else if(id == CCTV2) {
            if(direction == NORTH || direction == SOUTH) {
                result = monitorNorth(x,y,result);
                result = monitorSouth(x,y,result);
            } else { // EAST, WEST
                result = monitorEast(x,y,result);
                result = monitorWest(x,y,result);
            }
        } else if(id == CCTV3) {
            if(direction == NORTH) {
                result = monitorNorth(x,y,result);
                result = monitorEast(x,y,result);
            } else if(direction ==EAST) {
                result = monitorEast(x,y,result);
                result = monitorSouth(x,y,result);
            } else if(direction ==SOUTH) {
                result = monitorSouth(x,y,result);
                result = monitorWest(x,y,result);
            } else { // WEST
                result = monitorWest(x,y,result);
                result = monitorNorth(x,y,result);
            }
        } else if(id == CCTV4) {
            if(direction == NORTH) {
                result = monitorWest(x,y,result);
                result = monitorNorth(x,y,result);
                result = monitorEast(x,y,result);
            } else if(direction == EAST) {
                result = monitorNorth(x,y,result);
                result = monitorEast(x,y,result);
                result = monitorSouth(x,y,result);
            } else if(direction == SOUTH) {
                result = monitorEast(x,y,result);
                result = monitorSouth(x,y,result);
                result = monitorWest(x,y,result);
            } else { // WEST
                result = monitorSouth(x,y,result);
                result = monitorWest(x,y,result);
                result = monitorNorth(x,y,result);
            }
        } else if(id == CCTV5) {
            result = monitorNorth(x,y,result);
            result = monitorEast(x,y,result);
            result = monitorSouth(x,y,result);
            result = monitorWest(x,y,result);
        }

        return result;
    }

    private static boolean isInArea(int x, int y) { return x>=0 && x<m && y>=0 && y<n; }

    private static char[][] monitorEast(int x, int y, char[][] map) {
        int nx = x+1;
        int ny = y;
        while(isInArea(nx,ny)) {
            if(map[ny][nx] == EMPTY) { map[ny][nx] = WATCH; }
            else if(map[ny][nx] == WALL){ break; }
            nx++;
        }
        return map;
    }

    private static char[][] monitorWest(int x, int y, char[][] map) {
        int nx = x-1;
        int ny = y;
        while(isInArea(nx,ny)) {
            if(map[ny][nx] == EMPTY) { map[ny][nx] = WATCH; }
            else if(map[ny][nx] == WALL){ break; }
            nx--;
        }
        return map;
    }

    private static char[][] monitorNorth(int x, int y, char[][] map) {
        int nx = x;
        int ny = y-1;
        while(isInArea(nx,ny)) {
            if(map[ny][nx] == EMPTY) { map[ny][nx] = WATCH; }
            else if(map[ny][nx] == WALL){ break; }
            ny--;
        }
        return map;
    }

    private static char[][] monitorSouth(int x, int y, char[][] map) {
        int nx = x;
        int ny = y+1;
        while(isInArea(nx,ny)) {
            if(map[ny][nx] == EMPTY) { map[ny][nx] = WATCH; }
            else if(map[ny][nx] == WALL){ break; }
            ny++;
        }
        return map;
    }

    private static int getBlindSpots(char[][] map) {
        int result =0;
        for(char[] line : map) {
            for(char ch : line) {
                if(ch == EMPTY) {
                    result++;
                }
            }
        }
        return result;
    }

    private static class CCTVInfo {
        char id;
        int x,y;
        public CCTVInfo(char id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }
}
