package programmers;

public class FriendsBlocks_17679 {
    final static int[] dx = {1, 1, 0}, dy = {0,1,1};
    static int answer;
    private static int solution(int m, int n, String[] board) {
        answer = 0;
        char[][] chBoard = new char[m][n];
        for(int y=0; y<m; y++) {
            chBoard[y] = board[y].toCharArray();
        }
        delete(m,n,chBoard);
        return answer;
    }

    private static char[][] delete(int m, int n, char[][] board) {
        int prev = answer-1;
        while(prev != answer) {
            prev = answer;
            int[][] delete = new int[m][n];
            for(int y=0; y<m-1; y++) {
                for(int x=0; x<n-1; x++) {
                    char origin = board[y][x];
                    if(origin != ' ') {
                        if(canRemove(origin, board, x, y)) {
                            delete = setDelete(delete, x, y);
                        }
                    }
                }
            }
            board = setBoard(delete, board, m,n);
        }
        return board;
    }
    private static boolean canRemove(char origin, char[][] board, int x, int y) {
        for(int i=0; i<3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            char target = board[ny][nx];
            if(origin != target) {
                return false;
            }
        }
        return true;
    }
    private static int[][] setDelete(int[][] delete, int x, int y) {
        delete[y][x] =1;
        for(int i=0; i<3; i++) {
            delete[y+dy[i]][x+dx[i]] =1;
        }
        return delete;
    }
    private static char[][] setBoard(int[][] delete, char[][] board, int m, int n) {
        for(int y=0; y<m; y++) {
            for(int x=0; x<n; x++) {
                if(delete[y][x] ==1) {
                    answer++;
                    board[y][x] = ' ';
                    for(int ny =y; ny>=0; ny--) {
                        if(ny-1 >=0) {
                            board[ny][x] = board[ny-1][x];
                            board[ny-1][x] = ' ';
                        }
                    }
                }
            }
        }
        return board;
    }

    public static void main(String[] args) {
        int m =4;
        int n =5;
        String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
        System.out.println(solution(m,n,board));
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/17679
 */