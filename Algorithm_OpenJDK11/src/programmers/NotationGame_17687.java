package programmers;

public class NotationGame_17687 {
    static char[] DIGIT_CH = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

    public static String solution(int n, int t, int m, int p) {
        StringBuffer sb = new StringBuffer();
        StringBuffer result = new StringBuffer();
        for(int num=0; num<t*m; num++) {    // 최대 경우의 수 만큼 숫자를 돌면서
            sb.append(getNumString(num, n)); // N진법으로 변환한 수를 String에 붙여준다.
        }
        for(int turn=0; turn<t; turn++) {
            int idx = turn*m+p-1; // 튜브가 말하는 차례 idx
            result.append(sb.charAt(idx));  // 미리 만들어둔 전체 순서에서 튜브가 말할 숫자 가져온다
        }
        return result.toString();
    }

    private static String getNumString(int num, int digit) {
        StringBuffer sb = new StringBuffer();
        while(num/digit!=0) {
            int idx = num%digit;
            sb.append(DIGIT_CH[idx]);
            num /= digit;
        }
        sb.append(DIGIT_CH[num%digit]);
        sb.reverse();
        return sb.toString();
    }

    public static void main(String[] args) {
        int n =16;
        int t =16;
        int m =2;
        int p=1;
        System.out.println(solution(n,t,m,p));
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/17687
 */