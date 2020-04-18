package devMatching;

public class Test3 {
    static int min;
    private static int solution(int[] numbers, int K) {
        int answer = -1;
        min = numbers.length * numbers.length;

        if(isComplete(numbers, K)) {
            return 0;
        }

        dfs(numbers, 0, 1, K, 0);

        if(min != numbers.length * numbers.length) {
            answer = min;
        }

        return answer;
    }

    private static void dfs(int[] numbers, int start, int swapIdx, int K, int cnt) {
        for(int i=start; i<numbers.length-1; i++) {
            if(isComplete(numbers, K)) {
                min = Math.min(min, cnt);
                return;
            }
            if(swapIdx > numbers.length-1) {
                return;
            } else {
                for(int j=swapIdx; j<numbers.length; j++) {
                    if (i != j) {
                        int tmp = numbers[i];
                        numbers[i] = numbers[j];
                        numbers[j] = tmp;
                        dfs(numbers, start, j + 1, K, cnt + 1);
                        numbers[j] = numbers[i];
                        numbers[i] = tmp;
                    }
                }
            }
        }
    }

    private static boolean isComplete(int[] numbers, int K) {
        for(int i=1; i<numbers.length-1; i++) {
            int left = numbers[i-1];
            int right = numbers[i+1];

            if(Math.abs(left-numbers[i]) > K || Math.abs(right-numbers[i]) > K) {
                return false;
            }
        }
        return true;
    }



    public static void main(String[] args) {
//        int[] numbers = {3, 7, 2, 8, 6, 4, 5, 1};
//        int K = 3;
//        int[] numbers = {10, 40, 30, 20};
//        int K = 20;
        int[] numbers = {10, 40, 30, 20, 50, 75, 55};
        int K = 20;

        System.out.println(solution(numbers, K));
    }
}