package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FreshMan_1946 {
    public static void main(String[] args) throws IOException {
        solution(new BufferedReader(new InputStreamReader(System.in)));
    }

    private static void solution(BufferedReader br) throws IOException {
        int testCase = Integer.parseInt(br.readLine());
        final String NEWLINE = "\n";
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<testCase; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] participants = new int[n+1];
            for(int j=0; j<n; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                participants[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
            }
            sb.append(getFreshMan(n,participants)).append(NEWLINE);
        }
        System.out.println(sb.toString());
    }

    private static int getFreshMan(int n, int[] participants) {
        final int FIRST =1; // 1등
        int result =FIRST;
        int min = participants[FIRST];
        for(int i=2; i<=n; i++) {
            if(min > participants[i]) { // 서류 심사의 1등의 면접 결과보다 면접을 잘봤으면
                result++;
                min = participants[i]; // 면접 결과 최소값 최신화 => 만약 바로 1이 나오면, 서류 2등 면접 1등이니까 뒤에는 쭉 탈락 이런 느낌
            }  
        }
        return result;
    }
}
