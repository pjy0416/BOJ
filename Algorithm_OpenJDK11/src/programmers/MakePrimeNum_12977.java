package programmers;

public class MakePrimeNum_12977 {
    static int answer =0;
    private static int solution(int[] nums) {
        permutation(new int[nums.length+1], nums, 0, 0, 0);
        return answer;
    }

    private static void permutation(int[] visit, int[] num, int idx, int cnt, int sum) {
        if(cnt == 3) {
            if(isPrime(sum)) {
                answer++;
            }
            return;
        }

        for(int i = idx; i<num.length; i++) {
            if(visit[i] ==0) {
                visit[i] =1;
                permutation(visit, num, i+1, cnt+1, sum+num[i]);
                visit[i] =0;
            }
        }
    }
    private static boolean isPrime(int num) {
        for(int i=2; i<num; i++) {
            if(num%i ==0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,7,6,4};
        System.out.println(solution(nums));

    }
}
/*
https://programmers.co.kr/learn/courses/30/lessons/12977
 */