package programmers;

import java.util.ArrayList;
import java.util.LinkedList;

public class CandidateKey_42890 {
    static ArrayList<int[]> keyList;

    private static int solution(String[][] relation) {  // column : 행 , row : 열
        keyList = new ArrayList<>();
        // 모든 조합 만들기
        for(int i=1; i<=relation[0].length; i++) {  // 1개 ~ n개
            combination(0, i, new int[relation[0].length], new LinkedList<Integer>(), relation);
        }

        // superkey 조합 만들기
        getSuperKeys(relation);

        // 후보키가 아닌 슈퍼키 제거
        if(keyList.size() >1) {
            for(int i=1; i<keyList.size(); i++) {
                int[] originArr = keyList.get(i);
                for(int j=i-1; j>=0; j--) {
                    int[] targetArr = keyList.get(j);
                    int cnt =0;
                    for(int origin : originArr) {
                        for(int target : targetArr) {
                            if(target == origin) {
                                cnt++;
                            }
                        }
                    }
                    // 삭제
                    if(cnt == targetArr.length) {
                        keyList.remove(i--);
                        break;
                    }
                }
            }
        }

        return keyList.size();
    }

    private static void getSuperKeys(String[][] relation) { // 슈퍼키 아닌 키 제거
        for(int i=0; i<keyList.size(); i++) {
            int[] keyArr = keyList.get(i); // 키 조합
            loop:
            for(int col=0; col<relation.length-1; col++) {
                for(int find =col+1; find<relation.length; find++) {
                    boolean isContain = true;
                    for(int idx =0; idx<keyArr.length; idx++) {
                        if(!relation[col][keyArr[idx]].equals(relation[find][keyArr[idx]])) {
                            isContain = false;
                            break;
                        }
                    }
                    if(isContain) { // 모든 정보에 대해 이미 가지고 있으면
                        keyList.remove(i--); // 삭제
                        break loop;
                    }
                }
            }
        }
    }

    private static void combination(int idx, int limit, int[] visit, LinkedList<Integer> list, String[][] relation) {
        if(list.size() == limit) {
            int[] tmp = new int[limit];
            for(int i=0; i<limit; i++) {
                tmp[i] = list.get(i);
            }
            keyList.add(tmp);
            return;
        }

        for(int i=idx; i<visit.length; i++) {
            if(visit[i] ==0 && !keyList.contains(i)) {
                visit[i] =1;
                list.offer(i);
                combination(i+1, limit, visit, list, relation);
                list.pollLast();
                visit[i] =0;
            }
        }
    }
    public static void main(String[] args) {
        String[][] relation = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};
        System.out.println(solution(relation));
    }
}
/*
https://programmers.co.kr/learn/courses/30/lessons/42890#

 */
