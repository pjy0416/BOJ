package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Biologist_3116 {
    static final int[] dx = {0,-1,0,1,1,1,0,-1,-1}, dy = {0,1,1,1,0,-1,-1,-1,0};
    static final int NO_MEETING = 0, DEFAULT_COUNT =2, MAX_TIME = 2000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        Bacteria[] bacteriaArr = new Bacteria[n];

        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            bacteriaArr[i] = new Bacteria(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        br.close();
        solution(n, bacteriaArr);
    }

    private static void solution(int n, Bacteria[] bacteriaArr) {
        // 만나는지 검사
        int[] answerArr = inspection(n,bacteriaArr);
        // print
        printAnswer(answerArr);
    }

    private static int[] inspection(int n, Bacteria[] bacteriaArr) {
        int[] groups = new int[MAX_TIME];
        int[] counts = new int[MAX_TIME];
        int groupNum =0;
        int[] answer = new int[2];

        // 칸 구분해줘야함
        for(int i=0; i<n; i++) {
            groupNum++;
            for(int j=i; j<n; j++) {
                int time = getMeetTime(bacteriaArr[i],bacteriaArr[j]);
                if(time ==NO_MEETING) {
                    continue;
                }
                if (groups[time] != groupNum ) {
                    groups[time] = groupNum;
                    counts[time] =DEFAULT_COUNT;
                } else {
                    counts[time]++;
                }
                if(isChangeAnswer(counts, answer, time)) {
                    answer[0] = counts[time];
                    answer[1] = time;
                }
            }
        }

        return answer;
    }
    private static boolean isChangeAnswer(int[] counts, int[] answer, int time) {
        return counts[time] > answer[0] || (counts[time] == answer[0] && time < answer[1]);
    }

    private static int getMeetTime(Bacteria origin, Bacteria target) {
        int x=NO_MEETING;  // x가 만나는 시간, 0 인 경우는 언제든 만남
        int y=NO_MEETING;  // x가 만나는 시간, 0 인 경우는 언제든 만남

        int xDiv = dx[target.direction] - dx[origin.direction];
        int yDiv = dy[target.direction] - dy[origin.direction];

        if(xDiv !=0) {
            int xMod = (origin.x - target.x) % xDiv;
            x = xMod ==0 ? (origin.x - target.x) / xDiv : NO_MEETING;
        }
        if(yDiv !=0) {
            int yMod = (origin.y - target.y) % yDiv;
            y = yMod ==0 ? (origin.y - target.y) / yDiv : NO_MEETING;
        }

        if(x==0 && y==0) { // 만나지 못하는 경우 ~> 같은 방향
            return NO_MEETING;
        } else if(x <0 || y<0) { // 서로 다른 방향으로 진행할 경우
            return NO_MEETING;
        }
        int time = Math.max(x,y); // x == y or 0,y 초 or x,0 초에 만남

        return (origin.x + dx[origin.direction]*time == target.x + dx[target.direction]*time) &&
                (origin.y + dy[origin.direction]*time == target.y + dy[target.direction]*time) ?
                time : NO_MEETING;
    }

    private static void printAnswer(int[] answerArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(answerArr[0]).append("\n").append(answerArr[1]);
        System.out.println(sb.toString());
    }

    private static class Bacteria {
        int x,y,direction;

        public Bacteria(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
}
