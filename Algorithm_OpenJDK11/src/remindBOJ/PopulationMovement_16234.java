package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class PopulationMovement_16234 {
    final static int[] dx = {1,-1,0,0}, dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution(N,L,R,map);
        br.close();
    }

    private static void solution(int N, int L, int R, int[][] map) {
        int answer =0;
        boolean isChange = true;

        while(isChange) {
            isChange = false;
            int[][] group = new int[N][N];
            int groupIdx =0;
            LinkedList<Pos_16234> moveList = new LinkedList<>();
            int[] newPopulations = new int[N*N+1];  // 생길 수 있는 연합 최대 경우 ( 연합이 없는 경우 )

            for(int y=0; y<N; y++) {
                for(int x=0; x<N; x++) {
                    if(group[y][x] ==0) {
                        group[y][x] = ++groupIdx;
                        moveList.offer(new Pos_16234(x,y));
                        int nations =1;
                        int populations = map[y][x];

                        while(!moveList.isEmpty()) {
                            Pos_16234 pos = moveList.poll();

                            for(int i=0; i<4; i++) {
                                int nx = pos.x +dx[i];
                                int ny = pos.y +dy[i];
                                if(isInArea(nx,ny,N)) {
                                    if(group[ny][nx] ==0 && isUnited(map[pos.y][pos.x], map[ny][nx], L, R)) {
                                        isChange = true;
                                        group[ny][nx] = groupIdx;
                                        nations++;
                                        populations += map[ny][nx];
                                        moveList.offer(new Pos_16234(nx,ny));
                                    }
                                }
                            }
                        }
                        newPopulations[groupIdx] = populations/nations;
                    }
                }
            }
            if(isChange) {
                answer++;
            }

            for(int y=0; y<N; y++) {
                for(int x=0; x<N; x++) {
                    map[y][x] = newPopulations[group[y][x]];
                }
            }
        }
        System.out.println(answer);
    }

    private static boolean isUnited(int val1, int val2, int L, int R) {
        return Math.abs(val1-val2) >= L && Math.abs(val1-val2) <=R;
    }

    private static boolean isInArea(int x, int y, int max) {
        return x>=0 && x<max && y>=0 && y<max;
    }
}

class Pos_16234 {
    int x;
    int y;

    public Pos_16234(int x, int y) {
        this.x= x;
        this.y = y;
    }
}