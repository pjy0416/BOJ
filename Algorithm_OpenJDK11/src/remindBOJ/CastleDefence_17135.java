package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CastleDefence_17135 {
  static int n,m,d, answer;
  static char[][] map;

  static final int MAX_ARCHER =3, STEP =1;
  static final char NONE ='0', ENEMY ='1';

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    d = Integer.parseInt(st.nextToken());
    map = new char[n][m];
    for(int i=0; i<n; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<m; j++) {
        map[i][j] = st.nextToken().charAt(0);
      }
    }

    br.close();
    solution();
  }

  private static void solution() {
    answer=0;
    setArcherPosition(0, new LinkedList<>());
    System.out.println(answer);
  }

  private static void setArcherPosition(int start, LinkedList<Pos> list) {
    if(list.size() == MAX_ARCHER) {
      countKillEnemies(list);
      return;
    }
    for(int i=start; i<m; i++) {
      list.offer(new Pos(i,n));
      setArcherPosition(i+1, list);
      list.pollLast();
    }
  }

  private static void countKillEnemies(LinkedList<Pos> list) {
    char[][] currentMap = copyMap();
    int turn =0;
    Pos[] archers = getArchers(list);
    boolean[][] isDead = new boolean[n][m];
    int count =0;

    while(turn++ <n) {
      Queue<Pos> deadQueue = new LinkedList<>();
      for(Pos archer : archers) {
        findEnemy:
        for(int dist=1; dist<=d; dist++) {
          int dx = -(dist-1);
          int dy = -1;
          for(int k=1; k<2*dist; k++) {
            int nx = archer.x + dx;
            int ny = archer.y + dy;

            if(isInRange(nx,ny) && currentMap[ny][nx] == ENEMY) {
              if(!isDead[ny][nx]) {
                isDead[ny][nx] = true;
                deadQueue.offer(new Pos(nx, ny));
              }
              break findEnemy;
            }

            dx+= STEP;
            dy += k >=dist ? STEP : -STEP;
          }
        }
        archer.y-= STEP;
      }
      count += deadQueue.size();
      while(!deadQueue.isEmpty()){
        Pos dead = deadQueue.poll();
        currentMap[dead.y][dead.x] = NONE;
      }
    }
    answer = Math.max(answer, count);
  }

  private static boolean isInRange(int x, int y) {
    return x >=0 && x<m && y>=0;
  }

  private static Pos[] getArchers(LinkedList<Pos> list) {
    Pos[] archers = new Pos[MAX_ARCHER];
    for(int i=0; i<3; i++) {
      Pos archer = list.get(i);
      archers[i] = new Pos(archer.x, archer.y);
    }
    return archers;
  }

  private static char[][] copyMap() {
    char[][] copy = new char[n][m];
    for(int i=0; i<n; i++) {
      for(int j=0; j<m; j++) {
        copy[i][j] = map[i][j];
      }
    }
    return copy;
  }

  private static class Pos {
    int x,y;
    public Pos(int x, int y) {
      this.x = x;
      this.y = y;
    }
    @Override
    public String toString() {
      return "x : " + x + "," + " y : " + y;
    }
  }
}
