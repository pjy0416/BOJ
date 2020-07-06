package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Castle_2234 {
    private final static int[] dx = {1,-1,0,0}, dy = {0,0,1,-1};
    private final static char[][] moveArr= {{'N','E','W','S'},{'N','E','S'},{'E','W','S'},{'E','S'},{'S','W','N'},{'N','S'},{'W','S'},{'S'},{'E','W','N'},{'E','N'},{'E','W'},{'E'},{'W','N'},{'N'},{'W'},{}};
    private static HashMap<Character, CastlePos> direction;
    private static HashMap<Integer, Integer> roomSizeMap;
    static int maxRoom, groupInfo[][], sizeInfo[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();
        setDirection();
        solution(map,m,n);
    }

    private static void setDirection() {
        direction = new HashMap<>();
        direction.put('E', new CastlePos(1,0));
        direction.put('W', new CastlePos(-1, 0));
        direction.put('S', new CastlePos(0,1));
        direction.put('N', new CastlePos(0,-1));
    }

    private static void solution(int[][] map, int m, int n) {
        maxRoom =0;
        roomSizeMap = new HashMap<>();
        int breakWallRoomSize = 0;

        setInfos(map,m,n);

        int mul = m*n;
        for(int i=0; i<mul; i++) {
            int x = i%m;
            int y = i/m;
            int originGroup = groupInfo[y][x];

            for(int j=0; j<4; j++) {
                int nx = x+dx[j];
                int ny = y+dy[j];
                if(isInArea(nx,ny,m,n)) {
                    if(originGroup != groupInfo[ny][nx]) {
                        breakWallRoomSize = Math.max(breakWallRoomSize, sizeInfo[y][x]+sizeInfo[ny][nx]);
                    }
                }
            }
        }

        System.out.println(roomSizeMap.size());  // 방의 갯수
        System.out.println(maxRoom); // 가장 넓은 방의 넓이
        System.out.println(breakWallRoomSize); // 벽을 하나 허물 경우 가장 넓은 방
    }

    private static void setInfos(int[][] map, int m, int n) {
        groupInfo = new int[n][m];
        sizeInfo = new int[n][m];
        int idx =0;

        LinkedList<CastlePos> moveList = new LinkedList<>();
        int mul = m*n;
        for(int i=0; i<mul; i++) {
            int x = i%m;
            int y = i/m;
            if(groupInfo[y][x] ==0) {
                groupInfo[y][x] = ++idx;
                moveList.offer(new CastlePos(x,y));
                int cnt =1;

                while(!moveList.isEmpty()) {
                    CastlePos pos = moveList.poll();
                    for(char ch : moveArr[map[pos.y][pos.x]]) {
                        CastlePos directionPos = direction.get(ch);
                        int nx = pos.x + directionPos.x;
                        int ny = pos.y + directionPos.y;
                        if(groupInfo[ny][nx] ==0) {
                            cnt++;
                            groupInfo[ny][nx] = idx;
                            moveList.offer(new CastlePos(nx, ny));
                        }
                    }
                }
                maxRoom = Math.max(cnt,maxRoom);
                roomSizeMap.put(idx,cnt);
            }
            sizeInfo[y][x] = roomSizeMap.get(groupInfo[y][x]);
        }
    }

    private static boolean isInArea(int x, int y, int maxX, int maxY) {
        return x>=0 && x<maxX && y>=0 && y<maxY;
    }
}

class CastlePos {
    int x;
    int y;

    public CastlePos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}