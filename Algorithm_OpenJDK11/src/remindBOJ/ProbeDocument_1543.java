package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProbeDocument_1543 {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        String document = br.readLine();
        String keyword = br.readLine();

        br.close();
        solution(document, keyword);
    }

    private static void solution(String document, String keyword) {
        int answer =0;
        int cuttingLen = keyword.length(), maxLen = document.length();

        for(int i=0; i<maxLen; i++) {
            if(i+cuttingLen > maxLen) {
                break;
            }
            String target = document.substring(i, i+cuttingLen);
            if(target.equals(keyword)) {
                answer++;
                i += cuttingLen-1;
            }
        }

        System.out.println(answer);
    }
}
/*
abab12
ab12ab
12abab
 */
