package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class StartLink_5014 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int f = Integer.parseInt(st.nextToken()), s = Integer.parseInt(st.nextToken()), g = Integer.parseInt(st.nextToken()),
            u = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken());
    br.close();
    solution(f,s,g,u,d);
  }

  private static void solution(int f, int s, int g, int u, int d) {
    // 총 건문을 F 높이, S층 시작 ~> G층으로 가야하고, U는 +, d는 -버튼
    // 10층 높이, 1층 시작으로, 10층 까야하고, U는 +2, D는 -1
    final int ZERO =0;
    final String FAIL = "use the stairs";

    StringBuilder sb = new StringBuilder();
    boolean[] visit = new boolean[f+1];
    visit[s] = true;
    Queue<Pos> queue = new LinkedList<>();
    queue.offer(new Pos(s,ZERO));

    while(!queue.isEmpty()) {
      Pos now = queue.poll();
      if(now.floor == g) {
        sb.append(now.count);
        break;
      }
      int upStair = now.floor + u, downStair = now.floor - d, nextCount = now.count+1;
      if(upStair <= g && !visit[upStair]) {
        visit[upStair] =true;
        queue.offer(new Pos(upStair, nextCount));
      }
      if(downStair >=ZERO && !visit[downStair]) {
        visit[downStair] = true;
        queue.offer(new Pos(downStair, nextCount));
      }
    }
    if(sb.length() ==ZERO) {
      sb.append(FAIL);
    }

    System.out.println(sb.toString());
  }

  private static class Pos {
    int floor, count;

    public Pos(int floor, int count) {
      this.floor = floor;
      this.count = count;
    }
  }
}
/*
10 1 10 2 1
=>6

100 2 1 1 0
=> 불가능
 */