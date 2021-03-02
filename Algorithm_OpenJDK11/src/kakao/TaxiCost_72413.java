package kakao;

import java.util.Arrays;

public class TaxiCost_72413 {
  public static void main(String[] args) {
    int[] n = {6,7,6}, s = {4,3,4}, a = {6,4,5}, b = {2,1,6};
    int[][][] fares = {
            {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}},
            {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}},
            {{2,6,6}, {6,3,7}, {4,6,7}, {6,5,11}, {2,5,12}, {5,3,20}, {2,4,8}, {4,3,9}}
    };
    int[] expectedResult = {82, 14, 18};

    for(int i=0; i<3; i++) {
      int receivedResult = solution(n[i],s[i],a[i],b[i],fares[i]);
      System.out.println("expect : " + expectedResult[i] + " received : " + receivedResult);
    }
  }

  private static int solution(int n, int s, int a, int b, int[][] fares) {
    int[][] map = initMap(n,fares);
    floydWarshal(n,map);
    return getMinCost(map,n, s, a, b);
  }

  private static int getMinCost(int[][] map, int n, int s, int a, int b) {
    int answer = Integer.MAX_VALUE;
    for(int k=1; k<=n; k++) { // 문제에서 요구하는 답 = min(d[s][k] + d[k][a] + d[k][b]) (단, k = 1 ~ n)
      answer = Math.min(answer, map[s][k]+ map[k][a] + map[k][b]);
    }
    return answer;
  }

  private static void floydWarshal(int n, int[][] map) {
    for(int k=1; k<=n; k++) {
      for(int i=1; i<=n; i++) {
        for(int j=1; j<=n; j++) {
          if(i!=j && map[i][k] != Integer.MAX_VALUE && map[k][j] != Integer.MAX_VALUE) { // 출발지와 목적지가 다르고, 길이 경유지로 연결
            map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]); // 최솟값 갱신
          }
        }
      }
    }
  }

  private static int[][] initMap(int n, int[][] fares) {
    int[][] map = new int[n+1][n+1];
    for(int i=1; i<=n; i++) {
      Arrays.fill(map[i], Integer.MAX_VALUE);
      map[i][i] = 0; // 자기 자신은 MAX가 아닌 0, 이게 초기화 핵심
    }

    for(int[] fare : fares) {
      map[fare[0]][fare[1]] = fare[2];
      map[fare[1]][fare[0]] = fare[2];
    }

    return map;
  }
}
