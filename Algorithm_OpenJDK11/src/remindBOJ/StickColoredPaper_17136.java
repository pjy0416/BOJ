package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class StickColoredPaper_17136 {
  static final int LEN = 10;
  static final int MAX_PAPER_SIZE =5;
  static final int DEFAULT_REMAIN =5;

  static boolean[][] map;
  static int[] remains;
  static int answer = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    map = new boolean[LEN][LEN];
    final String COLORED_PARER = "1";
    int total =0;

    for(int i=0; i<LEN; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int j=0; j<LEN; j++) {
        if(st.nextToken().equals(COLORED_PARER)) {
          map[i][j] = true;
          total++;
        }
      }
    }

    br.close();
    solution(total);
  }

  private static void solution(int total) {
    remains = new int[MAX_PAPER_SIZE];
    Arrays.fill(remains, DEFAULT_REMAIN);

    find(0, 0, total,0);
    if (answer == Integer.MAX_VALUE) {
      answer = -1;
    }
    System.out.println(answer);
  }

  private static void find(int x, int y, int total, int count) {
    if(total ==0) {
      answer = Math.min(answer, count);
      return;
    }

    if(count > answer) {
      return;
    }
    if(x ==LEN) {
      find(0, y+1, total, count);
      return;
    }
    if(y == LEN) { // 검증 끝
      return;
    }

    boolean goNext = false;
    for(int i=0; i<MAX_PAPER_SIZE; i++) {
      if(remains[i] >0 && isAllColoredPaper(x,y,i)) { // 된다.
        remains[i]--;
        setMap(x, y, i, false);
        find(x+1, y, total-(i+1)*(i+1), count+1);
        setMap(x,y,i, true);
        remains[i]++;
        goNext= true;
      }
    }
    if(!goNext) {
      find(x + 1, y, total, count);
    }
  }

  private static boolean isInRange(int x, int y) {
    return x>=0 && x<LEN && y>=0 && y<LEN;
  }

  private static boolean isAllColoredPaper(int x, int y, int area) {
    int maxX = x+area, maxY = y+area;
    if(!isInRange(maxX, maxY)) {
      return false;
    }

    for(int ny= y; ny<= maxY; ny++) {
      for (int nx= x; nx<= maxX; nx++) {
        if(!map[ny][nx]) {
          return false;
        }
      }
    }
    return true;
  }

  private static void setMap(int x, int y, int area, boolean target) {
    for(int ny= y; ny<= y+area; ny++) {
      for (int nx= x; nx <= x+area; nx++) {
        map[ny][nx] = target;
      }
    }
  }
}
