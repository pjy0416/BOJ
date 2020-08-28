package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NQueen_9663 {
    static int n, answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        br.close();
        solution();
    }

    private static void solution() {
        makeQueen(new int[n+1], 1);
        System.out.println(answer);
    }

    private static void makeQueen(int[] visit, int row) {
        if(row == n+1) { // 되는 경우
            answer++;
            return;
        }
        for(int i=1; i<=n; i++) {
            if(visit[i] ==0 && isPossible(row, visit, i)) {      // 배치한 열이 아니면(가로)
                visit[i] =row;  // 저장한 세로 행을 넣어줌
                makeQueen(visit, row+1);
                visit[i] =0;    // 초기화
            }
        }
    }

    private static boolean isPossible(int row, int[] visit, int col) {
        if(row <1)  return true;    // 첫번째 행은 아무곳에나 배치 가능
        for(int i=1; i<=n; i++) {   // col 탐색
            int origin = visit[i];  // row
            if(origin !=0 && Math.abs(i-col) == Math.abs(row-origin))   return false;
            // 이미 퀸 배치한 곳만 검사 && 대각선 검증
        }
        return true;
    }
}
