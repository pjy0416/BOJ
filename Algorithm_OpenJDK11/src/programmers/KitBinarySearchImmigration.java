package programmers;

import java.util.Arrays;

public class KitBinarySearchImmigration {
    private static long solution(int n, int[] times) {
        Arrays.sort(times);

        return binarySearch(n, times, times[times.length-1]);
    }
    private static long binarySearch(int n, int[] times, long maxTime) {
        // 시간 범위
        long left =1;   // 1분 부터
        long right = maxTime*(long)n; // 최대 걸리는 시간 (가장 느린 심사관)

        while(left < right) {
            long mid = (left+right)/2;  // 걸리는 시간 범위의 중간값을 찾아주고
            long people = getPeople(times, mid);    // 이 시간이 몇명의 사람을 수용하는지 받아와준다.
            if(people < n) {    // 심사한 사람의 수가 n보다 작을 때
                left = mid+1;   // 시간을 더 잡아주기 위해서 left를 mid+1로 설정
            } else {            // 시간이 여유로워서 사람을 n보다 더 많이 심사했으면
                right = mid;    // 최대 시간 범위를 mid로 줄여준다.
            }
        }
        return left;
    }

    private static long getPeople(int[] times, long mid) {    // 모든 창구에서 걸리는 최대 시간 리턴
        long result =0;
        for(int time : times) {
            result += mid/time;
        }
        return result;
    }

    public static void main(String[] args) {
//        int[] times = {7, 10};
//        int[] times = {8, 10, 3};
//        int[] times = {1, 10};
        int[] times = {1, 10, 3 ,20, 7, 6};
//        int[] times = {1000000000, 1000000000, 1000000000};
        int n =100;

        System.out.println(solution(n,times));
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/43238
입국심사

문제 설명
n명이 입국심사를 위해 줄을 서서 기다리고 있습니다. 각 입국심사대에 있는 심사관마다 심사하는데 걸리는 시간은 다릅니다.
처음에 모든 심사대는 비어있습니다. 한 심사대에서는 동시에 한 명만 심사를 할 수 있습니다.
가장 앞에 서 있는 사람은 비어 있는 심사대로 가서 심사를 받을 수 있습니다.
하지만 더 빨리 끝나는 심사대가 있으면 기다렸다가 그곳으로 가서 심사를 받을 수도 있습니다.

모든 사람이 심사를 받는데 걸리는 시간을 최소로 하고 싶습니다.
입국심사를 기다리는 사람 수 n, 각 심사관이 한 명을 심사하는데 걸리는 시간이 담긴 배열 times가 매개변수로 주어질 때,
모든 사람이 심사를 받는데 걸리는 시간의 최솟값을 return 하도록 solution 함수를 작성해주세요.

제한사항
입국심사를 기다리는 사람은 1명 이상 1,000,000,000명 이하입니다.
각 심사관이 한 명을 심사하는데 걸리는 시간은 1분 이상 1,000,000,000분 이하입니다.
심사관은 1명 이상 100,000명 이하입니다.

입출력 예
n	          times	        return
6	         [7, 10]	      28

입출력 예 설명
가장 첫 두 사람은 바로 심사를 받으러 갑니다.

7분이 되었을 때, 첫 번째 심사대가 비고 3번째 사람이 심사를 받습니다.

10분이 되었을 때, 두 번째 심사대가 비고 4번째 사람이 심사를 받습니다.

14분이 되었을 때, 첫 번째 심사대가 비고 5번째 사람이 심사를 받습니다.

20분이 되었을 때, 두 번째 심사대가 비지만 6번째 사람이 그곳에서 심사를 받지 않고 1분을 더 기다린 후에 첫 번째 심사대에서 심사를 받으면 28분에 모든 사람의 심사가 끝납니다.

출처

※ 공지 - 2019년 9월 4일 문제에 새로운 테스트 케이스를 추가하였습니다. 도움을 주신 weaver9651 님께 감사드립니다.
 */