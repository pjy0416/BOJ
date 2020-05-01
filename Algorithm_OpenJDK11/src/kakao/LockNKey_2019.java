package kakao;

import java.util.ArrayList;

public class LockNKey_2019 {

    private static boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;

        int[][] newLock = getNewLock(lock);
        ArrayList<int[][]> keyList = getKeyList(key);
        int hole = getHole(lock);
        for(int i=0; i<4; i++) {
            if(checkUnlock(keyList.get(i), newLock,hole)) {
                answer = true;
                break;
            }
        }

        return answer;
    }

    private static int getHole(int[][] lock) {
        int cnt =0;
        for(int[] line : lock) {
            for(int num : line) {
                if(num ==0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static int[][] getNewLock(int[][] lock) {
        int len = lock.length;
        int[][] result = new int[len*3][len*3];
        for(int y=len; y<len*2; y++) {
            for(int x=len; x<len*2; x++) {
                result[y][x] = lock[y-len][x-len];
            }
        }
        return result;
    }

    private static ArrayList<int[][]> getKeyList(int[][] key) {
        ArrayList<int[][]> result = new ArrayList<>();
        result.add(key);
        for(int i=0; i<3; i++) {
            result.add(getRotateKey(result.get(i)));
        }
        return result;
    }

    private static int[][] getRotateKey(int[][] key) {
        int len = key.length;
        int[][] result = new int[len][len];

        for(int y=0; y<len; y++) {  // rotate right
            for(int x=0; x<len; x++) {
                result[x][len-1-y] = key[y][x];
            }
        }

        return result;
    }

    private static boolean checkUnlock(int[][] key, int[][] newLock, int hole) {
        for(int lockY=1+(newLock.length/3-key.length); lockY<newLock.length/3*2; lockY++) {
            for(int lockX=1+(newLock.length/3-key.length); lockX<newLock.length/3*2; lockX++) {
                //좌표를 돌면서 키 확인
                int cnt =0;
                boolean isBreak = false;
                for(int keyY=0; keyY<key.length; keyY++) {
                    for(int keyX=0; keyX<key.length; keyX++) {
                        int mapY = lockY + keyY;
                        int mapX = lockX + keyX;
                        if(isOriginalSpot(mapY, mapX, newLock.length)) {
                            if(newLock[mapY][mapX] ==1) {   // 자물쇠의 홈
                                if(key[keyY][keyX] == 1) {  // 서로 돌기면
                                    isBreak = true;
                                    break;
                                }
                            } else {    // 열쇠 돌기가 들어와야 하는 부분
                                if(key[keyY][keyX] ==0) { // 돌기가 없으면 못연다
                                    isBreak = true;
                                    break;
                                }
                                cnt++;
                            }
                        }
                    }
                    if(isBreak) {
                        break;
                    }
                }
                if(cnt == hole) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isOriginalSpot(int y, int x, int length) {
        int originLen = length/3;
        if(x >= originLen && x <originLen*2 && y >=originLen && y < originLen*2) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};

        boolean result = solution(key,lock);

        if(result) {
            System.out.println("푼다");
        } else {
            System.out.println("못푼다");
        }
    }
}


/*
https://programmers.co.kr/learn/courses/30/lessons/60059

자물쇠와 열쇠
문제 설명
고고학자인 튜브는 고대 유적지에서 보물과 유적이 가득할 것으로 추정되는 비밀의 문을 발견하였습니다.
그런데 문을 열려고 살펴보니 특이한 형태의 자물쇠로 잠겨 있었고
문 앞에는 특이한 형태의 열쇠와 함께 자물쇠를 푸는 방법에 대해 다음과 같이 설명해 주는 종이가 발견되었습니다.

잠겨있는 자물쇠는 격자 한 칸의 크기가 1 x 1인 N x N 크기의 정사각 격자 형태이고
특이한 모양의 열쇠는 M x M 크기인 정사각 격자 형태로 되어 있습니다.

자물쇠에는 홈이 파여 있고 열쇠 또한 홈과 돌기 부분이 있습니다.
열쇠는 회전과 이동이 가능하며 열쇠의 돌기 부분을 자물쇠의 홈 부분에 딱 맞게 채우면 자물쇠가 열리게 되는 구조입니다.
자물쇠 영역을 벗어난 부분에 있는 열쇠의 홈과 돌기는 자물쇠를 여는 데 영향을 주지 않지만,
자물쇠 영역 내에서는 열쇠의 돌기 부분과 자물쇠의 홈 부분이 정확히 일치해야 하며 열쇠의 돌기와 자물쇠의 돌기가 만나서는 안됩니다.
또한 자물쇠의 모든 홈을 채워 비어있는 곳이 없어야 자물쇠를 열 수 있습니다.

열쇠를 나타내는 2차원 배열 key와 자물쇠를 나타내는 2차원 배열 lock이 매개변수로 주어질 때,
열쇠로 자물쇠를 열수 있으면 true를, 열 수 없으면 false를 return 하도록 solution 함수를 완성해주세요.

제한사항

key는 M x M(3 ≤ M ≤ 20, M은 자연수)크기 2차원 배열입니다.
lock은 N x N(3 ≤ N ≤ 20, N은 자연수)크기 2차원 배열입니다.
M은 항상 N 이하입니다.
key와 lock의 원소는 0 또는 1로 이루어져 있습니다.
0은 홈 부분, 1은 돌기 부분을 나타냅니다.
입출력 예
                key	                            lock	                    result
[[0, 0, 0], [1, 0, 0], [0, 1, 1]]	[[1, 1, 1], [1, 1, 0], [1, 0, 1]]	     true
입출력 예에 대한 설명

key를 시계 방향으로 90도 회전하고, 오른쪽으로 한 칸, 아래로 한 칸 이동하면 lock의 홈 부분을 정확히 모두 채울 수 있습니다.
 */