package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TreeParents_11725 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] map = inItMap(n);      // 배열은 메모리 초과 => 최악의 경우 100,000 by 100,000

        for(int i=1; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int left =Integer.parseInt(st.nextToken());
            int right =Integer.parseInt(st.nextToken());
            map[left].add(right);
            map[right].add(left);
        }
        br.close();
        solution(n, map);
    }

    private static void solution(int n, ArrayList<Integer>[] map) {
        final int root = 1; // root =>1
        int[] parents = new int[n+1];
        Queue<Integer> queue = new LinkedList<>();

        parents[root] = root;   // 부모가 없으니까 자기 자신 입력
        queue.offer(root);

        while(!queue.isEmpty()) {
            int origin = queue.poll();
            for(int child : map[origin]) {
                if(parents[child] ==0) {
                    parents[child] = origin;
                    queue.offer(child);
                }
            }
        }

        final String newLine = "\n";
        StringBuffer sb = new StringBuffer();

        for(int i=2; i<=n; i++) sb.append(parents[i]).append(newLine);

        System.out.println(sb.toString());
    }
    private static ArrayList<Integer>[] inItMap(int n) {
        ArrayList<Integer>[] result = new ArrayList[n+1];
        for(int i=1; i<=n; i++) result[i] = new ArrayList<>();
        return result;
    }
}
