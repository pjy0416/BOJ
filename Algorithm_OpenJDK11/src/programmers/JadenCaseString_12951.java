package programmers;

public class JadenCaseString_12951 {
    private static String solution(String s) {
        StringBuffer answer = new StringBuffer();
        String[] strArr = s.split(" ");
        for(String str : strArr) {
            answer.append(getJadenCase(str)).append(" ");
        }
        if(s.charAt(s.length()-1) != ' ') {
            answer.deleteCharAt(answer.length()-1);
        }
        return answer.toString();
    }

    private static String getJadenCase(String str) {
        StringBuffer sb = new StringBuffer();

        for(int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);
            if(i==0 &&  ch>=97) {
                ch -= 32;
            } else if(i !=0 && ch >=65 && ch<=96) {// 대문자 경우
                ch += 32;
            }
            sb.append(ch);
        }

        return sb.toString();
    }
    public static void main(String[] args) {
        String str = "3people unFollowed me";
        System.out.println(solution(str));
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/12951
JadenCase 문자열 만들기
문제 설명
JadenCase란 모든 단어의 첫 문자가 대문자이고, 그 외의 알파벳은 소문자인 문자열입니다. 문자열 s가 주어졌을 때, s를 JadenCase로 바꾼 문자열을 리턴하는 함수, solution을 완성해주세요.

제한 조건
s는 길이 1 이상인 문자열입니다.
s는 알파벳과 공백문자(" ")로 이루어져 있습니다.
첫 문자가 영문이 아닐때에는 이어지는 영문은 소문자로 씁니다. ( 첫번째 입출력 예 참고 )
입출력 예
s	return
3people unFollowed me	3people Unfollowed Me
for the last week	For The Last Week
 */
