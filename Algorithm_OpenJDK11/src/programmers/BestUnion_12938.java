package programmers;

public class BestUnion_12938 {
    private static int[] solution(int n, int s) {
        int[] answer;
        int div = s/n;
        int mod = s%n;
        if(div ==0) {   // 존재하지 않는 경우
            answer = new int[]{-1};
        } else {    // 존재하는 경우
            answer = new int[n];
            for(int i=0; i<n; i++) {
                answer[i] = div;
            }
            for(int i=1; i<=mod; i++) {
                answer[n-i]++;
            }
        }
        return answer;
    }
    public static void main(String[] args) {
        int n = 2;
        int k = 9;
        int[] result = solution(n,k);
        for(int num : result) {
            System.out.print(num + " ");
        }
    }
}
/*
https://programmers.co.kr/learn/courses/30/lessons/12938
 */