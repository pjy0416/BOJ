package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RightTriangle_4153 {
    final static String endPoint = "0 0 0", right = "right\n", wrong = "wrong\n";
    final static int triangles = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr;
        StringBuffer sb = new StringBuffer();

        while(!(inputStr = br.readLine()).equals(endPoint)) {
            StringTokenizer st = new StringTokenizer(inputStr);
            long[] triangleArr = new long[triangles];
            for(int i=0; i<triangles; i++) {
                triangleArr[i] = Long.parseLong(st.nextToken());
            }
            sb.append(solution(triangleArr));
        }
        br.close();
        System.out.print(sb.toString());
    }

    private static String solution(long[] triangleArr) {
        Arrays.sort(triangleArr);
        if(triangleArr[0]*triangleArr[0] + triangleArr[1]*triangleArr[1] == triangleArr[2]*triangleArr[2])  return right;
        return wrong;
    }
}
