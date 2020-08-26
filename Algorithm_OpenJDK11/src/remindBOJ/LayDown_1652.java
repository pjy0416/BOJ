package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LayDown_1652 {
    final static char EMPTY ='.';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[][] map = new char[n+1][n+1];
        for(int i=0; i<n; i++) {
            char[] charArr = br.readLine().toCharArray();
            for(int j=0; j<n; j++)  map[i][j] = charArr[j];
        }
        br.close();
        solution(n, map);
    }

    private static void solution(int n, char[][] map) {
        StringBuffer sb = new StringBuffer();
        sb.append(getHorizontal(n, map)).append(" ");
        sb.append(getVertical(n, map));
        System.out.println(sb.toString());
    }

    private static int getVertical(int n, char[][] map) {
        boolean[][] visit = new boolean[n+1][n+1];
        int answer =0;
        for(int x=0; x<n; x++) {
            for(int y=0; y<n; y++) {
                if(map[y][x] == EMPTY && !visit[y][x]) {
                    visit[y][x] = true;
                    int nextY = y + 1;
                    while (map[nextY][x] == EMPTY) {
                        visit[nextY][x] = true;
                        nextY++;
                    }
                    if(visit[y+1][x])   answer++;
                }
            }
        }
        return answer;
    }

    private static int getHorizontal(int n, char[][] map) {
        boolean[][] visit = new boolean[n+1][n+1];
        int answer =0;
        for(int y=0; y<n; y++) {
            for(int x=0; x<n; x++) {
                if(map[y][x] == EMPTY && !visit[y][x]) {
                    visit[y][x] = true;
                    int nextX = x + 1;
                    while (map[y][nextX] == EMPTY) {
                        visit[y][nextX] = true;
                        nextX++;
                    }
                    if(visit[y][x+1])   answer++;
                }
            }
        }
        return answer;
    }

    private static class Pos {
        int x,y;
        public Pos(int x, int y) {
            this.x =x;
            this.y =y;
        }
    }
}

