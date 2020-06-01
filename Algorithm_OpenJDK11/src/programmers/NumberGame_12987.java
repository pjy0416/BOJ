package programmers;

import java.util.Arrays;

public class NumberGame_12987 {
    private static int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int answer = 0;
        int idx=0;
        for(int a : A) {
            for(int i=idx; i<B.length; i++) {
                if(a<B[i]) {
                    idx =i+1;
                    answer++;
                    break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] A = {5,1,3,7};
        int[] B = {2,2,6,8};
        System.out.println(solution(A,B));
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/12987
 */