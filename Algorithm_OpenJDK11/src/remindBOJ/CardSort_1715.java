package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class CardSort_1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        while(n-- >0) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        br.close();
        System.out.println(solution(pq));
    }

    private static int solution(PriorityQueue<Integer> pq) {
        int answer =0;
        final int ENOUGH = 1;
        while(pq.size() != ENOUGH) {
            int sum = pq.poll() + pq.poll();
            answer += sum;
            pq.offer(sum);
        }
        return answer;
    }
}
