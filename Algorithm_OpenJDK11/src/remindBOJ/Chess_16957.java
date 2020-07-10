package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Chess_16957 {
    final static int[] dx = {0,1,1,1,0,-1,-1,-1}, dy = {-1,-1,0,1,1,1,0,-1};
    final static int MAX_VALUE = 300001;
    static int maxY, maxX;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        maxY = Integer.parseInt(st.nextToken());
        maxX = Integer.parseInt(st.nextToken());

        int[][] map = new int[maxY][maxX];
        parents = new int[maxY*maxX];

        for(int i=0; i<maxY; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<maxX; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                parents[i*maxX+j] = i*maxX+j;
            }
        }

        solution(map);
        br.close();
    }

    private static void solution(int[][] map) {
        StringBuffer sb = new StringBuffer();
        final String space = " ", newLine = "\n";
        for(int y=0; y<maxY; y++) {
            for(int x=0; x<maxX; x++) {
                if(parents[y*maxX+x] ==y*maxX+x) {
                    dfs(x,y,map);
                }
            }
        }

        int mul = maxX*maxY;
        int[] answer = new int[mul];

        for(int i=0; i<mul; i++) {
            answer[find(i)]++;
        }

        for(int y=0; y<maxY; y++) {
            for(int x=0; x<maxX; x++) {
                sb.append(answer[y*maxX+x]).append(space);
            }
            sb.append(newLine);
        }
        System.out.println(sb.toString());
    }

    private static int find(int val) {
        if(val == parents[val]) { return val; }
        return parents[val] = find(parents[val]);
    }

    private static void dfs(int x, int y, int[][] map) {
        int min = MAX_VALUE;
        int minX=0, minY=0;

        for(int i=0; i<8; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(isInArea(nx,ny)) {
                if(min > map[ny][nx]) {
                    min = map[ny][nx];
                    minX = nx;
                    minY = ny;
                }
            }
        }
        if(min < map[y][x]) {
            if(parents[y*maxX+x] == maxX*y+x) {
                parents[y*maxX+x] = minY * maxX + minX;
                dfs(minX, minY, map);
            }
        }
    }

    private static boolean isInArea(int x, int y) {
        return x>=0 && x<maxX && y>=0 && y<maxY;
    }
}
