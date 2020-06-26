package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HideAndSeek_1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int left = Integer.parseInt(st.nextToken());
        int right = Integer.parseInt(st.nextToken());

        solution(left,right);

        br.close();

    }

    private static void solution(int left, int right) {
        final int MAX = 100001;
        int answer =0;
        int[] steps = new int[MAX];
        Queue<Integer> moveQ = new LinkedList<>();
        moveQ.offer(left);

        int[] dx = new int[3];
        dx[0] = 1;
        dx[1] = -1;

        while(!moveQ.isEmpty()) {
            int idx = moveQ.poll();
            if(idx == right) {
                answer = steps[idx];
                break;
            }
            dx[2] = idx;

            for(int i=0; i<3; i++) {
                int nx = idx + dx[i];
                if(nx >=0 && nx < MAX) {
                    if(steps[nx] ==0) {
                        steps[nx] = steps[idx]+1;
                        moveQ.offer(nx);
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
/*
public class HideAndSeek_1697 { // 첫번째 정답
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int left = Integer.parseInt(st.nextToken());
        int right = Integer.parseInt(st.nextToken());

        solution(left,right);

        br.close();

    }

    private static void solution(int left, int right) { // 63퍼
        final int MAX = 100001;
        int answer =0;
        boolean[] visit = new boolean[MAX];
        Queue<Spot> moveQ = new LinkedList<>();
        moveQ.offer(new Spot(left,0));

        int[] dx = new int[3];
        dx[0] = 1;
        dx[1] = -1;

        while(!moveQ.isEmpty()) {
            Spot spot = moveQ.poll();
            int idx = spot.idx;
            int step = spot.step;
            if(idx == right) {
                answer = step;
                break;
            }
            dx[2] = idx;
            step++;

            for(int i=0; i<3; i++) {
                int nx = idx + dx[i];
                if(nx >=0 && nx < MAX) {
                    if(!visit[nx]) {
                        visit[nx] = true;
                        moveQ.offer(new Spot(nx, step));
                    }
                }
            }
        }
        System.out.println(answer);
    }
}

class Spot {
    int idx;
    int step;

    public Spot(int idx, int step) {
        this.idx = idx;
        this.step = step;
    }
}
 */
// https://www.acmicpc.net/problem/1697