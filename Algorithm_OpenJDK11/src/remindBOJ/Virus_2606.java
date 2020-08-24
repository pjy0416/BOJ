package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Virus_2606 {
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int cases = Integer.parseInt(br.readLine());
        initParents(n);

        for(int i=0; i<cases; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            union(Math.min(left,right),Math.max(left,right));
        }
        int origin = find(1);   // 1번 컴퓨터
        int answer =0;
        for(int i=2; i<=n; i++) {
            if(find(i) == origin)    answer++;
        }
        System.out.println(answer);
        br.close();
    }

    private static int find(int val) {
        if(val == parents[val]) return val;
        return parents[val] = find(parents[val]);
    }

    private static void union(int left, int right) {
        left = find(left);
        right = find(right);
        if(left != right)   parents[right] = left;
    }

    private static void initParents(int n) {
        parents = new int[n+1];
        for(int i=1; i<=n; i++) parents[i] = i;
    }
}

//7
//4
//2 3
//5 6
//1 2
//1 5

//4
//3
//1 2
//2 3
//3 4