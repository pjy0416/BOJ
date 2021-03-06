package programmers;

public class KitDPRoadToSchool {
    private static int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n+1][m+1];
        for(int y=1; y<=n; y++) {   // map 생성
            for(int x=1; x<=m; x++) {
                map[y][x] =1;
            }
        }
        for(int[] pos : puddles) {  // 웅덩이는 0으로 변환
            map[pos[1]][pos[0]] = 0;
        }

        map[0][1] =1;
        for(int y=1; y<=n; y++) {   // map 생성
            for(int x=1; x<=m; x++) {
                if(map[y-1][x] ==0 && map[y][x-1] ==0) {
                    map[y][x] =0;
                }
            }
        }

        return getResult(m,n, map);
    }

    private static int getResult(int m, int n, int[][] map) {
        for(int y=2; y<=n; y++) {
            for(int x=2; x<=m; x++) {
                if(map[y][x] !=0) {
                    map[y][x] = (map[y-1][x] + map[y][x-1]) % 1000000007;
                }
            }
        }
        return map[n][m];
    }
    /*
    final static int[] dx = {-1,0};
    final static int[] dy = {0,-1};

    private static int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n+1][m+1];
        final int MOD = 1000000007;
        for(int[] puddle : puddles) {
            dp[puddle[1]][puddle[0]] =-1;
        }
        dp[0][1] =1;
        for(int y=1; y<=n; y++) {
            for(int x=1; x<=m; x++) {
                if(dp[y][x] != -1) {
                    for(int i=0; i<2; i++) {
                        int ny = y+dy[i];
                        int nx = x+dx[i];
                        if(dp[ny][nx] != -1) {
                            dp[y][x] = (dp[y][x] + dp[ny][nx])%MOD;
                        }
                    }
                }
            }
        }
        return dp[n][m];
    }
     */

    public static void main(String[] args) {
        int m =4;
        int n =3;
        int[][] puddles ={{2,2}};

        System.out.println(solution(m,n,puddles));
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/42898
등굣길

문제 설명
계속되는 폭우로 일부 지역이 물에 잠겼습니다.
물에 잠기지 않은 지역을 통해 학교를 가려고 합니다.
집에서 학교까지 가는 길은 m x n 크기의 격자모양으로 나타낼 수 있습니다.

아래 그림은 m = 4, n = 3 인 경우입니다.

가장 왼쪽 위, 즉 집이 있는 곳의 좌표는 (1, 1)로 나타내고
가장 오른쪽 아래, 즉 학교가 있는 곳의 좌표는 (m, n)으로 나타냅니다.

격자의 크기 m, n과 물이 잠긴 지역의 좌표를 담은 2차원 배열 puddles이 매개변수로 주어집니다.
집에서 학교까지 갈 수 있는 최단경로의 개수를 1,000,000,007로 나눈 나머지를 return 하도록 solution 함수를 작성해주세요.

제한사항
격자의 크기 m, n은 1 이상 100 이하인 자연수입니다.
m과 n이 모두 1인 경우는 입력으로 주어지지 않습니다.
물에 잠긴 지역은 0개 이상 10개 이하입니다.
집과 학교가 물에 잠긴 경우는 입력으로 주어지지 않습니다.

입출력 예
m	n	 puddles	return
4	3	[[2, 2]]	  4
입출력 예 설명
 */
