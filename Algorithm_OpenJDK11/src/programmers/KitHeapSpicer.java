package programmers;

import java.util.PriorityQueue;

public class KitHeapSpicer {

    /*
    1. 모든 큐를 탐색하면서 최소 2개의 지수를 변경하는 솔루션 메소드 필요
    2. 큐의 크기가 1개일 때의 경우도 생각
    3. 큐가 비었을 때 끝나게끔 만들어야한다.
     */

    private static int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        //Queue에 집어넣기
        for(int scov : scoville) {
            queue.offer(scov);
        }

        // scoville 지수 체크하는 로직
        while(!queue.isEmpty()) {
            int size = queue.size();
            if(size > 1) {  // Scoville 지수 로직
                for(int i=0; i<size; i++) {
                    if(queue.peek() < K) {  // 기준 K보다 작은 경우
                        queue.offer(queue.poll() + queue.poll()*2);
                        answer++;
                        break;
                    }
                    queue.poll();
                }
            } else {    // queue에 남은게 0 or 1인 경우
                if(!queue.isEmpty()) {   // 1개 남은 경우
                    if(queue.peek() < K) {
                        answer = -1;
                    }
                }
                // else를 안달아주는 이유는 어차피 queue에 담긴게 없다면 위에서 Scoville 지수를 만드는 로직을 한 번이라도 수행했기 때문.
                break;
            }

        }

        return answer;
    }

    public static void main(String[] args) {
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K =7;

        System.out.println(solution(scoville, K));
    }
}

/*
문제 설명
매운 것을 좋아하는 Leo는 모든 음식의 스코빌 지수를 K 이상으로 만들고 싶습니다.
모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 Leo는 스코빌 지수가 가장 낮은 두 개의 음식을 아래와 같이 특별한 방법으로 섞어 새로운 음식을 만듭니다.

#######################################################################################################
###### 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2) #######
#######################################################################################################

Leo는 모든 음식의 스코빌 지수가 K 이상이 될 때까지 반복하여 섞습니다.
Leo가 가진 음식의 스코빌 지수를 담은 배열 scoville과 원하는 스코빌 지수 K가 주어질 때,
모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수를 return 하도록 solution 함수를 작성해주세요.

제한 사항
scoville의 길이는 1 이상 1,000,000 이하입니다.
K는 0 이상 1,000,000,000 이하입니다.
scoville의 원소는 각각 0 이상 1,000,000 이하입니다.
모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우에는 -1을 return 합니다.

입출력 예
      scoville	                K	    return
[1, 2, 3, 9, 10, 12]	        7	       2

입출력 예 설명
스코빌 지수가 1인 음식과 2인 음식을 섞으면 음식의 스코빌 지수가 아래와 같이 됩니다.
새로운 음식의 스코빌 지수 = 1 + (2 * 2) = 5
가진 음식의 스코빌 지수 = [5, 3, 9, 10, 12]

스코빌 지수가 3인 음식과 5인 음식을 섞으면 음식의 스코빌 지수가 아래와 같이 됩니다.
새로운 음식의 스코빌 지수 = 3 + (5 * 2) = 13
가진 음식의 스코빌 지수 = [13, 9, 10, 12]

모든 음식의 스코빌 지수가 7 이상이 되었고 이때 섞은 횟수는 2회입니다.
 */
