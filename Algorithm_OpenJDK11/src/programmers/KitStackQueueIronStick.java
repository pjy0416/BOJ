package programmers;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KitStackQueueIronStick {
    public static void main(String[] args) {
        String arrangement = "()(((()())(())()))(())";

        System.out.println(solution(arrangement));
    }

    private static String getRazerString(String arrangement) {
        String result ="";

        Pattern pattern = Pattern.compile("[\\(]+[\\)]");
        Matcher razer = pattern.matcher(arrangement);

        // 찾을 때마다 레이저 표시 바꿔줌 (막대 정보를 유지하기 위해서)
        StringBuffer razerString = new StringBuffer();      // String으로 치환시키기 위해 SB를 써준다.

        while (razer.find()) {
            razer.appendReplacement(razerString, razer.group().replace("()", "!"));
        }

        razer.appendTail(razerString);
        result = razerString.toString();

        return result;
    }

    private static int solution(String arrangement) {
        int answer = 0;
        Stack<Integer> pipe = new Stack<>();
        String razerString = getRazerString(arrangement);

//        System.out.println(razerString);
        for(int i=0; i<razerString.length(); i++) {
            char ch = razerString.charAt(i);

            if(ch == '(') { // (괄호를 만날 때마다 스틱을 추가해주고
                pipe.push(0);
            } else if(ch == '!'){        // 레이저가 있을 때마다, 모든 스택에 담긴 파이프에 레이저 갯수 추가
                // 스택 돌면서 레이저 갯수 추가
                for(int j=0; j<pipe.size(); j++) {
                    pipe.set(j, pipe.get(j)+1);
                }
            } else {    // )괄호를 만날 때마다 스택 안에 있는 레이저 갯수를 세서 제거
                answer += pipe.pop()+1;
            }
        }

        return answer;
    }
}

// must creat things

// 1. 스틱이 열릴 때마다 개체를 생성한다
// 2. 그 개체에 레이저 갯수를 담는다.
// 3. 스틱이 닫칠때마다 레이저 갯수를 파악하여 스틱이 분열되는 수를 반환해준다.

// 이 코드의 단점
// 1. 문자열을 치환하는데 시간과 메모리를 낭비할 경우가 존재
// 2. 스택을 돌면서 레이저를 추가할 때, 시간이 오래걸리는 경우가 존재

/*
여러 개의 쇠막대기를 레이저로 절단하려고 합니다.
효율적인 작업을 위해서 쇠막대기를 아래에서 위로 겹쳐 놓고, 레이저를 위에서 수직으로 발사하여 쇠막대기들을 자릅니다.
쇠막대기와 레이저의 배치는 다음 조건을 만족합니다
.
- 쇠막대기는 자신보다 긴 쇠막대기 위에만 놓일 수 있습니다.      (아래로 갈수록 길어짐)
- 쇠막대기를 다른 쇠막대기 위에 놓는 경우 완전히 포함되도록 놓되, 끝점은 겹치지 않도록 놓습니다. (위에 오는 쇠막대기 사이즈가 작아야함)
- 각 쇠막대기를 자르는 레이저는 적어도 하나 존재합니다.
- 레이저는 어떤 쇠막대기의 양 끝점과도 겹치지 않습니다.

이러한 레이저와 쇠막대기의 배치는 다음과 같이 괄호를 이용하여 왼쪽부터 순서대로 표현할 수 있습니다.

(a) 레이저는 여는 괄호와 닫는 괄호의 인접한 쌍 '()'으로 표현합니다. 또한 모든 '()'는 반드시 레이저를 표현합니다.
(b) 쇠막대기의 왼쪽 끝은 여는 괄호 '('로, 오른쪽 끝은 닫힌 괄호 ')'로 표현됩니다.
위 예의 괄호 표현은 그림 위에 주어져 있습니다.
쇠막대기는 레이저에 의해 몇 개의 조각으로 잘리는데, 위 예에서 가장 위에 있는 두 개의 쇠막대기는 각각 3개와 2개의 조각으로 잘리고,
이와 같은 방식으로 주어진 쇠막대기들은 총 17개의 조각으로 잘립니다.

쇠막대기와 레이저의 배치를 표현한 문자열 arrangement가 매개변수로 주어질 때,
잘린 쇠막대기 조각의 총 개수를 return 하도록 solution 함수를 작성해주세요.

제한사항
arrangement의 길이는 최대 100,000입니다.
arrangement의 여는 괄호와 닫는 괄호는 항상 쌍을 이룹니다.
입출력 예
arrangement	return
()(((()())(())()))(())	17
입출력 예 설명
문제에 나온 예와 같습니다.

 */