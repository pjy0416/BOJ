package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class SheepGroup_11123 {
    final static char sheep = '#';
    final static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<char[][]> sheepList = new ArrayList<>();
        int cases = Integer.parseInt(br.readLine());

        for(int i=0; i<cases; i++) {
            String sizeInfo = br.readLine();
            int ySize = Integer.parseInt(sizeInfo.split(" ")[0]);

            char[][] map = new char[ySize][];

            for(int y=0; y<ySize; y++) {
                map[y] = br.readLine().toCharArray();
            }
            sheepList.add(map);
        }

        solution(sheepList);

        br.close();
    }

    private static void solution(ArrayList<char[][]> sheepList) {
        for(char[][] sheepMap : sheepList) {
            int ySize = sheepMap.length;
            int xSize = sheepMap[0].length;
            int[][] group =new int[ySize][xSize];
            int idx =1;

            for(int y=0; y<ySize; y++) {
                for(int x=0; x<xSize; x++) {
                    if(sheepMap[y][x] == sheep && group[y][x] ==0) {
                        group = groupingSheep(group, sheepMap, idx++, x, y);
                    }
                }
            }
            System.out.println(idx-1);
        }
    }

    private static int[][] groupingSheep(int[][] group, char[][] sheepMap, int idx, int x, int y) {
        LinkedList<int[]> moveList = new LinkedList<>();
        moveList.offer(new int[]{x,y});
        group[y][x] = idx;


        while(!moveList.isEmpty()) {
            int[] pos = moveList.poll();

            for(int i=0; i<4; i++) {
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];

                if(isInArea(nx,ny,sheepMap[0].length, sheepMap.length)) {
                    if(sheepMap[ny][nx] == sheep && group[ny][nx] ==0) {
                        moveList.offer(new int[] {nx,ny});
                        group[ny][nx] = idx;
                    }
                }
            }
        }
        return group;
    }

    private static boolean isInArea(int x, int y, int maxX, int maxY) {
        return x>=0 && x<maxX && y>=0 && y<maxY ? true : false;
    }
}
/*
https://www.acmicpc.net/problem/11123
 */