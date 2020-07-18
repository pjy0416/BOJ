package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ColoringChessboard_1018 {
    final static char[][] WHITE_BOARD = {{'W','B','W','B','W','B','W','B'},
                                        {'B','W','B','W','B','W','B','W'},
                                        {'W','B','W','B','W','B','W','B'},
                                        {'B','W','B','W','B','W','B','W'},
                                        {'W','B','W','B','W','B','W','B'},
                                        {'B','W','B','W','B','W','B','W'},
                                        {'W','B','W','B','W','B','W','B'},
                                        {'B','W','B','W','B','W','B','W'}};
    final static char[][] BALCK_BOARD = {{'B','W','B','W','B','W','B','W'},
                                        {'W','B','W','B','W','B','W','B'},
                                        {'B','W','B','W','B','W','B','W'},
                                        {'W','B','W','B','W','B','W','B'},
                                        {'B','W','B','W','B','W','B','W'},
                                        {'W','B','W','B','W','B','W','B'},
                                        {'B','W','B','W','B','W','B','W'},
                                        {'W','B','W','B','W','B','W','B'}};
    final static int MAX_LEN = 8;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] board = new char[n][m];

        for(int i=0; i<n; i++) {
            board[i] = br.readLine().toCharArray();
        }
        br.close();
        solution(n,m,board);
    }

    private static void solution(int n, int m, char[][] board) {
        int answer = Integer.MAX_VALUE;

        for(int yStart=0; yStart<=n-MAX_LEN; yStart++) {
            for(int xStart=0; xStart<=m-MAX_LEN; xStart++) {
                answer = Math.min(answer, getColoring(board,yStart,xStart));
            }
        }
        System.out.println(answer);
    }

    private static int getColoring(char[][] board, int yStart, int xStart) {
        int white =0, black =0;
        int endX = xStart+MAX_LEN;
        int endY = yStart+MAX_LEN;
        for(int y=yStart; y<endY; y++) {
            for(int x=xStart; x<endX; x++) {
                if(board[y][x] != WHITE_BOARD[y-yStart][x-xStart]) {
                    white++;
                }
                if(board[y][x] != BALCK_BOARD[y-yStart][x-xStart]) {
                    black++;
                }
            }
        }
        return Math.min(white,black);
    }
}
