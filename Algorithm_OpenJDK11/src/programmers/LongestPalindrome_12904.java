package programmers;

public class LongestPalindrome_12904 {

    private static int solution(String s) {
        return search(s);
    }

    private static int search(String s) {
        int answer =0;

        for(int i=s.length(); i>0; i--) {
            if(isPalindrome(s, i)) {
                answer = i;
                break;
            }
        }

        return answer;
    }

    private static boolean isPalindrome(String s, int len) {
        boolean result = true;
        for(int i=0; i<=s.length()-len; i++) {
            int left = i;
            int right = (len-1+i);
            result = true;
            for(int steps =0; steps<(len)/2; steps++) {
                if(s.charAt(left++) != s.charAt(right--)) {
                    result = false;
                    break;
                }
            }
            if(result) {
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "abcdcba";
        System.out.println(solution(s));
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/12904
 */