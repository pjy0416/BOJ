package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class FindArea_2583 {
    private static class Pos{
        int x,y;
        public Pos(int x, int y){
            this.x =x;
            this.y =y;
        }
    }

    final static int[] dx ={1,-1,0,0}, dy ={0,0,1,-1};
    final static String SPACE = " ";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        boolean[][] isArea = new boolean[m][n];

        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            for(int x=startX; x<endX; x++) {
                for(int y=startY; y<endY; y++) {
                    isArea[y][x] = true;
                }
            }
        }
        br.close();
        solution(m,n,isArea);
    }

    private static void solution(int m, int n, boolean[][] isArea) {
        LinkedList<Pos> moveList = new LinkedList<Pos>();
        ArrayList<Integer> ansList = new ArrayList<>();

        for(int y=0; y<m; y++) {
            for(int x=0; x<n; x++) {
                if(!isArea[y][x]) {
                    isArea[y][x] = true;
                    moveList.offer(new Pos(x,y));
                    int area=1;

                    while(!moveList.isEmpty()) {
                        Pos pos = moveList.poll();
                        for(int i=0; i<4; i++) {
                            int nx = pos.x+dx[i];
                            int ny = pos.y+dy[i];
                            if(!isOutOfIdx(nx,ny,n,m)) {
                                if(!isArea[ny][nx]) {
                                    isArea[ny][nx] = true;
                                    moveList.offer(new Pos(nx,ny));
                                    area++;
                                }
                            }
                        }
                    }
                    ansList.add(area);
                }
            }
        }
        System.out.println(ansList.size());
        Collections.sort(ansList);
        StringBuffer sb = new StringBuffer();
        for(int answer : ansList) {
            sb.append(answer).append(SPACE);
        }
        System.out.println(sb.toString());
    }

    private static boolean isOutOfIdx(int x, int y, int n, int m) {
        return x<0 || x>=n || y<0 || y>=m;
    }
}
