package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Gear_14891 {
    final static int units = 4, elements =8, turn_right =1, turn_left = -1, left = 0, right =4, top = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] gears = new char[units][elements];
        for(int i=0; i<units; i++)  gears[i] = getGearStatus(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int[][] commands = new int[k][2];
        for(int i=0; i<k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            commands[i][0] = Integer.parseInt(st.nextToken())-1;
            commands[i][1] = Integer.parseInt(st.nextToken());
        }
        br.close();
        solution(gears, commands);
    }

    private static char[] getGearStatus(String readLine) {
        char[] gear = readLine.toCharArray(); // 0번이 2번으로 가야함. // 오른쪽 두바퀴
        gear = turnRight(turnRight(gear));
        return gear;
    }

    private static void solution(char[][] gears, int[][] commands) {
        for(int[] command : commands) {
            int standard = command[0];
//            if(command[1] == turn_right)    gears[standard] = turnRight(gears[standard]);
//            else                            gears[standard] = turnLeft(gears[standard]);
            gears = turnGears(standard, command[1], gears);
        }
        int score = getScore(gears);
        System.out.println(score);
    }

    private static int getScore(char[][] gears) {
        int result =0;
        final char sPole = '1';
        for(int i=0; i<units; i++) {
            if(gears[i][top] == sPole)  result += Math.pow(2,i);
        }
        return result;
    }

    // 톱니의 가장 왼쪽은 0번, 가장 오른쪽은 3번 인덱스에 있음
    private static char[][] turnGears(int standard, int direction, char[][] gears) {
        int prev = direction;
        // gear를 돌리기 전에 옆 기어들의 극을 먼저 확인해야함
        boolean[] shouldTurn = new boolean[units];
        shouldTurn[standard] = true;
        for(int i=standard; i>0; i--) {
            if(gears[i][left] != gears[i-1][right]) shouldTurn[i-1] = true;
        }
        for(int i=standard; i<units-1; i++) {
            if(gears[i][right] != gears[i+1][left]) shouldTurn[i+1] = true;
        }
        // 기준점 기어부터 돌리기
        if(direction == turn_left)  gears[standard] = turnLeft(gears[standard]);
        else                        gears[standard] = turnRight(gears[standard]);

        // 기준 톱니바퀴 왼쪽 수정
        for(int i=standard-1; i>=0; i--) {
            if(shouldTurn[i]) {   // 다르면 바퀴 돌려야함
                if(prev == turn_left) {
                    gears[i] = turnRight(gears[i]);  // 반시계 회전
                    prev = turn_right;
                } else {
                    gears[i] = turnLeft(gears[i]); // 시계 회전
                    prev = turn_left;
                }
            } else  break;      // 같으면 그대로 유지 ~> 나머지도 유지
        }
        prev = direction;
        for(int i=standard+1; i<units; i++) { // 기준 톱니바퀴 오른쪽 수정
            if(shouldTurn[i]) {
                if(prev == turn_left) {
                    gears[i] = turnRight(gears[i]);  // 반시계 회전
                    prev = turn_right;
                } else {
                    gears[i] = turnLeft(gears[i]); // 시계 회전
                    prev = turn_left;
                }
            } else break; // 같은 경우는 그대로 ~> 나머지도 유지
        }
        return gears;
    }
    private static char[] turnLeft(char[] gear) {
        char[] result = new char[elements];
        // index 하나씩 땡기기 
        for(int i=1; i<elements; i++)   result[i-1] = gear[i];
        result[elements-1] = gear[0];
        return result;
    }
    private static char[] turnRight(char[] gear) {
        char[] result = new char[elements];
        // index 하나씩 밀기
        for(int i=0; i<elements-1; i++) result[i+1] = gear[i];
        result[0] = gear[elements-1];
        return result;
    }
}
