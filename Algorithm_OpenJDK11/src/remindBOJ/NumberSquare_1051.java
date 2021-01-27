package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NumberSquare_1051 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] map = new char[n][m];

        for(int i=0; i<n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        br.close();
        solution(n,m,map);
    }

    private static void solution(int n, int m, char[][] map) {
        int side = getSquareSide(n,m,map);

        System.out.println(side*side);
    }

    private static int getSquareSide(int n, int m, char[][] map) {
        int max =1;
        int min = Math.min(m,n);
        for(int y=0; y<n; y++) {
            for(int x=0; x<m; x++) {
                for(int i=min; i>=1; i--) {
                    if(isInRange(x+i,y+i,m,n) && isSamePoint(map,x,y,i)) {
                        max = Math.max(max, 1+i);
                        break;
                    }
                }

            }
        }
        return max;
    }

    private static boolean isSamePoint(char[][] map, int x, int y, int i) {
        int nextX = x + i, nextY = y + i;
        return map[y][x] == map[y][nextX] && map[y][x] == map[nextY][x] && map[nextY][x] == map[nextY][nextX];
    }

    private static boolean isInRange(int x, int y, int m, int n) {
        return x<m && y<n;
    }
}
