package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ChocolateCutting_2163 {
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        solution(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
    }

    private static void solution(int x, int y) {
        answer=0;
        dfs(x,y);
        System.out.println(answer);
    }

    private static void dfs(int x, int y) {
        if(x ==1 && y ==1) {
            return;
        }
        if(x !=1) {
            dfs(1, y);
            dfs(x-1, y);
        } else if(y !=1) {
            dfs(x,1);
            dfs(x,y-1);
        }
        answer++;
    }
}
