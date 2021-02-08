package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Airport_10775 {
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
    boolean[] gates = new boolean[g+1];
    int answer =0;

    for(int gate : airplanes) {
      boolean docking = false;
      for(int i=gate; i>0; i--) {
        if(!gates[i]) {
          gates[i] = true;
          answer++;
          docking = true;
          break;
        }
      }
      if(!docking) {
        break;
      }
    }
    System.out.println(answer);
  }
}
