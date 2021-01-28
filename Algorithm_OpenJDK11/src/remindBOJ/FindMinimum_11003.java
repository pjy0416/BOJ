package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class FindMinimum_11003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int[] numArr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
        solution(numArr, n, l);
    }

    private static void solution(int[] numArr, int n, int l) {
        StringBuilder sb = new StringBuilder();
        final char SPACE = ' ';
        ArrayDeque<Node> deque = new ArrayDeque<>();

        for(int i=0; i<n; i++) {
            while(!deque.isEmpty() && deque.peekLast().num > numArr[i]) {
                deque.pollLast();
            }

            deque.offer(new Node(i, numArr[i]));
            if(deque.peek().index <= i-l) {
                deque.poll();
            }

            sb.append(deque.peek().num).append(SPACE);
        }
        System.out.println(sb.toString());
    }

    private static boolean isInRange(int target, int now, int l) {
        return target > now-l;
    }

    private static class Node implements Comparable<Node> {
        int index, num;

        public Node(int index, int num) {
            this.index = index;
            this.num = num;
        }

        @Override
        public int compareTo(Node node) {
            return this.num - node.num;
        }
    }
}
