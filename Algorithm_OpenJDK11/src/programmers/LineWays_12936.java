package programmers;

import java.util.ArrayList;

public class LineWays_12936 {
    private static int[] solution(int n, long k) {
        int[] answer= new int[n];
        long[] factorials = getFactorials(n);
        ArrayList<Integer> numList = getNumList(n);
        k--;
        for(int i=0; i<n; i++) {
            int idx = (int) (k/factorials[i]);
            k %= factorials[i];
            answer[i] = numList.get(idx);
            numList.remove(idx);
        }
        return answer;
    }
    private static long[] getFactorials(int n) {
        long[] result = new long[n];
        result[n-1] = 1;    // 마지막 idx => 0!

        for(int i = n-2; i>=0; i--) {
            result[i] = (n-i-1)*result[i+1];
        }

        return result;
    }

    private static ArrayList<Integer> getNumList(int n) {
        ArrayList<Integer> result = new ArrayList<>();

        for(int i=1; i<=n; i++) {
            result.add(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 5;
        long k =5;
        int[] result = solution(n,k);

        for(int num : result) {
            System.out.print(num + " ");
        }
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/12936#
 */