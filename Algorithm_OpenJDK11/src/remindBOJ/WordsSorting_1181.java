package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;

public class WordsSorting_1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<MyWord> pq = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());

        while(n-- >0) {
            pq.offer(new MyWord(br.readLine()));
        }
        br.close();

        solution(pq);
    }

    private static void solution(PriorityQueue<MyWord> pq) {
        HashSet<String> set = new HashSet<>();

        while(!pq.isEmpty()) {
            String str = pq.poll().str;
            if(!set.contains(str)) {
                System.out.println(str);
            }
            set.add(str);
        }
    }
}

class MyWord implements Comparable<MyWord> {
    String str;

    public MyWord(String str) {
        this.str = str;
    }

    @Override
    public int compareTo(MyWord m) {
        if(this.str.length() > m.str.length()) {
            return 1;
        } else if(this.str.length() == m.str.length()) {
            for(int i=0; i<this.str.length(); i++) {
                if(this.str.charAt(i) > m.str.charAt(i)) {
                    return 1;
                } else if(this.str.charAt(i) < m.str.charAt(i)){
                    return -1;
                }
            }
        }
        return -1;
    }
}
