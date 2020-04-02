package programmers;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class KitGreedyIntermittentCamera {  // 효율성 1,3 틀림
    private static int solution(int[][] routes) {
        PriorityQueue<CarRoute> routeQ = new PriorityQueue<>();
        ArrayList<CarRoute> cameraList = new ArrayList<>();

        for(int[] route : routes) {
            routeQ.offer(new CarRoute(route[0], route[1]));
        }

        cameraList.add(routeQ.poll());

        while(!routeQ.isEmpty()) {
            CarRoute target = routeQ.poll();
//            System.out.println(route.start + " , " + route.end);
            boolean isChanged = false;
            for(int i=0; i<cameraList.size(); i++) {
                CarRoute origin = cameraList.get(i);
                if(isOverlap(origin, target)) {
                    origin.start = target.start;
                    origin.end = Math.min(origin.end, target.end);
//                    System.out.println("겹쳐서 변경");
                    cameraList.set(i, origin);
                    isChanged = true;
                    break;
                }
            }
            if(!isChanged) {
                cameraList.add(target);
            }
        }

        /*for(CarRoute camera : cameraList) {
            System.out.println(camera.start + " , " + camera.end);
        }*/

        return cameraList.size();
    }

    private static boolean isOverlap(CarRoute origin, CarRoute target) {
        return target.start <= origin.end ? true : false;
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

    public CarRoute(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(CarRoute o) {
        return this.start > o.start ? 1 : -1;
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
