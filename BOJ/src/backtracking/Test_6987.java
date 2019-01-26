package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_6987 {        // wrongCode
    final static int testCase = 4;
    final static int team = 6;

    private static void printResult(int[][] nations) {
        for(int i=0; i<testCase; i++) {
            if(canMatch(nations[i])) {
                System.out.print("1 ");
            } else {
                System.out.print("0 ");
            }
        }
    }

    private static boolean canMatch(int[] nation) {
        int win =0;
        int lose =0;
        int draw =0;
        int drawSum =0;
        int drawNation =0;
        int winMax =0;
        int loseMax =0;
        int prevWin =0;
        int prevLose =0;

        for(int i=0; i<team; i++) {
            int tempWin = nation[i*3];
            int tempDraw = nation[i*3 +1];
            int tempLose = nation[i*3 +2];

            win += tempWin;
            lose += tempLose;
            drawSum += tempDraw;

            if(tempDraw !=0) {
                drawNation++;
                if(draw >0) {
                    draw -= tempDraw;
                } else {
                    draw += tempDraw;
                }
            }

            if(tempWin+tempDraw+tempLose != 5) {
                return false;
            }
            if(tempWin >5 || tempDraw>5 || tempLose >5) {
                return false;
            }
            if(tempWin ==5) {
                if(winMax !=0) {
                    return false;
                }
                winMax++;
            }
            if(tempLose ==5) {
                if(loseMax !=0) {
                    return false;
                }
                loseMax++;
            }
            prevWin = tempWin;
            prevLose = tempLose;
        }

        if(win != lose) {
            return false;
        }
        if(drawSum >0 && drawNation <=1) {
            return false;
        }
        if(draw != 0) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        int[][] nations = new int[testCase][team*3];

        for(int i=0; i<testCase; i++) {     // 입력
            String inputStr = br.readLine();
            for(int idx =0; idx < team; idx++) {
                int win = idx*3 *2;
                int draw = (idx*3 +1)*2;
                int lose = (idx*3 +2)*2;

                nations[i][idx*3] = inputStr.charAt(win)-'0';
                nations[i][idx*3+1] = inputStr.charAt(draw) -'0';
                nations[i][idx*3+2] = inputStr.charAt(lose) - '0';
            }
        }

        printResult(nations);

        br.close();
    }
}
