package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Floyd_11404 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine()), m = Integer.parseInt(br.readLine());
    List<City>[] cities = initCities(n);
    while(m-- >0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int left = Integer.parseInt(st.nextToken())-1, right = Integer.parseInt(st.nextToken())-1, cost = Integer.parseInt(st.nextToken());
      cities[left].add(new City(right, cost));
    }

    br.close();
    solution(n, cities);
  }

  private static List<City>[] initCities(int n) {
    List<City>[] cities = new ArrayList[n];
    for(int i=0; i<n; i++) {
      cities[i] = new ArrayList<>();
    }
    return cities;
  }

  private static void solution(int n, List<City>[] cities) {
    int[][] costArr = initCostArr(n, cities);
    setCostArr(n, costArr);
    printCost(costArr);
  }

  private static void printCost(int[][] costArr) {
    final String NEW_LINE = "\n", SPACE = " ";
    final String ZERO = "0";
    StringBuilder sb = new StringBuilder();
    for(int[] costLine : costArr) {
      for(int cost : costLine) {
        sb.append(cost == Integer.MAX_VALUE ? ZERO : cost).append(SPACE);
      }
      sb.append(NEW_LINE);
    }
    System.out.println(sb.toString());
  }

  private static void setCostArr(int n, int[][] costArr) {
    for(int stopOver=0; stopOver<n; stopOver++) {
      for(int start=0; start<n; start++) {
        for(int end=0; end<n; end++) {
          if(start != end && costArr[start][stopOver] != Integer.MAX_VALUE && costArr[stopOver][end] != Integer.MAX_VALUE) {
            costArr[start][end] = Math.min(costArr[start][end], costArr[start][stopOver] + costArr[stopOver][end]);
          }
        }
      }
    }
  }

  private static int[][] initCostArr(int n, List<City>[] cities) {
    int[][] costArr = new int[n][n];
    for(int i=0; i<n; i++) {
      Arrays.fill(costArr[i], Integer.MAX_VALUE);
      for(City city : cities[i]) {
        costArr[i][city.vertex] = Math.min(city.cost, costArr[i][city.vertex]);
      }
    }

    return costArr;
  }

  private static class City {
    int vertex, cost;
    public City(int vertex, int cost) {
      this.vertex = vertex;
      this.cost = cost;
    }
  }
}
