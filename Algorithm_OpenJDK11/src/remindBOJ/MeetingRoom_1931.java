package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MeetingRoom_1931 {
    private static class Meeting {
        int start, end;
        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Meeting[] applies = new Meeting[n];
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            applies[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        br.close();
        solution(n,applies);
    }

    private static void solution(int n, Meeting[] applies) {
        int prev =-1;
        int cnt =0;

        Arrays.sort(applies,(Meeting m1, Meeting m2) -> m1.end == m2.end ? Integer.compare(m1.start,m2.start) : Integer.compare(m1.end, m2.end));
        for(Meeting meeting : applies) {
            if(prev <= meeting.start) { // 이전 종료 시간과 요청이 들어온 미팅의 시작 시간을 비교
                prev = meeting.end;
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
