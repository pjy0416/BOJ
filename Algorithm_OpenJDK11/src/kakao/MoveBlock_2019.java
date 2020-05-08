package kakao;

import java.util.LinkedList;
import java.util.Queue;

public class MoveBlock_2019 {
    private static int solution(int[][] board) {
        Queue<Robot> queue = new LinkedList<>();
        queue.offer(new Robot());

        return goEndPoint(board, queue);
    }

    private static int goEndPoint(int[][] board, Queue<Robot> queue) {
        int N = board.length;
        int result =-1;

        int[][][] visit = itItVisit(N);

        while(!queue.isEmpty()) {
            Robot robot = queue.poll();

            if(robot.isArrive(N)) { // 도착 검사
                result = robot.getTime();
                break;
            }
//            System.out.println(robot.left.x + "," +robot.left.y + "\t " + robot.right.x + "," +robot.right.y +"\t " + robot.direction);
            move(robot, visit, queue, board, N);
            turn(robot, visit, queue, board, N);
            robot.reverse();
            turn(robot, visit, queue, board, N);
        }

        return result;
    }
    private static void move(Robot robot, int[][][] visit, Queue<Robot> queue, int[][] board, int N) {
        for(int i=0; i<4; i++) {    // 상하좌우 이동
            Robot tmp = new Robot(clonePos(robot.getPos()), robot.direction, robot.getTime());
            if(moveCondition(tmp, board, N, i)) {
                tmp.move(i);    // 무브가 가능한 경우
                if(!isVisited(tmp, visit)) {
                    visit = checkVisit(visit, tmp); // 비짓 체크
                    tmp.increaseTime();
                    queue.offer(tmp);
                }
            }
        }
    }

    private static void turn(Robot robot, int[][][] visit, Queue<Robot> queue, int[][] board, int N) {
//        System.out.print("로봇 ");
//        System.out.println(robot.left.x + "," +robot.left.y + "\t " + robot.right.x + "," +robot.right.y +"\t " + robot.direction);
        for(int i=0; i<2; i++) {    // left, right 턴
            Robot tmp = new Robot(clonePos(robot.getPos()), robot.direction, robot.getTime());

//            System.out.println(tmp.left.x + "," +tmp.left.y + "\t " + tmp.right.x + "," +tmp.right.y +"\t " + tmp.direction);
            if(turnCondition(tmp, board, N, i)) {
                if(i ==0) {
                    tmp.turnLeft();
//                    System.out.println("좌턴");
                } else {
                    tmp.turnRight();
//                    System.out.println("우턴");
                }
//                System.out.print("바꾼거 ");
//                System.out.println(tmp.left.x + "," +tmp.left.y + "\t " + tmp.right.x + "," +tmp.right.y +"\t " + tmp.direction);
                if(!isVisited(tmp, visit)) {
                    tmp.increaseTime();
                    queue.offer(tmp);
                    visit = checkVisit(visit, tmp);
                }
            }
        }
    }
    private static Pos[] clonePos(Pos[] pos) {
        Pos[] result = new Pos[2];
        result[0] = new Pos(pos[0].x, pos[0].y);
        result[1] = new Pos(pos[1].x, pos[1].y);
        return result;
    }

    private static boolean turnCondition(Robot robot, int[][] board, int N, int cmd) {
        boolean result = false;
        if(cmd ==0) { // left
            if(canRotateLeft(robot,board,N)) {
                result = true;
            }
        } else {    // right
            if(canRotateRight(robot,board,N)) {
                result = true;
            }
        }
        return result;
    }

    private static int[][][] checkVisit(int[][][] visit, Robot robot) {
        Pos[] pos = robot.getPos();
        visit[pos[0].y][pos[0].x][robot.direction] = 1;
        visit[pos[1].y][pos[1].x][robot.direction] = 1;
        return visit;
    }

    private static boolean isVisited(Robot robot, int[][][] visit) {
        boolean result = false;
        Pos[] pos = robot.getPos();
        if(visit[pos[0].y][pos[0].x][robot.direction] == 1 && visit[pos[1].y][pos[1].x][robot.direction] ==1) {
            result = true;
        }
        return result;
    }

    private static boolean moveCondition(Robot robot, int[][] board, int N, int cmd) {
        Pos[] pos = robot.getPos();
        boolean result =false;
        switch(cmd) {
            case 0 :    // up
                if(upCondition(pos, board, N)) {
                    result=true;
                }
                break;
            case 1 :    // right
                if(rightCondition(pos, board, N)) {
                    result=true;
                }
                break;
            case 2 :    // down
                if(downCondition(pos, board, N)) {
                    result=true;
                }
                break;
            case 3 :    // left
                if(leftCondition(pos,board,N)) {
                    result=true;
                }
                break;
            default:
                break;

        }
        return result;
    }

    private static int[][][] itItVisit(int N) {
        int[][][] result = new int[N][N][4];
        result[0][0][0] =1;
        return result;
    }

    private static boolean canRotateLeft(Robot robot, int[][] board, int N) {
        Pos[] pos = robot.getPos();
        boolean result =false;
        switch (robot.direction) {
            case 0 :
                if(upCondition(pos, board, N)) {
                    result = true;
                }
                break;
            case 1:
                if(rightCondition(pos, board, N)) {
                    result = true;
                }
                break;
            case 2:
                if(downCondition(pos, board, N)) {
                    result = true;
                }
                break;
            case 3:
                if(leftCondition(pos, board, N)) {
                    result = true;
                }
                break;
            default:
                break;
        }
        return result;
    }

    private static boolean canRotateRight(Robot robot, int[][] board, int N) {
        Pos[] pos = robot.getPos();
        boolean result =false;

        switch (robot.direction) {  // 북
            case 0 :
                if(downCondition(pos, board, N)) {
                    result = true;
                }
                break;
            case 1: // 동
                if(leftCondition(pos, board, N)) {
                    result = true;
                }
                break;
            case 2: // 남
                if(upCondition(pos, board, N)) {
                    result = true;
                }
                break;
            case 3: // 서
                if(rightCondition(pos, board, N)) {
                    result = true;
                }
                break;
            default:
                break;
        }
        return result;
    }

    private static boolean upCondition(Pos[] pos, int[][] board, int N) {
        boolean result = false;
        if(isInArea(N, pos[0].x, pos[0].y-1) && isInArea(N, pos[1].x, pos[1].y-1)) {
            if(isBlank(pos[0].x, pos[0].y-1, board) && isBlank(pos[1].x, pos[1].y-1,board)) {
                result = true;
            }
        }
        return result;
    }
    private static boolean downCondition(Pos[] pos, int[][] board, int N) {
        boolean result = false;
        if(isInArea(N, pos[0].x, pos[0].y+1) && isInArea(N, pos[1].x, pos[1].y+1)) {
            if(isBlank(pos[0].x, pos[0].y+1, board) && isBlank(pos[1].x, pos[1].y+1,board)) {
                result = true;
            }
        }
        return result;
    }
    private static boolean rightCondition(Pos[] pos, int[][] board, int N) {
        boolean result = false;
        if(isInArea(N,pos[0].x+1, pos[0].y) && isInArea(N, pos[1].x+1, pos[1].y)) {
            if(isBlank(pos[0].x+1,pos[0].y, board) && isBlank(pos[1].x+1,pos[1].y,board)) {
                result = true;
            }
        }
        return result;
    }
    private static boolean leftCondition(Pos[] pos, int[][] board, int N) {
        boolean result =false;
        if(isInArea(N, pos[0].x-1, pos[0].y) && isInArea(N, pos[1].x-1, pos[1].y)) {
            if(isBlank(pos[0].x-1, pos[0].y, board) && isBlank(pos[1].x-1, pos[1].y,board)) {
                result = true;
            }
        }
        return result;
    }

    private static boolean isInArea(int N, int x, int y) {
        return x>=0 && x<N && y>=0 && y<N ? true : false;
    }

    private static boolean isBlank(int x, int y, int[][] board) {
        return board[y][x] ==0 ? true : false;
    }

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 1, 1},{0, 0, 0, 1, 0},{0, 1, 0, 1, 1},{1, 1, 0, 0, 1},{0, 0, 0, 0, 0}};
        System.out.println(solution(board));
    }
}

class Robot {
    int direction;  // 0 : 북, 1 : 동, 2 : 남, 3 : 서
    Pos left;     // x,y
    Pos right;    // x,y
    private int time;    // 걸린 시간

    public Robot() {
        this.left = new Pos(0,0);
        this.right = new Pos(1,0);
        this.direction =0;
        this.time =0;
    }
    public Robot(Pos[] pos, int direction, int time) {
        this.left = pos[0];
        this.right = pos[1];
        this.direction =direction;
        this.time =time;
    }

    public void turnLeft() {
        switch (this.direction) {
            case 0:
                this.right.x = this.left.x;
                this.right.y = this.left.y-1;
                break;
            case 1:
                this.right.x = this.left.x+1;
                this.right.y = this.left.y;
                break;
            case 2:
                this.right.x = this.left.x;
                this.right.y = this.left.y+1;
                break;
            case 3:
                this.right.x = this.left.x-1;
                this.right.y = this.left.y;
                break;
            default:
                break;
        }
        this.direction = this.direction-1 <0 ? 3 : this.direction-1;
    }

    public void turnRight() {
        switch (this.direction) {
            case 0:
                this.right.x = this.left.x;
                this.right.y = this.left.y+1;
                break;
            case 1:
                this.right.x = this.left.x-1;
                this.right.y = this.left.y;
                break;
            case 2:
                this.right.x = this.left.x;
                this.right.y = this.left.y-1;
                break;
            case 3:
                this.right.x = this.left.x+1;
                this.right.y = this.left.y;
                break;
            default:
                break;
        }

        this.direction = (this.direction +1)%4;
    }

    public void reverse() {
        Pos tmp = this.left;
        this.left = right;
        this.right = tmp;
        direction = (direction+2)%4;
    }

    public Pos[] getPos() {
        Pos[] result = {this.left, this.right};
        return result;
    }

    public void move(int cmd) {
        switch (cmd) {
            case 0: // up
                this.left.y--;
                this.right.y--;
                break;
            case 1: // right
                this.left.x++;
                this.right.x++;
                break;
            case 2: // down
                this.left.y++;
                this.right.y++;
                break;
            case 3: // left
                this.left.x--;
                this.right.x--;
                break;
            default:
                break;
        }
    }

    public boolean isArrive(int N) {
        boolean result = false;
        switch (direction) {
            case 0: // 북
                result = this.left.x == N-2 && this.left.y ==N-1 && this.right.x == N-1 && this.right.y ==N-1 ? true : false;
                break;
            case 1: // 동
                result = this.left.x == N-1 && this.left.y ==N-2 && this.right.x == N-1 && this.right.y ==N-1 ? true : false;
                break;
            case 2: // 남
                result = this.right.x == N-2 && this.right.y ==N-1 && this.left.x == N-1 && this.left.y ==N-1 ? true : false;
                break;
            case 3: // 서
                result = this.left.x == N-1 && this.left.y ==N-1 && this.right.x == N-1 && this.right.y ==N-2 ? true : false;
                break;
            default:
                break;
        }
        return result;
    }

    public void increaseTime() {
        this.time++;
    }

    public int getTime() {
        return this.time;
    }
}

class Pos {
    int x;
    int y;

    public Pos(int x, int y) {
        this.x=x;
        this.y=y;
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/60063
블록 이동하기

문제 설명
로봇개발자 무지는 한 달 앞으로 다가온 카카오배 로봇경진대회에 출품할 로봇을 준비하고 있습니다.
준비 중인 로봇은 2 x 1 크기의 로봇으로
무지는 0과 1로 이루어진 N x N 크기의 지도에서 2 x 1 크기인 로봇을 움직여
(N, N) 위치까지 이동 할 수 있도록 프로그래밍을 하려고 합니다.
로봇이 이동하는 지도는 가장 왼쪽, 상단의 좌표를 (1, 1)로 하며 지도 내에 표시된 숫자 0은 빈칸을 1은 벽을 나타냅니다.
로봇은 벽이 있는 칸 또는 지도 밖으로는 이동할 수 없습니다.
로봇은 처음에 아래 그림과 같이 좌표 (1, 1) 위치에서 가로방향으로 놓여있는 상태로 시작하며, 앞뒤 구분없이 움직일 수 있습니다.

로봇이 움직일 때는 현재 놓여있는 상태를 유지하면서 이동합니다.
예를 들어, 위 그림에서 오른쪽으로 한 칸 이동한다면 (1, 2), (1, 3) 두 칸을 차지하게 되며,
아래로 이동한다면 (2, 1), (2, 2) 두 칸을 차지하게 됩니다.
로봇이 차지하는 두 칸 중 어느 한 칸이라도 (N, N) 위치에 도착하면 됩니다.

로봇은 다음과 같이 조건에 따라 회전이 가능합니다.

위 그림과 같이 로봇은 90도씩 회전할 수 있습니다.
단, 로봇이 차지하는 두 칸 중, 어느 칸이든 축이 될 수 있지만,
회전하는 방향(축이 되는 칸으로부터 대각선 방향에 있는 칸)에는 벽이 없어야 합니다.
로봇이 한 칸 이동하거나 90도 회전하는 데는 걸리는 시간은 정확히 1초 입니다.

0과 1로 이루어진 지도인 board가 주어질 때,
로봇이 (N, N) 위치까지 이동하는데 필요한 최소 시간을 return 하도록 solution 함수를 완성해주세요.

제한사항
board의 한 변의 길이는 5 이상 100 이하입니다.
board의 원소는 0 또는 1입니다.
로봇이 처음에 놓여 있는 칸 (1, 1), (1, 2)는 항상 0으로 주어집니다.
로봇이 항상 목적지에 도착할 수 있는 경우만 입력으로 주어집니다.
입출력 예
                                    board	                                            result
[[0, 0, 0, 1, 1],[0, 0, 0, 1, 0],[0, 1, 0, 1, 1],[1, 1, 0, 0, 1],[0, 0, 0, 0, 0]]	        7

입출력 예에 대한 설명
문제에 주어진 예시와 같습니다.
로봇이 오른쪽으로 한 칸 이동 후, (1, 3) 칸을 축으로 반시계 방향으로 90도 회전합니다.
다시, 아래쪽으로 3칸 이동하면 로봇은 (4, 3), (5, 3) 두 칸을 차지하게 됩니다.
이제 (5, 3)을 축으로 시계 방향으로 90도 회전 후, 오른쪽으로 한 칸 이동하면 (N, N)에 도착합니다.
따라서 목적지에 도달하기까지 최소 7초가 걸립니다.
 */