package kakao;

import java.util.HashSet;

public class test4 {
    static boolean[] isIN;
    static int MINROOMNUM = 0;

    public static void main(String[] args) {
        long k = 10;
        long[] room_number = {1,3,4,1,3,1};

        solution2(k,room_number);
    }

    public static long[] solution2(long k, long[] room_number) {
        int len = room_number.length;
        long[] answer = new long[len];
        inItBool(len);
        int idx = 0;

        for(long num : room_number) {
            int wantedRoom = (int) (num - 1);   // 방의 인덱스 화     1번 방

            if (isIN[(int) wantedRoom]) { // 방 있으면
                isIN[wantedRoom] = true;
                answer[idx++] = wantedRoom+1;
            } else {        // 없으면
                int i = findRoom(len);
                isIN[i] = true;
                answer[idx++] = i+1;
            }

        }
        return answer;
    }

    private static void inItBool(int len) {
        isIN = new boolean[len];
        for(int i=0; i<len; i++) {
            isIN[i] = false;
        }
    }

    static int findRoom(int max) {
        for(int i=MINROOMNUM; i<max; i++) {
            if(!isIN[i]) {
                return i;
            }
        }
        return 0;
    }
}



/*
방 배정하는거, 손님이 원하는 방 번호와, 준비된 방 번호에서 배정되지 않은 방이면 원하는 방으로 배정, 이미 배정된 곳이면 최대한 빠른 룸 넘버로 배정


*/
