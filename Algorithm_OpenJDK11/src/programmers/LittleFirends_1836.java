package programmers;

import java.util.ArrayList;
import java.util.Collections;

public class LittleFirends_1836 {
    final static String IMPOSSIBLE = "IMPOSSIBLE";

    private static String solution(int m, int n, String[] board) {
        StringBuffer sb = new StringBuffer();
        char[][] chBoard = getChBoard(m,n,board);
        ArrayList<Pos_1836> blockList = getBlockList(m, n, chBoard);

        boolean isRemoved = true;
        while(isRemoved) {
            isRemoved = false;
            for(int i=0; i<blockList.size()-1; i+=2) {
                Pos_1836 origin = blockList.get(i);
                Pos_1836 target = blockList.get(i+1);
                if(canRemove(chBoard, origin,target)) {
                    // 삭제
                    blockList.remove(i);    // origin 삭제
                    blockList.remove(i);    // target 삭제
                    //board 수정
                    chBoard[origin.y][origin.x] = '.';
                    chBoard[target.y][target.x] = '.';
                    //타일 추가
                    sb.append(origin.ch);
                    // 삭제 표시
                    isRemoved = true;
                    break;
                }
            }
        }

        if(blockList.size() !=0) {
            return IMPOSSIBLE;
        }

        return sb.toString();
    }

    private static ArrayList<Pos_1836> getBlockList(int m, int n, char[][] board) {
        ArrayList<Pos_1836> result = new ArrayList<>();

        for(int y=0; y<m; y++) {
            for(int x=0; x<n; x++) {
                if(board[y][x] >=65 && board[y][x]<=90) { // 알파벳
                    if(!result.contains(board[y][x])) {
                        result.add(new Pos_1836(x,y,board[y][x]));
                    }
                }
            }
        }
        Collections.sort(result);

        return result;
    }
    private static char[][] getChBoard(int m, int n, String[] board) {
        char[][] result = new char[m][n];
        for(int i=0; i<m; i++) {
            result[i] = board[i].toCharArray();
        }
        return result;
    }

    private static boolean canRemove(char[][] board, Pos_1836 origin, Pos_1836 target) {
        boolean result = true;
        if(origin.x == target.x) {  // 세로로 일직선 상
            result = increaseY(origin.x, origin.y, target.y, board, origin.ch);
        } else if(origin.y == target.y) {   // 가로로 일직선 상
            result = increaseX(origin.y, origin.x, target.x, board, origin.ch);
        } else {    // 좌상, 우하   || 우상, 좌하에 두개가 위치함
            if(origin.x < target.x) {   // 좌상 우하
                // 아래부터 가는 경우
                boolean down = increaseY(origin.x, origin.y, target.y, board, origin.ch);
                if(down) {
                    down = increaseX(target.y,origin.x,target.x,board,origin.ch);
                    System.out.println(origin.ch);
                }

                // 오른쪽부터 가는 경우
                boolean right = increaseX(origin.y, origin.x, target.x, board, origin.ch);
                if(right) {
                    right = increaseY(target.x, origin.y, target.y, board, origin.ch);
                }
                if(!(down || right)) {
                    result = false;
                }
            } else {    // 우상 좌하
                //아래부터 가는경우
                boolean down = increaseY(target.x, origin.y,target.y,board, origin.ch);
                if(down) {// 오른쪽 확인
                    down= increaseX(origin.y,target.x,origin.x,board,origin.ch);
                }

                // 오른쪽부터 가는경우
                boolean right = increaseX(target.y,target.x,origin.x,board,origin.ch);
                if(right) { // 위에 확인
                    right = increaseY(origin.x,origin.y,target.y,board,origin.ch);
                }
                if(!(down||right)) {
                    result = false;
                }
            }
        }

        return result;
    }

    private static boolean increaseY(int standardX, int standardY, int targetY, char[][] board, char ch) {
        for(int y=standardY; y<=targetY; y++) {
            if(board[y][standardX] != '.' && board[y][standardX] != ch) {
                return false;
            }
        }
        return true;
    }

    private static boolean increaseX(int standardY, int standardX, int targetX, char[][] board, char ch) {
        for(int x=standardX; x<=targetX; x++) {
            if(board[standardY][x] != '.' && board[standardY][x] != ch) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int m =4;
        int n =4;
        String[] board = {".ZI.", "M.**", "MZU.", ".IU."};
        System.out.println(solution(m,n,board));
    }
}

class Pos_1836 implements Comparable<Pos_1836>{
    int x;
    int y;
    char ch;

    public Pos_1836(int x, int y, char ch) {
        this.x = x;
        this.y = y;
        this.ch = ch;
    }

    @Override
    public int compareTo(Pos_1836 p) {
        return this.ch >= p.ch ? 1:-1;
    }
}
/*
https://programmers.co.kr/learn/courses/30/lessons/1836#
 */