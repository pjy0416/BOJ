package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Plagiarism_2428 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] files = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());

    for(int i=0; i<n; i++) {
      files[i] = Integer.parseInt(st.nextToken());
    }

    br.close();
    solution(n,files);
  }

  private static void solution(int n, int[] files) {
    Arrays.sort(files); // 오름차
    Queue<Integer> queue = new LinkedList<>();
    final int MIN_PAIR =2;
    long answer =0;

    for(int file : files) {
      while(!queue.isEmpty()) {
        if(isPair(queue.peek(), file)) {
          break;
        }
        queue.poll();
      }
      queue.offer(file);
      if(queue.size() >= MIN_PAIR) {
        answer += queue.size()-1;
      }
    }

    System.out.println(answer);
  }

  private static boolean isPair(int smallFile, int bigFile) {
    return bigFile*9 <= smallFile *10;
  }
}

/*
 작은 파일의 크기가 큰 파일 크기의 90%보다도 작을 때는, 이러한 쌍은 검사하지 않고 넘어가기로 했다.
 따라서, (Fi, Fj) 쌍을 검사해야 하는데, 이때, i≠j이고,
 size(Fi) ≤ size(Fj)이면서, size(Fi) ≥ 0.9 × size(Fj)인 쌍만 검사하려고 한다.

 10
 1 5 3 4 9 10 13 2 7 6

 10
 1 2 3 4 5 6 7 9 10 13
 */