package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class AvoidFoodWaste_1743 {
  static final int[] DX = {1,-1,0,0}, DY = {0,0,1,-1};
  static final int DIRECTIONS =4;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
    boolean[][] isFoodWaste = new boolean[r+1][c+1];

    while(k-- >0) {
      st = new StringTokenizer(br.readLine());
      int y = Integer.parseInt(st.nextToken()), x = Integer.parseInt(st.nextToken());
      isFoodWaste[y][x] = true;
    }
    solution(r,c,isFoodWaste);
  }

  private static void solution(int r, int c, boolean[][] isFoodWaste) {
    boolean[][] visit = new boolean[r+1][c+1];
    Queue<Pos> queue = new LinkedList<>();
    int answer =0;

    for(int y=1; y<=r; y++) {
      for(int x=1; x<=c; x++) {
        if(!visit[y][x] && isFoodWaste[y][x]) {
          visit[y][x] = true;
          queue.offer(new Pos(x,y));
          answer = Math.max(answer, getFoodWasteSize(r,c,visit,queue,isFoodWaste));
        }
      }
    }
    System.out.println(answer);
  }

  private static int getFoodWasteSize(int r, int c, boolean[][] visit, Queue<Pos> queue, boolean[][] isFoodWaste) {
    int count =0;
    while(!queue.isEmpty()) {
      Pos now = queue.poll();
      count++;

      for(int i=0; i<DIRECTIONS; i++) {
        int nx = now.x + DX[i], ny = now.y + DY[i];
        if(isInRange(nx,ny,c,r) && !visit[ny][nx] && isFoodWaste[ny][nx]) {
          visit[ny][nx] = true;
          queue.offer(new Pos(nx,ny));
        }
      }
    }
    return count;
  }

  private static boolean isInRange(int x, int y, int c, int r) {
    return x>0 && x<=c && y>0 && y<=r;
  }

  private static class Pos {
    int x,y;

    public Pos(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
