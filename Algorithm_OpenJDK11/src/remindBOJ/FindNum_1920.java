package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FindNum_1920 {
    final static String EXIST = "1", NO_EXIST = "0", NEWLINE="\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Object[] originObj = getDataObj(br);
        Object[] targetObj = getDataObj(br);
        br.close();
        solution((int)originObj[0],(int[])originObj[1], (int)targetObj[0], (int[])targetObj[1]);
    }

    private static Object[] getDataObj(BufferedReader br) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] numArr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        Object[] result = {n, numArr};
        return result;
    }

    private static void solution(int n, int[] originArr, int m, int[] targetArr) {
        StringBuffer sb = new StringBuffer();
        Arrays.sort(originArr);
        for(int target : targetArr) {
            if(inInNumber(target, n, originArr))    sb.append(EXIST);
            else                                    sb.append(NO_EXIST);
            sb.append(NEWLINE);
        }
        System.out.println(sb.toString());
    }

    private static boolean inInNumber(int target, int n, int[] originArr) { // binary search
        int left =0, right =n-1;

        while(left <=right) {
            int mid = (left+right)/2;
            int origin = originArr[mid];
            if(origin == target)        return true;
            else if(origin > target)    right = mid-1;
            else                        left = mid+1;
        }
        return false;
    }
}
