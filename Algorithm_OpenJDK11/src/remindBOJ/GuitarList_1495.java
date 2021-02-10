package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GuitarList_1495 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()), s = Integer.parseInt(st.nextToken()) , m = Integer.parseInt(st.nextToken());
    int[] volumeArr = new int[n];
    st = new StringTokenizer(br.readLine());

    for(int i=0; i<n; i++) {
      volumeArr[i] = Integer.parseInt(st.nextToken());
    }

    br.close();
    solution(n,s,m,volumeArr);
  }

  private static void solution(int n, int s, final int M, int[] volumeArr) {
    boolean[][] dp = new boolean[n+1][M+1];
    dp[0][s] = true;

    for(int i=1; i<=n; i++) {
      int prev = i-1;
      for(int j=0; j<=M; j++) {
        if(!dp[prev][j]) {
          continue;
        }
        int minus = j - volumeArr[prev], plus = j + volumeArr[prev];
        if(isInRange(plus, M)) {
          dp[i][plus] = true;
        }
        if(isInRange(minus, M)) {
          dp[i][minus] = true;
        }
      }
    }

    int answer =-1;
    for(int i=M; i>=0; i--) {
      if(dp[n][i]) {
        answer = i;
        break;
      }
    }
    System.out.println(answer);
  }

  private static boolean isInRange(int num, int max) {
    return num >=0 && num <=max;
  }
}
