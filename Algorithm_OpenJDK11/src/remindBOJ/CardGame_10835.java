package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CardGame_10835 {
  static int[][] dp;
  static int[] left, right;
  static int n;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    left = getCards(st);
    st = new StringTokenizer(br.readLine());
    right = getCards(st);

    br.close();
    solution();
  }

  private static void solution() {
    initDP();
    int answer =getMaxScore(0,0);
    System.out.println(answer);
  }

  private static void initDP() {
    dp = new int[n+1][n+1];
    for(int i=0; i<=n; i++) {
      Arrays.fill(dp[i], Integer.MIN_VALUE);
    }
  }

  private static int getMaxScore(int leftIndex, int rightIndex) {
    if(leftIndex >= n || rightIndex >= n) { // end of game
      return 0;
    }
    if(dp[leftIndex][rightIndex] != Integer.MIN_VALUE) { // memoization
      return dp[leftIndex][rightIndex];
    }
    dp[leftIndex][rightIndex] =0; // init

    if(left[leftIndex] > right[rightIndex]) {
      dp[leftIndex][rightIndex] += right[rightIndex] + getMaxScore(leftIndex, rightIndex+1); // drop only right card
    } else {
      dp[leftIndex][rightIndex] += Math.max(getMaxScore(leftIndex+1, rightIndex), getMaxScore(leftIndex+1,rightIndex+1));
      // max(drop only left, drop both left and right)
    }
    return dp[leftIndex][rightIndex];
  }

  private static int[] getCards(StringTokenizer st) {
    int[] cards = new int[n];
    for(int i=0; i<n; i++) {
      cards[i] = Integer.parseInt(st.nextToken());
    }
    return cards;
  }
}

/*
언제든지 왼쪽 카드만 통에 버릴 수도 있고 왼쪽 카드와 오른쪽 카드를 둘 다 통에 버릴 수도 있다. 이때 얻는 점수는 없다.
오른쪽 카드에 적힌 수가 왼쪽 카드에 적힌 수보다 작은 경우에는 오른쪽 카드만 통에 버릴 수도 있다.
오른쪽 카드만 버리는 경우에는 오른쪽 카드에 적힌 수만큼 점수를 얻는다.
(1)과 (2)의 규칙에 따라 게임을 진행하다가 어느 쪽 더미든 남은 카드가 없다면 게임이 끝나며 그때까지 얻은 점수의 합이 최종 점수가 된다.

정렬해서 값 구하기 ~> 투 포인터 ~> DP
 */
