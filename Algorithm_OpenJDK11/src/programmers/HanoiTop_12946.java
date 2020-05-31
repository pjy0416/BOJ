package programmers;

public class HanoiTop_12946 {
    static int[][] result;
    static int idx =0;

    private static int[][] solution(int n) {
        int size = (int)Math.pow(2,n)-1;
        result = new int[size][2];
        hanoi(n,1,3,2);

        return result;
    }

    private static void hanoi(int n, int start, int dest, int stopOver) {
        if(n ==0) {
            return;
        }
        // 1번 기둥의 n개 원반에서 n-1개 를 2번 기둥으로 옮김(3번 기둥 경유)
        hanoi(n-1,start,stopOver,dest);
        result[idx][0] = start;
        result[idx++][1] = dest;

        //2번 기둥의 n-1개의 원반을 3번 기둥으로 옮김(1번 기둥 경유)
        hanoi(n-1,stopOver,dest,start);
    }
    public static void main(String[] args) {
        int[][] result = solution(2);
        for(int[] num : result) {
            System.out.println(num[0] + " " + num[1]);
        }
    }
}
/*
https://programmers.co.kr/learn/courses/30/lessons/12946#
 */
