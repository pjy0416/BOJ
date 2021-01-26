package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class JewelryThief {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        PriorityQueue<Jewelry> jewelryPQ = new PriorityQueue<>();

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            jewelryPQ.offer(new Jewelry(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int[] bagArr = new int[k];
        for(int i=0; i<k; i++) {
            bagArr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bagArr);

        br.close();
        solution(jewelryPQ, bagArr, n);
    }

    private static void solution(PriorityQueue<Jewelry> jewelryPQ, int[] bagArr, int n) {
        long answer =0;
        int i=0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int bag : bagArr) {
            while(!jewelryPQ.isEmpty()) {
                Jewelry jewelry = jewelryPQ.poll();
                if(jewelry.weight > bag) {
                    jewelryPQ.offer(jewelry);
                    break;
                } else {
                    pq.offer(jewelry.value);
                }
            }
            if(!pq.isEmpty()) {
                answer += pq.poll();
            }
        }
        System.out.println(answer);
    }

    private static class Jewelry implements Comparable<Jewelry>{
        int weight, value;

        public Jewelry(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewelry j) {
            return this.weight - j.weight;
        }
    }
}
/*
4 2
1 65
5 23
2 99
8 88
10
2

99 + 88

5 3
1 65
5 35
2 99
7 99
9 88
7
2
8
 */