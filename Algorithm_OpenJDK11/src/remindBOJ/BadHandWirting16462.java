package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ‘0’과 ‘6’ 그리고 ‘9’가 구분이 안 될 정도로 단순하게 쓰신다.
 * ‘나조교’ 조교님은 구분이 되지않는 숫자 ‘0’, ‘6’, ‘9’를 학생들의 기쁨을 위해 ‘9’로 통일시켜 버리기로 하였다.
 * 단, 점수가 100점이 넘는 경우 100점으로 처리
 * 학생들의 평균을 구해주도록 하자.
 *
 * Input
 * 첫째 줄에 학생의 수 N이 주어진다. (1 ≤ N ≤ 1,000)
 * 다음 N개의 줄에는 ‘나교수’교수님이 적으신 학생들의 성적 정수 Qi가 차례대로 주어진다. (1 ≤ Qi ≤ 100)
 *
 * ouput
 * 첫째 줄에 ‘나조교’ 조교님이 계산한 학생들의 평균 성적과 가장 가까운 정수를 출력한다. 그런 정수가 여러 개라면 그 중 가장 큰 것을 출력
 */

// 0, 6, 9 는 9로 통일, 단 100점은 100점

public class BadHandWirting16462 {
    private static int[] scores;

    static int upScore(String scoreStr) {
        return scoreStr.length()>2 ? 100 : changedScore(scoreStr);
    }

    static int changedScore(String str) {
        return Integer.parseInt(str.replaceAll("0","9").replaceAll("6","9"));
    }

    static void getResult(int students) {
        int sum =0;
        for(int score : scores) {
            sum +=score;
        }
//        int result = sum%students>0 ? sum/students +1 : sum/students;
        int result = Math.round((float)sum/students);
        System.out.println(result);
//        System.out.println(String.format("%0.f", (float)sum/students));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int students = Integer.parseInt(br.readLine());
        scores = new int[students];

        for(int i=0; i<students; i++) {
            scores[i] = upScore(br.readLine());
        }

        getResult(students);

        br.close();
    }
}

/*
5
96
60
100
88
6
*/
