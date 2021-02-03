package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class StackSequence_1874 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] numArr = new int[n];
    for(int i=0; i<n; i++) {
      numArr[i] = Integer.parseInt(br.readLine());
    }

    br.close();
//    solution(numArr);
    solution(n, numArr);
  }

  private static void solution(int n, int[] numArr) {
    System.out.println(getAnswer(n, numArr));
  }

  private static String getAnswer(int n, int[] numArr) { // stack (int array)
    StringBuilder sb = new StringBuilder();
    final String NO = "NO", NEW_LINE = "\n";
    final char PLUS = '+', MINUS ='-';

    int[] stack = new int[n+1];
    int top =0, start=1;
    for(int num : numArr) {
      if(stack[top] > num) {
        return NO;
      } else {
        if(stack[top] < num) {
          while(start <=num) {
            stack[++top] = start++;
            sb.append(PLUS).append(NEW_LINE);
          }
        }
        top--;
        sb.append(MINUS).append(NEW_LINE);
      }
    }
    return sb.toString();
  }

  /*
  private static void solution(int[] numArr) { // stack (priority queue)
    System.out.println(getAnswer(numArr));
  }

  private static String getAnswer(int[] numArr) {
    StringBuilder sb = new StringBuilder();
    final char PUSH ='+', POP = '-';
    final String NO = "NO", NEW_LINE = "\n";

    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    pq.offer(0);
    int start =1;
    for(int num : numArr) {
      if(pq.peek() > num) {
        return NO;
      } else {
        if(pq.peek() < num) {
          for(int i=start; i<=num; i++) {
            pq.offer(i);
            sb.append(PUSH).append(NEW_LINE);
          }
          start = num+1;
        }
        pq.poll();
        sb.append(POP).append(NEW_LINE);
      }
    }
    return sb.toString();
  }
  */
}
