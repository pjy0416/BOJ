package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SumOfNumbers_2003 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());

    int[] numArr = new int[n];
    for(int i=0; i<n; i++) {
      numArr[i] = Integer.parseInt(st.nextToken());
    }
    br.close();
    solution(n,m,numArr);
  }

  private static void solution(int n, int m, int[] numArr) {
    int left =0, right =0, sum =0, answer=0;

    while(true) {
      if(sum >= m) { // 현재까지의 합이 m 이상이면 left를 옮겨준다.
        sum -= numArr[left++];
      } else if(right ==n) {
        break;
      } else { // 아니라면 right를 옮겨준다.
        sum += numArr[right++];
      }
      if(sum == m) { // m인 경우를 카운트
        answer++;
      }
    }

    System.out.println(answer);
  }
}
