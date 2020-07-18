package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class KnightMove_7562 {
    static int[] dx ={-1,-2,-2,-1,1,2,2,1}, dy={-2,-1,1,2,2,1,-1,-2};

    public static void main(String[] args) throws IOException {
        solution(new BufferedReader(new InputStreamReader(System.in)));
    }

    private static void solution(BufferedReader br) throws IOException {
        int testCase = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();
        final String NEWLINE = "\n";

        for(int i=0; i<testCase; i++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            Pos start = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),0);
            st = new StringTokenizer(br.readLine());
            Pos dest = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),0);

            sb.append(getMove(start,dest,n)).append(NEWLINE);
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static int getMove(Pos start, Pos dest, int n) {
        int result =0;
        LinkedList<Pos> moveList = new LinkedList<>();
        moveList.offer(start);
        boolean[][] visit = new boolean[n][n];
        visit[start.y][start.x] = true;

        while(!moveList.isEmpty()) {
            Pos pos = moveList.poll();
            if(pos.x == dest.x && pos.y == dest.y) {
                result = pos.step;
                break;
            }
            for(int i=0; i<8; i++) {
                int nx = pos.x+dx[i];
                int ny = pos.y+dy[i];
                int step = pos.step+1;
                if(inInArea(nx,ny,n)) {
                    if(!visit[ny][nx]) {
                        visit[ny][nx] = true;
                        moveList.offer(new Pos(nx,ny,step));
                    }
                }
            }
        }
        return result;
    }

    private static boolean inInArea(int x, int y, int n) {
        return x>=0 && x<n && y>=0 && y<n;
    }

    private static class Pos {
        int x,y,step;
        public Pos(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
}
