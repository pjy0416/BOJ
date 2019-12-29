package programmers;

import java.util.HashMap;

public class KitHashMarathon {
    public static void main(String[] args) {
        String[] participant = {"mislav", "stanko", "mislav", "ana"};
        String[] completion = {"stanko", "ana", "mislav"};

        String result = solution(participant, completion);
        System.out.println(result);
    }

    private static String solution(String[] participant, String[] completion) {
        String answer="";
        HashMap<String, Integer> partMap = saveData(participant);
        answer = checkData(completion, partMap);

        return answer;
    }

    private static HashMap<String, Integer> saveData(String[] participant) {
        HashMap<String, Integer> partMap = new HashMap<>();
        for(String name : participant) {    // Key Value로 저장
            if(partMap.containsKey(name)) {
                partMap.replace(name, partMap.get(name)+1);
            } else {
                partMap.put(name,1);
            }
        }

        return partMap;
    }

    private static String checkData(String[] completion, HashMap<String, Integer> participant) {
        String result ="";

        for(String name : completion) {
            int num = participant.get(name);
            if(num > 1) {    // 동명이인 참여자가 두 명 이상인 경우
               participant.replace(name, num-1);
            } else {
                participant.remove(name);   // 완료했으니까 지워
            }
        }

        result = participant.keySet().iterator().next();

        return result;
    }
}


/*
문제 설명
수많은 마라톤 선수들이 마라톤에 참여하였습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.

마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때,
 완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.

제한사항
마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.
completion의 길이는 participant의 길이보다 1 작습니다.
참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.
참가자 중에는 동명이인이 있을 수 있습니다.
입출력 예
participant             	                                   completion	                                    return
["leoL, "kiki", "eden"]	                                    ["eden", "kiki"]	                                leo
["marina", "josipa", "nikola", "vinko", "filipa"]	    ["josipa", "filipa", "marina", "nikola"]    	        vinko
["mislav", "stanko", "mislav", "ana"]	                ["stanko", "ana", "mislav"]	                        mislav
입출력 예 설명
예제 #1
leo는 참여자 명단에는 있지만, 완주자 명단에는 없기 때문에 완주하지 못했습니다.

예제 #2
vinko는 참여자 명단에는 있지만, 완주자 명단에는 없기 때문에 완주하지 못했습니다.

예제 #3
mislav는 참여자 명단에는 두 명이 있지만, 완주자 명단에는 한 명밖에 없기 때문에 한명은 완주하지 못했습니다.

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        return answer;
    }
}
 */