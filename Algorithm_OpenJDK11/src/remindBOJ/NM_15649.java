package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class NM_15649 {
    static int n,k;
    final static String SPACE = " ";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        br.close();
        printAllCase(new boolean[n+1], new LinkedList<>());
    }

    private static void printAllCase(boolean[] check, LinkedList<Integer> list) {
        if(list.size() == k) {
            StringBuffer sb = new StringBuffer();
            for(int num : list) sb.append(num).append(SPACE);
            System.out.println(sb.toString());
            return;
        }
        for(int i=1; i<=n; i++) {
            if(!check[i]) {
                check[i] = true;
                list.offer(i);
                printAllCase(check,list);
                list.pollLast();
                check[i]=false;
            }
        }
    }
}
