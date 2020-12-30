package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GoingDown_2096 {
    final static int[] dx = {-1,1};
    final static int scale =3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][scale];

        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<scale; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        solution(n, map);

    }

    private static void solution(int n, int[][] map) {
        final int scale = 3, height = 2, prevY =0, nowY =1;
        int[][] minMap = new int[height][scale], maxMap = new int[height][scale];

        for(int x=0; x<scale; x++) { // init min & max map
            minMap[0][x] = map[0][x];
            maxMap[0][x] = map[0][x];
        }

        for(int y=1; y<n; y++) {
            for(int x=0; x<scale; x++) {
                int minNumber = minMap[prevY][x];
                int maxNumber = maxMap[prevY][x];

                for(int i=0; i<2; i++) {
                    int prevX = x+dx[i];
                    if(isInArea(prevX)) {
                        minNumber = Math.min(minNumber, minMap[prevY][prevX]);
                        maxNumber = Math.max(maxNumber, maxMap[prevY][prevX]);
                    }
                }

                minMap[nowY][x] = minNumber + map[y][x];
                maxMap[nowY][x] = maxNumber + map[y][x];
            }

            for(int x=0; x<scale; x++) {
                minMap[prevY][x] = minMap[nowY][x];
                maxMap[prevY][x] = maxMap[nowY][x];
            }
        }

        printAnswer(maxMap, minMap);
    }

    private static void printAnswer(int[][] maxMap, int[][] minMap) {
        StringBuilder sb = new StringBuilder();
        final String space = " ";
        int lastY = 0;
        int max = Math.max(maxMap[lastY][0], Math.max(maxMap[lastY][1], maxMap[lastY][2]));
        int min = Math.min(minMap[lastY][0], Math.min(minMap[lastY][1], minMap[lastY][2]));
        sb.append(max).append(space).append(min);

        System.out.println(sb.toString());
    }

    private static boolean isInArea(int x) {
        return x>=0 && x<scale;
    }
}
