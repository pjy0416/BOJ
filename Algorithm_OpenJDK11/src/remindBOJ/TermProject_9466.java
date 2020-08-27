package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TermProject_9466 {
    final static String NEWLINE="\n";
    static int count;
    static int[] wishArr;
    static boolean[] visit;
    static boolean[] finish;

    public static void main(String[] args) throws IOException {
        solution(new BufferedReader(new InputStreamReader(System.in)));
    }

    private static void solution(BufferedReader br) throws IOException {
        int testCases = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<testCases; i++) {
            int n = Integer.parseInt(br.readLine());
            wishArr = new int[n+1];
            visit = new boolean[n+1];
            finish = new boolean[n+1];
            count =0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++)     wishArr[j] = Integer.parseInt(st.nextToken());
            for(int j=1; j<=n; j++)     dfs(j);
            sb.append(n-count).append(NEWLINE);
        }
        System.out.println(sb.toString());
    }

    private static void dfs(int now) {
        if(visit[now])  return;
        visit[now] = true;
        int next = wishArr[now];
        if(!visit[next])        dfs(next);  // 연결된 학생들 visit 체크
        else {
            if(!finish[next])   {   // 연결된 학생이 체크되지 않았으면
                count++;
                for(int i=next; i != now; i = wishArr[i])   count++; // now 만나기전까지 count
            }
        }
        finish[now] = true; // 끝남 체크
    }
}
