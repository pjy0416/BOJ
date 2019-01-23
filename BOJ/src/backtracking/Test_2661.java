package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_2661 {
//    Ref : https://mygumi.tistory.com/213
//    backtracking 어렵다......
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int line;
    static boolean stop = false;
    public static void main(String[] args) throws IOException {

        bestNum();

        br.close();
    }

    private static void bestNum() throws IOException{
        line = Integer.parseInt(br.readLine());
        dfs(1,"1");
    }
    private static void dfs(int len, String s) {
        if (stop) {
            return;
        }

        if (line == len) {
            stop = true;
            System.out.println(s);
        } else {
            for (int i = 1; i <= 3; i++) {
                if (isSatisfy(s + i)) {
                    dfs(len + 1, s + i);
                }
            }
        }
        // backtracking

    }

    private static boolean isSatisfy(String s) {
        int len = s.length();
        int loop = len/2;
        int start = len -1;
        int end = len;

        for(int i=1; i<=loop; i++) {
            if(s.substring(start -i, end -i).equals(s.substring(start, end))) {
                return false;
            }
            start -=1;
        }
        return true;
    }
}
