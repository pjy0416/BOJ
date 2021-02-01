package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BestFriends_3078 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        int[] students = new int[n];
        for(int i=0; i<n; i++) {
            students[i] = br.readLine().length();
        }

        br.close();
        solution(n,k,students);
    }

    private static void solution(int n, int k, int[] students) {
        final int MAX = 20, MIN =2, DUPLICATE_COUNT =1;
        int[] bestFriends = new int[MAX+1];
        Queue<int[]> queue = new LinkedList<>();
        int answer =0;

        for(int i=0; i<n; i++) {
            queue.offer(new int[] {i, students[i]});
            bestFriends[students[i]]++;
            if(!isInRange(i, queue.peek()[0], k)) {
                bestFriends[queue.poll()[1]]--;
            }
            if(bestFriends[students[i]] >=MIN) {
                answer += bestFriends[students[i]] - DUPLICATE_COUNT;
            }
        }
        System.out.println(answer);
    }

    private static boolean isInRange(int now, int target, int k) {
        return now -k <= target && now + k >= target;
    }
}
