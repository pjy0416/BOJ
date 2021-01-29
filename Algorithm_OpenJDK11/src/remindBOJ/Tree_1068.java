package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tree_1068 {
    static final int ROOT =-1, REMOVED =-2, ZERO =0, ADDITIONAL =1;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        parents = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            parents[i] = Integer.parseInt(st.nextToken());
        }
        int removeNode = Integer.parseInt(br.readLine());

        br.close();
        solution(removeNode, n);
    }

    private static void solution(int removeNode, int n) {
        parents[removeNode] = REMOVED;
        int answer =0;

        for(int i=0; i<n; i++) {
            if(parents[i] != REMOVED) { // 자신이 루트가 아니고 지워지지 않은 경우
                answer += find(parents[i]) != REMOVED && !hasChildren(i) ? ADDITIONAL : ZERO;
            }
        }
        System.out.println(answer);
    }

    private static boolean hasChildren(int i) {
        for(int parentIdx : parents) {
            if(parentIdx == i) {
                return true;
            }
        }
        return false;
    }

    private static int find(int val) {
        if(val == REMOVED) {
            return val;
        } else if(val == ROOT) {
            return val;
        }
        return find(parents[val]);
    }
}
