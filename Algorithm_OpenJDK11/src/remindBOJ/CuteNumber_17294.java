package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CuteNumber_17294 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        br.close();
        System.out.println(solution(str));
    }

    private static String solution(String str) {
        final String GOODNUM = "◝(⑅•ᴗ•⑅)◜..°♡ 뀌요미!!";
        final String BADNUM = "흥칫뿡!! <(￣ ﹌ ￣)>";
        if(str.length() ==1) {
            return GOODNUM;
        } else {
            char[] chArr = str.toCharArray();
            int diff = chArr[0] - chArr[1];
            for(int i=1; i<chArr.length-1; i++) {
                if(chArr[i]-chArr[i+1] != diff) {
                    return BADNUM;
                }
            }
            return GOODNUM;
        }
    }
}
