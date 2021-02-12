package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class MaximumDifference_10819 {
  static int n, answer;
  static int[] numArr;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    numArr = new int[n];

    for(int i=0; i<n; i++) {
      numArr[i] = Integer.parseInt(st.nextToken());
    }

    br.close();
    solution();
  }

  private static void solution() {
    dfs(new boolean[n], new LinkedList<>());
    System.out.println(answer);
  }

  private static void dfs(boolean[] visit, LinkedList<Integer> numList) {
    if(numList.size() == n) {
      setAnswer(numList);
      return;
    }

    for(int i=0; i<n; i++) {
      if(!visit[i]) {
        visit[i] = true;
        numList.offer(numArr[i]);
        dfs(visit, numList);
        numList.pollLast();
        visit[i] = false;
      }
    }
  }

  private static void setAnswer(LinkedList<Integer> numList) {
    int sum =0;
    for(int i=0; i<n-1; i++) {
      sum += Math.abs(numList.get(i) - numList.get(i+1));
    }
    answer = Math.max(sum, answer);
  }
}

/*
3
20 1 15
 */