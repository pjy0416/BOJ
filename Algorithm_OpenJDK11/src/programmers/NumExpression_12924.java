package programmers;

public class NumExpression_12924 {
    private static int solution(int n) {
        int answer =0;

        for(int i=1; i<=n; i++) {
            int sum =0;
            for(int j=i; j<=n; j++) {
                sum+=j;
                if(sum >= n) {
                    if(sum==n) {
                        answer++;
                    }
                    break;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 15;
        System.out.println(solution(n));
    }
}
