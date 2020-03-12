package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class KitGreedyJoyStick {

    private static int solution(String name) {
        int answer = 0;

        char[] alphas = name.toCharArray();

        ArrayList<Integer> posList = moveDirection(alphas);
        int direction = posList.get(posList.size()-1);

        posList.remove(posList.size()-1); // 방향 제거

        if(posList.size() == alphas.length) {   // 받은 이름이 다 A일 때
            return 0;
        }

        if(posList.size() !=0) {
            if(posList.get(0) == 0) {   // 시작 인덱스는 빼주기
                posList.remove(0);
            }
        }

        if(direction ==0) { // 역방향일 때 내림차순 정렬
            Collections.sort(posList, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2-o1;
                }
            });
        } else {    // 순방향일 때 오름차순 정렬
            Collections.sort(posList);
        }


        int idx=0;
        for(int i=0; i<alphas.length; i++) {
            int changeCnt = moveAlpha(alphas[idx]);
            answer += changeCnt;



            if(direction ==0) { // 역방향
                idx--;
                if(idx < 0) {
                    idx = alphas.length-1;
                }
            } else {    // 순방향
                idx++;
            }
            answer++;   // 커서 이동 횟수 count

            if(alphas.length-1 - i == posList.size() && endCheck(idx, posList, direction)) { // 남은 글자 수랑 A의 카운트 포지션이 같을 때
                break;
            } else {
                if(!posList.isEmpty() && posList.get(0) == idx) {
                    posList.remove(0);
                }
            }
        }

        return answer-1;
    }

    private static int decideDirection(char[] chArr, int start) {
        int forward = 0;
        int reverse = 0;

        // 정방향 체크
        for (int i = start + 1; i < chArr.length; i++) {
            if (chArr[i] != 'A') {
                break;
            } else {
                forward++;
            }
        }

        // 역방향 체크
        int idx = start;
        for (int i = 0; i < chArr.length; i++) {
            if (idx <=0) {
                idx = (chArr.length) - 1;
            }

            if (chArr[idx--] != 'A') {
                reverse++;
            } else {
                break;
            }
        }

        System.out.println("정방향 : " + forward);
        System.out.println("역방향 : " + reverse);

        // 큰거 반대로 가야함
        return forward < reverse ? 1 : 0;
    }

    private static boolean endCheck(int start, ArrayList<Integer> posList, int direction) {
        boolean result =true;

        // 0 ~> 역방향
        if(direction ==0) {
            for (int i = 0; i < posList.size(); i++) {
//                System.out.println(start + "와 " +posList.get(i) + "비교 중");
                if (start-- != posList.get(i)) {
                    result = false;
                    break;
                }
            }
        } else {
            // 1 ~> 순방향
            for (int i = 0; i < posList.size(); i++) {
                if (start++ != posList.get(i)) {
                    result = false;
                    break;
                }
            }
        }

        return result;
    }

    private static ArrayList<Integer> moveDirection(char[] chArr) {
        ArrayList<Integer> aPosLists = new ArrayList<>();
        int leftCnt =0;
        int rightCnt =0;
        for(int i=0; i<chArr.length; i++) {
            if(chArr[i] == 'A') {
                aPosLists.add(i);
            }
        }

        for(int idx : aPosLists) {
            if(chArr.length%2 == 0) {   // 짝수일 때
                if(idx < chArr.length/2) {
//                    System.out.println(idx +"에서 카운트");
                    leftCnt++;
                } else {
                    rightCnt++;
                }
            } else {  // 홀수일 때
                if(idx <= chArr.length/2) {
//                    System.out.println(idx +"에서 카운트");
                    leftCnt++;
                } else {
                    rightCnt++;
                }
            }
        }

        if(leftCnt <= rightCnt) {
            aPosLists.add(1);  // 마지막에 순방향 추가
        } else {
            aPosLists.add(0);  // 마지막에 역방향 추가
        }
        return aPosLists;
    }
    private static int moveAlpha(char target) {
        return target <= 78 ? target - 'A' : ('Z'-target)+1;
    }
    public static void main(String[] args) {
        String name = "BBAABB";
        System.out.println(solution(name));
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/42860

문제 설명
조이스틱으로 알파벳 이름을 완성하세요. 맨 처음엔 A로만 이루어져 있습니다.
ex) 완성해야 하는 이름이 세 글자면 AAA, 네 글자면 AAAA

조이스틱을 각 방향으로 움직이면 아래와 같습니다.

▲ - 다음 알파벳
▼ - 이전 알파벳 (A에서 아래쪽으로 이동하면 Z로)
◀ - 커서를 왼쪽으로 이동 (첫 번째 위치에서 왼쪽으로 이동하면 마지막 문자에 커서)
▶ - 커서를 오른쪽으로 이동
예를 들어 아래의 방법으로 JAZ를 만들 수 있습니다.

- 첫 번째 위치에서 조이스틱을 위로 9번 조작하여 J를 완성합니다.
- 조이스틱을 왼쪽으로 1번 조작하여 커서를 마지막 문자 위치로 이동시킵니다.
- 마지막 위치에서 조이스틱을 아래로 1번 조작하여 Z를 완성합니다.
따라서 11번 이동시켜 "JAZ"를 만들 수 있고, 이때가 최소 이동입니다.
만들고자 하는 이름 name이 매개변수로 주어질 때,
이름에 대해 조이스틱 조작 횟수의 최솟값을 return 하도록 solution 함수를 만드세요.

제한 사항
name은 알파벳 대문자로만 이루어져 있습니다.
name의 길이는 1 이상 20 이하입니다.
입출력 예
name	    return
JEROEN	      56
JAN	          23
출처

※ 공지 - 2019년 2월 28일 테스트케이스가 추가되었습니다.
*/
