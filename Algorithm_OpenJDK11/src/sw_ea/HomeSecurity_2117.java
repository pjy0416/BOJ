package sw_ea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HomeSecurity_2117 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        solution(Integer.parseInt(br.readLine()));

        br.close();
    }

    private static void solution(int testCase) throws IOException {
        int[] result = new int[testCase];

        for(int round =0; round <testCase; round++) {
            String[] cityInfo = br.readLine().split(" ");
            int N = Integer.parseInt(cityInfo[0]); // N*N의 도시 크기
            int M = Integer.parseInt(cityInfo[1]); // 지불 가능 비용 M
            int[][] map = new int[N][N];

            // Map 저장
            for(int i=0; i<N; i++) {
                String[] line = br.readLine().split(" ");
                int idx =0;
                for(String num : line) {
                    map[i][idx++] = Integer.parseInt(num);
                }
            }

            result[round] = 1; // k=1 일때의 값을 저장해놓기
//            System.out.println("N = > " + N);
            // 집들의 최대 지불 비용까지 최대값 구하기
            int maxloop = N+1 >M ? N+1 : M;
            for(int k=2; k<=maxloop; k++) {    // k=2 부터 시작 , 맵을 전체 다 확인하는거는 N+1까지니까
//                System.out.println("K 도는 중 => " + k);

                int cnt =0; // 서비스 영역 안에 들어오는 집의 갯수 카운트

                // 맵을 돌면서 카운팅하는 로직 필요
                for(int y=0; y<N; y++) {
                    for(int x=0; x<N; x++) {
                        cnt = Math.max(cnt, serviceCnt(k, x, y, map, N));
//                        System.out.println("카운트 변함");
//                        System.out.println("CNT = " + cnt);
                    }
                }

                int profit = M*cnt - getCost(k) ;
//                System.out.println("M = > " + M + "\t\t k = " + k);
//                System.out.println("cost => " + cost + "\t\t profit => " + profit);

                result[round] = (profit >=0 && result[round] < cnt) ? cnt : result[round];
            }
        }

        for(int i=0; i<testCase; i++) {
            System.out.print("#" + (i+1) + " ");
            System.out.println(result[i]);
        }
    }

    private static int serviceCnt(int k, int posX, int posY, int[][] map, int N) {
        int cnt =0;
//        System.out.println("k => " + k);
//        System.out.println("기준점 : " + posX + ", " + posY);
        // 마름모 확인하는 로직..

        // x가 그 포지션 그대로일때,

        // y가 그 포지션 그대로일 때,

        // x,y 둘 다 변할 때

        for(int i=0; i<=k-1; i++) { // y축 마름모, 기준점을 중심으로 위 아래로 퍼지는 식,
            int aboveY = posY-i;
            int underY = posY+i;

            for(int j=k-1-i; j>=0; j--) {
                int rightX = posX + j;
                int leftX = posX - j;

                if (j != 0) {    // x값 변화
                    if (i == 0) { // 받은 위치의 x축 라인 증감 식
//                        System.out.println("y값 고정, x값 변화 y = " + i + " x = " + j);
//                        System.out.println("우측 X = " + rightX + ", " + posY);
//                        System.out.println("좌측 X = " + leftX + ", " + posY);

                        // 우측 X 확인
                        cnt += existHome(N, rightX, posY, map);
                        // 좌측 X 확인
                        cnt += existHome(N, leftX, posY, map);
                    } else if (i == k - 1) {
                        // y축이 최대로 증감했을 때는 x축이 0이 아닌 경우가 있으면 안됨.
                        break;
                    } else {    // 상하좌우 다 확인
//                        System.out.println("x,y 값 모두 변화 y = " + i + " x = " + j);
//                        System.out.println("#########상단 Y축 기준!!");
//                        System.out.println("우측 X = " + rightX + ", " + aboveY);
//                        System.out.println("좌측 X = " + leftX + ", " + aboveY);
//                        System.out.println("#########하단 Y축 기준!!");
//                        System.out.println("우측 X = " + rightX + ", " + underY);
//                        System.out.println("좌측 X = " + leftX + ", " + underY);

                        // 상단 Y
                            // 우측 X
                        cnt += existHome(N, rightX, aboveY, map);
                            // 좌측 X
                        cnt += existHome(N, leftX, aboveY, map);
                        // 하단 Y
                            // 우측 X
                        cnt += existHome(N, rightX, underY, map);
                            // 좌측 X
                        cnt += existHome(N, leftX, underY, map);
                    }

                } else { //x값 고정, y값 변화

                    // 받은 위치를 기준으로 x축이 증감이 없는 y축 한 줄
                    if(i !=0) { // y축이 0인 경우는 카운팅이 되면 안됨.
                        aboveY = posY - i;
                        underY = posY + i;

//                        System.out.println("#x값 고정, y값 변화 y = " + i + " x = " + j);
//                        System.out.println("상단 Y = " + posX + ", " + aboveY);
//                        System.out.println("하단 Y = " + posX + ", " + underY);
                        // 상단 Y 확인
                        cnt += existHome(N, posX, aboveY, map);
                        // 하단 Y 확인
                        cnt += existHome(N, posX, underY, map);
                    }
                }
            }

        }

        // 자기 자신도 추가
        cnt += existHome(N, posX, posY, map);

        return cnt;
    }

    private static boolean isInArea(int n, int x, int y) {
        return x >=0 && y >=0 && x <n && y <n ? true : false;
    }

    private static int existHome(int N, int x, int y, int[][] map) {
        if(isInArea(N, x, y)) {
            if(map[y][x] ==1) {
//                System.out.println(x + ", " +y +" 좌표에서 카운트");
//                if(x==4 && y==4) {
//                    System.out.println("우측 X 들어옴");
//                } else if(x==2 && y==4) {
//                    System.out.println("좌측 X 들어옴");
//                } else if(x==3 && y==3) {
//                    System.out.println("상단 Y 들어옴");
//                } else if(x==3 && y==5) {
//                    System.out.println("하단 Y 들어옴");
//                }
//                System.out.println("맵에 있음");
                return 1;
            }
        }
        return 0;
    }

    private static int getCost(int k) {
        return (int) (Math.pow(k,2)+Math.pow(k-1,2));
    }
}

/*
1
8 3
0 0 0 0 0 1 0 0
0 1 0 1 0 0 0 1
0 0 0 0 0 0 0 0
0 0 0 1 0 1 0 0
0 0 1 1 0 0 0 0
0 0 0 0 0 0 0 0
0 0 0 0 1 0 1 0
1 0 0 0 0 0 0 0
 */