package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 전체 탐색의 경우 최악일 때, O(N^2) ~> 시간 효율이 안좋음
 * find - union 을 사용하면, 탐색 시간을 효율적으로 줄일 수 있음
 * G번 게이트부터 채우면서, 만약 이미 채워져있으면 G-1번 게이트로 옮김
 * find 결과 0이 나오면, 이미 1 ~ 입력받은 G번 게이트까지 꽉 차있어서 더 이상 도킹 불가
 */
public class Airport_10775 {
  static int[] gates;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int g = Integer.parseInt(br.readLine()), p = Integer.parseInt(br.readLine());
    int[] airplanes = new int[p];
    for(int i=0; i<p; i++) {
      airplanes[i] = Integer.parseInt(br.readLine());
    }

    br.close();
    solution(g, airplanes);
  }

  private static void solution(int g, int[] airplanes) {
    final int ZERO = 0;
    initGates(g);
    int answer =ZERO;

    for(int gate : airplanes) {
      gate = find(gate);
      if(gate == ZERO) {
        break;
      }
      union(gate, gate-1);
      answer++;
    }
    for(int i=1; i<=g; i++) {
      System.out.print(gates[i] + " ");
    }
    System.out.println();
    System.out.println(answer);
  }

  private static void initGates(int g) {
    gates = new int[g+1];
    for(int i=1; i<=g; i++) {
      gates[i] = i;
    }
  }

  private static int find(int x) {
    if(gates[x] == x) {
      return gates[x];
    }
    return gates[x] = find(gates[x]);
  }

  private static void union(int val1, int val2) {
    val1 = find(val1);
    val2 = find(val2);
    if(val1 != val2) {
      gates[val1] = val2;
    }
  }
}
