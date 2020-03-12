package programmers;

import java.util.ArrayList;

public class KitGreedySportsWear {
    private static int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        ArrayList<Integer> redundancy = new ArrayList<>();
//        Set<Integer> redundancy = new HashSet<>();
        for(int idx :reserve) {
            redundancy.add(idx);
        }
        for(int i=0; i<lost.length; i++) {
            int idx = lost[i];
//            System.out.println(idx +" 차례");
            if(redundancy.contains(idx)) {  // 자기 자신이 잃어버리고 여유분도 있는 애 부터 소거
                redundancy = removeNum(redundancy, idx);
//                System.out.println("자기 자신 삭제");
            } else if(redundancy.contains(idx-1)) { // 이 전에 애가 여유분 있으면
                redundancy = removeNum(redundancy, idx-1);
//                System.out.println("이 전에 삭제");
            } else if(redundancy.contains(idx+1)) { // 이 다음 애가 여유분이 있으면
                if(i+1 < lost.length) {    // OutOfException 방지
                    if( lost[i+1] == idx+1) { // 이 다음애도 잃어버린 애면
                        answer++;  // 지금 애가 못빌리니까++
                    } else {
                        redundancy = removeNum(redundancy, idx+1);
                    }
                }
//                System.out.println("다음 애 삭제");
            } else {
                answer++;
            }
        }

        return n-answer;
    }

    private static ArrayList<Integer> removeNum(ArrayList<Integer> redundancy, int target) {
        for(int i=0; i<redundancy.size(); i++) {
            if(redundancy.get(i) == target) {
                redundancy.remove(i);
                break;
            }
        }
        return redundancy;
    }

    public static void main(String[] args) {
//        int[][] params = {{5}, {2,4}, {1,3,5}};
//        int[][] params = {{5}, {2,4}, {3}};
//        int[][] params = {{3}, {3}, {1}};
//        int[][] params = {{10}, {3,9,10}, {3,8,9}};           // ~> 9
//        int[][] params = {{7}, {2,3,4}, {1,2,3,6}};             // ~> 6
//        int[][] params = {{5},{3,5},{2,4}};                     // ~> 5
        int[][] params = {{5},{1,3},{2,4}};                     // ~> 5

//        n = 5, lost = [3, 5], reserve = [2, 4], return =5
        System.out.println(solution(params[0][0], params[1], params[2]));

    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/42862

문제 설명
점심시간에 도둑이 들어, 일부 학생이 체육복을 도난당했습니다.
다행히 여벌 체육복이 있는 학생이 이들에게 체육복을 빌려주려 합니다.
학생들의 번호는 체격 순으로 매겨져 있어, 바로 앞번호의 학생이나 바로 뒷번호의 학생에게만 체육복을 빌려줄 수 있습니다.
예를 들어, 4번 학생은 3번 학생이나 5번 학생에게만 체육복을 빌려줄 수 있습니다.
체육복이 없으면 수업을 들을 수 없기 때문에 체육복을 적절히 빌려 최대한 많은 학생이 체육수업을 들어야 합니다.

전체 학생의 수 n, 체육복을 도난당한 학생들의 번호가 담긴 배열 lost,
여벌의 체육복을 가져온 학생들의 번호가 담긴 배열 reserve가 매개변수로 주어질 때,
체육수업을 들을 수 있는 학생의 최댓값을 return 하도록 solution 함수를 작성해주세요.

제한사항
전체 학생의 수는 2명 이상 30명 이하입니다.
체육복을 도난당한 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
여벌의 체육복을 가져온 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
여벌 체육복이 있는 학생만 다른 학생에게 체육복을 빌려줄 수 있습니다.
여벌 체육복을 가져온 학생이 체육복을 도난당했을 수 있습니다.
이때 이 학생은 체육복을 하나만 도난당했다고 가정하며,
남은 체육복이 하나이기에 다른 학생에게는 체육복을 빌려줄 수 없습니다.

입출력 예
n	lost	reserve	  return
5	[2, 4]	[1, 3, 5]	5
5	[2, 4]	[3]	        4
3	[3]	    [1]	        2
입출력 예 설명
예제 #1
1번 학생이 2번 학생에게 체육복을 빌려주고,
3번 학생이나 5번 학생이 4번 학생에게 체육복을 빌려주면 학생 5명이 체육수업을 들을 수 있습니다.

예제 #2
3번 학생이 2번 학생이나 4번 학생에게 체육복을 빌려주면 학생 4명이 체육수업을 들을 수 있습니다.

출처

※ 공지 - 2019년 2월 18일 지문이 리뉴얼되었습니다.
※ 공지 - 2019년 2월 27일, 28일 테스트케이스가 추가되었습니다.
*/
