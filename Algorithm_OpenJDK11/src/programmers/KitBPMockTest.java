package programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class KitBPMockTest {
    static Queue<Integer>[] students;

    private static int[] solution(int[] answers) {
        final int persons = 3;
        int[] scores = new int[persons];
        Queue<Integer> ansQueue = new LinkedList<>();

        inItQueue(persons);

        for(int i=0; i<persons; i++) {
            for(int ans : answers) {
                int num = students[i].poll();
//                System.out.println(i + "번 째 학생 답 : "+ num + "\t 정답 : " + ans);
                if(ans == num) {
                    scores[i]++;
                }
                students[i].offer(num);
            }
        }

        int max =0;
        for(int score : scores) {
            max = max < score ? score : max;
        }

        for(int i=0; i<scores.length; i++) {
//            System.out.println((i+1)+"번 째 학생 점수 = " + scores[i]);
            if(scores[i] == max)    {
                ansQueue.offer((i+1));
            }
        }

        int size = ansQueue.size();
        int[] answer = new int[size];
        for(int i=0; i<size; i++) {
            answer[i] = ansQueue.poll();
        }
        Arrays.sort(answer);
        return answer;
    }

    private static void inItQueue(int persons) {
        students = new LinkedList[persons];
        int[][] ans = {{1,2,3,4,5},
                {2,1,2,3,2,4,2,5},
                {3,3,1,1,2,2,4,4,5,5}};

        for(int i=0; i<persons; i++) {
            students[i] = new LinkedList<>();
            for(int j=0; j<ans[i].length; j++) {
                students[i].offer(ans[i][j]);
            }
        }

    }

    public static void main(String[] args) {
//        int[] answers = {1,2,3,4,5};
        int[] answers = {1,3,2,4,2};

        int[] results = solution(answers);

        for(int num : results) {
            System.out.println(num);
        }
    }
}


/*
문제 설명
수포자는 수학을 포기한 사람의 준말입니다.
수포자 삼인방은 모의고사에 수학 문제를 전부 찍으려 합니다.
수포자는 1번 문제부터 마지막 문제까지 다음과 같이 찍습니다.

1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
12345 반복
2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
21 23 24 25 반복
3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...
33 11 22 44 55 반복

1번 문제부터 마지막 문제까지의 정답이 순서대로 들은 배열 answers가 주어졌을 때,
가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 return 하도록 solution 함수를 작성해주세요.

제한 조건
시험은 최대 10,000 문제로 구성되어있습니다.
문제의 정답은 1, 2, 3, 4, 5중 하나입니다.
가장 높은 점수를 받은 사람이 여럿일 경우, return하는 값을 오름차순 정렬해주세요.
입출력 예
answers	        return
[1,2,3,4,5]	     [1]
[1,3,2,4,2]	    [1,2,3]
입출력 예 설명
입출력 예 #1

수포자 1은 모든 문제를 맞혔습니다.
수포자 2는 모든 문제를 틀렸습니다.
수포자 3은 모든 문제를 틀렸습니다.
따라서 가장 문제를 많이 맞힌 사람은 수포자 1입니다.

입출력 예 #2

모든 사람이 2문제씩을 맞췄습니다.
*/