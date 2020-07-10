package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FindRoute_11403 {
    final static String EXIST = "1", NOEXIST ="0", SPACE = " ";
    static String[][] map;
    static boolean[] visit;
    static int n;
    static String judgeStr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n =Integer.parseInt(br.readLine());
        map = new String[n][n];
        for(int i=0 ;i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = st.nextToken();
            }
        }
        br.close();
        solution();
    }

    private static void solution() {
        visit = new boolean[n];
        for(int i=0; i<n; i++) {
            StringBuffer sb = new StringBuffer();
            for(int j=0; j<n; j++) {
                judgeStr = NOEXIST;
                visit = new boolean[n];
                find(i,j);
                sb.append(judgeStr).append(SPACE);
                if(judgeStr.equals(EXIST)) {
                    map[i][j] = EXIST;
                }
            }
            System.out.println(sb.toString());
        }
    }

    private static void find(int start, int target) {
        if(map[start][target].equals(EXIST)) {
            judgeStr = EXIST;
            return;
        } else {
            for (int i = 0; i < n; i++) {
                if (!visit[i] && map[start][i].equals(EXIST)) {
                    visit[i] = true;
                    find(i, target);
                }
            }
        }
    }
}
