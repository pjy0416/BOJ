package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RoomArrangement_14697 {
  static final int ROOM_LENGTH =3, ZERO =0, MAX_STUDENTS = 300;

  static int[] rooms;
  static boolean[] visit;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    rooms = new int[ROOM_LENGTH];
    for(int i=0; i<ROOM_LENGTH; i++) {
      rooms[i] = Integer.parseInt(st.nextToken());
    }
    int n = Integer.parseInt(st.nextToken());

    br.close();
    solution(n);
  }

  private static void solution(int n) {
    visit = new boolean[MAX_STUDENTS+1];
    find(n);
    System.out.println(visit[ZERO] ? 1 : 0);
  }

  private static void find(int remain) {
    for(int room : rooms) {
      int idx = remain-room;
      if(idx >= ZERO && !visit[idx]) {
        visit[idx] = true;
        find(idx);
      }
    }
  }
}
