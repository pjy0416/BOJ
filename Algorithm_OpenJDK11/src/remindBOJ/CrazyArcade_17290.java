package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class CrazyArcade_17290 {
    final static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    final static int SIZE = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //r : y, c : x
        String[] strArr = br.readLine().split(" ");
        int[] pos = {Integer.parseInt(strArr[0])-1, Integer.parseInt(strArr[1])-1};
        char[][] map = new char[SIZE][SIZE];

        for(int i=0; i<SIZE; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(solution(pos, map));

        br.close();
    }

    private static int solution(int[] pos, char[][] map) {
        int answer =0;

        PriorityQueue<Bazzi> pq = new PriorityQueue<>();
        pq.offer(new Bazzi(pos[1], pos[0],0)); // (y,x)

        int[][] visit = new int[SIZE][SIZE];
        visit[pos[0]][pos[1]] = 1; // (y,x)
        while(!pq.isEmpty()) {
            Bazzi bazzi = pq.poll();

            if(isSafe(bazzi, map)) {
                answer = bazzi.step;
                break;
            }

            for(int i=0; i<4; i++) {
                int ny = bazzi.y + dy[i];
                int nx = bazzi.x + dx[i];

                if(isInArea(nx,ny)) {
                    if(visit[ny][nx] ==0) {
                        visit[ny][nx] =1;
                        int step = bazzi.step+1;
                        pq.offer(new Bazzi(nx,ny, step));
                    }
                }
            }
        }
        return answer;
    }

    private static boolean isInArea(int nx, int ny) {
        return nx >=0 && nx <SIZE && ny >=0 && ny <SIZE ? true : false;
    }

    private static boolean isSafe(Bazzi bazzi, char[][] map) {
        for(int i=0; i<SIZE; i++) {
            if(map[i][bazzi.x] == 'o') {
                return false;
            }
        }
        for(int i=0; i<SIZE; i++) {
            if(map[bazzi.y][i] == 'o') {
                return false;
            }
        }
        return true;
    }
}

class Bazzi implements Comparable<Bazzi>{
    int x;
    int y;
    int step;

    public Bazzi(int x, int y, int step) {
        this.x = x;
        this.y = y;
        this.step =step;
    }

    @Override
    public int compareTo(Bazzi b) {
        return this.step > b.step ? 1: -1;
    }
}
/*
https://www.acmicpc.net/problem/17290
 */