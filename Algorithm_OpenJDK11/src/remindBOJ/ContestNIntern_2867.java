package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ContestNIntern_2867 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();

        int female = Integer.parseInt(st.nextToken());
        int male = Integer.parseInt(st.nextToken());
        int intern = Integer.parseInt(st.nextToken());

        solution(female, male, intern);
    }

    private static void solution(int female, int male, int intern) {
        final int DIV = 2;
        while(intern !=0) {
            if(female/DIV < male) { male--; }
            else { female--; }
            intern--;
        }
        System.out.println(Math.min(female/DIV, male));
    }
}
