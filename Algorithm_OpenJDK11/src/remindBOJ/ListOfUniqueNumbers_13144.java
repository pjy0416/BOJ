package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ListOfUniqueNumbers_13144 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] numArr = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());

    for(int i=0; i<n; i++) {
      numArr[i] = Integer.parseInt(st.nextToken());
    }

    br.close();
    solution(numArr);
  }

  private static void solution(int[] numArr) {
    final int MAX_NUM = 100000;
    boolean[] isContain = new boolean[MAX_NUM+1];
    Queue<Integer> queue = new LinkedList<>();
    long answer =0;

    for(int num : numArr) {
      if(isContain[num]) {
        while(!queue.isEmpty()) {
          int prev = queue.poll();
          if(prev == num) {
            break;
          }
          isContain[prev] = false;
        }
      }
      queue.offer(num);
      isContain[num] = true;
      answer += queue.size();
    }

    System.out.println(answer);
  }
}
