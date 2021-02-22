package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Scale_10159 {
  static final int DEFAULT =0, HEAVY =1, LIGHT =-1;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine()), m = Integer.parseInt(br.readLine());
    int[][] products = new int[n+1][n+1];

    while(m-- >0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int left = Integer.parseInt(st.nextToken()), right = Integer.parseInt(st.nextToken());
      products[left][right] = HEAVY;
      products[right][left] = LIGHT;
    }


    br.close();
    solution(products, n);
  }

  private static void solution(int[][] products, int n) {
    floydWarshall(products,n);
    StringBuilder sb = new StringBuilder();
    final String NEW_LINE = "\n";

    for(int i=1; i<=n; i++) {
      int count =0;
      for(int j=1; j<=n; j++) {
        if(i!=j && products[i][j] == DEFAULT) {
          count++;
        }
      }
      sb.append(count).append(NEW_LINE);
    }
    System.out.println(sb.toString());
  }

  private static void floydWarshall(int[][] products, int n) {
    for(int k=1; k<=n; k++) {
      for(int i=1; i<=n; i++) {
        for(int j=1; j<=n; j++) {
          if(i!=j && products[i][k] != DEFAULT && products[i][k] == products[k][j]) {
            products[i][j] = products[i][k];
          }
        }
      }
    }
  }
}
