package programmers;

public class Tournament_12985 {
    private static int solution(int n, int a, int b) {
        int result =0;
        int left = Math.min(a,b);   //25번이 안돼서 추가
        int right = Math.max(a,b);

        int[] exponentArr = getExponentArr();
        int player1 = getExponent(left,exponentArr);
        int player2 = getExponent(right,exponentArr);
        if(left%2 ==1 && right-left ==1) {  // 1번, 25번
            return 1;
        }
        while(player1 == player2) {
            left-= exponentArr[player1-1];
            right-= exponentArr[player2-1];
            player1 = getExponent(left,exponentArr);
            player2 = getExponent(right,exponentArr);
        }
        result = Math.max(player1, player2);
        return result;
    }

    private static int[] getExponentArr() {
        int[] result = new int[21];
        for(int i=1; i<=20; i++) {
            result[i] = (int)Math.pow(2,i);
        }
        return result;
    }

    private static int getExponent(int num, int[] arr) {
        int result =1;
        for(int i=1; i<=20; i++) {
            if(num <=arr[i]) {
                result = i;
                break;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int n = 8;
        int a = 4;
        int b = 7;
        System.out.println(solution(n,a,b));
    }
}
/*
https://programmers.co.kr/learn/courses/30/lessons/12985#
 */