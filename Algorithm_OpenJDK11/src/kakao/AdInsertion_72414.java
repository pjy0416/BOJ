package kakao;

import java.util.StringTokenizer;

/*
동영상 재생시간과 공익광고 재생시간은 00시간 00분 01초 이상 99시간 59분 59초 이하입니다.
동영상 재생시간 = 재생이 종료된 시각 - 재생이 시작된 시각
(예를 들어, 00시 00분 01초부터 00시 00분 10초까지 동영상이 재생되었다면, 동영상 재생시간은 9초 입니다.) ↩
공익광고 재생시간은 동영상 재생시간보다 짧거나 같게 주어집니다.
 */
public class AdInsertion_72414 {
  final static String TIME_DELIM = ":", ADDITIONAL_TIME_STRING = "0", LOG_DELIM = "-";
  final static int SCALE = 60, TIME_ARRAY_LENGTH =3, TIME_STRING_LENGTH =2;

  static int maxEnd =0; // 마지막으로 시청을 종료한 시점에 대한 저장

  public static void main(String[] args) {
    String[] play_times = {"02:03:55", "99:59:59", "50:00:00"};
    String[] adv_times = {"00:14:15", "25:00:00", "50:00:00"};
    String[][] logs = {
            {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"},
            {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"},
            {"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"}
    };
    String[] results = {"01:30:59", "01:00:00", "00:00:00"};

    for(int i=0; i<3; i++) {
      System.out.println("expected => " + results[i]);
      System.out.println("received => " + solution(play_times[i], adv_times[i], logs[i]));
    }
  }

  public static String solution(String play_time, String adv_time, String[] logs) {
    maxEnd =0; // maxEnd가 전역 변수기 때문에, 현재 main에서 돌릴려면 0으로 초기화해야함
    int maxSeconds = getSeconds(play_time);
    int advTimes = getSeconds(adv_time);

    long[] timeLapse = getTimeLapse(logs, maxSeconds);
    return getAnswer(timeLapse,advTimes, maxSeconds);
  }

  private static long[] getTimeLapse(String[] logs, int maxSeconds) {
    long[] timeLapse = new long[maxSeconds+1];
    StringTokenizer st = null;
    for(String log : logs) {
      st = new StringTokenizer(log, LOG_DELIM);
      int start = getSeconds(st.nextToken());
      int end = getSeconds(st.nextToken());
      for(int i = start; i<end; i++) { // 종료 시점에는 시청을 안한 것으로 판단.
        timeLapse[i]++;
      }
      maxEnd = Math.max(maxEnd, end); // 마지막 시청 종료 시간
    }
    return timeLapse;
  }

  private static int getSeconds(String timeString) {
    int seconds =0;
    StringTokenizer st = new StringTokenizer(timeString, TIME_DELIM);
    seconds += Integer.parseInt(st.nextToken())*SCALE*SCALE;
    seconds += Integer.parseInt(st.nextToken())*SCALE;
    seconds += Integer.parseInt(st.nextToken());

    return seconds;
  }

  private static String getTimeString(int seconds) {
    StringBuilder[] timeArr = new StringBuilder[TIME_ARRAY_LENGTH];
    for(int i=TIME_ARRAY_LENGTH-1; i>=0; i--) {
      timeArr[i] = new StringBuilder();
      if(i > 0) {
        timeArr[i].append(seconds % SCALE);
        seconds/= SCALE;
      } else {
        timeArr[i].append(seconds);
      }
      if(timeArr[i].length() < TIME_STRING_LENGTH) {
        timeArr[i].insert(0,ADDITIONAL_TIME_STRING);
      }
    }

    return String.join("", timeArr[0], TIME_DELIM, timeArr[1], TIME_DELIM, timeArr[2]);
  }

  private static String getAnswer(long[] timeLapse, int advTimes, int maxSeconds) {
    int start =0;
    long counts =0;
    for(int i=0; i<=advTimes; i++) { // 시작하자마자 광고를 튼 경우
      counts+= timeLapse[i];
    }

//    long max = counts;
//    for(int i=advTimes+1; i<=maxEnd; i++) {
//      counts = counts - timeLapse[i-advTimes] + timeLapse[i];
//      if(counts > max) {
//        counts = max;
//        start = i-advTimes+1;
//      }
//    }

    long max = counts;
    for(int i=1; advTimes+i <=maxSeconds; i++) { // 1초 시작 ~ 마지막 종료 시점에 광고가 끝나는 상황까지 탐색
      counts = counts - timeLapse[i-1] + timeLapse[advTimes+i-1]; // 1초씩 광고 시간을 옮겨가면서, 시청자를 세줌
      if(counts > max) {
        counts = max;
        start = i;
      }
    }

    return getTimeString(start);
  }
}
