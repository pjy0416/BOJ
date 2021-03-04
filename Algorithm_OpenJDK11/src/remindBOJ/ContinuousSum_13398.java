package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ContinuousSum_13398 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] sequence = new int[n+1];

    for(int i=1; i<=n; i++) {
      sequence[i] = Integer.parseInt(st.nextToken());
    }

    br.close();
    solution(n,sequence);
  }

  private static void solution(int n, int[] sequence) {
    final int ZERO =0;
    int[] proceedToTheRight = new int[n+2], proceedToTheLeft = new int[n+2]; // 최대 부분합 우측 진행, 좌측 진행 배열, n+2 : 좌우 공백
    proceedToTheRight[1] = sequence[1]; // 우측으로 진행되는 부분합의 처음 시작
    proceedToTheLeft[n] = sequence[n]; // 좌측으로 진행되는 부분합의 처음 시작
    int max = sequence[1];

    for(int i=2; i<=n; i++) {
      proceedToTheRight[i] = Math.max(ZERO, proceedToTheRight[i-1]) + sequence[i]; // 우측 진행 최대 부분합 저장
      proceedToTheLeft[n-i+1] = Math.max(ZERO, proceedToTheLeft[n-i+2]) + sequence[n-i+1]; // 좌측 진행 최대 부분합 저장
      max = Math.max(max, proceedToTheRight[i]); // 우측으로 진행하는 부분합의 최대를 저장 (삭제를 안한 경우)
    }

    for(int i=2; i<=n; i++) {
      max = Math.max(max, proceedToTheRight[i-1] + proceedToTheLeft[i+1]); // 하나 삭제한 경우 중 최댓값 갱신
    }

    System.out.println(max);
  }
}

/*
1
-1
5
-100 1 2 -99 3
 */
