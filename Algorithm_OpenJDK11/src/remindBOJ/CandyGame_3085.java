package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class CandyGame_3085 {
    final static int[] dx = {0, 0,0,1,-1}, dy = {0, 1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] map = new char[N][];

        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        br.close();

        solution(map, N);
    }

    private static void solution(char[][] map, int N) {
        int answer =0;

        for(int y=0; y<N; y++) {
            for(int x=0; x<N; x++) {
                Pos originPos = new Pos(x,y);
                for(int direction =0; direction<5; direction++) {
                    Pos targetPos = new Pos(x + dx[direction], y + dy[direction]);
                    if(!isInArea(targetPos,N)) {
                        continue;
                    }
                    swapMap(map, originPos, targetPos);
                    answer = Math.max(answer, getCandyCount(map, N, originPos));
                    swapMap(map, originPos, targetPos);
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean isInArea(Pos pos, int n) {
        return pos.x >=0 && pos.y >=0 && pos.x <n && pos.y <n;
    }

    private static void swapMap(char[][] map, Pos origin, Pos target) {
        char tmp = map[origin.y][origin.x];
        map[origin.y][origin.x] = map[target.y][target.x];
        map[target.y][target.x] = tmp;
    }

    private static int getCandyCount(char[][] map, int N, Pos originPos) {
        boolean[] visit = new boolean[N];
        int verticalCount =0, horizontalCount =0;
        int[] directions = {1,-1};
        LinkedList<Pos> moveList = new LinkedList<>();
        moveList.push(originPos);

        //vertical check
        while(!moveList.isEmpty()) {
            Pos now = moveList.poll();
            verticalCount++;
            visit[now.y] = true;
            for(int i=0; i<2; i++) {
                Pos movedPos = new Pos(now.x, now.y + directions[i]);
                if(isInArea(movedPos, N) && !visit[movedPos.y] && map[now.y][now.x] == map[movedPos.y][movedPos.x]) {
                    moveList.push(movedPos);
                }
            }
        }

        Arrays.fill(visit, false);
        moveList.push(originPos);

        //horizontal check
        while(!moveList.isEmpty()) {
            Pos now = moveList.poll();
            horizontalCount++;
            visit[now.x] = true;
            for(int i=0; i<2; i++) {
                Pos movedPos = new Pos(now.x + directions[i], now.y);
                if(isInArea(movedPos, N) && !visit[movedPos.x] && map[now.y][now.x] == map[movedPos.y][movedPos.x]) {
                    moveList.push(movedPos);
                }
            }
        }
        return Math.max(verticalCount, horizontalCount);
    }


    private static class Pos {
        int x,y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
