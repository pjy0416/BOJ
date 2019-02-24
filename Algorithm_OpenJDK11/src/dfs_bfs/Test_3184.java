package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_3184 {
    private static int[] directionX = {1, -1, 0, 0};             // 동 서 남 북
    private static int[] directionY = {0, 0, 1, -1};             // 동 서 남 북
    private static Boolean[][] isVisited;
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
        wayPos = new MyQueue();

        int[] animals = new int[2];
        animals[0] = 0;     // 양
        animals[1] = 0;     // 늑대

        for(int y=0; y<size; y++) {
            for(int x=0; x<depth; x++) {
                char ch = ways[y].charAt(x);
                if(ch != '#' && !isVisited[y][x]) {
                    wayPos.init();          // 길 초기화
                    wayPos.add(x,y);
                    isVisited[y][x] = true;
                    if(ch == 'o') {
                        wayPos.addSheep();
                    } else if(ch == 'v') {
                        wayPos.addWolf();
                    }
                    animals = search(size, depth, ways, animals);
                }
            }
        }
        System.out.println(animals[0] + " " + animals[1]);
    }


    private static int[] search(int size, int depth, String[] ways, int[] animals) {
        int[] result = new int[2];

        while(true) {
            if(wayPos.getIdx() == wayPos.getSize()) {   // 울타리 안에서 모든거 돌았으면
                // 울타리 위치 꺼내서 주변 탐색
                if(wayPos.getSheep() > wayPos.getWolf()) {
                    result[0] = animals[0] + wayPos.getSheep();
                    result[1] = animals[1];
                } else {
                    result[0] = animals[0];
                    result[1] = animals[1] + wayPos.getWolf();
                }

                return result;
            }

            int posX = wayPos.getX();
            int posY = wayPos.getY();

            for (int i = 0; i < 4; i++) {
                int x = posX + directionX[i];             // 다음 좌표
                int y = posY + directionY[i];             // 다음 좌표

                if (x >= 0 && x < depth && y >= 0 && y < size) {        // out of index 방지
                    if (!isVisited[y][x]) {                      // 들린적이 없을 때만
                        char ch = ways[y].charAt(x);
                        switch (ch) {
                            case '.':
                                wayPos.add(x, y);
                                break;
                            case 'v':
                                wayPos.add(x, y);
                                wayPos.addWolf();
                                break;
                            case 'o':
                                wayPos.add(x, y);
                                wayPos.addSheep();
                                break;
                            default:
                                break;
                        }
                        isVisited[y][x] = true;
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
        x = new int[50000];
        y = new int[50000];
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

    void init() {
        first =0;
        size =0;
        wolf =0;
        sheep =0;
    }

}