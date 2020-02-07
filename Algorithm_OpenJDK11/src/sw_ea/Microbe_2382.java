package sw_ea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Microbe_2382 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        solution(Integer.parseInt(br.readLine()));

        br.close();
    }

    private static void solution(int cases) throws IOException {
        int[] results = new int[cases];
        // case 만큼 loop
        for(int round=0; round<cases; round++) {
            // N = N*N의 셀 구역, M = 미생물 격리 시간, K = 미생물 군집 수
            int N,M,K;

            String[] caseInfo = br.readLine().split(" ");

            if(caseInfo.length ==3) {   // 케이스 정보를 입력 받는 경우
                N = Integer.parseInt(caseInfo[0]);
                M = Integer.parseInt(caseInfo[1]);
                K = Integer.parseInt(caseInfo[2]);
                Queue<Integer>[] sectors = inItSector(N);    // N의 구역 설정 (정사각형 이니까 한 라인만 만들기 (가로 or 세로)
                int[][] microbes = new int[K][4];   // K개 만큼 생성, 각 개체는 4개의 정보를 저장

                for(int i =0; i<K; i++) {
                    String[] microInfo = br.readLine().split(" ");
                    int ver = Integer.parseInt(microInfo[0]);
                    microbes[i][0] = ver;    // 세로 좌표
                    microbes[i][1] = Integer.parseInt(microInfo[1]);    // 가로 좌표
                    microbes[i][2] = Integer.parseInt(microInfo[2]);    // 미생물 수
                    microbes[i][3] = Integer.parseInt(microInfo[3]);    // 이동 방향

                    sectors[ver].offer(i); // 구역에 미생물 번호 저장
                }

                for(int i =0; i<M; i++) {       // M시간동안 돌리기
                    /*
                    ////////////// 프린트 문 ////////////////
                    System.out.println("이동하기 전 \n");
                    for(int m =0; m<microbes.length; m++) {
                        System.out.println("미생물 번호 : \t세로 \t가로 \t미생물 수 \t 이동 방향");
                        System.out.println("\t" + m + "\t\t\t" + microbes[m][0] + "\t\t" + microbes[m][1] + "\t\t" + microbes[m][2] + "\t\t\t" + microbes[m][3]);
                    }
                    ////////////// 프린트 문 ////////////////
                    */

                    // 실질적인 미생물 이동
                    HashMap<Integer,ArrayList<Integer>> movedMicro = new HashMap<>();
                    for(int qIdx =0; qIdx <N; qIdx++) { // 구역의 세로를 순차적으로 돌면서
                        while(!sectors[qIdx].isEmpty()) {  // 정보가 저장된 미생물이 있으면
                            int microIdx = sectors[qIdx].poll();

                            microbes[microIdx] = moveSpot(microbes[microIdx]); // 위치를 이동시켜준다.

                            // 이동 후 약품 칠해진 곳에 갔다면
                            if(isInProtection(microbes[microIdx] ,N) ) {
                                microbes[microIdx][2] /= 2; // 반감
                                microbes[microIdx][3] = changeDirection(microbes[microIdx][3]);
                            }
                            int movedPos = microbes[microIdx][0];

                            if(movedMicro.containsKey(movedPos)) {
                                movedMicro.get(movedPos).add(microIdx);
                            } else {
                                ArrayList<Integer> tmp = new ArrayList<>();
                                tmp.add(microIdx);
                                movedMicro.put(movedPos, tmp);
                            }
                        }
                    }
                    while(!movedMicro.isEmpty()) {
                        Iterator<Integer> it = movedMicro.keySet().iterator();

                        if(it.hasNext()) {
                            int key = it.next();
                            ArrayList<Integer> micArr = movedMicro.get(key);
                            for(int arrIdx=0; arrIdx<micArr.size(); arrIdx++) {
                                sectors[key].offer(micArr.get(arrIdx));
                            }
                            movedMicro.remove(key);
                        }
                    }

                    /*
                    ////////////// 프린트 문 ////////////////
                    System.out.println("\n이동 후 \n");
                    for(int m =0; m<microbes.length; m++) {
                        System.out.println("미생물 번호 : \t세로 \t가로 \t미생물 수 \t 이동 방향");
                        System.out.println("\t" + m + "\t\t\t" + microbes[m][0] + "\t\t" + microbes[m][1] + "\t\t" + microbes[m][2] + "\t\t\t" + microbes[m][3]);
                    }
                    ////////////// 프린트 문 ////////////////
                    */

                    // 미생물 겹친 구역 체크
                    for(int qIdx =0; qIdx <N; qIdx++) {
                        HashMap<Integer, Integer> horSV = new HashMap<>();
                        HashMap<Integer, Integer> prevSV = new HashMap<>();

                        // 겹친 구역 합치기
                        while(!sectors[qIdx].isEmpty()) {  // 정보가 저장된 미생물이 있으면
                            int newIdx = sectors[qIdx].poll();  // 미생물 번호 받기
                            int hor = microbes[newIdx][1];      // 미생물의 가로위치 확인

                            if(horSV.containsKey(hor)) {    // 좌표에 존재하면
                                int origin = horSV.get(hor); // 좌표에 존재하는 기존 미생물 인덱스 받기
                                int prev = prevSV.get(hor); // 좌표에 존하는 이전 값 가져온다.
//                                prev += (microbes[newIdx][2] - microbes[origin][2]); // 다음 비교시 합치기 전 미생물 수를 비교하기 위해

                                // 새로받은 미생물 수가 더 많으면
                                if(microbes[origin][2] - prev < microbes[newIdx][2]) {
//                                    System.out.println("기존 : " + origin +"과 " +newIdx +"비교 시 " +newIdx+"가 더 크기때문에 바꿔줌");
//                                    System.out.println("합치기 전 prev : " + prev);
                                    //합치기 전에
                                    prev += microbes[origin][2];
//                                    System.out.println("합친 후 prev : " + prev);

                                    microbes[newIdx][2] += microbes[origin][2]; //합쳐주고
                                    microbes[origin][2] =0; // 기존 것 초기화
                                    horSV.replace(hor, newIdx);
                                } else { // 기존 군집이 더 많으면
//                                    System.out.println("기존 : " + origin +"과 " +newIdx +"비교 시 " +origin+"가 더 크기때문에 그대로 유지");
//                                    System.out.println("합치기 전 prev : " + prev);
                                    //합치기 전에
                                    prev += microbes[newIdx][2];
//                                    System.out.println("합친 후 prev : " + prev);

                                    microbes[origin][2] += microbes[newIdx][2]; //합쳐주고
                                    microbes[newIdx][2] =0; // 새로운 것 초기화
                                }
                                prevSV.replace(hor, prev);
                            } else {
                                horSV.put(hor, newIdx);
                                prevSV.put(hor, 0);
                            }
                        }

                        //합친 후 남아있는 미생물을 다시 구역 Queue에 넣어주기
                        while(!horSV.isEmpty()) {
                            Iterator<Integer> it = horSV.keySet().iterator();
                            if(it.hasNext()) {
                                int key = it.next();
                                sectors[qIdx].offer(horSV.get(key));    // Queue에 삽입
                                horSV.remove(key);  // Map에서 제거
                            }
                        }
                    }
                    /*
                    ////////////// 프린트 문 ////////////////
                    System.out.println("\n겹치는 위치 합친 후 \n");
                    for(int m =0; m<microbes.length; m++) {
                        System.out.println("미생물 번호 : \t세로 \t가로 \t미생물 수 \t 이동 방향");
                        System.out.println("\t" + m + "\t\t\t" + microbes[m][0] + "\t\t" + microbes[m][1] + "\t\t" + microbes[m][2] + "\t\t\t" + microbes[m][3]);
                    }
                    ////////////// 프린트 문 ////////////////
                    */
                }
                for(int qIdx =0; qIdx <N; qIdx++) {
                    while(!sectors[qIdx].isEmpty()) {
                        int microIdx = sectors[qIdx].poll();
                        results[round] += microbes[microIdx][2];
                    }
                }
            }

        }
        for(int i=0; i<results.length; i++) {
            System.out.println("#" + (i+1) +" " + results[i]);
        }
    }

    private static Queue<Integer>[] inItSector(int N) {
        Queue<Integer>[] sectors = new LinkedList[N];    // N의 구역 설정 (N by N 크기기 때문에)
        for(int i=0; i<N; i++) {
            sectors[i] = new LinkedList<>();
        }
        return sectors;
    }

    private static boolean isInProtection(int[] micro, int N) {
        return micro[0] == 0 || micro[0] == N-1 || micro[1] ==0 || micro[1] == N-1 ? true : false;
    }

    private static int[] moveSpot(int[] micro) {
        // 상: 1, 하: 2, 좌: 3, 우: 4
        switch (micro[3]) {
            case 1 : // 상       ~> 세로좌표 -1
                micro[0] -=1;
                break;
            case 2 : // 하       ~> 세로좌표 +1
                micro[0] +=1;
                break;
            case 3 : // 좌       ~> 가로좌표 -1
                micro[1] -=1;
                break;
            case 4 : // 우       ~> 가로좌표 +1
                micro[1] +=1;
                break;
            default:
                break;
        }
        return micro;
    }

    private static int changeDirection(int direction) {
        int result =0;
        // 상: 1, 하: 2, 좌: 3, 우: 4
        switch (direction) {
            case 1 :    // 1번 상 ~> 2번 하
                result =2;
                break;
            case 2 :    // 2번 하 ~> 1번 상
                result =1;
                break;
            case 3 :    // 3번 좌 ~> 4번 우
                result =4;
                break;
            case 4 :    // 4번 우 ~> 3번 좌
                result =3;
                break;
            default:
                break;
        }
        return result;
    }
}

/*
[제약사항]

1. 시간제한 : 최대 50개 테스트 케이스를 모두 통과하는데, C/C++/Java 모두 5초

2. 구역의 모양은 정사각형으로 주어지며, 한 변의 셀의 개수 N은 5이상 100이하의 정수이다. (5 ≤ N ≤ 100)

3. 최초 배치되어 있는 미생물 군집의 개수 K는 5이상 1,000이하의 정수이다. (5 ≤ K ≤ 1,000)

4. 격리 시간 M은 1이상 1,000이하의 정수이다. (1 ≤ M ≤ 1,000)

5. 최초 약품이 칠해진 가장자리 부분 셀에는 미생물 군집이 배치되어 있지 않다.

6. 최초에 둘 이상의 군집이 동일한 셀에 배치되는 경우는 없다.

7. 각 군집 내 미생물 수는 1 이상 10,000 이하의 정수이다.

8. 군집의 이동방향은 상하좌우 4방향 중 한 방향을 가진다. (상: 1, 하: 2, 좌: 3, 우: 4)

9. 주어진 입력으로 진행하였을 때, 동일한 셀에 같은 미생물 수를 갖는 두 군집이 모이는 경우는 발생하지 않는다.

10.  각 군집의 정보는 세로 위치, 가로 위치, 미생물 수, 이동 방향 순으로 주어진다. 각 위치는 0번부터 시작한다.

입력
10
7 2 9
1 1 7 1
2 1 7 1
5 1 5 4
3 2 8 4
4 3 14 1
3 4 3 3
1 5 8 2
3 5 100 1
5 5 1 1

출력
#1 145
#2 5507
#3 9709
#4 2669
#5 3684
#6 774
#7 4797
#8 8786
#9 1374
#10 5040
 */