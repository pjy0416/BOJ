package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ABCDE_13023 {
  static final int MAX_COUNT =5;
  static boolean found;
  static List<Integer>[] friends;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    initFriends(n);

    while(m-- >0) {
      st = new StringTokenizer(br.readLine());
      int left = Integer.parseInt(st.nextToken()), right = Integer.parseInt(st.nextToken());
      friends[left].add(right);
      friends[right].add(left);
    }

    br.close();
    solution(n);
  }

  private static void initFriends(int n) {
    friends = new ArrayList[n];
    for(int i=0; i<n; i++) {
      friends[i] = new ArrayList<>();
    }
  }

  private static void solution(int n) {
    for(int i=0; i<n; i++) {
      boolean[] visit = new boolean[n];
      dfs(i, 1, visit);
    }

    System.out.println(found ? '1' : '0');
  }

  private static void dfs(int now, int count, boolean[] visit) {
    if(count == MAX_COUNT) {
      found = true;
      return;
    }
    if(found) {
      return;
    }
    visit[now] = true;
    for(int friend : friends[now]) {
      if(!visit[friend]) {
        dfs(friend, count+1, visit);
      }
    }
    visit[now] = false;
  }
}
