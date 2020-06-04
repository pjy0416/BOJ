package programmers;

import java.util.ArrayList;
import java.util.HashMap;

public class SkillTree_49993 {
    static HashMap<Character,Integer> skillMap;
    private static int solution(String skill, String[] skill_trees) {
        skillMap = new HashMap<>();
        ArrayList<Character> skillOrder = getOrderList(skill);

        return getCount(skillOrder, skill_trees);
    }

    private static int getCount(ArrayList<Character> skillOrder, String[] skill_trees) {
        int result =skill_trees.length;
        ArrayList<Character> checkList = new ArrayList<>();

        for(String str : skill_trees) {
            for(int i=0; i<str.length(); i++) {
                char skill = str.charAt(i);
                if(skillOrder.contains(skill)) {
                    int order = skillMap.get(skill);
                    if(order != 0) {
                        if(!checkList.contains(skillOrder.get(order-1))) {
                            result--;
                            break;
                        }
                        checkList.add(skill);
                    } else {
                        checkList.add(skill);
                    }
                }
            }
            checkList.clear();
        }
        return result;
    }

    private static ArrayList<Character> getOrderList(String skill) {
        ArrayList<Character> result = new ArrayList<>();
        for(int i=0; i<skill.length(); i++) {
            result.add(skill.charAt(i));
            skillMap.put(skill.charAt(i), i);
        }
        return result;
    }
    /*
    private static int solution(String skill, String[] skill_trees) {
        int answer = skill_trees.length;
        HashMap<Character,Integer> skillMap = new HashMap<>();
        for(int i=0; i<skill.length(); i++) {
            skillMap.put(skill.charAt(i), (i+1));
        }
        for(String str : skill_trees) {
            int idx =0;
            for(int i=0; i<str.length(); i++) {
                char ch = str.charAt(i);
                if(skillMap.containsKey(ch)) { // 스킬 순서가 필요한 경우
                    if(skillMap.get(ch) != idx+1) { // 순서에 안맞는 경우
                        answer--;
                        break;
                    }
                    idx++;
                }
            }
        }
        return answer;
    }
     */

    public static void main(String[] args) {
        String skill = "CBD";
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};

        System.out.println(solution(skill,skill_trees));
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/49993
스킬트리
문제 설명
선행 스킬이란 어떤 스킬을 배우기 전에 먼저 배워야 하는 스킬을 뜻합니다.

예를 들어 선행 스킬 순서가 스파크 → 라이트닝 볼트 → 썬더일때,
썬더를 배우려면 먼저 라이트닝 볼트를 배워야 하고,
라이트닝 볼트를 배우려면 먼저 스파크를 배워야 합니다.

위 순서에 없는 다른 스킬(힐링 등)은 순서에 상관없이 배울 수 있습니다.
따라서 스파크 → 힐링 → 라이트닝 볼트 → 썬더와 같은 스킬트리는 가능하지만,
썬더 → 스파크나 라이트닝 볼트 → 스파크 → 힐링 → 썬더와 같은 스킬트리는 불가능합니다.

선행 스킬 순서 skill과 유저들이 만든 스킬트리1를 담은 배열 skill_trees가 매개변수로 주어질 때,
가능한 스킬트리 개수를 return 하는 solution 함수를 작성해주세요.

제한 조건
스킬은 알파벳 대문자로 표기하며, 모든 문자열은 알파벳 대문자로만 이루어져 있습니다.
스킬 순서와 스킬트리는 문자열로 표기합니다.
예를 들어, C → B → D 라면 CBD로 표기합니다
선행 스킬 순서 skill의 길이는 1 이상 26 이하이며, 스킬은 중복해 주어지지 않습니다.
skill_trees는 길이 1 이상 20 이하인 배열입니다.
skill_trees의 원소는 스킬을 나타내는 문자열입니다.
skill_trees의 원소는 길이가 2 이상 26 이하인 문자열이며, 스킬이 중복해 주어지지 않습니다.

입출력 예
skill	         skill_trees	            return
"CBD"	["BACDE", "CBADF", "AECB", "BDA"]	   2

입출력 예 설명
BACDE: B 스킬을 배우기 전에 C 스킬을 먼저 배워야 합니다. 불가능한 스킬트립니다.
CBADF: 가능한 스킬트리입니다.
AECB: 가능한 스킬트리입니다.
BDA: B 스킬을 배우기 전에 C 스킬을 먼저 배워야 합니다. 불가능한 스킬트리입니다.
스킬 트리: 유저가 스킬을 배울 순서 ↩
 */
