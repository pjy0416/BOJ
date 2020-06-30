package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EggsCrash_16987 {
    private static int ANSWER;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[][] eggs = new int[size][2];

        for(int i=0; i<size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken());  // 내구도
            eggs[i][1] = Integer.parseInt(st.nextToken());  // 무게
        }

        solution(size, eggs);
        br.close();
    }

    private static void solution(int size, int[][] eggs) {
        ANSWER = 0;
        crashEggs(0, size, eggs);
        System.out.println(ANSWER);
    }

    private static void crashEggs(int now, int size, int[][] eggs) {
        if(now < size) { // out of idx 방지
            if (eggs[now][0] > 0) {   // 손에 든 계란이 깨지지 않은 경우만
                for (int i = 0; i < size; i++) {
                    if (eggs[i][0] > 0 && i != now) {    // 깨지지 않은 계란일 경우에만 친다
                        eggs[i][0] -= eggs[now][1]; // i의 내구도를 now의 무게만큼 줄여준다.
                        eggs[now][0] -= eggs[i][1]; // now의 내구도를 i의 무게만큼 줄여준다.
                        crashEggs(now + 1, size, eggs);
                        eggs[i][0] += eggs[now][1];
                        eggs[now][0] += eggs[i][1];
                    }
                }
            } else {
                crashEggs(now+1, size, eggs);
            }
        }
        int cnt =0;
        for(int i=0; i<size; i++) {
            if(eggs[i][0] <=0) {
                cnt++;
            }
        }
        ANSWER = Math.max(cnt, ANSWER);
    }
}
