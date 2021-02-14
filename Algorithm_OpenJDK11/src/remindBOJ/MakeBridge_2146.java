package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MakeBridge_2146 {
  static final int DIRECTIONS = 4;
  static final int[] dx = {1,-1,0,0}, dy = {0,0,1,-1};

  static int n;
  static boolean[][] map;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    map = new boolean[n][n];
    final String ISLAND = "1";

    for(int i=0; i<n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int j=0; j<n; j++) {
        if(st.nextToken().equals(ISLAND)) {
          map[i][j] = true;
        }
      }
    }

    br.close();
    solution();
  }

  private static void solution() {
    boolean[][] visit = new boolean[n][n];
    List<List<Pos>> islandList = new ArrayList<>();
    setIslandList(visit,islandList);

    int answer = Integer.MAX_VALUE;
    int size = islandList.size();
    for(int i=0; i<size; i++) {
      for(int j=0; j<size; j++) {
        if(i!=j) {
          answer = Math.min(answer, getDist(islandList.get(i), islandList.get(j)));
        }
      }
    }

    System.out.println(answer);
  }

  private static int getDist(List<Pos> originList, List<Pos> targetList) {
    int result = Integer.MAX_VALUE;

    for(Pos origin : originList) {
      for(Pos target : targetList) {
        result = Math.min(result, Math.abs(origin.x - target.x) + Math.abs(origin.y - target.y) -1);
      }
    }
    return result;
  }

  private static void setIslandList(boolean[][] visit, List<List<Pos>> islandList) {
    for(int y=0; y<n; y++) {
      for(int x=0; x<n; x++) {
        if(!visit[y][x] && map[y][x]) {
          Queue<Pos> queue = new LinkedList<>();
          queue.offer(new Pos(x,y));
          islandList.add(getIslandPosList(queue, visit));
        }
      }
    }
  }

  private static List<Pos> getIslandPosList(Queue<Pos> queue, boolean[][] visit) {
    List<Pos> islandPosList = new ArrayList<>();
    Pos start = queue.peek();
    visit[start.y][start.x] = true;

    while(!queue.isEmpty()) {
      Pos now = queue.poll();
      islandPosList.add(now);
      for(int i=0; i<DIRECTIONS; i++) {
        int nx = now.x + dx[i], ny = now.y + dy[i];
        if(isInRange(nx,ny) && !visit[ny][nx] && map[ny][nx]) {
          queue.offer(new Pos(nx, ny));
          visit[ny][nx] = true;
        }
      }
    }
    return islandPosList;
  }

  private static boolean isInRange(int x, int y) {
    return x>=0 && x<n && y>=0 && y<n;
  }

  private static class Pos {
    int x, y;

    public Pos(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
