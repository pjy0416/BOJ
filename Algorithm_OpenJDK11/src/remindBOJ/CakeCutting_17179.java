package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CakeCutting_17179 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] cutArr = new int[M+1];
        cutArr[M] = L;
        for(int i=0; i<M; i++) {
            cutArr[i] = Integer.parseInt(br.readLine());
        }

        int[] cntArr = new int[N];
        for(int i=0; i<N; i++) {
            cntArr[i] = Integer.parseInt(br.readLine());
        }

        solution(M,cutArr,cntArr);
        br.close();
    }

    private static void solution(int m, int[] cutArr, int[] cntArr) {
        for(int cnt : cntArr) {
            System.out.println(getLen(cnt,m,cutArr));
        }
    }

    private static int getLen(int cnt, int m, int[] cutArr) {
        int result =0;
        int left =0, right =cutArr[m];

        while(left <= right) {
            int mid = (left+right)/2;
            if(canCut(mid, cnt, m, cutArr)) {
                left = mid+1;
                result = Math.max(result, mid);
            } else {
                right = mid-1;
            }
        }
        return result;
    }

    private static boolean canCut(int mid, int cnt, int m, int[] cutArr) {
        int prev = 0;
        for (int i = 0; i <= m; i++) {
            if (cutArr[i] - prev >= mid) {
                cnt--;
                prev = cutArr[i];
            }
        }
        return cnt < 0 ? true : false;
    }
}
