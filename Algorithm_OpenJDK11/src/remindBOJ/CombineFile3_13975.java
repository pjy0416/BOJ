package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CombineFile3_13975 {
    static StringBuilder sb;
    final static String newLine = "\n";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        for(int i=0; i<testCases; i++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>();
            for(int j=0; j<n; j++) {
                pq.offer(Long.parseLong(st.nextToken()));
            }
            solution(pq);
        }

        br.close();
        System.out.println(sb.toString());
    }

    private static void solution(PriorityQueue<Long> pq) {
        long answer =0;

        while(pq.size() !=1) {
            long time = pq.poll();
            time += pq.poll();
            answer += time;
            pq.offer(time);
        }
        sb.append(answer).append(newLine);
    }
}
