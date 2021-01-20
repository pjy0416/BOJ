package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BullsAndCows {
    final static int MAX = 10, ZERO_ASCII = 48, FULL_STRIKE =3;
    static boolean[][][] answerArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        initAnswerArray();

        int n = Integer.parseInt(br.readLine());
        while(n-- >0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String numString = st.nextToken();
            int strike = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());

            if(strike == FULL_STRIKE) {
                System.out.println(numString);
            }
            updateAnswerArr(numString.toCharArray(), strike, ball);
        }
        br.close();
        printAnswerCount();
    }

    private static void initAnswerArray() {
        answerArr = new boolean[MAX][MAX][MAX];
        for(int i=1; i<MAX; i++) {
            for(int j=1; j<MAX; j++) {
                Arrays.fill(answerArr[i][j], true);
            }
        }
    }

    private static void updateAnswerArr(char[] questionArr, int strike, int ball) {
        for(int first =1; first < MAX; first++) {
            for(int second =1; second<MAX; second++) {
                for(int third =1; third<MAX; third++) {
                    if(answerArr[first][second][third] && !isValid(new int[] {first, second, third}, questionArr, strike, ball)) { // true일 때만 검증
                        answerArr[first][second][third] = false;
                    }
                }
            }
        }
    }

    private static boolean isValid(int[] origin, char[] questionArr, int strike, int ball) {
        int strikeCount = 0, ballCount =0;

        for(int i=0; i<3; i++) {
            if(origin[i] == questionArr[i] - ZERO_ASCII) {
                strikeCount++;
            }
            for(int j=0; j<3; j++) {
                if(i != j && origin[i] == questionArr[j] - ZERO_ASCII) {
                    ballCount++;
                }
            }
        }
        return strike == strikeCount && ball == ballCount;
    }

    private static void printAnswerCount() {
        int count =0;
        for(int i=1; i<MAX; i++) {
            for(int j=1; j<MAX; j++) {
                for(int k=1; k<MAX; k++) {
                    if(answerArr[i][j][k] && i != j && j !=k && i != k) { // 중복 불가
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
}

/*
2
123 0 0
456 0 0
 */