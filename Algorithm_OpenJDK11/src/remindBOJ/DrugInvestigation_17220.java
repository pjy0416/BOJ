package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DrugInvestigation_17220 {
    final static int asciiA = 65;
    static int n;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int left = st.nextToken().charAt(0) - asciiA;
            int right = st.nextToken().charAt(0) - asciiA;
            map[left][right] = 1;
        }

        st = new StringTokenizer(br.readLine());
        int criminals = Integer.parseInt(st.nextToken());

        for(int i=0; i<criminals; i++) {
            int criminal = st.nextToken().charAt(0) - asciiA;
            setCriminalMap(criminal);
        }
        br.close();
        solution();
    }

    private static void setCriminalMap(int criminal) {
        for(int i=0; i<n; i++) {
            map[i][criminal] =0;
            if(map[criminal][i] !=0) {
                map[criminal][i] =0;
                int count =0;
                for(int j=0; j<n; j++) {
                    if(map[j][i] !=0) {
                        count++;
                    }
                }
                if(count ==0) {
                    setCriminalMap(i);
                }
            }
        }
    }
    private static void solution() {
        int count =0;
        for(int[] nodes : map) {
            for(int num : nodes) {
                if(num !=0) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
