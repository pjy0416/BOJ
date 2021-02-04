package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jewelry_2492 {
  static final String NEW_LINE = "\n", SPACE = " ";
  static int width,height,t,k;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    width = Integer.parseInt(st.nextToken());
    height = Integer.parseInt(st.nextToken());
    t = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());

    Pos[] jewelryArr = new Pos[t];
    for(int i=0; i<t; i++) {
      st = new StringTokenizer(br.readLine());
      jewelryArr[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }
    br.close();
    solution(jewelryArr);
  }

  private static void solution(Pos[] jewelryArr) {
    int max = 0;
    Pos maxPos = new Pos(0,0);

    for(int i=0; i<t; i++) {
      for(int j=0; j<t; j++) {
        int x = jewelryArr[i].x + k > width ? width - k : jewelryArr[i].x;
        int y = jewelryArr[j].y + k > height ? height - k : jewelryArr[j].y;

        int count = getCount(x,y,jewelryArr);
        if(count > max) {
          max = count;
          maxPos.x = x;
          maxPos.y = y+k;
        }
      }
    }
    StringBuilder sb = new StringBuilder();
    sb.append(maxPos.x).append(SPACE).append(maxPos.y).append(NEW_LINE);
    sb.append(max);

    System.out.println(sb.toString());
  }

  private static int getCount(int x, int y, Pos[] jewelryArr) {
    final int MAX_X = x+k, MAX_Y = y+k;
    int count =0;
    for(Pos jewelry : jewelryArr) {
      if(x <= jewelry.x && jewelry.x <=MAX_X && y <= jewelry.y && jewelry.y <= MAX_Y) {
        count++;
      }
    }
    return count;
  }

  private static class Pos {
    int x, y;
    public Pos(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}

/*
1 1 1 1
0 0
 */