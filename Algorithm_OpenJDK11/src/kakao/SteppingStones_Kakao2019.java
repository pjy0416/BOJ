package kakao;

public class SteppingStones_Kakao2019 {
    private static int solution(int[] stones, int k) {
        return binarySearch(stones, k);
    }

    private static int binarySearch(int[] stones, int k) {
        int left = 200000000;   // stones 최대값을 넣어줌 (다 건너는 상황)
        int right =0;           // 최소값을 넣어줌 (못건너는 상황)
        int answer =0;

        for(int num : stones) {
            left = Math.min(num,left);  // min 값
            right = Math.max(num,right);    // max 값
        }

        while(left <= right) {
            int mid = (left+right)/2;
            if(isPass(stones, mid, k)) {
                left = mid+1;
                answer = mid;
            } else {
                right = mid-1;
            }
        }
        return answer;
    }

    private static boolean isPass(int[] stones, int mid, int k) {
        int prev =-1;   // 처음 땅에서 시작 ~> 돌이 0 IDX 부터 시작하기 떄문
        for(int i=0; i<stones.length; i++) {
            if(i-prev >k) {  // 못건너는 상황에서 k보다 커지면
                return false;
            }
            if(stones[i] >= mid) { // 건널 수 있음
                prev = i;   // 건넘 표시
            }
        }
        return stones.length-prev <= k ? true : false;  // 마지막 돌로 부터 다음 땅까지 건널 수 있는지 검증
    }


    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};

        int k =12;

        System.out.println(solution(stones, k));
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/64062

징검다리 건너기
문제 설명
[본 문제는 정확성과 효율성 테스트 각각 점수가 있는 문제입니다.]

카카오 초등학교의 니니즈 친구들이 라이언 선생님과 함께 가을 소풍을 가는 중에 징검다리가 있는 개울을 만나서 건너편으로 건너려고 합니다.
라이언 선생님은 니니즈 친구들이 무사히 징검다리를 건널 수 있도록 다음과 같이 규칙을 만들었습니다.

징검다리는 일렬로 놓여 있고 각 징검다리의 디딤돌에는 모두 숫자가 적혀 있으며 디딤돌의 숫자는 한 번 밟을 때마다 1씩 줄어듭니다.
디딤돌의 숫자가 0이 되면 더 이상 밟을 수 없으며 이때는 그 다음 디딤돌로 한번에 여러 칸을 건너 뛸 수 있습니다.
단, 다음으로 밟을 수 있는 디딤돌이 여러 개인 경우 무조건 가장 가까운 디딤돌로만 건너뛸 수 있습니다.
니니즈 친구들은 개울의 왼쪽에 있으며, 개울의 오른쪽 건너편에 도착해야 징검다리를 건넌 것으로 인정합니다.
니니즈 친구들은 한 번에 한 명씩 징검다리를 건너야 하며, 한 친구가 징검다리를 모두 건넌 후에 그 다음 친구가 건너기 시작합니다.

디딤돌에 적힌 숫자가 순서대로 담긴 배열 stones와 한 번에 건너뛸 수 있는 디딤돌의 최대 칸수 k가 매개변수로 주어질 때,
최대 몇 명까지 징검다리를 건널 수 있는지 return 하도록 solution 함수를 완성해주세요.

[제한사항]
징검다리를 건너야 하는 니니즈 친구들의 수는 무제한 이라고 간주합니다.
stones 배열의 크기는 1 이상 200,000 이하입니다.
stones 배열 각 원소들의 값은 1 이상 200,000,000 이하인 자연수입니다.
k는 1 이상 stones의 길이 이하인 자연수입니다.

[입출력 예]
            stones	                k	    result
[2, 4, 5, 3, 2, 1, 4, 2, 5, 1]	    3   	   3

입출력 예에 대한 설명
입출력 예 #1

따라서 최대 3명이 디딤돌을 모두 건널 수 있습니다.
 */
