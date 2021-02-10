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
    boolean[][] isTall = new boolean[n+1][n+1];

    while(m-- >0) {
      st = new StringTokenizer(br.readLine());
      int smaller = Integer.parseInt(st.nextToken()), taller = Integer.parseInt(st.nextToken());
      isTall[taller][smaller] = true;
    }

    br.close();
    solution(n, isTall);
  }

  private static void solution(int n, boolean[][] isTall) {
    floydWarshall(n, isTall);
    int[] count = getCountArr(n, isTall);
    int answer = getAnswer(n, count);

    System.out.println(answer);
  }

  private static int getAnswer(int n, int[] count) {
    int answer =0;
    final int MAX = n-1;
    for(int i=1; i<=n; i++) {
      if(count[i] == MAX) {
        answer++;
      }
    }
    return answer;
  }

  private static int[] getCountArr(int n, boolean[][] isTall) {
    int[] count = new int[n+1];

    for(int i=1; i<=n; i++) {
      for(int j=1; j<=n; j++) {
        if(isTall[i][j]) {
          count[i]++;
          count[j]++;
        }
      }
    }
    return count;
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
