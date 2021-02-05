package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PrimeNumber_2581 {
  static final String FAIL = "-1", NEW_LINE = "\n";

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int m = Integer.parseInt(br.readLine());
    int n = Integer.parseInt(br.readLine());

    br.close();
    solution(m,n);
  }

  private static void solution(int m, int n) {
    boolean[] isPrime = getPrimeArr(n);
    int minNum = Integer.MAX_VALUE, sum =0;
    StringBuilder sb = new StringBuilder();

    for(int i=m; i<=n; i++) {
      if(isPrime[i]) {
        minNum = Math.min(minNum, i);
        sum += i;
      }
    }

    if(minNum != Integer.MAX_VALUE) {
      sb.append(sum).append(NEW_LINE).append(minNum);
    } else {
      sb.append(FAIL);
    }
    System.out.println(sb.toString());
  }

  private static boolean[] getPrimeArr(int n) {
    boolean[] isPrime= new boolean[n+1];
    Arrays.fill(isPrime, true);
    isPrime[1] =false;

    for(int i=2; i<=n; i++) {
      if(isPrime[i]) {
        for(int j=i*i; j<=n; j+=i) {
          isPrime[j] = false;
        }
      }
    }
    return isPrime;
  }
}
