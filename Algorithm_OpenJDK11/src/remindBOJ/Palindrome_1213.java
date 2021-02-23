package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Palindrome_1213 {
  static final int ASCII_START = 65, MAX_LEN = 26;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    char[] chArr = br.readLine().toCharArray();

    br.close();
    solution(chArr);
  }

  private static void solution(char[] chArr) {
    int[] chCounts = new int[MAX_LEN];
    for(char ch : chArr) {
      chCounts[ch-ASCII_START]++;
    }
    StringBuilder sb = new StringBuilder();

    if(isPalindromeCondition(chCounts)) {
      sb.append(getPalindrome(chCounts, chArr.length));
    } else {
      sb.append("I'm Sorry Hansoo");
    }
    System.out.println(sb.toString());
  }

  private static char[] getPalindrome(int[] chCounts, int len) {
    final int DIV = 2, ZERO =0;
    char[] palindromeArr = new char[len];
    int left =0, right =len-1;
    int oddIdx = Integer.MIN_VALUE;

    for(int i=0; i<MAX_LEN; i++) {
      if(chCounts[i] ==ZERO) {
        continue;
      } else if(chCounts[i] % DIV !=ZERO) {
        oddIdx = i;
        chCounts[i]--;
      }
      int flag = chCounts[i] / DIV;
      while(flag-- >0) {
        char ch = (char) (i+ASCII_START);
        palindromeArr[left++] = ch;
        palindromeArr[right--] = ch;
      }
    }

    if(oddIdx != Integer.MIN_VALUE) {
      palindromeArr[left] = (char) (oddIdx+ASCII_START);
    }
    return palindromeArr;
  }

  private static boolean isPalindromeCondition(int[] chCounts) {
    final int STANDARD =2;
    int oddCount =0;

    for(int count : chCounts) {
      if(count % STANDARD !=0) {
        oddCount++;
      }
    }
    return oddCount < STANDARD;
  }
}
