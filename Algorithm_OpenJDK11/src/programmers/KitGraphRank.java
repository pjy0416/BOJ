package programmers;

import java.util.LinkedList;

public class KitGraphRank {
    private static int solution(int n, int[][] results) {
        LinkedList<Integer>[] winnerList = inItLinkedList(n);     //i가 이긴 선수들 List
        LinkedList<Integer>[] loserList = inItLinkedList(n);      //i가 진 선수들 List

        for(int[] game : results) {
            winnerList[game[0]].offer(game[1]);  // 선수가 이긴것만 저장
            loserList[game[1]].offer(game[0]); // 선수가 진것만 저장
        }

        for(int i=1; i<=n; i++) {
            winnerList = addMatches(winnerList, loserList, i, n);   // i 선수가 이기는 상대 선수 추가
            loserList = addMatches(loserList, winnerList, i, n);    // i 선수가 지는 상대 선수 추가
        }

        return getFixCount(winnerList, loserList, n);       // Rank 를 정할 수 있는 선수 수 return
    }

    private static int getFixCount(LinkedList<Integer>[] winnerList, LinkedList<Integer>[] loserList, int n) {
        int cnt=0;
        for(int i=1; i<=n; i++) {
            if(isAllMatches(winnerList[i], loserList[i], n)) { // 경기기록이 다 있으면
                cnt++;
            }
        }
        return cnt;
    }

    private static boolean isAllMatches(LinkedList<Integer> winList, LinkedList<Integer> loseList, int n) { // 모든 경기기록 체크
        return winList.size() + loseList.size() == n-1 ? true : false;
    }

    private static LinkedList<Integer>[] inItLinkedList(int n) {    // InIt
        LinkedList<Integer>[] result = new LinkedList[n+1];
        for(int i=1; i<=n; i++) {
            result[i] = new LinkedList<>();
        }
        return result;
    }

    // 선수의 상관 관계를 업데이트 시켜줄 메소드
    private static LinkedList<Integer>[] addMatches(LinkedList<Integer>[] match1, LinkedList<Integer>[] match2, int player, int n) {
        //match1,match2가 서로 winList or loseList가 될 것임
        // match1이 WinnerList, match2가 loserList => i 선수가 이긴 선수들에게 진 선수들을 i선수가 이기는 선수들에 추가
        // 반대의 경우 => i선수가 진 선수들에게 이긴 선수들을 i 선수가 지는 선수들에 추가
        for(int origin : match2[player]) {    // match2에 담긴 player가 치룬 경기 정보를 받아와서
            for (int target : match1[player]) {  // match1에 담긴 player가 경기한 선수들
                if (!match1[origin].contains(target)) {  // 중복 체크
                    match1[origin].offer(target);        // match2에서 겨룬 선수에대해 match1에 저장
                }
            }
        }
        return match1;
    }

    /*
    private static int solution(int n, int[][] results) {
        int answer = 0;
        int[][] matches = new int[n+1][n+1];
        for(int[] match : results) {
            matches[match[0]][match[1]] = 1; // 이김
            matches[match[1]][match[0]] = -1; // 짐
        }

        // match 저장
        for(int player=1; player<=n; player++) {
            for(int enemy =1; enemy<=n; enemy++) {
                if(matches[player][enemy] ==1) {    // 내가 이긴 애들
                    for(int i=1; i<=n; i++) {
                        if(matches[enemy][i] == 1) {    // 상대가 이긴 애들
                            matches[player][i] =1; // 나도 이김
                            matches[i][player] = -1;
                        }
                    }
                } else if(matches[player][enemy] ==-1) {    // 내가 진 애들
                    for(int i=1; i<=n; i++) {
                        if(matches[player][i] ==1) { // 내가 이긴애들 다 이겨
                            matches[enemy][i] =1;
                            matches[i][enemy] =-1;
                        }
                    }
                }
            }
        }

        // count
        for(int y=1; y<=n; y++) {
            int cnt =0;
            for(int x=1; x<=n; x++) {
                if(matches[y][x] !=0) {
                    cnt++;
                }
            }
            if(cnt == n-1) {
                answer++;
            }
        }

        return answer;
    }
     */

    public static void main(String[] args) {
        int n=5;
        int[][] result = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};

        System.out.println(solution(n, result));
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/49191
순위

문제 설명
n명의 권투선수가 권투 대회에 참여했고 각각 1번부터 n번까지 번호를 받았습니다.
권투 경기는 1대1 방식으로 진행이 되고, 만약 A 선수가 B 선수보다 실력이 좋다면 A 선수는 B 선수를 항상 이깁니다.
심판은 주어진 경기 결과를 가지고 선수들의 순위를 매기려 합니다.
하지만 몇몇 경기 결과를 분실하여 정확하게 순위를 매길 수 없습니다.

선수의 수 n, 경기 결과를 담은 2차원 배열 results가 매개변수로 주어질 때 정확하게 순위를 매길 수 있는 선수의 수를 return 하도록 solution 함수를 작성해주세요.

제한사항

선수의 수는 1명 이상 100명 이하입니다.
경기 결과는 1개 이상 4,500개 이하입니다.
results 배열 각 행 [A, B]는 A 선수가 B 선수를 이겼다는 의미입니다.
모든 경기 결과에는 모순이 없습니다.

입출력 예
n	                results	                     return
5	[[4, 3], [4, 2], [3, 2], [1, 2], [2, 5]]	    2

입출력 예 설명
2번 선수는 [1, 3, 4] 선수에게 패배했고 5번 선수에게 승리했기 때문에 4위입니다.
5번 선수는 4위인 2번 선수에게 패배했기 때문에 5위입니다.
 */
