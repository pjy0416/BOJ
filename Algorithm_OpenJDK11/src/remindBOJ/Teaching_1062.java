package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Teaching_1062 {
  static final int ASCII_DEFAULT = 97, ALPHABET_LENGTH = 26;

  static int answer;
  static int[] words;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken()) -5; // prefix, suffix 기본 5개 a c i n t
    words = new int[n];

    for(int i=0; i<n; i++) {
      String inputWord = br.readLine();
      int len = inputWord.length();
      for(int j = 0; j<len; j++) {
        words[i] |= (1 << (inputWord.charAt(j) - ASCII_DEFAULT));
      }
    }

    br.close();
    solution(m);
  }

  private static void solution(int m) {
    if(m>=0) {
      final int[] DEFAULT_ALPHABETS = {0,2,8,13,19}; // a, c, i, n, t
      boolean[] check = initCheck(DEFAULT_ALPHABETS);
      int bitMask = 0;
      for(int idx : DEFAULT_ALPHABETS) {
        bitMask |= (1 << idx);
      }
      permutation(1, m, bitMask, check);
    }
    System.out.println(answer);
  }

  private static boolean[] initCheck(int[] DEFAULT_ALPHABET) {
    boolean[] check = new boolean[ALPHABET_LENGTH];
    for(int idx : DEFAULT_ALPHABET) {
      check[idx] = true;
    }
    return check;
  }

  private static void permutation(int index, int count, int bitMask, boolean[] check) {
    if(count == 0) {
      // 검증
      findMax(bitMask);
      return;
    }
    if(index == ALPHABET_LENGTH) { // out of index
      return;
    }
    if(!check[index]) { // 새로 추가하는 경우
      check[index] = true;
      permutation(index+1, count-1, bitMask | (1 << index), check);
      check[index] = false;
    }
    permutation(index+1, count, bitMask, check); // 추가 없이 다음으로
  }

  private static void findMax(int bitMask) {
    int count =0;
    for(int target : words) {
      if(target == (target & bitMask)) {
        count++;
      }
    }

    answer = Math.max(count, answer);
  }
}
