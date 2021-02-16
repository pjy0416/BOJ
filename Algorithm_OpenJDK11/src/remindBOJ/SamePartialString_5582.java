package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SamePartialString_5582 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    char[] origin = br.readLine().toCharArray(), target = br.readLine().toCharArray();
    br.close();
    solution(origin, target);
  }

  private static void solution(char[] origin, char[] target) {
    final int originLen = origin.length, targetLen = target.length;
    int[][] dp = new int[originLen+1][targetLen+1];
    int answer=0;

    for(int i=1; i<=originLen; i++) {
      for(int j=1; j<=targetLen; j++) {
        if(origin[i-1] == target[j-1]) {
          dp[i][j] = dp[i-1][j-1]+1;
          answer = Math.max(answer,dp[i][j]);
        }
      }
    }

    System.out.println(answer);
  }
}

