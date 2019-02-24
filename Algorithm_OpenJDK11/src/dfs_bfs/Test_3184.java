package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_3184 {
    private static int[] directionX = {1, -1, 0, 0};             // 동 서 남 북
    private static int[] directionY = {0, 0, 1, -1};             // 동 서 남 북
    private static Boolean[][] isVisited;
    private static Boolean[][] isWall;
    private static MyQueue wallPos;
    private static MyQueue wayPos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        int size = Integer.parseInt(str.split(" ")[0]);
        int depth = Integer.parseInt(str.split(" ")[1]);

        String[] ways = new String[size];
        for(int i=0; i<size; i++) {
            ways[i] = br.readLine();
        }

        bfs(size, depth, ways);

        br.close();
    }

    private static void bfs(int size, int depth, String[] ways) {
        //포지션 0,0 부터 증가하면서 울타리 만나면 스탑
        isVisited= initBool(size, depth);
        wallPos = new MyQueue();
        wayPos = new MyQueue();

        wayPos.add(0,0);
        isVisited[0][0] = true;

        int wolf =0;
        int sheep =0;

        while(true) {
            if(isVisited[size-1][depth-1]) {
                break;
            }

            if(wayPos.getIdx() == wayPos.getSize()) {   // 울타리 안에서 모든거 돌았으면
                // 울타리 위치 꺼내서 주변 탐색
                if(wayPos.getSheep() > wayPos.getWolf()) {
                    System.out.println("이게 언제되는데");
                    sheep += wayPos.getSheep();
                } else {
                    wolf += wayPos.getWolf();
                }
                wayPos = new MyQueue();
                System.out.println("초기화");
                search(wallPos, size, depth, ways);
            }
            search(wayPos, size, depth, ways);



        }
        System.out.println("\n\n" + sheep + " " + wolf);
    }

    private static void search(MyQueue pos, int size, int depth, String[] ways) {
//        System.out.println("자자\t늑대는?" + pos.getWolf() + "양은?\t\t" + pos.getSheep());
        for(int idx=pos.getIdx(); idx<pos.getSize(); idx++) {
            int posX = pos.getX();
            int posY = pos.getY();
            System.out.println(posX + "," + posY);
            for(int i=0; i<4; i++) {
                int x = posX + directionX[i];             // 다음 좌표
                int y = posY + directionY[i];             // 다음 좌표

                if(x >=0 && x<depth && y>=0 && y<size) {        // out of index 방지
                    if(!isVisited[y][x]) {                      // 들린적이 없을 때만
                        char ch = ways[y].charAt(x);
//                        System.out.println(x+ "," + y + "가즈아");
                        switch (ch) {
                            case '#':
                                System.out.println(x + "," + y + "여긴 벽");
                                wallPos.add(x,y);
                                isVisited[y][x] = true;
                                break;
                            case '.':
                                wayPos.add(x,y);
                                isVisited[y][x] = true;
                                break;
                            case 'v' :
                                wayPos.add(x,y);
                                wayPos.addWolf();
                                System.out.println("여기는 늑대");
                                isVisited[y][x] = true;
                                break;
                            case 'o' :
                                wayPos.add(x,y);
                                wayPos.addSheep();
                                System.out.println("여기는 양");
                                isVisited[y][x] = true;
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
    }

    private static Boolean[][] initBool(int size, int depth) {
        Boolean[][] result= new Boolean[size][depth];

        for(int i=0; i<size; i++) {
            for(int j=0; j<depth; j++) {
                result[i][j] = false;
            }
        }

        return result;
    }
}

class MyQueue {
    private int[] x;
    private int[] y;
    private int size;
    private int first;
    private int wolf;
    private int sheep;

    MyQueue() {
        x = new int[100];
        y = new int[100];
        size =0;
        first =0;
        wolf =0;
        sheep =0;
    }

    void add(int posX, int posY) {
        x[size] = posX;
        y[size] = posY;
        addSize();
    }

    private void addSize() {
        this.size++;
    }

    int getSize() {
        return this.size;
    }

    int getX() {
        return this.x[first];
    }

    int getY() {
        return this.y[first++];
    }

    int getIdx() {
        return first;
    }

    void addWolf() {
        this.wolf++;
    }

    void addSheep() {
        this.sheep++;
    }

    int getWolf() {
        return this.wolf;
    }

    int getSheep() {
        return this.sheep;
    }

}