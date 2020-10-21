package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Tetromino_14500 {
    final static int[][] barDX = {{1,2,3},{0,0,0}}, barDY = {{0,0,0},{1,2,3}};
    final static int[][] squareDX = {{1,1,0}}, squareDY = {{0,1,1}};
    final static int[][] lShapeDX = {{-1,-1,-1},{0,1,2},{1,1,1},{0,-1,-2}}, lShapeDY = {{0,-1,-2},{1,1,1},{0,1,2},{1,1,1}};
    final static int[][] zShapeDX = {{0,1,1},{-1,-1,-2}}, zShapeDY = {{1,1,2},{0,1,1}};
    final static int[][] fShapeDX = {{-1,0,1},{1,1,1},{-1,0,1},{-1,-1,-1}}, fShapeDY= {{-1,-1,-1},{-1,0,1},{1,1,1},{-1,0,1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = parseInt(st.nextToken());
        int m = parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for(int y=0; y<n; y++) {
            st = new StringTokenizer(br.readLine());
            for(int x=0; x<m; x++) {
                map[y][x] = parseInt(st.nextToken());
            }
        }

        br.close();
        solution(n,m,map);
    }

    private static void solution(int n, int m, int[][] map) {
        int answer =0;

        Tetromino bar = getTetromino(m,n,barDX,barDY);
        Tetromino square = getTetromino(m,n, squareDX, squareDY);
        Tetromino lShape = getTetromino(m,n, lShapeDX, lShapeDY);
        Tetromino zShape = getTetromino(m,n, zShapeDX, zShapeDY);
        Tetromino fShape = getTetromino(m,n, fShapeDX, fShapeDY);

        for(int y=0; y<n; y++) {
            for(int x=0; x<m; x++) {
                answer = Math.max(bar.getMaxSum(x,y,map), answer);
                answer = Math.max(square.getMaxSum(x,y,map), answer);
                answer = Math.max(lShape.getMaxSum(x,y,map), answer);
                answer = Math.max(zShape.getMaxSum(x,y,map), answer);
                answer = Math.max(fShape.getMaxSum(x,y,map), answer);
            }
        }
        System.out.println(answer);
    }

    private static int parseInt(String str) {
        return Integer.parseInt(str);
    }

    private static Tetromino getTetromino(int m, int n, int[][] dxArr, int[][] dyArr) {
        Tetromino shape = new Tetromino(m,n);
        for(int i=0; i<dxArr.length; i++) {
            shape.addDifferentials(dxArr[i],dyArr[i]);
        }
        return shape;
    }
}

class Tetromino {
    ArrayList<int[]> dxList, dyList;
    final int len = 3;
    int maxX, maxY;

    public Tetromino(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.dxList = new ArrayList<>();
        this.dyList = new ArrayList<>();
    }

    public void addDifferentials(int[] dx, int[] dy) {
        this.dxList.add(dx);
        this.dyList.add(dy);
    }

    public int getMaxSum(int x, int y, int[][] map) {
        final int size = dxList.size();
        int max =0;
        for(int i=0; i<size; i++) {
            int[] dx = dxList.get(i);
            int[] dy = dyList.get(i);
            int addAll = this.addAllSum(x,y,dx,dy,map);
            int addX = this.addXSum(x,y,dx,dy,map);
            int addY = this.addYSum(x,y,dx,dy,map);
            int minusAll = this.minusAllSum(x,y,dx,dy,map);
            max = Math.max(Math.max(Math.max(Math.max(addAll,minusAll),addX),addY),max);
        }
        return max;
    }

    private int addAllSum(int x, int y, int[] dx, int[] dy, int[][] map) {
        int sum =map[y][x];
        for(int j=0; j<this.len; j++) {
            int nx = x+dx[j];
            int ny = y+dy[j];
            if(!isInArea(nx,ny)) {
                sum =0;
                break;
            }
            sum += map[ny][nx];
        }
        return sum;
    }
    private int addXSum(int x, int y, int[] dx, int[] dy, int[][] map) {
        int sum =map[y][x];
        for(int j=0; j<this.len; j++) {
            int nx = x+dx[j];
            int ny = y-dy[j];
            if(!isInArea(nx,ny)) {
                sum =0;
                break;
            }
            sum += map[ny][nx];
        }
        return sum;
    }
    private int addYSum(int x, int y, int[] dx, int[] dy, int[][] map) {
        int sum =map[y][x];
        for(int j=0; j<this.len; j++) {
            int nx = x-dx[j];
            int ny = y+dy[j];
            if(!isInArea(nx,ny)) {
                sum =0;
                break;
            }
            sum += map[ny][nx];
        }
        return sum;
    }
    private int minusAllSum(int x, int y, int[] dx, int[] dy, int[][] map) {
        int sum =map[y][x];
        for(int j=0; j<this.len; j++) {
            int nx = x-dx[j];
            int ny = y-dy[j];
            if(!isInArea(nx,ny)) {
                sum =0;
                break;
            }
            sum += map[ny][nx];
        }
        return sum;
    }

    private boolean isInArea(int x, int y) {
        return x >=0 && y >=0 && x<this.maxX && y<this.maxY;
    }
}

