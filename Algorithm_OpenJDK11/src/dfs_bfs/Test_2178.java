package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_2178 {
    private static int n,m;
    private static String[] map;
    private static boolean[][] isVisited;

    //BFS 문제다..
    private static int minRoot() {
        isVisited = initBool();
        int cnt =0;
        int[] queueX = new int[100];
        int[] queueY = new int[100];
        int[] step = new int[100];
        int[] wayX = {0, 1, -1, 0};       // 남 동 서 북
        int[] wayY = {1, 0, 0, -1};       // 남 동 서 북

        int top=0, size =0; // queue 에 쓰일 변수 초기화

        queueX[size] =0;
        queueY[size] =0;
        step[cnt] =1;
        size++;

        while(true) {
            if(isVisited[n-1][m-1]) {
                int sum=1;
                for(int i=0; i<cnt; i++) {
                    System.out.println(step[i]);
                    sum+= step[i];
                }
                System.out.println(sum);
                break;
            }

            int nx = queueX[top];
            int ny = queueY[top];
            top++;

            for(int i=0; i<4; i++) {
                int y = ny + wayY[i];
                int x = nx + wayX[i];

                if(y <=n-1 && y >=0 && x >= 0 && x <=m-1) {
                    if(!isVisited[y][x] && map[y].charAt(x) =='1') {
                        System.out.println(y + "," +x +"가자");
                        queueX[i] = x;
                        queueY[i] = y;
                        size++;
                        isVisited[y][x] = true;
                        step[cnt] =1;
                    }
                }
            }
            cnt++;
        }

        return cnt;
    }

    private static boolean[][] initBool() {
        boolean[][] isVisited = new boolean[n][m];

        for(int y=0; y<n; y++) {
            for(int x=0; x<m; x++) {
                isVisited[y][x] = false;
            }
        }
        return isVisited;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = br.readLine();

        n = Integer.parseInt(inputStr.split(" ")[0]);
        m = Integer.parseInt(inputStr.split(" ")[1]);

        map = new String[n];

        for(int i=0; i<n; i++) {
            map[i] = br.readLine();
        }

        System.out.println(minRoot());

        br.close();
    }
}
