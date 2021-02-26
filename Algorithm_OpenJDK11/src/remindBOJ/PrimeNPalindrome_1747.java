package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PrimeNPalindrome_1747 {
  static final int MAX = 1003001;
  static final int DIV = 2;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    br.close();

    solution(n);
  }

  private static void solution(int n) {
    boolean[] prime = initPrimes();
    int answer =0;

    for(int i=n; i<=MAX; i++) {
      if(prime[i] && isPalindrome(i)) {
        answer =i;
        break;
      }
    }
    System.out.println(answer);
  }

  private static boolean[] initPrimes() {
    boolean[] prime = new boolean[MAX+1];
    Arrays.fill(prime, true);
    final int START_MAX = (int) Math.sqrt(MAX);

    for(int i=2; i<=START_MAX; i++) {
      if(prime[i]) {
        for(int j=i*i; j<=MAX; j+=i) {
          prime[j] = false;
        }
      }
    }
    prime[1] = false;
    return prime;
  }

  private static boolean isPalindrome(int number) {
    char[] chArr = String.valueOf(number).toCharArray();
    final int LEN = chArr.length;
    int left = LEN / DIV, right = LEN / DIV;
    if(LEN % DIV ==0) {
      left--;
    }

    while(left >=0) {
      if(chArr[left--] != chArr[right++]) {
        return false;
      }
    }

    return true;
  }
}
