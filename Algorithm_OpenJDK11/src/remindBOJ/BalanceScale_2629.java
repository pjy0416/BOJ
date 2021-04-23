package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BalanceScale_2629 {
  private static final int MAX_SINKER_WEIGHT = 500;
  private static final int MAX_SCALE_WEIGHT = MAX_SINKER_WEIGHT * 30;

  private static boolean[][] cache;
  private static int[] sinkers;
  private static int sinkerLength;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    sinkerLength = Integer.parseInt(br.readLine());
    sinkers = getIntArrayFromString(sinkerLength, br.readLine());
    int marbleLength = Integer.parseInt(br.readLine());
    int[] marbles = getIntArrayFromString(marbleLength, br.readLine());

    br.close();
    solution(marbleLength, marbles);
  }

  private static int[] getIntArrayFromString(int length, String dataString) {
    int[] result = new int[length + 1];
    StringTokenizer st = new StringTokenizer(dataString);

    for (int i=0; i<length; i++) {
      result[i] = Integer.parseInt(st.nextToken());
    }

    return result;
  }

  private static void solution(int marbleLength, int[] marbles) {
    initCache(sinkerLength);
    setMeasurableWeight(0, 0);
    String answer = getAnswer(marbleLength, marbles);
    System.out.println(answer);
  }

  private static void initCache(int sinkerLength) {
    cache = new boolean[sinkerLength + 1][sinkerLength * MAX_SINKER_WEIGHT + 1];
    for(boolean[] line : cache) {
      Arrays.fill(line, false);
    }
  }

  private static void setMeasurableWeight(int now, int weight) {
    if (now > sinkerLength) {
      return;
    }

    if (cache[now][weight]) {
      return;
    }

    cache[now][weight] = true;

    setMeasurableWeight(now + 1, weight + sinkers[now]);
    setMeasurableWeight(now + 1, weight);
    setMeasurableWeight(now + 1, Math.abs(weight - sinkers[now]));
  }

  private static String getAnswer(int marbleLength, int[] marbles) {
    String possible = "Y";
    String impossible = "N";
    String newLine = "\n";

    StringBuilder sb = new StringBuilder();
    for (int i=0; i<marbleLength; i++) {
      int marble = marbles[i];

      if (marble > MAX_SCALE_WEIGHT) {
        sb.append(impossible);
      } else if (cache[sinkerLength][marble]) {
        sb.append(possible);
      } else {
        sb.append(impossible);
      }
      sb.append(newLine);
    }

    return sb.toString();
  }
}
