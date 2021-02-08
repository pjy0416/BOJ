package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MarbleEscape_13460 {
  static final int[] dx = {0,0,1,-1}, dy = {1,-1,0,0};
  static final char HOLE = 'O', WALL = '#', ROAD = '.';
  static char[][] map;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
    map = new char[n][m];
    Pos redBall = null, blueBall = null;
    final char RED_BALL = 'R', BLUE_BALL ='B';

    for(int y=0; y<n; y++) {
      map[y] = br.readLine().toCharArray();
      for(int x=0; x<m; x++) {
        if(map[y][x] == RED_BALL) {
          redBall = new Pos(x,y);
          map[y][x] = ROAD;
        } else if(map[y][x] == BLUE_BALL) {
          blueBall = new Pos(x,y);
          map[y][x] = ROAD;
        }
      }
    }

    br.close();
    solution(n,m,redBall,blueBall);
  }

  private static void solution(int n, int m, Pos redBall, Pos blueBall) {
    final int END_COUNT = 10;
    int answer = -1, count =-1;
    Queue<Pos[]> moveQueue = new LinkedList<>();
    moveQueue.offer(new Pos[]{redBall, blueBall});
    boolean[][][][] visit = new boolean[n][m][n][m];
    visit[redBall.y][redBall.x][blueBall.y][blueBall.x] = true;
    boolean found = false;

    while(count++ < END_COUNT) {
      int size = moveQueue.size();
      for(int i=0; i<size; i++) {
        Pos[] balls = moveQueue.poll();

        if(isHole(balls[0]) && !isHole(balls[1])) { // 답인 경우
          answer = count;
          found = true;
          break;
        } else if(!isHole(balls[1])) { // 구멍에 빠진 구슬이 없는 경우 움직여 줘야함
          for(int dir=0; dir<4; dir++) {
            Pos[] nextBalls = getNextPositions(dir, balls);
            if(!visit[nextBalls[0].y][nextBalls[0].x][nextBalls[1].y][nextBalls[1].x]) {
              visit[nextBalls[0].y][nextBalls[0].x][nextBalls[1].y][nextBalls[1].x] = true;
              moveQueue.offer(nextBalls);
            }
          }
        }
      }
      if(found) {
        break;
      }
    }

    System.out.println(answer);
  }

  private static Pos[] getNextPositions(int dir, Pos[] balls) {
    Pos redBall = balls[0].clone();
    Pos blueBall = balls[1].clone();

    if(isMoveRedFirst(dir, balls)) {
      move(dir, redBall, blueBall);
    } else {
      move(dir, blueBall, redBall);
    }
    return new Pos[]{redBall, blueBall};
  }

  private static void move(int dir, Pos first, Pos second) {
    while(map[first.y][first.x] == ROAD) {
      first.x += dx[dir];
      first.y += dy[dir];
    }
    if(map[first.y][first.x] != HOLE) {
      first.y -= dy[dir];
      first.x -= dx[dir];
    }
    while(map[second.y][second.x] == ROAD) {
      second.x += dx[dir];
      second.y += dy[dir];
      if(second.y == first.y && second.x == first.x) {
        break;
      }
    }
    if(map[second.y][second.x] != HOLE) {
      second.y -= dy[dir];
      second.x -= dx[dir];
    }
  }

  private static boolean isMoveRedFirst(int dir, Pos[] balls) {
    boolean result = true;
    switch (dir) { // 0 : red , 1 : blue
      case 0: // 아래쪽 이동
        if(balls[0].y < balls[1].y) { // blue가 더 아래 있는 경우
          result = false;
        }
        break;
      case 1: // 위쪽 이동
        if(balls[1].y < balls[0].y) { // blue가 더 위에 있는 경우
          result = false;
        }
        break;
      case 2: // 오른쪽 이동
        if(balls[0].x < balls[1].x) {
          result = false;
        }
        break;
      case 3: // 왼쪽 이동
        if(balls[1].x < balls[0].x) {
          result = false;
        }
        break;
      default:
        break;
    }
    return result;
  }

  private static boolean isHole(Pos ball) {
    return map[ball.y][ball.x] == HOLE;
  }


  private static class Pos {
    int x, y;
    public Pos(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public Pos clone() {
      return new Pos(this.x, this.y);
    }
  }
}
