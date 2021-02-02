package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reverse_1439 {
  static final char ZERO = '0', ONE = '1', DEFAULT = 'D';

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    char[] numCharArr = br.readLine().toCharArray();

    br.close();
    solution(numCharArr);
  }

  private static void solution(char[] numCharArr) {
    int prev = numCharArr[0];
    int[] countArr = new int[2];
    countArr[prev-ZERO]++;

    for(char ch : numCharArr) {
      if(prev != ch) {
        countArr[ch-ZERO]++;
      }
      prev = ch;
    }
    System.out.println(Math.min(countArr[0], countArr[1]));
  }
}
