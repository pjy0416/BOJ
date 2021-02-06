package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class KevinBacon_1389 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
    boolean[][] friends = new boolean[n+1][n+1];

    for(int i=0; i<m; i++) {
      st = new StringTokenizer(br.readLine());
      int left = Integer.parseInt(st.nextToken()), right = Integer.parseInt(st.nextToken());
      friends[left][right] = true;
      friends[right][left] = true;
    }

    br.close();
    solution(n, friends);
  }

  private static void solution(int n, boolean[][] friends) {
    int min = Integer.MAX_VALUE, answer =0;
    for(int i=1; i<=n; i++) {
      int count = getKevinBacon(i, n, friends);
      if(min > count) {
        min = count;
        answer =i;
      }
    }
    System.out.println(answer);
  }

  private static int getKevinBacon(int start, int n, boolean[][] friends) {
    int[] kevinBacons = new int[n+1];
    final int NO_RELATION =0;
    kevinBacons[start] = 0;
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(start);

    while(!queue.isEmpty()) {
      int now = queue.poll();

      for(int i=1; i<=n; i++) {
        if(kevinBacons[i] == NO_RELATION && friends[now][i]) {
          kevinBacons[i] = kevinBacons[now]+1;
          queue.offer(i);
        }
      }
    }

    int count =0;
    for(int kevinBacon : kevinBacons) {
      count += kevinBacon;
    }
    return count - kevinBacons[start];
  }
}
