package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class FamilyTree_2644 {
    final static int fail = -1, nothing =0;

    static int[][] move;
    static int[] familyTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int left = Integer.parseInt(st.nextToken());
        int right = Integer.parseInt(st.nextToken());
        familyTree = new int[n+1];

        int m = Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int parents = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            familyTree[child] = parents;
        }
        br.close();
        solution(n, left, right);
    }

    private static void solution(int n, int left, int right) {
        int result;

        initMove(n);

        result = getResult(left,right, n);
        if(result == Integer.MAX_VALUE)     result = fail;

        System.out.println(result);
    }

    private static void initMove(int n) {
        move = new int[n+1][n+1];
        for(int i=1; i<=n; i++) {
            Arrays.fill(move[i], -1);
        }
    }

    private static int getResult(int left, int right, int n) {
        int result =Integer.MAX_VALUE;
        findParents(left);
        findParents(right);
        for(int i=1; i<=n; i++) {
//            System.out.println("left => " + move[left][i] + " right => " + move[right][i]);
            if(move[left][i] !=-1 && move[right][i] !=-1) result = Math.min(result, move[right][i] + move[left][i]);
        }
        return result;
    }

    private static void findParents(int child) {
        LinkedList<Integer> treeMove = new LinkedList<>();
        treeMove.offer(familyTree[child]);
        move[child][familyTree[child]] = 1;
        int cnt =1;
        if(familyTree[child] ==0)   move[child][child] = nothing;


        while(!treeMove.isEmpty()) {
            int now = treeMove.poll();
            int parent = familyTree[now];
            if(parent !=nothing) {
                move[child][parent] = ++cnt;
                treeMove.offer(parent);
            }
        }
    }
}

