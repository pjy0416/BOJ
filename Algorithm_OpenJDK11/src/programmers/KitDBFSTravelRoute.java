package programmers;

import java.util.ArrayList;
import java.util.HashMap;

public class KitDBFSTravelRoute {
    static ArrayList<String> routeList;
    private static String[] solution(String[][] tickets) {
        String[] answer;

        HashMap<String, ArrayList<String>> ticketMap = new HashMap<>();
        routeList = new ArrayList<>();

        for(String[] ticket : tickets) {
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

        answer = new String[routeList.size()+1];
        answer[0] = "ICN";

        for(int i=0; i<routeList.size(); i++) {
            answer[i+1] = routeList.get(i);
        }

        return answer;
    }

    private static void dfs(ArrayList<String> route, String city, HashMap<String, ArrayList<String>> ticketMap, int cnt) {
        if(cnt ==0) {
            if(routeList.size() == route.size()) {  // 비교
                if(haveToChange(route)) {
                    routeList.clear();
                    routeList.addAll(route);
                }
            } else {
                routeList.addAll(route);
            }
            return;
        }

        if(ticketMap.containsKey(city)) {
            ArrayList<String> starts = ticketMap.get(city);
            int size = starts.size();
            for (int i = 0; i < size; i++) {
                String dest = starts.get(i);

                starts.remove(i);
                ticketMap.replace(city, starts);
                route.add(dest);
                dfs(route, dest, ticketMap, cnt - 1);

                starts.add(i, dest);
                ticketMap.replace(city, starts);
                route.remove(route.size() - 1);
            }
        }
    }

    private static boolean haveToChange(ArrayList<String> route) {
        for(int i=0; i<route.size(); i++) {
            String origin = routeList.get(i);
            String target = route.get(i);
            if(!origin.equals(target)) {
                for (int j = 0; j < 3; j++) {
                    if (origin.charAt(j) > target.charAt(j)) {
                        return true;
                    } else if (origin.charAt(j) < target.charAt(j)){
                        return false;
                    }
                }
            }
        }

        return false;
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