package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FindIndex_1786 {
    final static String NEW_LINE = "\n", SPACE = " ";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String origin = br.readLine();
        String pattern = br.readLine();

        br.close();
        solution(origin, pattern);
    }

    private static void solution(String origin, String pattern) {
        ArrayList<Integer> answerList = KMP(origin.toCharArray(), pattern.toCharArray());
        print(answerList);
    }

    private static void print(ArrayList<Integer> answerList) {
        StringBuilder sb = new StringBuilder();
        sb.append(answerList.size()).append(NEW_LINE);
        for(int index : answerList) {
            sb.append(index).append(SPACE);
        }
        System.out.println(sb.toString());
    }

    private static ArrayList<Integer> KMP(char[] origin, char[] pattern) {
        ArrayList<Integer> foundList = new ArrayList<>();
        int[] matchingArr = getMatchingArray(pattern);
        int originLen = origin.length, patternLen = pattern.length;
        int index =0; // pattern 탐색할 인덱스

        for(int i=0; i<originLen; i++) {
            while(index >0 && origin[i] != pattern[index]) {
                index = matchingArr[index-1];
            }
            if(origin[i] == pattern[index]) {
                if(index == patternLen-1) {
                    foundList.add(i-patternLen+2); // index 순서를 구하기 위한 +1, index를 1부터 시작하는 조건의 +1
                    index = matchingArr[index];
                } else {
                    index++;
                }
            }
        }
        return foundList;
    }

    private static int[] getMatchingArray(char[] pattern) {
        int len = pattern.length;
        int[] matchingArr = new int[len]; // 접두사와 접미사의 일치 정보를 담을 Array
        int index = 0; // 패턴 탐색 인덱스

        for(int i=1; i<len; i++) {
            while(index>0 && pattern[i] != pattern[index]) { // 매칭되는 지점은 건너뜀
                index = matchingArr[index-1];   // index 재할당 (건너뜀)
            }
            if(pattern[i] == pattern[index]) { // 접두사와 접미사가 같은 경우
                matchingArr[i] = ++index;      // 인덱스(길이) 카운트
            }
        }
        return matchingArr;
    }
}
