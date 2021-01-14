package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Snake_3190 {
    final static char WALL = 'W', APPLE ='A', SNAKE ='S', SPACE = ' ', LEFT = 'L', RIGHT = 'D';
    final static int directionLength =4, MAX_TIME = 10000, TURN_LEFT = -1, TURN_RIGHT =1;
    final static Pos[] directionMove= { new Pos(0,-1), new Pos(1,0), new Pos(0,1), new Pos(-1,0)}; // 0 북, 1 동 2 남 3 서

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 보드 크기 N
        char[][] map = initMap(n);

        int k = Integer.parseInt(br.readLine()); // 사과 개수 K
        StringTokenizer st;
        while(k >0) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());   // 행의 위치
            int x =  Integer.parseInt(st.nextToken());  // 열의 위치
            map[y][x] = APPLE;
            k--;
        }

        int l = Integer.parseInt(br.readLine());
        char[] commands = new char[MAX_TIME+1];
        for(int i=0; i<l; i++) {
            st = new StringTokenizer(br.readLine());
            commands[Integer.parseInt(st.nextToken())] = st.nextToken().charAt(0);
        }
        br.close();
        solution(n, map, commands);
    }

    private static char[][] initMap(int n) {
        char[][] map = new char[n+2][n+2];
        Arrays.fill(map[0], WALL);
        Arrays.fill(map[n+1], WALL);
        for(int i=1; i<=n; i++) {
            Arrays.fill(map[i], SPACE);
            map[i][0] = WALL;
            map[i][n+1] = WALL;
        }
        map[1][1] = SNAKE;
        return map;
    }

    private static void solution(int n, char[][] map, char[] commands) {
        int answer =1;
        int direction = 1; // 오른쪽을 보는 방향으로 시작
        LinkedList<Pos> snake = new LinkedList<>();
        snake.offer(new Pos(1,1)); // 초기 뱀 위치
        while(answer < MAX_TIME) {
            Pos now = snake.peek();
            Pos next = new Pos(now.x + directionMove[direction].x, now.y + directionMove[direction].y);
            // 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
            if(isWall(next, n) || map[next.y][next.x] == SNAKE) { // 벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다.
                break;
            }
            if(map[next.y][next.x] != APPLE) { // 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
                Pos tail = snake.pollLast();
                map[tail.y][tail.x] = SPACE;
            }
            // 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
            snake.offerFirst(next);
            map[next.y][next.x] = SNAKE;


            if(commands[answer] == LEFT) {
                direction = getDirection(direction, TURN_LEFT);
            } else if(commands[answer] == RIGHT) {
                direction = getDirection(direction, TURN_RIGHT);
            }
            answer++;
        }
        System.out.println(answer);
    }

    private static int getDirection(int direction, int turn) {
        int next = (direction + turn) % directionLength;
        return next <0 ? next+directionLength : next;
    }

    private static boolean isWall(Pos next, int n) {
        return next.x ==0 || next.y ==0 || next.x == n+1 || next.y ==n+1;
    }

    private static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
