package programmers;

import java.util.LinkedList;

public class Cache_17680 {
    final static int CACHEHIT =1, CACHEMISS =5;

    public static int solution(int cacheSize, String[] cities) {
        return getExecuteTime(cacheSize, cities);
    }

    private static int getExecuteTime(int cacheSize, String[] cities) {
        int result =0;
        LinkedList<String> cache = new LinkedList<>();
        for(String city : cities) {
            String standardCity = getStandard(city);
            if(cache.contains(standardCity)) {
                result += CACHEHIT;
                for(int i=0; i<cache.size(); i++) { // LRU 부분
                    if(cache.get(i).equals(standardCity)) {
                        cache.remove(i);
                    }
                }
            } else {
                result += CACHEMISS;
            }
            cache.offer(standardCity);
            if(cache.size() > cacheSize) {
                cache.poll();
            }
        }
        return result;
    }

    private static String getStandard(String str) {
        StringBuffer sb = new StringBuffer();

        for(int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);
            if(ch >=97) {
                ch-=32;
            }
            sb.append(ch);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int cacheSize = 3;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        System.out.println(solution(cacheSize, cities));
    }
}
/*
https://programmers.co.kr/learn/courses/30/lessons/17680
 */