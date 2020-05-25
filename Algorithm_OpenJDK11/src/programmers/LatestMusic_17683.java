package programmers;

import java.util.PriorityQueue;

public class LatestMusic_17683 {
    private static String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        PriorityQueue<Music> pq = new PriorityQueue<>();
        for(String info : musicinfos) {
            String[] infoArr = info.split(",");
            pq.offer(new Music(infoArr[0],infoArr[1],infoArr[2],infoArr[3]));
        }

        while(!pq.isEmpty()) {
            Music music = pq.poll();
            if(music.isPart(m)) {
                answer = music.name;
                break;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String m = "CC#BCC#BCC#BCC#B";
        String[] musicinfos = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};
        System.out.println(solution(m, musicinfos));
    }
}

class Music implements Comparable<Music>{
    private final char SHARP = '#';
    int playTime;
    String note;
    String name;

    public Music(String startTime, String endTime, String name, String notes) {
        this.name = name;
        this.playTime = getTime(endTime) - getTime(startTime);
        this.note = getNote(notes, this.playTime);
    }

    public int getTime(String timeStr) {
        String[] timeArr = timeStr.split(":");
        int hour = Integer.parseInt(timeArr[0]);
        int min = Integer.parseInt(timeArr[1]);

        return hour*60 + min;
    }

    public String getNote(String notes, int time) {
        StringBuffer sb = new StringBuffer();
        int len = notes.length();
        int remain = time;

        if(notes.replaceAll("[^#]","").length() !=0) {
            len -= notes.replaceAll("[^#]","").length();
        }

        while(remain > 0) {
            if(remain -len >=0) {
                sb.append(notes);
                remain-= len;
            } else {    // 잔여 횟수가 len보다 적게 남았으면
                for(int i=0; i<notes.length(); i++) {
                    char ch = notes.charAt(i);
                    if(ch != SHARP) {
                        sb.append(ch);
                        remain--;
                    }
                    if(i+1 < notes.length()) {
                        if(notes.charAt(i+1) == SHARP) {
                            sb.append(SHARP);
                        }
                    }
                    if(remain ==0) {
                        break;
                    }
                }
            }
        }
        return sb.toString();
    }

    public boolean isPart(String str) {
        boolean result = false;
        int len = str.length();
        for(int i=0; i<=this.note.length()-len; i++) {
            StringBuffer part = new StringBuffer(this.note.substring(i,i+len));

            if(i+len < this.note.length()) {
                if(this.note.charAt(i+len) == SHARP) {
                    part.append(SHARP);
                }
            }
            if(part.toString().equals(str)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public int compareTo(Music m) {
        return this.playTime <= m.playTime ? 1 : -1;
    }
}
