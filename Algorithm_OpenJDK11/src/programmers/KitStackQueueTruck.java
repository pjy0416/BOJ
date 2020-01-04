package programmers;

import java.util.*;

public class KitStackQueueTruck {
    public static void main(String[] args) {
        int bridge_length =100;
        int weight =100;
        int[] truck_weights = {10,10,10,10,10,10,10,10,10,10};

        solution(bridge_length,weight,truck_weights);
    }
    private static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        Queue<int[]> acrossing = new LinkedList<>();    // 0인덱스에 트럭, 1에 시간 저장
        Queue<Integer> acrossed = new LinkedList<>();

        int idx =0;     // truck

        // 다 건널때 까지 looping, 전체를 감싸는 while이 시간초가 될 것
        while(acrossed.size() != truck_weights.length) {    // 다 건넜을 경우
            answer++;

//            System.out.println("###################" + answer + "분 째 일어나는 일들입니다. ###################");
            // 건너갈만큼 시간이 된 트럭이 있는지 확인
            if(!acrossing.isEmpty()) {  // 큐 empty 확인
                // 여기에 들어갈만한 조건 생각하기 (건너고있는 모든 차 확인하는 loop)
                int[] tmp = ((LinkedList<int[]>) acrossing).peek(); // 확인
                if(tmp[1] >= bridge_length) {   // 다리를 다 지났는지 확인
//                    System.out.println( tmp[0] + "폴");
                    acrossed.add(acrossing.poll()[0]);   // 다리 위에서 제거하는 동시에 다리 위를 건넌 목록에 추가하기
                }

//                System.out.print("다리를 건너는 트럭 : ");
                // 건너고 있는 트럭의 시간 더 해주기
                for(int i=0; i<acrossing.size(); i++) {
                    int[] tm = ((LinkedList<int[]>) acrossing).get(i);
                    tm[1]++;
                    ((LinkedList<int[]>) acrossing).set(i, tm);

//                    System.out.print(tm[0] + " ");
                }
//                System.out.println();
            }

            // 다리 무게를 충분히 견딜 수 있는지 확인하고 추가하는 작업
            int totalWeight = 0;
            for(int i=0; i<acrossing.size(); i++) {
                totalWeight += ((LinkedList<int[]>) acrossing).get(i)[0];
            }

            if (totalWeight < weight && idx != truck_weights.length) {   // 여기도 조건 생각하기
                int[] tmp = {0,1};
                tmp[0] = truck_weights[idx];
                acrossing.add(tmp);
                totalWeight += truck_weights[idx++];

                if(totalWeight > weight) {
                    idx--;
                    ((LinkedList<int[]>) acrossing).pollLast();
                } /* else {
                    System.out.print(tmp[0]);
                } */

            }
//            System.out.println();
//            System.out.print("다리를 지난 트럭 : ");
            /*
            for(int i=0; i<acrossed.size(); i++) {
                System.out.print(((LinkedList<Integer>) acrossed).get(i) + " ");
            }
            System.out.println();
            */
        }
//        System.out.println(answer);
        return answer;
    }
}

/*
문제 설명
트럭 여러 대가 강을 가로지르는 일 차선 다리를 정해진 순으로 건너려 합니다.
모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다.
트럭은 1초에 1만큼 움직이며, 다리 길이는 bridge_length이고 다리는 무게 weight까지 견딥니다.
※ 트럭이 다리에 완전히 오르지 않은 경우, 이 트럭의 무게는 고려하지 않습니다.

예를 들어, 길이가 2이고 10kg 무게를 견디는 다리가 있습니다.
무게가 [7, 4, 5, 6]kg인 트럭이 순서대로 최단 시간 안에 다리를 건너려면 다음과 같이 건너야 합니다.

경과 시간	    다리를 지난 트럭	    다리를 건너는 트럭	        대기 트럭
   0             []	                   []	            [7,4,5,6]
  1~2	         []	                   [7]	            [4,5,6]
   3	         [7]	               [4]	            [5,6]
   4	         [7]	               [4,5]	        [6]
   5	        [7,4]	               [5]	            [6]
   6~7	        [7,4,5]	               [6]	            []
   8	        [7,4,5,6]	           []	            []
따라서, 모든 트럭이 다리를 지나려면 최소 8초가 걸립니다.

solution 함수의 매개변수로 다리 길이 bridge_length,
다리가 견딜 수 있는 무게 weight,
트럭별 무게 truck_weights가 주어집니다.
이때 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 return 하도록 solution 함수를 완성하세요.

제한 조건
bridge_length는 1 이상 10,000 이하입니다.
weight는 1 이상 10,000 이하입니다.
truck_weights의 길이는 1 이상 10,000 이하입니다.
모든 트럭의 무게는 1 이상 weight 이하입니다.

입출력 예
bridge_length	                weight	                truck_weights	                return
    2	                         10                     	[7,4,5,6]	                   8
    100	                         100                        [10]	                      101
    100	                         100	           [10,10,10,10,10,10,10,10,10,10]	       110
*/
