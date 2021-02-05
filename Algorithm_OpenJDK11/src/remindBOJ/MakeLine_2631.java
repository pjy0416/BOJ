package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MakeLine_2631 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] kids = new int[n];
    for(int i=0; i<n; i++) {
      kids[i] = Integer.parseInt(br.readLine());
    }
    br.close();
    solution(n, kids);
  }

  private static void solution(int n, int[] kids) {
    int[] lis = new int[n];
    int max =0;

    for(int i=0; i<n; i++) {
      lis[i] =1;
      for(int j=0; j<i; j++) {
        if(kids[i] > kids[j]) {
          lis[i] = Math.max(lis[i], lis[j]+1);
        }
      }
      max = Math.max(max, lis[i]);
    }
    System.out.println(n-max);
  }
}
