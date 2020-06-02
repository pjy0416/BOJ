package programmers;

import java.util.PriorityQueue;

public class ShuttleBus_17678 {
    private static String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        PriorityQueue<MyTime> crewQ = new PriorityQueue<>();
        for(String timeStr : timetable) {
            crewQ.offer(new MyTime(timeStr));
        }

        int bus = 540;
        // 앞에 대기열 다 빼내기
        for(int turn =0; turn<n-1; turn++) {
            for(int i=0; i<m; i++) {
                if(crewQ.peek().time <= bus+turn*t) {
                    crewQ.poll();
                }
            }
        }

        int lastTime = bus+(n-1)*t;
        if(crewQ.size() < m) {  // 남은 대기열이 제일 늦게가도 탈 수 있으면
            answer = getStrTime(lastTime);
        } else {    // 여기서 탐색해야함
            int max =0;
            for(int i=0; i<m; i++) {
                max = Math.max(max, crewQ.poll().time);
            }
            if(max > lastTime) {
                max = lastTime;
            } else {
                max--;
            }
            answer = getStrTime(max);
        }

        return answer;
    }

    private static String getStrTime(int time) {
        StringBuffer sb =  new StringBuffer();
        // time / 60  ~> 시간
        int hour = time/60;
        int min = time%60;
        if(hour <10) {
            sb.append("0");
        }
        sb.append(String.valueOf(hour));
        sb.append(":");
        if(min < 10) {
            sb.append("0");
        }
        sb.append(String.valueOf(min));
        return sb.toString();
    }
    public static void main(String[] args) {
        int n =2;
        int t= 10;
        int m =2;
        String[] timetable = {"09:10", "09:09", "08:00"};
        System.out.println(solution(n,t,m,timetable));
    }
}

class MyTime implements Comparable<MyTime> {
    int time;

    public MyTime(String timeStr) {
        this.time = Integer.parseInt(timeStr.split(":")[0])*60 + Integer.parseInt(timeStr.split(":")[1]);
    }

    @Override
    public int compareTo(MyTime t) {
        return this.time > t.time ? 1 : -1;
    }
}
/*
https://programmers.co.kr/learn/courses/30/lessons/17678
 */