package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class JumpNum_2210 {
    final static int DIALSIZE = 5;
    final static int[] dx = {1,-1,0,0}, dy={0,0,1,-1};
    static String[][] dialArr;
    static HashSet<String> numSet;

    public static void main(String[] args) throws IOException {
        setDialArr();
        solution();
    }

    private static void setDialArr() throws IOException {
        dialArr = new String[5][5];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<DIALSIZE; i++) {
            dialArr[i] = br.readLine().split(" ");
        }
        br.close();
    }

    private static void solution() {
        numSet = new HashSet<>();
        for(int y=0; y<DIALSIZE; y++) {
            for(int x=0; x<DIALSIZE; x++) {
                dfs(1,x,y,new StringBuffer(dialArr[y][x]));
            }
        }
        System.out.println(numSet.size());
    }

    private static void dfs(int cnt, int x, int y, StringBuffer sb) {
        if(cnt == 6) {
            numSet.add(sb.toString());
            return;
        }

        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(isInArea(nx,ny)) {
                sb.append(dialArr[ny][nx]);
                dfs(cnt+1, nx, ny, sb);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }

    private static boolean isInArea(int x, int y) {
        return x>=0 && x<DIALSIZE && y>=0 && y<DIALSIZE;
    }
}
