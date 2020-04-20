package programmers;

import java.util.ArrayList;
import java.util.HashMap;

public class KitDBFSTravelRoute {
    static ArrayList<String> routeList;
    private static String[] solution(String[][] tickets) {
        String[] answer;

        HashMap<String, ArrayList<String>> ticketMap = new HashMap<>(); // 티켓을 도시별로 정리하기 위한 HashMap
        routeList = new ArrayList<>();

        for(String[] ticket : tickets) {    //티켓 정보 저장, 0 : 출발지, 1 : 도착지
            if(ticketMap.containsKey(ticket[0])) {
                ArrayList<String> tmp = ticketMap.get(ticket[0]);
                tmp.add(ticket[1]);
                ticketMap.replace(ticket[0], tmp);
            } else {
                ArrayList<String> tmp = new ArrayList<>();
                tmp.add(ticket[1]);
                ticketMap.put(ticket[0], tmp);
            }
        }

        dfs(new ArrayList<String>(), "ICN", ticketMap, tickets.length);

        answer = new String[routeList.size()+1];    // 루트에는 출바지인 ICN을 저장 안했기 때문에 +1만큼 초기화
        answer[0] = "ICN";      // 출발지 저장

        for(int i=0; i<routeList.size(); i++) {
            answer[i+1] = routeList.get(i); // 경로 저장
        }

        return answer;
    }

    private static void dfs(ArrayList<String> route, String city, HashMap<String, ArrayList<String>> ticketMap, int cnt) {
        if(cnt ==0) {   // 끝까지 다 왔으면
            if(routeList.size() == route.size()) {  // 루트 생성이 처음인지 아닌지 판별
                if(haveToChange(route)) {       // 새로운 route가 더 빠른 알파벳이면
                    routeList.clear();          // 기존거 비워주고
                    routeList.addAll(route);    // 새로운 루트 추가
                }
            } else {
                routeList.addAll(route);        // 처음에는 비어있기 때문에 루트 추가
            }
            return;
        }

        if(ticketMap.containsKey(city)) {   // Exception 방지
            ArrayList<String> starts = ticketMap.get(city); // 현재 도시에서 갈 수 있는 티켓들 List
            int size = starts.size();
            for (int i = 0; i < size; i++) {
                String dest = starts.get(i);    // 도착지

                starts.remove(i);   // 항공권 지워주고
                ticketMap.replace(city, starts);    // HashMap 수정
                route.add(dest);                    // route에 도착지 추가
                dfs(route, dest, ticketMap, cnt - 1);   // 도착지를 출발지로 다시 탐색

                starts.add(i, dest);            // 해당 도착지로 안간 경우, 원상복구
                ticketMap.replace(city, starts);    // HashMap 원상복구
                route.remove(route.size() - 1); // route에서 제거
            }
        }
    }

    private static boolean haveToChange(ArrayList<String> route) {  // 알파벳 비교
        for(int i=0; i<route.size(); i++) {
            String origin = routeList.get(i);   // 기존에 있는 루트
            String target = route.get(i);       // 새로운 루트
            if(!origin.equals(target)) {        // 같은 도시가 아닐 때
                for (int j = 0; j < 3; j++) {
                    if (origin.charAt(j) > target.charAt(j)) {  // 새로운 루트가 더 빠른 알파벳이면
                        return true;
                    } else if (origin.charAt(j) < target.charAt(j)){    // 원래 루트가 더 빠르면
                        return false;
                    }
                }
            }
        }

        return false;   // 올 일은 없지만 루트가 다 같은 경우
    }

    public static void main(String[] args) {
//        String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
//        String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
//        String[][] tickets = {{"ICN","BOO"}, {"ICN", "COO"},
//                            {"COO", "DOO"}, {"DOO", "COO"},
//                            {"BOO", "DOO"} ,{"DOO", "BOO"},
//                            {"BOO", "ICN"}, {"COO", "BOO"}};
        String[][] tickets = {{"ICN", "COO"}, {"ICN", "BOO"}, {"COO", "ICN"}, {"BOO", "DOO"}};

        String[] result = solution(tickets);
        for(String str : result) {
            System.out.print(str + " ");
        }

        ArrayList<String> tmp = new ArrayList<>();

        for(String str : tmp) {
            System.out.println(str);
        }
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/43164
여행경로

문제 설명
주어진 항공권을 모두 이용하여 여행경로를 짜려고 합니다. 항상 ICN 공항에서 출발합니다.

항공권 정보가 담긴 2차원 배열 tickets가 매개변수로 주어질 때,
방문하는 공항 경로를 배열에 담아 return 하도록 solution 함수를 작성해주세요.

제한사항
모든 공항은 알파벳 대문자 3글자로 이루어집니다.
주어진 공항 수는 3개 이상 10,000개 이하입니다.
tickets의 각 행 [a, b]는 a 공항에서 b 공항으로 가는 항공권이 있다는 의미입니다.
주어진 항공권은 모두 사용해야 합니다.
만일 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return 합니다.
모든 도시를 방문할 수 없는 경우는 주어지지 않습니다.

입출력 예
                    tickets	                                               return
[[ICN, JFK], [HND, IAD], [JFK, HND]]	                            [ICN, JFK, HND, IAD]
[[ICN, SFO], [ICN, ATL], [SFO, ATL], [ATL, ICN], [ATL,SFO]]	[ICN, ATL, ICN, SFO, ATL, SFO]
입출력 예 설명
예제 #1

[ICN, JFK, HND, IAD] 순으로 방문할 수 있습니다.

예제 #2

[ICN, SFO, ATL, ICN, ATL, SFO] 순으로 방문할 수도 있지만 [ICN, ATL, ICN, SFO, ATL, SFO] 가 알파벳 순으로 앞섭니다.
 */