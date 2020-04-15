package programmers;

public class KitDPTravel {
    private static int solution(int K, int[][] travel) {
        int[][] answer = new int[travel.length][K+1];
        int max =0;
        answer[0][travel[0][0]] = travel[0][1]; // 도보 길
        answer[0][travel[0][2]] = travel[0][3]; // 자전거 길

        for(int i=1; i<travel.length; i++) {
            for(int route=0; route<=K; route++) {
                if(answer[i-1][route] !=0) {
                    int walkRoute = route + travel[i][0];
                    int cycleRoute = route + travel[i][2];
                    //도보로 가는 경우
                    if(walkRoute <=K) {
                        answer[i][walkRoute] = Math.max(answer[i][walkRoute],answer[i-1][route] + travel[i][1]);    // 이미 저장된거랑 대소비교
                        max = Math.max(max, answer[i][walkRoute]);
                    }
                    //자전거로 가는 경우
                    if(cycleRoute <=K) {
                        answer[i][cycleRoute] = Math.max(answer[i][cycleRoute],answer[i-1][route] + travel[i][3]);
                        max = Math.max(max, answer[i][cycleRoute]);
                    }
                }
            }
        }
        return max;
    }


    public static void main(String[] args) {
//        int K = 1650;
//        int[][] travel = {{500, 200, 200, 100}, {800, 370, 300, 120}, {700, 250, 300, 90}};
        int K = 3000;
        int[][] travel = {{1000, 2000, 300, 700}, {1100, 1900, 400, 900}, {900, 1800, 400, 700}, {1200, 2300, 500, 1200}};

        System.out.println(solution(K, travel));
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/42899
서울에서 경산까지

문제 설명
서울에서 경산까지 여행을 하면서 모금 활동을 하려 합니다.
여행은 서울에서 출발해 다른 도시를 정해진 순서대로 딱 한 번 방문한 후 경산으로 도착할 예정입니다.
도시를 이동할 때에는 도보 혹은 자전거를 이용합니다.
이때 도보 이동에 걸리는 시간, 도보 이동 시 얻을 모금액, 자전거 이동에 걸리는 시간, 자전거 이동 시 얻을 모금액이 정해져 있습니다.
K시간 이내로 여행할 때 모을 수 있는 최대 모금액을 알아보려 합니다.

예를 들어 여행 루트가 다음과 같고 K = 1,650 일 때

1, 2번 구간은 도보로, 3번 구간은 자전거로 이동해 모금액을 660으로 하는 것이 가장 좋은 방법입니다. 이때, 1,600시간이 소요됩니다.

solution 함수의 매개변수로 각 도시를 이동할 때 이동 수단별로 걸리는 시간과 모금액을 담은 2차원 배열 travel과 제한시간 K가 주어집니다.
제한시간 안에 서울에서 경산까지 여행을 하며 모을 수 있는 최대 모금액을 return 하도록 solution 함수를 작성하세요.

제한사항
travel 배열의 각 행은 순서대로 [도보 이동에 걸리는 시간, 도보 이동 시 얻을 모금액, 자전거 이동에 걸리는 시간, 자전거 이동 시 얻을 모금액]입니다.
travel 배열 행의 개수는 3 이상 100 이하인 정수입니다.
travel 배열에서 시간을 나타내는 숫자(각 행의 첫 번째, 세 번째 숫자)는 10,000 이하의 자연수,
모금액을 나타내는 숫자(각 행의 두 번째, 네 번째 숫자)는 1,000,000 이하의 자연수입니다.
K는 0보다 크고 100,000보다 작거나 같은 자연수입니다.

입출력 예
  K	                                        travel	                                                              return
1650	[[500, 200, 200, 100], [800, 370, 300, 120], [700, 250, 300, 90]]	                                        660
3000	[[1000, 2000, 300, 700], [1100, 1900, 400, 900], [900, 1800, 400, 700], [1200, 2300, 500, 1200]]	        5900

입출력 예 설명
입출력 예#1
앞서 설명한 예와 같습니다.

입출력 예#2
1, 4번 구간은 도보로 이동하고 2, 3번 구간은 자전거로 이동하여 모금액을 5,900원으로 하는 것이 가장 좋은 방법입니다. 이때 걸리는 시간은 3,000시간입니다.
 */