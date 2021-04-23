package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AToB_16953 {
  private static final int NOT_FOUND = 1;
  private static int target;
  private static int answer;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    long start = Long.parseLong(st.nextToken());
    target = Integer.parseInt(st.nextToken());

    br.close();
    solution(start);
  }

  private static void solution(long start) {
    if (start == answer) {
      printAnswer();
      return;
    }

    answer = NOT_FOUND;
    findMinimumCalculate(start, 1);
    if (answer == NOT_FOUND) {
      answer *= -1;
    }

    printAnswer();
  }

  private static void findMinimumCalculate(long start, int calCount) {
    if (start > target || answer != NOT_FOUND) {
      return;
    }
    if (start == target) {
      answer = calCount;
      return;
    }

    findMinimumCalculate(getMultiplyNumber(start), calCount + 1);
    findMinimumCalculate(getAppendedOne(start), calCount + 1);
  }

  private static long getMultiplyNumber(long num) {
    return num*2;
  }

  private static long getAppendedOne(long num) {
    return num * 10 + 1;
  }

  private static void printAnswer() {
    System.out.println(answer);
  }
}
