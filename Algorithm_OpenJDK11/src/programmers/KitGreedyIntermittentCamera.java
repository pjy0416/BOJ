package programmers;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class KitGreedyIntermittentCamera {
    private static int solution(int[][] routes) {
        PriorityQueue<CarRoute> routeQ = new PriorityQueue<>();
        LinkedList<CarRoute> cameraList= new LinkedList<>();

        int answer = 0;

        for(int[] route : routes) {
            routeQ.offer(new CarRoute(route[0],route[1]));
        }

        while(!routeQ.isEmpty()) {
            CarRoute target = routeQ.poll();
            if(cameraList.isEmpty()) {
                cameraList.offer(target);
            } else {
                int size = cameraList.size();
                boolean isChanged = false;
                CarRoute origin;
                for(int j=0; j<size; j++) {
                    origin = cameraList.poll();
//                System.out.println("Origin : " + origin.start + ", " + origin.end);
//                System.out.println("Target : " + target.start + ", " + target.end);
                    if(containSection(origin, target.start)) { // 앞의 최저가 새로운 구간에 포함이 될 때
                        origin.start = target.start;
                        origin.end = getEnd(origin.end, target.end);
                        cameraList.offer(origin);    // 변경된 구간 다시 큐에 추가
                        isChanged = true;
//                        System.out.println("첫번째 변경 => " + origin.start + ", " + origin.end);
                        break;
                    } else if(containSection(target, origin.start)) {  // 새로운 구간의 최저가 앞의 구간에 포함될 때
                        origin.end = getEnd(origin.end, target.end);
                        cameraList.offer(origin);
                        isChanged = true;
//                        System.out.println("두번째 변경 => " + origin.start + ", " + origin.end);

                        break;
                    } else {
                        cameraList.offer(origin);
//                        System.out.println("변경 안됨");
                    }
                }
                if(!isChanged) {
                    cameraList.offer(target);
                }
            }
        }
        answer = cameraList.size();

        return answer;
    }

    private static boolean containSection(CarRoute origin, int target) {    // 겹치는 부분 찾기
        return target >= origin.start && target <= origin.end ? true : false;
    }

    private static int getEnd(int origin, int target) { // 더 좁은 구간의 End 반환
        return target <= origin ? target : origin;
    }
    public static void main(String[] args) {
//        int[][] routes = {{-20,15}, {-14,-5}, {-18,-13}, {-5,-3}};  //2
//        int[][] routes = {{-2,-1}, {1,2},{-3,0}};         // 2
//        int[][] routes = {{0,1}, {0,1}, {1,2}};    //1
//        int[][] routes = {{0,0}}; // 1
//        int[][] routes = {{0,1}, {2,3}, {4,5}, {6,7}};    //4
        int[][] routes = {{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}};    //2
//        int[][] routes = {{-20,15}, {-14,-5}, {-18,-13}, {-5,-3}};    //2
//        int[][] routes = {{-20,15}, {-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}};    //2
//        int[][] routes = {{0,12},{1,12},{2,12},{3,12},{5,6},{6,12},{10,12}};    //

        System.out.println(solution(routes));
    }
}

class CarRoute implements Comparable<CarRoute> {
    int start;
    int end;
    private int total;

    public CarRoute(int start, int end) {
        this.start = start;
        this.end = end;
        this.total = Math.abs(end-start);
    }

    @Override
    public int compareTo(CarRoute o) {
        return this.total > o.total ? 1 : -1;
    }
}
/*
https://programmers.co.kr/learn/courses/30/lessons/42884

단속카메라
문제 설명
고속도로를 이동하는 모든 차량이 고속도로를 이용하면서 단속용 카메라를 한 번은 만나도록 카메라를 설치하려고 합니다.

고속도로를 이동하는 차량의 경로 routes가 매개변수로 주어질 때,
모든 차량이 한 번은 단속용 카메라를 만나도록 하려면
최소 몇 대의 카메라를 설치해야 하는지를 return 하도록 solution 함수를 완성하세요.

제한사항

차량의 대수는 1대 이상 10,000대 이하입니다.
routes에는 차량의 이동 경로가 포함되어 있으며 routes[i][0]에는 i번째 차량이 고속도로에 진입한 지점,
routes[i][1]에는 i번째 차량이 고속도로에서 나간 지점이 적혀 있습니다.
차량의 진입/진출 지점에 카메라가 설치되어 있어도 카메라를 만난것으로 간주합니다.
차량의 진입 지점, 진출 지점은 -30,000 이상 30,000 이하입니다.

입출력 예

                routes	                         return
[[-20,15], [-14,-5], [-18,-13], [-5,-3]]	        2

입출력 예 설명

-5 지점에 카메라를 설치하면 두 번째, 네 번째 차량이 카메라를 만납니다.

-15 지점에 카메라를 설치하면 첫 번째, 세 번째 차량이 카메라를 만납니다.
 */
