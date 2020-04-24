package programmers;

import java.util.Arrays;

public class KitBinarySearchSteppingStones {
    private static int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        return binarySearch(distance,rocks,n);
    }

    private static int binarySearch(int distance, int[] rocks, int n) {
        long answer =0;
        long left = 1;
        long right = distance;

        while(left < right) {
            long mid = (left+right) /2;     // 최솟값 x (가정 return 값)
            int prev =0;    // 이전 돌을 저장할 변수, 처음 시작은 주어진 시작점 0
            int cnt =0;     // 제거할 돌의 갯수를 count할 변수

            for(int i=0; i<rocks.length; i++) {
                if(rocks[i] - prev < mid) {     // 돌 사이의 거리가 최솟값인 mid보다 작으니까
                    cnt++;                      // 돌 제거
                } else {                        // 크거나 같은 돌이니까 남은 돌에 있을 녀석임
                    prev = rocks[i];            // 이전 돌을 현재 돌로 change
                }
            }

            // 도착점 distance 와 거리도 재야함
            if(distance- prev <mid) {           // 마지막 돌에서 distance까지가 mid보다 작으면 카운트
                cnt++;
            }

            if(cnt <=n) {   // 주어진 n 이하로 제거해서 최소값 만들 수 있음
                answer = answer > mid ? answer : mid;   // 최솟값 중 최댓값 입력
                left = mid+1;   // 최소 거리 범위 조정
            } else {        // 못만드는 경우
                right = mid;    // 최대 거리 범위 조정
            }
        }

        return (int)answer;
    }


    public static void main(String[] args) {
        int distance = 25;
        int[] rocks = {2, 14, 11, 21, 17};
        int n =2;

        System.out.println(solution(distance,rocks,n));
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/43236

징검다리
문제 설명
출발지점부터 distance만큼 떨어진 곳에 도착지점이 있습니다.
그리고 그사이에는 바위들이 놓여있습니다. 바위 중 몇 개를 제거하려고 합니다.
예를 들어, 도착지점이 25만큼 떨어져 있고, 바위가 [2, 14, 11, 21, 17] 지점에 놓여있을 때
바위 2개를 제거하면 출발지점, 도착지점, 바위 간의 거리가 아래와 같습니다.

제거한 바위의 위치	    각 바위 사이의 거리	    거리의 최솟값
   [21, 17]	         [2, 9, 3, 11]	        2
   [2, 21]	         [11, 3, 3, 8]	        3
   [2, 11]	         [14, 3, 4, 4]	        3
   [11, 21]	         [2, 12, 3, 8]	        2
   [2, 14]	         [11, 6, 4, 4]	        4

위에서 구한 거리의 최솟값 중에 가장 큰 값은 4입니다.

출발지점부터 도착지점까지의 거리 distance, 바위들이 있는 위치를 담은 배열 rocks, 제거할 바위의 수 n이 매개변수로 주어질 때,
바위를 n개 제거한 뒤 각 지점 사이의 거리의 최솟값 중에 가장 큰 값을 return 하도록 solution 함수를 작성해주세요.

제한사항
도착지점까지의 거리 distance는 1 이상 1,000,000,000 이하입니다.
바위는 1개 이상 50,000개 이하가 있습니다.
n 은 1 이상 바위의 개수 이하입니다.

입출력 예
distance	        rocks	        n	    return
   25	    [2, 14, 11, 21, 17]	    2	      4

입출력 예 설명
문제에 나온 예와 같습니다.
 */
