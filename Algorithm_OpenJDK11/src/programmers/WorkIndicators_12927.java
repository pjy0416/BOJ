package programmers;

import java.util.Collections;
import java.util.PriorityQueue;

public class WorkIndicators_12927 {
    private static long solution(int n, int[] works) {
        PriorityQueue<Integer> workQ = new PriorityQueue<>(Collections.reverseOrder());
        long answer = 0;

        for(int work : works) {
            workQ.offer(work);
        }

        while(n !=0) {
            int tmp = workQ.poll();
            if(tmp ==0) {
                break;
            }
            workQ.offer(--tmp);
            n--;
        }
        for(int num : workQ) {
            answer += Math.pow(num,2);
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 12;
        int[] works = {4, 3, 3};
        System.out.println(solution(n,works));
    }
}
/*
https://programmers.co.kr/learn/courses/30/lessons/12927
 */