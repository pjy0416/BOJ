package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Sudoku_2580 {
  static int[][] map;
  static List<Pair> emptyList;
  static boolean found;
  static final int LEN =3, MAX_LEN = 9, EMPTY =0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    map = new int[MAX_LEN][MAX_LEN];
    emptyList = new ArrayList<>();
    for(int i = 0; i< MAX_LEN; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int j = 0; j< MAX_LEN; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        if(map[i][j] == EMPTY) {
          emptyList.add(new Pair(j,i));
        }
      }
    }

    br.close();
    solution();
  }

  private static void solution() {
    sudoku(EMPTY);
    StringBuilder sb = new StringBuilder();
    final String NEW_LINE = "\n", SPACE = " ";

    for(int[] line : map) {
      for(int num : line) {
        sb.append(num).append(SPACE);
      }
      sb.append(NEW_LINE);
    }
    System.out.println(sb.toString());
  }

  private static void sudoku(int index) {
    if(found) {
      return;
    }
    if(index == emptyList.size()) {
      found = true;
      return;
    }
    Pair now = emptyList.get(index);
    List<Integer> numList = getNumList(now);
    for(int num : numList) {
      map[now.y][now.x] = num;
      sudoku(index+1);
      if(found) {
        return;
      }
      map[now.y][now.x] = EMPTY;
    }
  }

  private static List<Integer> getNumList(Pair now) {
    List<Integer> numList = new ArrayList<>();
    boolean[] exist = new boolean[MAX_LEN +1];
    
    for(int i = 0; i< MAX_LEN; i++) {
      exist[map[now.y][i]] = true; // 가로 탐색
      exist[map[i][now.x]] = true; // 세로 탐색
    }

    // 칸 탐색 // 0~2, 3~5, 6~9
    Pair scale = getScale(now);
    for(int y=3*scale.y; y<3*(scale.y+1); y++) {
      for(int x=3*scale.x; x<3*(scale.x+1); x++) {
        exist[map[y][x]] = true;
      }
    }

    for(int i=1; i<=MAX_LEN; i++) {
      if(!exist[i]) {
        numList.add(i);
      }
    }
    return numList;
  }

  private static Pair getScale(Pair now) {
    int xScale =LEN+1, yScale =LEN+1;
    for(int i=1; i<=LEN; i++) {
      if(now.x < LEN*i) {
        xScale = Math.min(xScale, i-1);
      }
      if(now.y < LEN*i) {
        yScale = Math.min(yScale, i-1);
      }
    }
    return new Pair(xScale, yScale);
  }

  private static class Pair {
    int x,y;
    public Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
/*
1 3 5 4 6 9 2 7 8
7 8 2 1 3 5 6 4 9
4 6 9 2 7 8 1 3 5
3 2 1 5 4 6 8 9 7
8 7 4 9 1 3 5 2 6
5 9 6 8 2 7 4 1 3
9 1 7 6 5 2 3 8 4
6 4 3 7 8 1 9 5 2
2 5 8 3 9 4 7 6 1

0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
 */