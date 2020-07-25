package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Procession_1080 {
    private final static char ONE = '1', ZERO = '0';
    private final static int DIV = 3;
    private static int n,m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        char[][] map = new char [n][m];
        char[][] target = new char[n][m];

        for(int i=0; i<n; i++) {
            map[i] = br.readLine().toCharArray();
        }
        for(int i=0; i<n; i++) {
            target[i] = br.readLine().toCharArray();
        }

        br.close();
        solution(map, target);
    }

    private static void solution(char[][] map, char[][] target) {
        int answer =0;
        boolean isSame = false;

        for(int y=0; y<=n-DIV; y++) { // 시작점 y
            for(int x=0; x<=m-DIV; x++) { // 시작점 x
                isSame = isSameMap(map,target);
                if(isSame) { break; }
                else {
                    if(map[y][x] != target[y][x]) { // 시작점이 다르면 바꿔야겠네?
                        map = getReverseMap(map, x, y);
                        answer++;
                    }
                }
            }
        }
        isSame =isSameMap(map,target);
        if (!isSame) { answer = -1; }
        System.out.println(answer);
    }

    private static boolean isSameMap(char[][] map, char[][] target) {
        for(int y=0; y<n; y++) {
            for(int x=0; x<m; x++) {
                if(map[y][x] != target[y][x]) { return false; }
            }
        }
        return true;
    }

    private static char[][] getReverseMap(char[][] map, int x, int y) {
        for(int findY= y; findY <y+DIV; findY++) {
            for(int findX =x; findX <x+DIV; findX++) {
                if(map[findY][findX] == ZERO) { map[findY][findX] = ONE; }
                else { map[findY][findX] = ZERO; }
            }
        }
        return map;
    }

    private static char[][] copyMap(char[][] map) {
        char[][] result = new char[n][m];
        for(int y=0; y<n; y++) {
            for(int x=0; x<m; x++) {
                result[y][x] = map[y][x];
            }
        }
        return result;
    }
}
