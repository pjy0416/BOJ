package programmers;

public class JumpNTeleportation_12980 {
    private static int solution(int n) {
        // 처음에 dp로 하려고했으나 시간초과
        return getMove(n);
    }

    private static int getMove(int num) {
        int result =0;
        while(num !=0) {
            if(num%2 ==0) {
                num/=2;
            } else {
                result++;
                num--;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int n = 5000;
        System.out.println(solution(n));
    }
}
/*
https://programmers.co.kr/learn/courses/30/lessons/12980
 */