package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Work_2056 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] times = new int[n+1]; // 작업에 걸리는 시간
    int[] startingTime = new int[n+1]; // 선행 작업 이후 언제 시작하는지 저장

    for(int i=1; i<=n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      times[i] = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      while(m-- >0) {
        int parent = Integer.parseInt(st.nextToken());
        startingTime[i] = Math.max(startingTime[i], startingTime[parent] + times[parent]);
      }
    }

    br.close();
    solution(n, times, startingTime);
  }

  private static void solution(int n, int[] times, int[] startingTime) {
    int answer =0;
    for(int i=1; i<=n; i++) {
      answer = Math.max(answer, startingTime[i]+times[i]);
    }
    System.out.println(answer);
  }
}
