package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MinAndMax_2357 {
    final static String NEW_LINE = "\n", SPACE = " ";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] numArr = new int[n];
        for(int i=0; i<n; i++) {
            numArr[i] = Integer.parseInt(br.readLine());
        }

        int[][] commandArr = new int[m][2];
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            commandArr[i][0] = Integer.parseInt(st.nextToken());
            commandArr[i][1] = Integer.parseInt(st.nextToken());
        }
        br.close();

        solution(n, numArr, commandArr);
    }

    private static void solution(int n, int[] numArr, int[][] commandArr) {
        SegmentTree segmentTree = new SegmentTree(n, numArr);
        segmentTree.init(1, 0, n-1);
        StringBuilder sb = new StringBuilder();
        for(int[] commandInfo : commandArr) {
            int left = commandInfo[0] -1, right = commandInfo[1] -1;
            sb.append(segmentTree.findMinValue(1,0,n-1,left,right)).append(SPACE);
            sb.append(segmentTree.findMaxValue(1,0,n-1,left,right)).append(NEW_LINE);
        }
        System.out.println(sb.toString());
    }

    private static class SegmentTree {
        int[] minTree, maxTree, numArr;
        private int size;

        public SegmentTree(int n, int[] numArr) {
            this.size = 1 << ((int) Math.ceil(Math.log(n)/Math.log(2)) +1);
            this.minTree = new int[this.size];
            this.maxTree = new int[this.size];
            this.numArr = numArr;
        }

        public void init(int node, int start, int end) {
            this.minInit(node, start, end);
            this.maxInit(node, start, end);
        }

        private int minInit(int node, int start, int end) {
            if(start == end) {
                return this.minTree[node] = this.numArr[start];
            }
            int mid = (start+end)/2;
            return this.minTree[node] = Math.min(this.minInit(node*2, start, mid), this.minInit(node*2+1, mid+1, end));
        }

        private int maxInit(int node, int start, int end) {
            if(start == end) {
                return this.maxTree[node] = this.numArr[start];
            }
            int mid = (start+end)/2;
            return this.maxTree[node] = Math.max(this.maxInit(node*2, start, mid), this.maxInit(node*2+1, mid+1, end));
        }

        public int findMinValue(int node, int start, int end, int left, int right) {
            if(left > end || right < start) { // out of range
                return Integer.MAX_VALUE;
            }
            if(left <= start && right >= end) { // found value
                return this.minTree[node];
            }
            int mid = (start+end)/2;
            return Math.min(this.findMinValue(node*2, start, mid, left, right), this.findMinValue(node*2+1, mid +1, end, left, right));
        }

        public int findMaxValue(int node, int start, int end, int left, int right) {
            if(left > end || right < start) { // out of range
                return Integer.MIN_VALUE;
            }
            if(left <= start && right >= end) { // found value
                return this.maxTree[node];
            }
            int mid = (start+end)/2;
            return Math.max(this.findMaxValue(node*2, start, mid, left, right), this.findMaxValue(node*2+1, mid +1, end, left, right));
        }

        public void print() {
            System.out.println("원래 수 배열");
            for(int num : numArr) {
                System.out.print(num + " ");
            }
            System.out.println("\n최소 트리");
            for(int num : minTree) {
                System.out.print(num + " ");
            }
            System.out.println("\n최대 트리");
            for(int num: maxTree) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
