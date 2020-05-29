package programmers;

public class NewRouteLength_49994 {
    private static int solution(String dirs) {
        return go(dirs);
    }

    private static int go(String dirs) {
        int result =0;
        int[][][][] map = new int[11][11][11][11];
        Pos_49994 robot = new Pos_49994(5,5);

        for(int i=0; i<dirs.length(); i++) {
            char cmd = dirs.charAt(i);
            Pos_49994 cmdPos = getCmdPos(robot, cmd);
            if(isInArea(cmdPos.x, cmdPos.y)) {  // 맵
                if(map[robot.y][robot.x][cmdPos.y][cmdPos.x] ==0) {
                    map[robot.y][robot.x][cmdPos.y][cmdPos.x] =1;
                    map[cmdPos.y][cmdPos.x][robot.y][robot.x] =1;
                    result++;
                }
                robot = cmdPos;
            }
        }
        return result;
    }

    private static Pos_49994 getCmdPos(Pos_49994 robot, char cmd) {
        Pos_49994 result;
        int x = robot.x;
        int y = robot.y;
        switch(cmd) {
            case 'U':
                result = new Pos_49994(x,y-1);
                break;
            case 'L':
                result = new Pos_49994(x-1,y);
                break;
            case 'R':
                result = new Pos_49994(x+1,y);
                break;
            case 'D':
                result = new Pos_49994(x,y+1);
                break;
            default :
                result = null;
                break;
        }
        return result;
    }

    private static boolean isInArea(int x, int y) {
        return x>=0 && x<=10 && y>=0 && y<=10 ? true : false;
    }

    public static void main(String[] args) {
        String dirs = "ULURRDLLU";
        System.out.println(solution(dirs));
    }
}

class Pos_49994 {
    int x;
    int y;

    public Pos_49994(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
/*
https://programmers.co.kr/learn/courses/30/lessons/49994
 */