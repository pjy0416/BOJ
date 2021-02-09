package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LIS4_14002 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] numArr = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());

    for(int i=0; i<n; i++) {
      numArr[i] = Integer.parseInt(st.nextToken());
    }

    br.close();
    solution(n,numArr);
  }

  private static void solution(int n, int[] numArr) {
    List<Integer>[] lisList = new ArrayList[n];
    int answer =0;
    for(int i=0; i<n; i++) {
      lisList[i] = new ArrayList<>();
    }

    for(int i=0; i<n; i++) {
      lisList[i].add(numArr[i]);
      for(int j=0; j<i; j++) {
        if(numArr[j] < numArr[i] && lisList[i].size() < lisList[j].size() +1) {
          lisList[i].clear();
          lisList[i].addAll(lisList[j]);
          lisList[i].add(numArr[i]);
        }
      }
      if(lisList[answer].size() < lisList[i].size()) {
        answer = i;
      }
    }

    printAnswer(lisList[answer]);
  }

  private static void printAnswer(List<Integer> list) {
    StringBuilder sb = new StringBuilder();
    final String SPACE = " ", NEW_LINE = "\n";
    sb.append(list.size()).append(NEW_LINE);
    for(int num : list) {
      sb.append(num).append(SPACE);
    }
    System.out.println(sb.toString());
  }
}

/*
7
1 6 2 4 5 3 7

6
1 3 5 7 2 3

6
1 5 6 2 3 4

6
10 20 10 15 50 60

3
2 4 1

2
3 1

2
1 3

3
3 1 2

3
3 3 1

1
1
*/
