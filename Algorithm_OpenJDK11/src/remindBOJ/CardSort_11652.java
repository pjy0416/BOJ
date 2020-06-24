package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CardSort_11652 {
    public static void main(String[] args) throws IOException {
        solution(new BufferedReader(new InputStreamReader(System.in)));
    }

    private static void solution(BufferedReader br) throws IOException {
        int n = Integer.parseInt(br.readLine());
        /*HashMap<Long, Integer> cardMap = new HashMap<>(); // HashMap을 이용한 풀이(메모리를 조금 더 사용함)

        for(int i=0; i<n; i++) {
            long key = Long.parseLong(br.readLine());
            if(cardMap.containsKey(key)) {
                cardMap.replace(key, cardMap.get(key)+1);
            } else {
                cardMap.put(key, 1);
            }
        }
        List<Long> keySetList = new ArrayList<>(cardMap.keySet());

        Collections.sort(keySetList, new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                int cnt1 = cardMap.get(o1);
                int cnt2 = cardMap.get(o2);
                if(cnt1 < cnt2) {
                    return 1;
                } else if(cnt1 == cnt2) {
                    return o1 > o2? 1 : -1;
                }
                return -1;
            }
        });

        System.out.println(keySetList.get(0));*/
        List<Long> cardList = new ArrayList<>();    // List를 활용한 풀이, 공간은 덜 사용하지만 속도는 아주 조금 더 느림
        for(int i=0; i<n; i++) {
            cardList.add(Long.parseLong(br.readLine()));
        }
        Collections.sort(cardList);

        long answer = cardList.get(0);
        int answerCnt =0;

        long prev = answer;
        int prevCnt =0;
        for(long num : cardList) {
            if(num == prev) {
                prevCnt++;
            } else {
                if(answerCnt <prevCnt) {
                    answer = prev;
                    answerCnt = prevCnt;
                }
                prev = num;
                prevCnt =1;
            }
        }
        if(answerCnt < prevCnt) {
            answer = prev;
        }
        System.out.println(answer);
    }
}
//https://www.acmicpc.net/problem/11652
