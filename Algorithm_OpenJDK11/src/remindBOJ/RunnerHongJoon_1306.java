package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class RunnerHongJoon_1306 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] roads = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            roads[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
        solution(n,m,roads);
    }

    private static void solution(int n, int m, int[] roads) {
        StringBuilder sb = new StringBuilder();
        final char SPACE = ' ';
        PriorityQueue<AdsBoard> pq= new PriorityQueue<>();
        int range = 2*m-1;
        for(int i=0; i<range; i++) {
            if(i < n) {
                pq.offer(new AdsBoard(i, roads[i]));
            }
        }
        sb.append(pq.peek().intensity).append(SPACE);

        for(int i=range; i<n; i++) {
            pq.offer(new AdsBoard(i, roads[i]));
            boolean found = false;
            while(!found) {
                AdsBoard board = pq.peek();
                if(isInRange(board.index, i-m+1, m)) { // 번호를 붙이는거는 i-m+1 번째 ( m을 시작으로 증가)
                    found = true;
                    sb.append(board.intensity).append(SPACE);
                } else {
                    pq.poll();
                }
            }
        }

        System.out.println(sb.toString());
    }

    private static boolean isInRange(int intensity, int i, int m) {
        return intensity > i-m && intensity < i+m;
    }

    private static class AdsBoard implements Comparable<AdsBoard>{
        int index, intensity;

        public AdsBoard(int index, int intensity) {
            this.index = index;
            this.intensity = intensity;
        }

        @Override
        public int compareTo(AdsBoard board) {
            if(board.index == this.index) {
                return this.index - board.index;
            } else {
                return board.intensity - this.intensity;
            }
        }
    }
}
/*
5 2
1 1 1 3 2
=> 1 3 3

7 3
1 3 5 7 3 2 1
=> 7 7 7

7 2
1 3 5 7 3 2 1
=> 5 7 7 7 3

7 9
1 3 5 7 3 2 1
=> 7
*/