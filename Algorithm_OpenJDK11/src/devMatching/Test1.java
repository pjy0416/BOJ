package devMatching;

public class Test1 {

    private static int solution(String p, String s) {
        int answer = 0;

        for(int i=0; i<p.length(); i++) {
            if(p.charAt(i) != s.charAt(i)) {
                answer += changeCount(p.charAt(i), s.charAt(i));
            }
        }

        return answer;
    }

    private static int changeCount(char pCH, char sCH) {
        int p = Character.getNumericValue(pCH);
        int s = Character.getNumericValue(sCH);

        int left =0;
        int right =0;

        // left
        while(p !=s) {
            if(p ==0) {
                p=9;
            } else {
                p--;
            }
            left++;
        }

        p = Character.getNumericValue(pCH);
        s = Character.getNumericValue(sCH);

        // right
        while(p !=s) {
            if(p ==9) {
                p=0;
            } else {
                p++;
            }
            right++;
        }

        return Math.min(left, right);
    }

    public static void main(String[] args) {
        String p = "00000000000000000000";
        String s = "91919191919191919191";

        System.out.println(solution(p,s));
    }
}