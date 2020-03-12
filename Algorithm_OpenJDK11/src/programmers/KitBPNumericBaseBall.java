package programmers;

import java.util.ArrayList;
import java.util.LinkedList;

public class KitBPNumericBaseBall {
    static ArrayList<String> candidates = new ArrayList<>();

    private static int solution(int[][] baseball) {
        int answer = 0;

        LinkedList<String> tmp = new LinkedList<>();
        int[] check = new int[10];
        makeNums(tmp, check);

        for(int[] game : baseball) {
            // Idx) 0 : 게임 숫자 , 1 : 스트라이크 , 2 : 볼
            String[] numArr = { String.valueOf(game[0]/100), String.valueOf((game[0]%100)/10), String.valueOf(game[0]%10)};

           if(game[1] ==3) {    // 3Strike면 게임 끝
               return 1;
           } else {
               for(int i=0; i<candidates.size(); i++) {
                   String str = candidates.get(i);
                   String[] strArr = str.split("");
                   if (!isCase(strArr,numArr,game[1],game[2])) {
                       candidates.remove(str);
                       i--;
                   }
               }
           }
        }

        /*System.out.println("\n\n 이제 남은거~~~~~~~~~~~~~ \n");
        for(String nums : candidates) {
            System.out.println(nums);
        }*/

        answer = candidates.size();

        return answer;
    }

    private static boolean isCase(String[] origin, String[] target, int strike, int ball) {
        int strikeCnt =0;
        int ballCnt =0;
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(origin[i].equals(target[j])) {
                    if(i==j) {
                        strikeCnt++;
                    } else {
                        ballCnt++;
                    }
                }
            }
        }

        return strikeCnt == strike && ballCnt == ball ? true : false;
    }

    private static void makeNums(LinkedList<String> list, int[] check) {
        if(list.size() == 3) {
            String str = "";
            for(String i : list){
                str += i;
            }
            candidates.add(str);
            return;
        }

        for(int i=1; i<=9; i++) {
            if(check[i] == 0) {
                check[i] =1;
                list.add(String.valueOf(i));
                makeNums(list, check);
                check[i] =0;
                list.pollLast();
            }
        }
    }

    public static void main(String[] args) {
        int[][] baseball = {{123, 1, 1}, {356, 1, 0}, {327, 2, 0}, {489, 0, 1}};

        System.out.println(solution(baseball));
    }
}


/*
https://programmers.co.kr/learn/courses/30/lessons/42841

숫자 야구 게임이란 2명이 서로가 생각한 숫자를 맞추는 게임입니다. 게임해보기

각자 서로 다른 1~9까지 3자리 임의의 숫자를 정한 뒤 서로에게 3자리의 숫자를 불러서 결과를 확인합니다.
그리고 그 결과를 토대로 상대가 정한 숫자를 예상한 뒤 맞힙니다.

* 숫자는 맞지만, 위치가 틀렸을 때는 볼
* 숫자와 위치가 모두 맞을 때는 스트라이크
* 숫자와 위치가 모두 틀렸을 때는 아웃
예를 들어, 아래의 경우가 있으면

A : 123
B : 1스트라이크 1볼.
A : 356
B : 1스트라이크 0볼.
A : 327
B : 2스트라이크 0볼.
A : 489
B : 0스트라이크 1볼.

이때 가능한 답은 324와 328 두 가지입니다.

질문한 세 자리의 수, 스트라이크의 수, 볼의 수를 담은 2차원 배열 baseball이 매개변수로 주어질 때,
가능한 답의 개수를 return 하도록 solution 함수를 작성해주세요.

제한사항
질문의 수는 1 이상 100 이하의 자연수입니다.
baseball의 각 행은 [세 자리의 수, 스트라이크의 수, 볼의 수] 를 담고 있습니다.

입출력 예
                        baseball	                                return
[[123, 1, 1], [356, 1, 0], [327, 2, 0], [489, 0, 1]]	               2

입출력 예 설명

문제에 나온 예와 같습니다.
 */
