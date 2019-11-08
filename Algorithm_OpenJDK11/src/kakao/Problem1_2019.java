package kakao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem1_2019 {    // 76.0 / 100.0 점
    static int findNum(int cnt, int div) {
        int num =1;

        while (cnt % 10 == 0) {
            num++;
            cnt /= 10;
        }
        num += div;

//        System.out.println( cnt+ " <<-카운트 횟수" + div*cnt+ "중복된 길이" + div + "에서 표현되는 숫자 길이" + num);
        return num;
    }

    static int solution(String s) {
        int len = s.length();   // 전체 길이
        int answer = len;
        int min = 1001;

        int cnt=1;  // 중복된 횟수 카운팅

        for(int div =len/2; div >0; div--) {     // 문자열 자를 길이만큼 절반 ~ 1까지
//            System.out.println("================ " + div + "==================");
            answer = len;
            String tmp = s.substring(0, div);   // 첫번째 인덱스부터 길이만큼 자르기

            for(int times=1; times < len/div ; times++) {   // 100잔데 2개로 자르면 50개를 비교해야하기 때문.
                String dest = s.substring(div*times, div + div*times);

                if(tmp.equals(dest)) {  // 전에랑 글자 같아?
                    cnt++;              // 횟수 증가
                } else {    // 여러번 중복되는 것 체크
                    if(cnt !=1) {   // 현재까지 중복된 갯수만큼 글자 줄여주기
                        int reduceChar = div*cnt - findNum(cnt, div);   // 중복된 글자 수 - 표현될 글자 수
                        answer -= reduceChar ;   // 줄어든 글자 수 만큼 빼주기
                        cnt =1;         // 중복 체크 횟수 초기화
                    }
                }
                tmp = dest; // 비교 글자 바꾸기
            }

            if(cnt !=1) { // 검사 끝나고 잔여 횟수가 남아있으면
                int reduceChar = (cnt*div) - findNum(cnt, div);   // 중복된 글자 수 - 표현될 글자 수
                answer -= reduceChar;
            }

            min = min > answer ? answer : min;
            cnt =1;
        }

        answer = min;

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inStr = br.readLine();
        System.out.println(solution(inStr));

        br.close();
    }
}


/*
2020 입사
1번 문제
데이터 처리 전문가가 되고 싶은 어피치는 문자열을 압축하는 방법에 대해 공부를 하고 있습니다.
최근에 대량의 데이터 처리를 위한 간단한 비손실 압축 방법에 대해 공부를 하고 있는데,
문자열에서 같은 값이 연속해서 나타나는 것을 그 문자의 개수와 반복되는 값으로 표현하여 더 짧은 문자열로 줄여서 표현하는 알고리즘을 공부하고 있습니다.
간단한 예로 aabbaccc의 경우 2a2ba3c(문자가 반복되지 않아 한번만 나타난 경우 1은 생략함)와 같이 표현할 수 있는데,
이러한 방식은 반복되는 문자가 적은 경우 압축률이 낮다는 단점이 있습니다. 예를 들면, abcabcdede와 같은 문자열은 전혀 압축되지 않습니다.
어피치는 이러한 단점을 해결하기 위해 문자열을 1개 이상의 단위로 잘라서 압축하여 더 짧은 문자열로 표현할 수 있는지 방법을 찾아보려고 합니다.

예를 들어, ababcdcdababcdcd의 경우 문자를 1개 단위로 자르면 전혀 압축되지 않지만, 2개 단위로 잘라서 압축한다면 2ab2cd2ab2cd로 표현할 수 있습니다.
다른 방법으로 8개 단위로 잘라서 압축한다면 2ababcdcd로 표현할 수 있으며, 이때가 가장 짧게 압축하여 표현할 수 있는 방법입니다.

다른 예로, abcabcdede와 같은 경우, 문자를 2개 단위로 잘라서 압축하면 abcabc2de가 되지만, 3개 단위로 자른다면 2abcdede가 되어 3개 단위가 가장 짧은 압축 방법이 됩니다.
이때 3개 단위로 자르고 마지막에 남는 문자열은 그대로 붙여주면 됩니다.

압축할 문자열 s가 매개변수로 주어질 때,
위에 설명한 방법으로 1개 이상 단위로 문자열을 잘라 압축하여 표현한 문자열 중 가장 짧은 것의 길이를 return 하도록 solution 함수를 완성해주세요.

제한사항
s의 길이는 1 이상 1,000 이하입니다.
s는 알파벳 소문자로만 이루어져 있습니다.
입출력 예
s	result
"aabbaccc"	7
"ababcdcdababcdcd"	9
"abcabcdede"	8
"abcabcabcabcdededededede"	14
"xababcdcdababcdcd"	17
입출력 예에 대한 설명
입출력 예 #1

문자열을 1개 단위로 잘라 압축했을 때 가장 짧습니다.

입출력 예 #2

문자열을 8개 단위로 잘라 압축했을 때 가장 짧습니다.

입출력 예 #3

문자열을 3개 단위로 잘라 압축했을 때 가장 짧습니다.

입출력 예 #4

문자열을 2개 단위로 자르면 abcabcabcabc6de 가 됩니다.
문자열을 3개 단위로 자르면 4abcdededededede 가 됩니다.
문자열을 4개 단위로 자르면 abcabcabcabc3dede 가 됩니다.
문자열을 6개 단위로 자를 경우 2abcabc2dedede가 되며, 이때의 길이가 14로 가장 짧습니다.

입출력 예 #5

문자열은 제일 앞부터 정해진 길이만큼 잘라야 합니다.
따라서 주어진 문자열을 x / ababcdcd / ababcdcd 로 자르는 것은 불가능 합니다.
이 경우 어떻게 문자열을 잘라도 압축되지 않으므로 가장 짧은 길이는 17이 됩니다.
 */

/*
문제 풀이
첫 번째로 배치된, 가장 쉬운 문제입니다.
문자열 길이가 최대 1,000으로 제한이 크지 않기 때문에, 가능한 모든 방법을 탐색하면 됩니다.
문자열 길이가 N일 때, 길이가 N/2 보다 크게 잘랐을 때는 길이가 줄지 않습니다.
따라서 1 ~ N/2 길이로 자르는 방법을 모두 탐색한 후 그중 가장 짧은 방법을 선택하면 됩니다.
*/
