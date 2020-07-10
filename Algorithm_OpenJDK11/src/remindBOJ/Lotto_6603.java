package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Lotto_6603 {
    final static int MAX =6;
    final static String SPACE = " ", END = "0";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr;

        while(!(inputStr = br.readLine()).equals(END)) {
            StringTokenizer st = new StringTokenizer(inputStr);
            int n = Integer.parseInt(st.nextToken());

            String[] numArr = new String[n];
            for (int i = 0; i < n; i++) {
                numArr[i] = st.nextToken();
            }
            solution(n,numArr);
        }
        br.close();
    }

    private static void solution(int n, String[] numArr) {
        dfs(n,numArr,new LinkedList<>(), 0, 0);
        System.out.println();
    }

    private static void dfs(int n, String[] numArr, LinkedList<String> numList, int idx, int cnt) {
        if(cnt ==MAX) {
            StringBuffer sb = new StringBuffer();
            for(String str : numList) {
                sb.append(str).append(SPACE);
            }
            System.out.println(sb.toString());
            return;
        }

        for(int i=idx; i<n; i++) {
            numList.offer(numArr[i]);
            dfs(n, numArr, numList, i+1, cnt+1);
            numList.pollLast();
        }
    }
}
