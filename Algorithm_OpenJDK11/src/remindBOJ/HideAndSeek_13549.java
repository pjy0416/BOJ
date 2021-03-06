package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HideAndSeek_13549 {
  static final int MAX = 100000, DIRECTIONS_LENGTH =3, JUMP_INDEX =2;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    br.close();
    solution(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
  }

  private static void solution(int subin, int sister) {
    int answer = 0;
    int[] costArr = initCostArr();
    ArrayDeque<Pos> arrayDeque = new ArrayDeque<>();
    arrayDeque.offer(new Pos(subin, 0));

    int[] directions = new int[] {1,-1,0}, dCost = {1,1,0};

    while(!arrayDeque.isEmpty()) {
      Pos now = arrayDeque.poll();
      if(now.location == sister) {
        answer = now.cost;
        break;
      }
      directions[JUMP_INDEX] = now.location;
      for(int i=0; i<DIRECTIONS_LENGTH; i++) {
        int nextLocation = now.location + directions[i], nextCost = now.cost + dCost[i];
        if(isInRange(nextLocation) && costArr[nextLocation] > nextCost) {
          Pos next = new Pos(nextLocation, nextCost);
          if(arrayDeque.isEmpty()) {
            arrayDeque.offer(next);
          } else if(arrayDeque.peek().cost < nextCost) {
            arrayDeque.offer(next);
          } else {
            arrayDeque.offerFirst(next);
          }
          costArr[nextLocation] = nextCost;
        }
      }
    }
    System.out.println(answer);
  }


  private static boolean isInRange(int location) {
    return location >=0 && location <= MAX;
  }

  private static int[] initCostArr() {
    int[] costArr = new int[MAX+1];
    Arrays.fill(costArr, Integer.MAX_VALUE);
    return costArr;
  }

  private static class Pos {
    int location, cost;

    public Pos(int location, int cost) {
      this.location = location;
      this.cost = cost;
    }
  }
}
