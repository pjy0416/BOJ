package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class WorldCup_6987 {
  static final int NUM_OF_NATIONS = 6, RESULT_LENGTH = 4, NO_MORE_MATCH =0, MATCHING =1, TOTAL = 15, MAX_MATCH = 5;
  static int found;
  static boolean isMatched[][];
  static int[] game1, game2;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Nation[][] nationMatchResult = new Nation[RESULT_LENGTH][NUM_OF_NATIONS];
    for(int i=0; i<RESULT_LENGTH; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int j=0; j<NUM_OF_NATIONS; j++) {
        nationMatchResult[i][j] = new Nation(Integer.parseInt(st.nextToken()), // 승
                                            Integer.parseInt(st.nextToken()),  // 무
                                            Integer.parseInt(st.nextToken())); // 패
      }
    }


    br.close();
    solution(nationMatchResult);
  }

  private static void solution(Nation[][] nationMatchResult) {
    StringBuilder sb = new StringBuilder();
    final char SPACE = ' ';

    for(Nation[] match : nationMatchResult) {
      found = NO_MORE_MATCH;
      if(isFullGames(match)) {
        isMatched = new boolean[NUM_OF_NATIONS][NUM_OF_NATIONS];
        getResult(match, 0);
      }
      sb.append(found).append(SPACE);
    }
    System.out.println(sb.toString());
  }

  private static boolean isFullGames(Nation[] match) {
    int count =0;
    initGameOrders();
    for(Nation nation : match) {
      if(nation.win > MAX_MATCH || nation.draw > MAX_MATCH || nation.lose > MAX_MATCH) {
        return false;
      }
      count += nation.win + nation.draw + nation.lose;
    }
    return count == TOTAL*2;
  }

  private static void initGameOrders() {
    int idx=0;
    game1 = new int[TOTAL+1];
    game2 = new int[TOTAL+1];

    for(int i=0; i<=MAX_MATCH; i++) {
      for(int j=i+1; j<=MAX_MATCH; j++) {
        game1[idx] = i;
        game2[idx++] = j;
      }
    }
  }

  private static void getResult(Nation[] match, int order) {
    if(found == MATCHING) {
      return;
    }
    if(order ==TOTAL) {
      found = MATCHING;
      return;
    }
    int origin = game1[order], target = game2[order];

    if(match[origin].win > NO_MORE_MATCH && match[target].lose > NO_MORE_MATCH) {
      setMatchWin(match[origin], match[target], MATCHING);
      getResult(match, order+MATCHING);
      setMatchWin(match[origin], match[target], -MATCHING);
    }
    if(match[origin].draw > NO_MORE_MATCH && match[target].draw > NO_MORE_MATCH) {
      setMatchDraw(match[origin], match[target], MATCHING);
      getResult(match, order+MATCHING);
      setMatchDraw(match[origin], match[target], -MATCHING);
    }
    if(match[origin].lose > NO_MORE_MATCH && match[target].win > NO_MORE_MATCH) {
      setMatchWin(match[target], match[origin], MATCHING);
      getResult(match, order+MATCHING);
      setMatchWin(match[target],match[origin], -MATCHING);
    }
  }

  private static void setMatchWin(Nation origin, Nation target, int count) {
    origin.win -= count;
    target.lose -= count;
  }

  private static void setMatchDraw(Nation origin, Nation target, int count) {
    origin.draw-= count;
    target.draw-= count;
  }

  private static class Nation {
    int win, draw, lose;

    public Nation(int win, int draw, int lose) {
      this.win = win;
      this.draw = draw;
      this.lose = lose;
    }
  }
}
