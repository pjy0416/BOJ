package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class StartLink_14889 {
    static int answer;
    static int[][] abilities;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        abilities = new int[n][n];

        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                abilities[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution(n);
    }

    private static void solution(int n) {
        answer =Integer.MAX_VALUE;
        dfs(n,0,n/2, new ArrayList<>(), new ArrayList<>());
        System.out.println(answer);
    }

    private static void dfs(int n, int idx, int half, ArrayList<Integer> left, ArrayList<Integer> right) {
        if(left.size() > half || right.size() > half) {
            return;
        }

        if(idx == n) {
            int leftSum =0, rightSum =0;
            for(int i=0; i<half; i++) {
                for(int j=i; j<half; j++) {
                    leftSum += abilities[left.get(i)][left.get(j)] + abilities[left.get(j)][left.get(i)];
                    rightSum += abilities[right.get(i)][right.get(j)] + abilities[right.get(j)][right.get(i)];
                }
            }
            answer = Math.min(answer,Math.abs(leftSum-rightSum));
            return;
        }

        left.add(idx);
        dfs(n, idx+1, half, left, right);
        left.remove(left.size()-1);
        right.add(idx);
        dfs(n, idx+1, half, left, right);
        right.remove(right.size()-1);
    }
}
