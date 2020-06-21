package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ConnectCnt_11724 {
    static int[] parents;

    public static void main(String[] args) throws IOException{
        System.out.println(solution(new BufferedReader(new InputStreamReader(System.in))));
    }

    private static int solution(BufferedReader br) throws IOException {
        int answer=0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        inItParents(n);

        int m = Integer.parseInt(st.nextToken());
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            if(find(left) != find(right)) {
                union(left, right);
            }
        }
        br.close();

        boolean[] visit = new boolean[n+1];

        for(int i=1; i<=n; i++) {
            if(!visit[i]) {
                answer++;
                visit[i] = true;
                for(int j=1; j<=n; j++) {
                    if(i!=j && !visit[j]) {
                        if(find(i) == find(j)) {
                            visit[j] = true;
                        }
                    }
                }
            }
        }

        return answer;
    }

    private static void union(int left, int right) {
        left = find(left);
        right = find(right);

        if(left != right) {
            parents[right] = left;
        }
    }

    private static int find(int val) {
        if(val == parents[val]) {
            return val;
        }
        return parents[val] = find(parents[val]);
    }

    private static void inItParents(int n) {
        parents = new int[n+1];
        for(int i=1; i<=n; i++) {
            parents[i] = i;
        }
    }
}
//https://www.acmicpc.net/problem/11724
