package programmers;

import java.util.ArrayList;
import java.util.HashMap;

public class NewsClustering_17677 {
    final static int MULTIPLYING = 65536;
    private static int solution(String str1, String str2) {
        return getSimilarity(str1, str2);
    }

    private static int getSimilarity(String str1, String str2) {
        ArrayList<String> str1List = makeUnion(str1);
        ArrayList<String> str2List = makeUnion(str2);

        HashMap<String,Integer> str1Map = new HashMap();
        HashMap<String,Integer> str2Map = new HashMap();

        ArrayList<String> unionList = new ArrayList<>();
        ArrayList<String> intersectionList = new ArrayList<>();

        setLists(str1List,str1Map,str2List,unionList,intersectionList);
        setLists(str2List,str2Map,str1List,unionList,intersectionList);

        int union = 0;
        for(String str : unionList) {
            if(str1Map.containsKey(str)) { // 1만
                if(str2Map.containsKey(str)) {  // 둘 다
                    union += Math.max(str1Map.get(str),str2Map.get(str));
                } else {
                    union += str1Map.get(str);
                }
            } else if(str2Map.containsKey(str)) { // 2만
                union += str2Map.get(str);
            }
        }
        int intersection =0;
        for(String str : intersectionList) {
            intersection += Math.min(str1Map.get(str), str2Map.get(str));
        }
        double similiarity = (double)intersection/union;
        if(union ==0) { // 합집합이 0인 경우
            similiarity =1.0;
        }

        similiarity *= MULTIPLYING;
        return (int)(similiarity);
    }

    private static void setLists(ArrayList<String> origin, HashMap<String, Integer> strMap,ArrayList<String> target, ArrayList<String> unionList, ArrayList<String> intersectionList) {
        for(String str : origin) {
            if(!unionList.contains(str)) {
                unionList.add(str);
            }
            if(!intersectionList.contains(str) && target.contains(str)) {
                intersectionList.add(str);
            }
            if(!strMap.containsKey(str)) {
                strMap.put(str,1);
            } else {
                strMap.replace(str, strMap.get(str)+1);
            }
        }
    }

    private static ArrayList<String> makeUnion(String str) {
        ArrayList<String> result = new ArrayList<>();
        StringBuffer sb = new StringBuffer();

        for(int i=0; i<str.length()-1; i++) {
            char left = str.charAt(i);
            char right = str.charAt(i+1);

            if(isChar(left) && isChar(right)) { // 글자
                if(left >=97) { // 소문자 -> 대문자
                    left-=32;
                }
                if(right >=97) {// 소문자 -> 대문자
                    right-=32;
                }
                sb.append(left).append(right);
                result.add(sb.toString());
                sb.setLength(0);
            }

        }
        return result;
    }

    private static boolean isChar(char ch) {
        return (ch >=65 && ch<=90) || (ch>=97 && ch<=122) ? true : false;
    }

    public static void main(String[] args) {
        String str1 = "FRANCE";
        String str2 = "french";
        System.out.println(solution(str1,str2));
    }
}
/*
https://programmers.co.kr/learn/courses/30/lessons/17677
 */
