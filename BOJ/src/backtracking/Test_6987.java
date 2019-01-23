package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_6987 {        // wrongCode
    final static int testCase = 4;
    final static int team = 6;

    private static void printResult(Nation[][] nations) {
        for(int i=0; i<testCase; i++) {
            if(canMatch(nations[i])) {
                System.out.print("1 ");
            } else {
                System.out.print("0 ");
            }
        }
    }

    private static boolean canMatch(Nation[] nation) {
        int win =0;
        int lose =0;
        int draw =0;
        int drawSum =0;
        int drawNation =0;

        for(int i=0; i<team; i++) {
            win += nation[i].getWin();
            lose += nation[i].getLose();
            drawSum += nation[i].getDraw();

            if(nation[i].getDraw() !=0) {
                drawNation++;
                if(draw >0) {
                    draw -= nation[i].getDraw();
                } else {
                    draw += nation[i].getDraw();
                }
            }
        }

        if(win != lose) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        Nation[][] nations = new Nation[testCase][6];



        for(int i=0; i<testCase; i++) {     // 입력
            String inputStr = br.readLine();
            for(int idx =0; idx < team; idx++) {
                int win = idx*3 *2;
                int draw = (idx*3 +1)*2;
                int lose = (idx*3 +2)*2;
                nations[i][idx] = new Nation(inputStr.charAt(win)-'0', inputStr.charAt(draw)-'0', inputStr.charAt(lose)-'0');
            }
        }

        printResult(nations);


        br.close();
    }
}

class Nation {
    private int win, draw, lose;

    Nation(int w, int d, int l) {
        win = w;
        draw = d;
        lose = l;
    }

    public int getWin() {
        return win;
    }
    public int getDraw() {
        return draw;
    }
    public int getLose() {
        return lose;
    }
    public void setWin(int w) {
        win = w;
    }
    public void setDraw(int d) {
        draw = d;
    }
    public void setLose(int l) {
        lose = l;
    }
}
