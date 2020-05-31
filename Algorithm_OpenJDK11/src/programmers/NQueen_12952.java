package programmers;

public class NQueen_12952 {
    static int answer;
    private static int solution(int n) {
        answer = 0;
        makeQueen(new int[n+1], 1);
        return answer;
    }

    private static void makeQueen(int visit[], int row) {
        if(row ==visit.length) { // 되는 경우
            answer++;
            return;
        }

        for(int i=1; i<visit.length; i++) {
            if(visit[i] ==0) {  // 배치한 열이 아니면(가로)
                if(isPossible(row, visit, i)) {
                    visit[i] =row;  // 저장한 세로 행을 넣어줌
                    makeQueen(visit, row+1);
                    visit[i] =0;    // 초기화
                }
            }
        }
    }
    private static boolean isPossible(int row, int[] visit, int col) {
        boolean result = true;
        if(row > 1) {   // 첫번째 행은 다 배치 가능
            for(int i=1; i<visit.length; i++) {  // col 탐색
                int origin = visit[i];  // row
                if(origin !=0) { // 이미 퀸 배치한 곳만 검사
                    if(Math.abs(i-col) == Math.abs(row-origin)) {   // 대각선 검증
                        result = false;
                        break;
                    }
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(solution(4));
    }
}
/*
https://programmers.co.kr/learn/courses/30/lessons/12952#
 */