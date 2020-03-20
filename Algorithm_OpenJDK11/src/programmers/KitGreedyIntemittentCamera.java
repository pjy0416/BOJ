package programmers;


import java.util.LinkedList;

public class KitGreedyIntemittentCamera {
    private static int solution(int[][] routes) {
        LinkedList<int[]> routeList= new LinkedList<>();

        int answer = 0;
        int[] first = {routes[0][0],routes[0][1]};
        routeList.offerLast(first);

        for(int i=1; i<routes.length; i++) {
            int[] target = {routes[i][0], routes[i][1]};

            int size = routeList.size();
            int[] origin;
            for(int j=0; j<size; j++) {
                origin = routeList.poll();
                System.out.println("Origin : " + origin[0] + ", " + origin[1]);
                System.out.println("Target : " + target[0] + ", " + target[1]);
                if(containSection(origin, target[0])) { // 앞의 최저가 새로운 구간에 포함이 될 때
                    origin[0] = target[0];
                    origin[1] = target[1] <= origin[1] ? target[1] : origin[1];
                    routeList.offer(origin);    // 변경된 구간 다시 큐에 추가

                    break;
                } else if(containSection(target, origin[0])) {  // 새로운 구간의 최저가 앞의 구간에 포함될 때
                    origin[1] = target[1] <= origin[1] ? target[1] : origin[1];
                    routeList.offer(origin);

                    break;
                } else {
                    routeList.offer(origin);
                    routeList.offer(target);
                    break;
                }
            }
        }

        /*while(!routeList.isEmpty()) {
            int[] tmp = routeList.poll();
            System.out.println("Left : " + tmp[0] + ", Right : " + tmp[1]);
        }*/
        answer = routeList.size();

        return answer;
    }

    private static boolean containSection(int[] origin, int target) {
        return target >= origin[0] && target <= origin[1] ? true : false;
    }
    public static void main(String[] args) {
//        int[][] routes = {{-20,15}, {-14,-5}, {-18,-13}, {-5,-3}};  //2
//        int[][] routes = {{-2,-1}, {1,2},{-3,0}};         // 2
//        int[][] routes = {{0,1}, {0,1}, {1,2}};    //1
//        int[][] routes = {{0,0}}; // 1
//        int[][] routes = {{0,1}, {2,3}, {4,5}, {6,7}};    //4
//        int[][] routes = {{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}};    //2
//        int[][] routes = {{-20,15}, {-14,-5}, {-18,-13}, {-5,-3}};    //2
//        int[][] routes = {{-20,15}, {-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}};    //2
        int[][] routes = {{0,12},{1,12},{2,12},{3,12},{5,6},{6,12},{10,12}};    //

        System.out.println(solution(routes));
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
