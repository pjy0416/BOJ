package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LazyWhiteBear_10025 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        Bucket[] buckets = new Bucket[n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            buckets[i] = new Bucket(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        br.close();
        Arrays.sort(buckets);
        solution(k, buckets);
    }

    private static void solution(int k, Bucket[] buckets) {
        Deque<Bucket> queue = new LinkedList<>();
        int sum =0;
        int answer =0;

        for(Bucket bucket : buckets) {
            sum += bucket.ice;
            while(!queue.isEmpty()) {
                Bucket target = queue.poll();
                if(isInRange(target.pos, bucket.pos, k)) {
                    queue.offerFirst(target);
                    break;
                } else {
                    sum -= target.ice;
                }
            }
            answer = Math.max(answer, sum);
            queue.offer(bucket);
        }

        System.out.println(answer);
    }

    private static boolean isInRange(int pos1, int pos2, int k) {
        return Math.abs(pos1-pos2) <= 2*k;
    }

    private static class Bucket implements Comparable<Bucket>{
        int ice, pos;

        public Bucket(int ice, int pos) {
            this.ice = ice;
            this.pos = pos;
        }

        @Override
        public int compareTo(Bucket b) {
            return this.pos - b.pos;
        }
    }
}
