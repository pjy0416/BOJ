package programmers;

public class KitGreedyJoyStick {

    private static int solution(String name) {
        int answer = 0;

        char[] alphas = name.toCharArray();

        for(char ch : alphas) { // 알파벳 바꾸는 거는 미리 계산해주기
            answer += moveAlpha(ch);
        }

        int minMove = alphas.length -1;     // 일반적인 경우 커서 이동을 다 해줘야함. (시작지점 ~> 끝)

        // A가 있는 경우 움직이는 경우의 수가 줄어들 수 있음
        if(name.contains("A")) {
            int cnt =1; // 첫 커서에서 무조건 움직일 거기 때문에 1부터 시작

            //뒤에서 부터 세는 경우 (앞에 A가 연속으로 있을 때는 뒤에서부터 해주는게 이득
            for(int i=1; i<alphas.length; i++) {
                if(alphas[i] != 'A') {
                    cnt = alphas.length -i;
                    break;
                }
            }

            minMove = minMove > cnt ? cnt : minMove;
            int idx =0;

            while(idx < alphas.length) {
                int endIdx = idx+1;  // 연속된 A가 끝나는 지점
                if(alphas[idx] =='A') {
                    while(endIdx < alphas.length && alphas[endIdx] == 'A') {    // 문자열 끝까지 A인 곳을 찾음
                        endIdx++;
                    }

                    cnt = endIdx == alphas.length ? idx-1 : 1 + (idx-1)*2 + alphas.length-1-endIdx;   // 3번째 idx부터는 돌아오는데 걸리는 스텝 체크 ~> (idx-1)*2

                    minMove = minMove > cnt ? cnt : minMove;

                    idx = endIdx+1;
                } else {    // A가 아닌지점이 나타나면
                    idx++;  // 다음 인덱스 탐색
                }
            }
        }

        answer+=minMove;

        return answer;
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
