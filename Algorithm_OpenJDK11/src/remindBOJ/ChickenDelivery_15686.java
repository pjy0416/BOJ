package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ChickenDelivery_15686 {
    private static class Pos {
        int x,y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    final static int HOUSE = 1, CHICKEN =2;
    static int n, m, map[][], dist[][], answer;
    static ArrayList<Pos> chickenList, houseList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        chickenList = new ArrayList<>();
        houseList = new ArrayList<>();

        for(int i=0 ;i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                int info =Integer.parseInt(st.nextToken());
                map[i][j] = info;
                if(info == HOUSE) {
                    houseList.add(new Pos(j,i));
                } else if(info == CHICKEN) {
                    chickenList.add(new Pos(j,i));
                }
            }
        }
        br.close();
        solution();
    }

    private static void solution() {
        int houseSize = houseList.size();
        int chickSize = chickenList.size();
        dist = new int[houseSize][chickSize];

        for(int i=0; i<houseSize; i++) {
            Pos house = houseList.get(i);
            for(int j=0; j<chickSize; j++) {
                Pos chicken = chickenList.get(j);
                dist[i][j] = Math.abs(house.x-chicken.x) + Math.abs(house.y-chicken.y);
            }
        }

        answer = Integer.MAX_VALUE;
        boolean[] visit = new boolean[chickSize];
        permutation(visit,0,0);

        System.out.println(answer);
    }

    private static void permutation(boolean[] visit, int idx, int cnt) {
        if(cnt == m) {
            answer = Math.min(answer, getDist(visit));
            return;
        }
        if(idx >= chickenList.size()) { return; }
        visit[idx] = true;
        for(int i=idx; i<chickenList.size(); i++) {
            visit[i] = true;
            permutation(visit, i+1, cnt+1);
            visit[i] = false;
        }
    }

    private static int getDist(boolean[] visit) {
        int result =0;
        for(int i=0; i<houseList.size(); i++) {
            int tmp = Integer.MAX_VALUE;
            for(int j=0; j<chickenList.size(); j++) {
                if(visit[j]) { tmp = Math.min(tmp, dist[i][j]); }
            }
            result += tmp;
        }
        return result;
    }
}
