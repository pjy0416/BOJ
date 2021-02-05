package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Cheese_2636 {
  static final char CHEESE ='1', AIR ='0';
  static final int[] dx = {0,0,-1,1}, dy = {1,-1,0,0};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
    char[][] map = new char[n][m];
    int total =0;

    for(int i=0; i<n; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<m; j++) {
        map[i][j] = st.nextToken().charAt(0);
        if(map[i][j] == CHEESE) {
          total++;
        }
      }
    }

    br.close();
    solution(n,m,map, total);
  }

  private static void solution(int n, int m, char[][] map, int total) {
    LinkedList<Integer> remainCheeses = getRemainsQueue(n,m,map,total);
    StringBuilder sb = new StringBuilder();
    sb.append(remainCheeses.size()).append("\n");
    sb.append(remainCheeses.pollLast());
    System.out.println(sb.toString());
  }

  private static LinkedList<Integer> getRemainsQueue(int n, int m, char[][] map, int total) {
    Queue<Pos> airQueue = new LinkedList<>(), outerCheeseQueue = new LinkedList<>();
    LinkedList<Integer> remainCheeses = new LinkedList<>();
    airQueue.offer(new Pos(0,0));
    boolean[][] visit = new boolean[n][m];
    visit[0][0] = true;


    while(total >0) {
      remainCheeses.offer(total);
      while (!airQueue.isEmpty()) {
        Pos air = airQueue.poll();

        for (int i = 0; i < 4; i++) {
          int nx = air.x + dx[i], ny = air.y + dy[i];
          if (isInArea(nx, ny, m, n) && !visit[ny][nx]) {
            Pos now = new Pos(nx, ny);
            visit[ny][nx] = true;
            if (map[ny][nx] == AIR) {
              airQueue.offer(now);
            } else {
              outerCheeseQueue.offer(now);
            }
          }
        }
      }
      while (!outerCheeseQueue.isEmpty()) {
        Pos outerCheese = outerCheeseQueue.poll();
        total--;
        airQueue.offer(outerCheese);
        map[outerCheese.y][outerCheese.x] = AIR;
      }
    }
    return remainCheeses;
  }

  private static boolean isInArea(int x, int y, int m, int n) {
    return x >=0 && x<m && y>=0 && y<n;
  }

  private static class Pos {
    int x,y;

    public Pos(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
