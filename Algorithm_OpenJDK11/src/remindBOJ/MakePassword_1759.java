package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class MakePassword_1759 {
    final static String[] vowelArr = {"a","e","i","o","u"};
    final static String NEWLINE = "\n";
    final static int MIN_VOWEL =1, MIN_CONSONANT=2;
    static int l,r;
    static String[] wordArr;
    static StringBuffer sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        wordArr = br.readLine().split(" ");
        Arrays.sort(wordArr);
        br.close();
        solution();
    }

    private static void solution() {
        LinkedList<String> passList = new LinkedList<>();
        sb = new StringBuffer();
        dfs(0,0,0,passList);
        System.out.println(sb.toString());
    }

    private static void dfs(int idx, int vowel, int consonant, LinkedList<String> passList) {
        if(vowel+consonant == l) {
            if(vowel >= MIN_VOWEL && consonant >= MIN_CONSONANT) {
                for (String str : passList) {
                    sb.append(str);
                }
                sb.append(NEWLINE);
            }
            return;
        }
        if(idx == r) {
            return;
        }

        for(int i=idx; i<r; i++) {
            String now = wordArr[i];
            passList.offer(now);
            if(now.equals(vowelArr[0]) || now.equals(vowelArr[1]) || now.equals(vowelArr[2]) || now.equals(vowelArr[3]) || now.equals(vowelArr[4])) {
                dfs(i+1, vowel+1, consonant, passList);
            } else {
                dfs(i+1, vowel, consonant+1, passList);
            }
            passList.pollLast();
        }
    }
}
