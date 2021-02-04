package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class MultiTapScheduling_1700 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
    int[] products = new int[k], remains = new int[k+1];
    remains[0] = Integer.MAX_VALUE;

    st = new StringTokenizer(br.readLine());
    for(int i=0; i<k; i++) {
      int type =Integer.parseInt(st.nextToken());
      products[i] = type;
      remains[type]++;
    }

    br.close();
    solution(n, k, products, remains);
  }

  private static void solution(int n, int k, int[] products, int[] remains) {
    List<Integer> multiTap = new ArrayList<>();
    int answer =0;

    for(int i=0; i<k; i++) {
      int product = products[i];
      if(multiTap.contains(product)) {
        continue;
      }
      if(multiTap.size() ==n) {
        int[] lastIdxArr = new int[n];
        Arrays.fill(lastIdxArr, Integer.MAX_VALUE);
        for(int j=0; j<n; j++) {
          int target = multiTap.get(j);
          for(int l=i+1; l<k; l++) {
            if(target == products[l]) {
              lastIdxArr[j] = l;
              break;
            }
          }
        }
        int maxProductIdx =0, maxLastIdx = lastIdxArr[maxProductIdx];
        for(int j=1; j<n; j++) {
          if(maxLastIdx < lastIdxArr[j]) {
            maxLastIdx = lastIdxArr[j];
            maxProductIdx = j;
          }
        }
        multiTap.remove(maxProductIdx);
        answer++;
      }
      multiTap.add(product);
    }
    System.out.println(answer);
  }
}
/*
3 7
2 3 2 3 1 2 7

2 15
3 2 1 2 1 2 1 2 1 3 3 3 3 3 3
 */