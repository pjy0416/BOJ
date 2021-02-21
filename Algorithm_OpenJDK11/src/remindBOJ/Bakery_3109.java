package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bakery_3109 {
  static final int[] DX = {1,1,1}, DY = {-1,0,1};
  static final int DIRECTIONS =3;

  static boolean[][] isBuilding;
  static int r,c;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    r = Integer.parseInt(st.nextToken());
    c = Integer.parseInt(st.nextToken());
    isBuilding = new boolean[r][c];
    final char BUILDING = 'x';

    for(int i=0; i<r; i++) {
      String inputStr = br.readLine();
      for(int j=0; j<c; j++) {
        if(inputStr.charAt(j) == BUILDING) {
          isBuilding[i][j] = true;
        }
      }
    }

    br.close();
    solution();
  }

  private static void solution() {
    final int DEFAULT_VERTICAL = 0;
    boolean[][] visit = new boolean[r][c];
    int answer =0;

    for(int y=0; y<r; y++) {
      if(findPipeLines(DEFAULT_VERTICAL,y, visit)) {
        answer++;
      }
    }
    System.out.println(answer);
  }

  private static boolean findPipeLines(int x, int y, boolean[][] visit) {
    visit[y][x] = true;
    if(x == c-1) {
      return true;
    }
    for(int i=0; i<DIRECTIONS; i++) {
      int nx = x + DX[i], ny = y + DY[i];
      if(isInArea(nx,ny) && !visit[ny][nx] && !isBuilding[ny][nx]) {
        if(findPipeLines(nx,ny,visit)) {
          return true;
        }
      }
    }
    return false;
  }

  private static boolean isInArea(int x, int y) {
    return x <c && y>=0 && y<r;
  }
}
/*
 가스관과 빵집을 연결하는 파이프를 설치하려고 한다.
 빵집과 가스관 사이에는 건물이 있을 수도 있다.
 건물이 있는 경우에는 파이프를 놓을 수 없다.
 이 경로는 겹칠 수 없고, 서로 접할 수도 없다.
 모든 파이프라인은 첫째 열에서 시작해야 하고, 마지막 열에서 끝나야 한다.
 각 칸은 오른쪽, 오른쪽 위 대각선, 오른쪽 아래 대각선으로 연결할 수 있고, 각 칸의 중심끼리 연결하는 것이다.  
 */