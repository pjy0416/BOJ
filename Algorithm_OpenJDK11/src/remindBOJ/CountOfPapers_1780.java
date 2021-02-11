package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CountOfPapers_1780 {
  static final int PAPER_LENGTH =3;
  static final String NEW_LINE = "\n";

  static int n;
  static int[] papers;
  static int [][] map;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    map = new int[n+1][n+1]; // 1,1 에서 시작

    for(int y=1; y<=n; y++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int x=1; x<=n; x++) {
        map[y][x] = Integer.parseInt(st.nextToken()) +1;
      }
    }

    br.close();
    solution();
  }

  private static void solution() {
    papers = new int[PAPER_LENGTH];
    find(new Pos(1,1), new Pos(n,n));

    StringBuilder sb = new StringBuilder();
    for(int i=0; i<PAPER_LENGTH; i++) {
      sb.append(papers[i]).append(NEW_LINE);
    }
    System.out.println(sb.toString());
  }

  private static void find(Pos start, Pos end) {
    if(isAllSame(start, end)) {
      int paper = map[start.y][start.x];
      papers[paper]++;
    } else {
      int div = (end.y - start.y +1) /PAPER_LENGTH;
      for(int y = start.y; y <= end.y; y+= div) {
        for(int x = start.x; x<= end.x; x+= div) {
          find(new Pos(x,y), new Pos(x+div-1, y+div-1));
        }
      }
    }
  }

  private static boolean isAllSame(Pos start, Pos end) {
    int paper = map[start.y][start.x];
    for(int y = start.y; y <= end.y; y++) {
      for(int x = start.x; x <= end.x; x++) {
        if(paper != map[y][x]) {
          return false;
        }
      }
    }
    return true;
  }

  private static class Pos {
    int x,y;
    public Pos(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
