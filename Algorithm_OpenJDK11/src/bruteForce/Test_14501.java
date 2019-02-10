package bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_14501 {
    private static int size =0;
    private static int[][] bf;
    private static int[] tArr;
    private static int[] pArr;

    public static int getMax(int now, int start) {
        if(start > size-1) {   // 모든 인덱스 경우를 훑은 후
            return Math.max(bf[size-1][0],bf[size-1][1]);   // 둘 중 최대값
        }

        if(now >= size) {
            getMax(start+1, start+1);
        } else {
            bf[start][0] = bf[start][1];
            bf[start][1] += pArr[now];

            for(int i=now; i<size; i++) {
                bf[start][0] = Math.max(bf[start][1], bf[start][0] + pArr[i]);
                if(now < size-1 ) {
                    getMax(now + tArr[i], start);
                }
            }
        }

        return Math.max(bf[size-1][0],bf[size-1][1]);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        size = Integer.parseInt(br.readLine());

        tArr = new int[size];
        pArr = new int[size];

        for(int i=0; i<size; i++) {
            String str = br.readLine();
            tArr[i] = Integer.parseInt(str.split(" ")[0]);
            pArr[i] = Integer.parseInt(str.split(" ")[1]);
        }

        bf = new int[size][2];

        System.out.println(getMax(0,0));

        br.close();
    }
}
