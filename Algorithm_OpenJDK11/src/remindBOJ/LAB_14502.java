package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LAB_14502 {
    final static char BLANK ='0', WALL = '1', VIRUS ='2';
    final static int[] dx = {1,0,-1,0}, dy ={0,1,0,-1};
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][m];

        for(int i=0; i<n; i++) {
            map[i] = br.readLine().replaceAll(" ","").toCharArray();
        }
        solution(n,m,map);

        br.close();
    }

    private static void solution(int n, int m, char[][] map) {
        answer =0;
        getSafeArea(n,m,map,3,0);
        System.out.println(answer);
    }

    private static void getSafeArea(int n, int m, char[][] map, int cnt, int mul) {
        if(cnt ==0) {
            char[][] newMap = copyMap(n,m,map);
            for(int y=0; y<n; y++) {
                for(int x=0; x<m; x++) {
                    if(newMap[y][x] == VIRUS) {
                        newMap = spread(x,y,newMap);
                    }
                }
            }

            int safeArea =0;
            for(int y=0; y<n; y++) {
                for(int x=0; x<m; x++) {
                    if(newMap[y][x] == BLANK) {
                        safeArea++;
                    }
                }
            }
            answer = Math.max(answer,safeArea);
            return;
        }

        for(int step = mul; step <n*m; step++) {
            int y = step/m;
            int x = step%m;
            if(map[y][x] == BLANK) {
                map[y][x] = WALL;
                getSafeArea(n,m,map,cnt-1, mul+1);
                map[y][x] = BLANK;
            }
        }
    }

    private static char[][] spread(int x, int y, char[][] newMap) {
        for(int i=0; i<4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(isInArea(nx,ny,newMap[0].length, newMap.length)) {
                if(newMap[ny][nx] == BLANK) {
                    newMap[ny][nx] = VIRUS;
                    spread(nx,ny,newMap);
                }
            }
        }
        return newMap;
    }

    private static char[][] copyMap(int n, int m, char[][] map) {
        char[][] result = new char[n][m];
        for(int y=0; y<n; y++) {
            for(int x=0; x<m; x++) {
                result[y][x] = map[y][x];
            }
        }
        return result;
    }

    private static boolean isInArea(int x, int y, int m, int n) {
        return x>=0 && x<m && y>=0 && y<n ? true : false;
    }
}