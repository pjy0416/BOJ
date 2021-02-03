package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Combination_2407 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
    br.close();
    solution(n,m);
  }

  private static void solution(int n, int m) {
    BigInteger[] combination = initCombination(n);
    int mid = n/2;
    int standard = n % 2 ==0 ? mid : mid+1;
    for(int i=1; i<=mid; i++) {
      combination[i] = combination[i-1].multiply(BigInteger.valueOf((n-i+1))).divide(BigInteger.valueOf(i));
    }
    for(int i=1; i<=mid; i++) {
      combination[mid+i] = combination[standard-i];
    }

    System.out.println(combination[m]);
  }

  private static BigInteger[] initCombination(int n) {
    BigInteger[] combination = new BigInteger [n+1];
    combination[0] = new BigInteger("1");

    return combination;
  }
}
