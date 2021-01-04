package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IntervalSum_2042 {
    final static int UPDATE =1, SUM =2;
    final static String NEWLINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        long[] numArr = new long[n];
        for(int i=0; i<n; i++) {
            numArr[i] = Long.parseLong(br.readLine());
        }

        m+=k;
        long[][] commandArr = new long[m][3];
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                commandArr[i][j] = Long.parseLong(st.nextToken());
            }
        }
        br.close();
        solution(n, numArr, commandArr);
    }

    private static void solution(int n, long[] numArr, long[][] commandArr) {
        SegmentTree segmentTree = new SegmentTree(n, numArr);
        segmentTree.init(1, 0, n-1);
        StringBuilder sb = new StringBuilder();

        for(long[] commandInfos : commandArr) {
            final int command = (int) commandInfos[0];
            if(command == UPDATE) {
                int index = (int) commandInfos[1]-1;
                long diff = commandInfos[2] - numArr[index];
                numArr[index] = commandInfos[2];
                segmentTree.update(1, 0, n-1, index, diff);
            } else if(command == SUM) {
                int left = (int) commandInfos[1] -1, right = (int) commandInfos[2]-1;
                sb.append(segmentTree.sum(1, 0, n-1, left, right)).append(NEWLINE);
            }
        }
        System.out.println(sb.toString());
    }

    private static class SegmentTree {
        long[] tree, numArr;
        private int size;

        public SegmentTree(int n, long[] numArr) {
            this.size = 4*n;
            this.tree = new long[this.size];
            this.numArr = numArr;
        }

        public long init(int node, int start, int end) {
            if(start == end) {
                return this.tree[node] = this.numArr[start];
            }
            int mid = (start+end)/2;
            return tree[node] = this.init(node*2, start, mid) + this.init(node*2+1, mid+1, end);
        }

        public void update(int node, int start, int end, int index, long diff) {
            if (index < start || index > end){
                return;
            }
            tree[node] = tree[node] + diff;
            if (start != end) {
                int mid = (start+end)/2;
                this.update(node*2, start, mid, index, diff);
                this.update(node*2+1, mid+1, end, index, diff);
            }
        }

        public long sum(int node, int start, int end, int left, int right) {
            if(left > end || right < start) {
                return 0;
            }
            if(left <= start && right >= end) {
                return this.tree[node];
            }
            int mid = (start+end)/2;
            return this.sum(node*2, start, mid, left, right) + this.sum(node*2+1, mid +1, end, left, right);
        }
    }
}
/*
 * 세그먼트 트리
 * https://www.acmicpc.net/blog/view/9
 *
 * 펜윅 트리
 * https://www.acmicpc.net/blog/view/21
 */