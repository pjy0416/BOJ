package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BlackJack_2798 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] cardArr = new int[Integer.parseInt(st.nextToken())];
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<cardArr.length; i++) {
            cardArr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(cardArr, m));

        br.close();
    }

    private static int solution(int[] cardArr, int m) {
        int answer =0;
        for(int i=0; i<cardArr.length-2; i++) {
            for(int j=i+1; j<cardArr.length-1; j++) {
                for(int k=j+1; k<cardArr.length; k++) {
                    int sum =cardArr[i] + cardArr[j] + cardArr[k];
                    if(sum <= m) {
                        answer = Math.max(answer, sum);
                    }
                }
            }
        }

        return answer;
    }
}