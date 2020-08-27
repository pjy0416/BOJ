package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PointsOnLine_11663 {
    public static void main(String[] args) throws IOException {
        solution(new BufferedReader(new InputStreamReader(System.in)));
    }

    private static void solution(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long[] pointArr = new long[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)  pointArr[i] = Long.parseLong(st.nextToken());
        Arrays.sort(pointArr);

        StringBuffer sb = new StringBuffer();
        final String NEWLINE = "\n";
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            long left = Long.parseLong(st.nextToken());
            long right = Long.parseLong(st.nextToken());
            sb.append(getPoints(left,right,pointArr)).append(NEWLINE);
        }
        br.close();
        System.out.println(sb.toString());
    }

    private static int getPoints(long left, long right, long[] pointArr) {
        int size = pointArr.length;
        int leftIdx =0, rightIdx = size-1, less=-1, over=size;

        while(leftIdx <= rightIdx) {
            int midIdx = (leftIdx+rightIdx)/2;
            if(pointArr[midIdx] >= left) rightIdx = midIdx-1;   // 조건 충족
            else {
                less = midIdx;
                leftIdx = midIdx+1;
            }
        }
        
        leftIdx =0;
        rightIdx=size-1;
        
        while(leftIdx <= rightIdx) {
            int midIdx = (leftIdx+rightIdx)/2;
            if(pointArr[midIdx] <= right) leftIdx = midIdx+1;    // mid보다 right가 큰 경우 (조건 충족)
            else {
                over = midIdx;
                rightIdx = midIdx-1;
            }
        }
        return size-(size-over)-(less+1);
    }
}
/*
5 5
1 3 10 20 30
1 10
20 60
3 30
2 15
4 8

2 4
99999 100000
1 99999
100001 1000000000
1 100000
100000 1000000000
 */

