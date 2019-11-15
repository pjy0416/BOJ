package kakao;

public class test1 {            // OK

    static int solution(int[][] board, int[] moves) {
        MyStack1 stack = new MyStack1();
        int answer =0;

        for(int i=0; i<moves.length; i++) {             // 명령 횟수 만큼 실행
            int spot = moves[i] -1;                     // moves에는 1부터 시작이니까 인덱스 번호화
            int idx = findIDX(board, spot);             // 크레인 위치에서 인형 인덱스 찾기

            if(idx != -1) {                             // 크레인 위치에 인형이 있다면
                if(stack.peek() == board[idx][spot]) {  // 바구니 맨 위에 인형이랑 찾은 인형이 같으면
                    stack.pop();                        // 바구니에서 없애(인형 제거)
                    answer += 2;                        // 2증가 (터진 인형 두 개)
                } else {                                // 바구니 맨 위에 인형이랑 찾은 인형이 같지 않으면
                    stack.push(board[idx][spot]);       // 위치 인형 스택에 추가
                }
                board[idx][spot] =0;                    // 인형 가져온 위치 빈칸으로
            }
                                                        // 크레인 위치에 인형 없으면 for문 진행
        }

        return answer;                                  // 터뜨린 횟수 반환
    }

    static int findIDX(int[][] num, int spot) {
        int idx = -1;

        for(int i=0; i<num.length; i++) {    // 맨 위에부터 바닥까지 체크
            if(num[i][spot] !=0) {
                return i;
            }
        }
        return idx;                             // 없으면 -1 반환
    }


    public static void main (String[] args) {
        int [][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1},};
        int[] moves = {1,5,3,5,1,2,1,4};

        solution(board,moves);
    }
}

class MyStack1 {
    private static int MAXSIZE = 1001;
    private int[] basket = new int[MAXSIZE];
    private int top = -1;

    void push(int num) {
        if(top >= MAXSIZE) {
            top = 0;
        }
        basket[++top] = num;
    }

    int pop() {
        return top <0 ? 0 : basket[top--];
    }

    int peek() {
        return top <0 ? 0 : basket[top];
    }
}


/*

* [제한사항]
board 배열은 2차원 배열로 크기는 5 x 5 이상 30 x 30 이하입니다.
board의 각 칸에는 0 이상 100 이하인 정수가 담겨있습니다.
0은 빈 칸을 나타냅니다.
1 ~ 100의 각 숫자는 각기 다른 인형의 모양을 의미하며 같은 숫자는 같은 모양의 인형을 나타냅니다.
moves 배열의 크기는 1 이상 1,000 이하입니다.
moves 배열 각 원소들의 값은 1 이상이며 board 배열의 가로 크기 이하인 자연수입니다.
* */