package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CandyGame_3085 {
    private final static int[] dPos = {1,-1};
    private static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        char[][] map = new char[n][n];

        for(int y=0; y<n; y++) {
            map[y] = br.readLine().toCharArray();
        }
        br.close();

        solution(map);
    }

    private static void solution(char[][] map) {
        int answer =0;
        for(int y=0; y<n; y++) {
            for(int x=0; x<n; x++) {
                answer = Math.max(answer, Math.max(findVertical(x, map), findHorizontal(y, map)));
                for(int i=0; i<2; i++) {
                    int nx = x+dPos[i];
                    int ny = y+dPos[i];
                    if(isInArea(nx,y)) {
                        map = swapX(x, nx, y, map);
                        answer = Math.max(answer, findVertical(x, map));
                        map = swapX(x, nx, y, map);
                    }
                    if(isInArea(x,ny)) {
                        map = swapY(y, ny, x, map);
                        answer = Math.max(answer, findHorizontal(y,map));
                        map = swapY(y, ny, x, map);
                    }
                }
            }
        }
        System.out.println(answer);
    }

    private static boolean isInArea(int x, int y) {
        return x>=0 && y>=0 && x<n && y<n;
    }

    private static char[][] swapX(int x, int nx, int y, char[][] map) {
        char tmp = map[y][x];
        map[y][x] = map[y][nx];
        map[y][nx] = tmp;
        return map;
    }
    private static char[][] swapY(int y, int ny, int x, char[][] map) {
        char tmp = map[y][x];
        map[y][x] = map[ny][x];
        map[ny][x] = tmp;
        return map;
    }

    private static int findVertical(int x, char[][] map) { // 세로 검색
        int result =0;
        int partialSum =1;
        char prev = 'a';

        for(int y=0; y<n; y++) {
            if(prev != map[y][x]) {
                prev = map[y][x];
                partialSum =1;
            } else { partialSum++; }
            result = Math.max(result,partialSum);
        }
        return result;

    }
    private static int findHorizontal(int y, char[][] map) {    // 가로 검색
        int result =0;
        int partialSum =1;
        char prev = 'a';

        for(int x=0; x<n; x++) {
            if(prev != map[y][x]) {
                prev = map[y][x];
                partialSum =1;
            } else { partialSum++; }
            result = Math.max(result,partialSum);
        }
        return result;
    }
}
