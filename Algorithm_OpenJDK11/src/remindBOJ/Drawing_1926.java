package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Drawing_1926 {
  static final int[] DX = {0,0,1,-1}, DY = {1,-1,0,0};
  static final int DIRECTIONS =4, DEFAULT_GROUP_INDEX =0;;

  static int n,m;
  static boolean[][] drawingPaper;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    drawingPaper = new boolean[n][m];
    final String DRAWING = "1";

    for(int i=0; i<n; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<m; j++) {
        drawingPaper[i][j] = st.nextToken().equals(DRAWING) ? true : false;
      }
    }

    br.close();
    solution();
  }

  private static void solution() {
    int groupIdx =DEFAULT_GROUP_INDEX, maxDrawing =0;
    int[][] groups = new int[n][m];

    for(int y=0; y<n; y++) {
      for(int x=0; x<m; x++) {
        if(drawingPaper[y][x] && groups[y][x] == DEFAULT_GROUP_INDEX) {
          maxDrawing = Math.max(maxDrawing, grouping(new Pos(x,y),++groupIdx,groups));
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    sb.append(groupIdx).append("\n").append(maxDrawing);
    System.out.println(sb.toString());
  }

  private static int grouping(Pos pos, final int GROUP_INDEX, int[][] groups) {
    int count =1;
    Queue<Pos> queue = new LinkedList<>();
    queue.offer(pos);
    groups[pos.y][pos.x] = GROUP_INDEX;

    while(!queue.isEmpty()) {
      Pos now = queue.poll();

      for(int i=0; i<DIRECTIONS; i++) {
        int nx = now.x + DX[i], ny = now.y + DY[i];
        if(isInRange(nx,ny) && drawingPaper[ny][nx] && groups[ny][nx] == DEFAULT_GROUP_INDEX) {
          groups[ny][nx] = GROUP_INDEX;
          count++;
          queue.offer(new Pos(nx,ny));
        }
      }
    }
    return count;
  }

  private static boolean isInRange(int x, int y) {
    return x>=0 && x<m && y>=0 && y<n;
  }


  private static class Pos {
    int x,y;

    public Pos(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
