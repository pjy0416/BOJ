package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class RobotCleaner_14503 {
    final static int DIRECTIONS =4, EMPTY =0, WALL =1, CLEAN=2;
    final static int[] dx = {-1,0,1,0}, dy = {0,-1,0,1};
    static int r,c;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = getIntValue(st);
        c = getIntValue(st);
        map = new int[r][c];

        st = new StringTokenizer(br.readLine());
        int tmpY = getIntValue(st);
        Pos robot = new Pos(getIntValue(st), tmpY, getIntValue(st));

        for(int y=0; y<r; y++) {
            st = new StringTokenizer(br.readLine());
            for(int x=0; x<c; x++) {
                map[y][x] = getIntValue(st);
            }
        }
        br.close();
        solution(robot);
    }

    private static void solution(Pos robot) {
        LinkedList<Pos> moveList = new LinkedList<>();
        moveList.offer(robot);
        int answer =0;
        int rotate =0;

        while(!moveList.isEmpty()) {
            Pos pos = moveList.poll();
            int x = pos.x;
            int y = pos.y;
            int dir = pos.dir;
            int nextX, nextY, nextDir;

            if (map[y][x] == EMPTY) { // 빈 공간이면
                map[y][x] = CLEAN; // 청소하고
                answer++;           // 카운트 세주기
            }

            if(rotate == DIRECTIONS) { // 네방향 다 청소할 곳이 없을 때
                int calDir = getNewDir(dir); // 후진하기 위해 구하는 dir
                nextX = x + dx[calDir];
                nextY = y + dy[calDir];
                if(map[nextY][nextX] == WALL) { break; }

                moveList.offer(new Pos(nextX,nextY,dir)); // 방향 그대로 후진
                rotate =0;
            } else {
                nextDir = getNewDir(dir);
                nextX = x + dx[dir];
                nextY = y + dy[dir];
                if(map[nextY][nextX] == EMPTY) { // 비어있는 공간있으면 이동
                    moveList.offer(new Pos(nextX,nextY,nextDir));
                    rotate =0;
                }
                else { // 좌측으로 못가는 경우, 회전
                    moveList.offer(new Pos(x,y,nextDir));
                    rotate++;
                }
            }
        }
        System.out.println(answer);
    }

    private static int getIntValue(StringTokenizer st) { return Integer.parseInt(st.nextToken()); }

    private static int getNewDir(int dir) {
        int result = (dir+3)%DIRECTIONS;
        return result;
    }

    private static class Pos {
        int x,y,dir;

        public Pos (int x, int y, int dir) {
            this.x =x;
            this.y =y;
            this.dir =dir;
        }
    }
}

/*
현재 위치를 청소한다.
현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다.
왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
로봇 청소기는 이미 청소되어있는 칸을 또 청소하지 않으며, 벽을 통과할 수 없다.
 */