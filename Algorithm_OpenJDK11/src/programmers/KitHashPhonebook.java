package programmers;

import java.util.*;

public class KitHashPhonebook {
    public static void main(String[] args) {
        String[] phone_book = {"119", "97674223", "1195524421"};

        System.out.println(solution(phone_book));
    }

    private static boolean solution(String[] phone_book) {
        boolean answer = true;
        HashMap<Integer, ArrayList<String>> pbHash = new HashMap<>();   // 숫자의 길이별로 전화번호를 저장
        //ArrayList는 전화번호의 길이가 같은 번호들의 집합

        int min = 22;
        int max = 0;

        // Save Data
        for(int i=0; i<phone_book.length; i++) {
            String num = phone_book[i];
            int numLen = num.length();

            min = min > numLen ? numLen : min;      // for문을 더 돌리기 위해 최소값과
            max = max < numLen ? numLen : max;      // 최대값 저장

            if(pbHash.containsKey(numLen)) {    // 이미 해당 길이의 전화번호가 있으면
                ArrayList tmp = pbHash.get(numLen);
                tmp.add(num);
                pbHash.replace(numLen, tmp);        // 추가해서 저장
            } else {                            // 없는 길이의 전화번호면
                ArrayList<String> tmp = new ArrayList<>();
                tmp.add(num);
                pbHash.put(numLen, tmp);        // 새롭게 선언하여 저장
            }
        }

        // check
        for(int i=min; i <max; i++) {  // 전화번호의 길이가 최소부터 최대-1 까지 돌면서 검사

            if(pbHash.containsKey(i)) { // 해당 길이의 전화번호들이 있으면
                ArrayList<String> originList = pbHash.get(i);

                for(int j=i+1; j<=max; j++) {    // 길이가 하나 큰 것 부터 max까지 검증
                    if(pbHash.containsKey(j)) { // 전화번호를 뒤져서 존재하면
                        ArrayList<String> targetList = pbHash.get(j);
                        for(String origin : originList) {
                            int originLen = origin.length();
                            for(String target : targetList) {
                                if(origin.equals( target.substring(0, originLen))) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }

        return answer;
    }

}

/*
문제 설명
전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다.
전화번호가 다음과 같을 경우, 구조대 전화번호는 영석이의 전화번호의 접두사입니다.

구조대 : 119
박준영 : 97 674 223
지영석 : 11 9552 4421
전화번호부에 적힌 전화번호를 담은 배열 phone_book 이 solution 함수의 매개변수로 주어질 때,
어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를
그렇지 않으면 true를 return 하도록 solution 함수를 작성해주세요.

제한 사항
phone_book의 길이는 1 이상 1,000,000 이하입니다.
각 전화번호의 길이는 1 이상 20 이하입니다.
입출력 예제
phone_book	                                        return
["119", "97674223", "1195524421"]     	             false
["123","456","789"]	                                 true
["12","123","1235","567","88"]	                     false

입출력 예 설명
입출력 예 #1
앞에서 설명한 예와 같습니다.

입출력 예 #2
한 번호가 다른 번호의 접두사인 경우가 없으므로, 답은 true입니다.

입출력 예 #3
첫 번째 전화번호, “12”가 두 번째 전화번호 “123”의 접두사입니다. 따라서 답은 false입니다.

 */