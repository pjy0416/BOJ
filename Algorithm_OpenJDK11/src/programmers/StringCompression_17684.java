package programmers;

import java.util.HashMap;
import java.util.ArrayList;

public class StringCompression_17684 {
    static HashMap<String,Integer> dictionary;
    static int idx;
    static ArrayList<Integer> idxList;

    private static int[] solution(String msg) {
        inItDic();
        makeLZW(msg);
        return getAnswer();
    }

    private static void inItDic() {
        dictionary = new HashMap<>();
        for(int i=1; i<=26; i++) {
            char ch = (char)(i+64);
            dictionary.put(Character.toString(ch),i);
        }
        idx =27;
        idxList = new ArrayList<>();
    }

    private static void makeLZW(String msg) {
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<msg.length(); i++) {
            sb.append(msg.charAt(i));
            if(!dictionary.containsKey(sb.toString())) {
                dictionary.put(sb.toString(),idx++);
                sb.deleteCharAt(sb.length()-1);
                idxList.add(dictionary.get(sb.toString()));
                makeLZW(msg.substring(sb.length()));
                return;
            } else if(i == msg.length()-1) {
                idxList.add(dictionary.get(sb.toString()));
            }
        }
    }

    private static int[] getAnswer() {
        int[] answer = new int[idxList.size()];
        for(int i=0; i<answer.length; i++) {
            answer[i] = idxList.get(i);
        }
        return answer;
    }

    public static void main(String[] args) {
        String msg = "TOBEORNOTTOBEORTOBEORNOT";
        int[] result = solution(msg);

        for(int num : result) {
            System.out.print(num + " ");
        }
    }
}
/*
https://programmers.co.kr/learn/courses/30/lessons/17684
 */