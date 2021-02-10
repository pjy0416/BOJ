package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HeightOrder_2458 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
    boolean[][] isTall = new boolean[n+1][n+1], isSmall = new boolean[n+1][n+1];

    while(m-- >0) {
      st = new StringTokenizer(br.readLine());
      int smaller = Integer.parseInt(st.nextToken()), taller = Integer.parseInt(st.nextToken());
      isTall[taller][smaller] = true;
      isSmall[smaller][taller] = true;
    }

    br.close();
    solution(n, isTall, isSmall);
  }

  private static void solution(int n, boolean[][] isTall, boolean[][] isSmall) {
    int answer =0;
    floydWarshall(n, isTall);
    floydWarshall(n, isSmall);

    for(int i=1; i<=n; i++) {
      int count =0;
      for(int j=1; j<=n; j++) {
        if(isTall[i][j] || isSmall[i][j]) {
          count++;
        }
      }
      if(count == n-1) {
        answer++;
      }
    }
    System.out.println(answer);
  }

  private static void floydWarshall(int n, boolean[][] arr) {
    for(int k=1; k<=n; k++) {
      for (int i=1; i<=n; i++) {
        for(int j=1; j<=n; j++) {
          if(arr[i][k] && arr[k][j]) { // i,k && k,j => i,j
            arr[i][j] = true;
          }
        }
      }
    }
  }
}
