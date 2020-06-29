package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Emoticon_14226 {
    public final static int[] results = {0,0,2, 3, 4, 5, 5, 7, 6, 6, 7, 8, 7, 10, 9, 8, 8, 9, 8, 10, 9, 10, 10, 10, 9, 10, 10, 9, 11, 11, 10, 11, 10, 11, 11, 11, 10, 13, 12, 12, 11, 13, 12, 13, 12, 11, 12, 12, 11, 13, 12, 12, 12, 12, 11, 13, 13, 13, 13, 13, 12, 14, 13, 13, 12, 14, 13, 14, 13, 13, 13, 13, 12, 15, 14, 13, 14, 14, 13, 14, 13, 12, 15, 15, 14, 14, 15, 14, 14, 14, 13, 15, 14, 14, 14, 14, 13, 16, 15, 14, 14, 15, 14, 15, 14, 14, 14, 14, 13, 16, 15, 16, 15, 16, 15, 15, 15, 15, 15, 15, 14, 17, 16, 16, 15, 15, 15, 15, 14, 16, 15, 16, 15, 16, 15, 14, 15, 16, 15, 16, 15, 15, 15, 15, 14, 16, 17, 16, 16, 16, 15, 17, 16, 15, 16, 16, 15, 17, 16, 15, 15, 15, 14, 18, 17, 16, 17, 17, 16, 17, 16, 16, 17, 17, 16, 16, 16, 16, 16, 16, 15, 18, 17, 17, 16, 17, 16, 17, 16, 16, 16, 16, 15, 19, 18, 17, 17, 17, 16, 17, 16, 17, 17, 17, 16, 18, 17, 16, 16, 17, 16, 17, 16, 16, 16, 16, 15, 18, 18, 18, 17, 18, 17, 18, 17, 16, 18, 18, 17, 18, 17, 17, 17, 17, 16, 17, 17, 17, 17, 17, 16, 17, 16, 15, 18, 18, 18, 18, 17, 18, 17, 18, 17, 18, 17, 17, 16, 19, 18, 18, 17, 17, 18, 18, 17, 17, 18, 17, 17, 17, 16, 18, 17, 18, 18, 18, 17, 19, 18, 17, 17, 18, 17, 18, 17, 17, 17, 17, 16, 19, 18, 19, 19, 19, 18, 18, 18, 17, 18, 18, 17, 20, 19, 18, 18, 18, 17, 19, 18, 18, 18, 18, 17, 19, 18, 17, 18, 18, 17, 18, 17, 17, 17, 17, 16, 19, 20, 19, 19, 19, 18, 20, 19, 19, 19, 19, 18, 20, 19, 19, 18, 19, 18, 20, 19, 18, 19, 19, 18, 19, 18, 18, 18, 19, 18, 18, 18, 18, 18, 18, 17, 21, 20, 20, 19, 20, 19, 19, 18, 19, 19, 19, 18, 20, 19, 18, 18, 19, 18, 19, 18, 18, 18, 18, 17, 19, 20, 19, 20, 19, 18, 20, 19, 19, 19, 19, 18, 20, 19, 19, 18, 19, 18, 19, 18, 17, 19, 19, 18, 21, 20, 19, 19, 19, 18, 19, 18, 19, 19, 19, 18, 20, 19, 18, 18, 19, 18, 19, 18, 18, 18, 18, 17, 21, 20, 19, 20, 21, 20, 20, 19, 19, 20, 20, 19, 19, 20, 19, 19, 19, 18, 21, 20, 20, 20, 20, 19, 20, 19, 18, 19, 20, 19, 20, 19, 19, 19, 19, 18, 20, 19, 20, 19, 20, 19, 19, 19, 18, 19, 19, 18, 20, 19, 18, 18, 18, 17, 21, 20, 21, 20, 21, 20, 21, 20, 19, 19, 20, 20, 20, 19, 20, 20, 20, 19, 20, 20, 20, 19, 20, 19, 19, 18, 19, 21, 20, 20, 21, 20, 20, 19, 20, 19, 21, 20, 19, 20, 20, 19, 20, 19, 19, 20, 20, 19, 19, 19, 19, 19, 19, 18, 21, 20, 20, 19, 21, 20, 21, 20, 20, 20, 20, 19, 21, 21, 20, 20, 20, 19, 20, 19, 20, 20, 20, 19, 21, 20, 19, 19, 20, 19, 20, 19, 19, 19, 19, 18, 22, 21, 21, 20, 22, 21, 22, 21, 20, 21, 21, 20, 21, 20, 20, 20, 20, 19, 20, 20, 20, 20, 20, 19, 22, 21, 20, 21, 21, 20, 21, 20, 20, 20, 20, 19, 22, 21, 21, 20, 21, 20, 21, 20, 19, 20, 20, 19, 20, 21, 20, 20, 20, 19, 21, 20, 20, 20, 20, 19, 21, 20, 19, 19, 20, 19, 20, 19, 19, 19, 19, 18, 21, 20, 21, 22, 22, 21, 21, 21, 21, 21, 21, 20, 23, 22, 21, 21, 21, 20, 22, 21, 21, 20, 21, 20, 21, 20, 19, 21, 22, 21, 21, 20, 21, 21, 21, 20, 21, 22, 21, 21, 21, 20, 22, 21, 20, 21, 21, 20, 22, 21, 20, 20, 20, 19, 21, 20, 20, 21, 21, 20, 21, 20, 20, 20, 21, 20, 20, 20, 20, 20, 20, 19, 22, 21, 20, 21, 20, 19, 20, 19, 18, 22, 22, 21, 22, 21, 21, 20, 22, 21, 22, 21, 21, 21, 21, 20, 21, 22, 21, 21, 21, 20, 21, 20, 21, 21, 21, 20, 22, 21, 21, 20, 21, 20, 21, 20, 20, 20, 20, 19, 22, 21, 22, 22, 22, 21, 21, 22, 21, 21, 21, 20, 22, 21, 20, 21, 22, 21, 22, 21, 21, 21, 21, 20, 22, 21, 20, 21, 22, 21, 21, 20, 20, 21, 21, 20, 20, 21, 20, 20, 20, 19, 22, 21, 21, 21, 21, 20, 23, 22, 21, 22, 22, 21, 22, 21, 21, 21, 21, 20, 22, 21, 21, 20, 22, 21, 22, 21, 20, 21, 21, 20, 23, 22, 21, 21, 21, 20, 21, 20, 21, 21, 21, 20, 22, 21, 20, 20, 21, 20, 21, 20, 20, 20, 20, 19, 22, 23, 22, 22, 22, 21, 23, 22, 22, 22, 21, 22, 23, 22, 22, 21, 22, 21, 23, 22, 21, 22, 22, 21, 22, 21, 20, 22, 22, 21, 21, 21, 21, 21, 21, 20, 24, 23, 23, 22, 23, 22, 23, 22, 21, 22, 22, 21, 23, 22, 21, 21, 21, 20, 22, 21, 22, 22, 22, 21, 22, 22, 21, 21, 22, 21, 22, 21, 21, 21, 21, 20, 23, 22, 22, 21, 22, 21, 22, 21, 20, 22, 22, 21, 22, 21, 21, 21, 21, 20, 21, 21, 21, 21, 21, 20, 22, 21, 20, 21, 21, 20, 21, 20, 20, 20, 20, 19, 23, 23, 22, 22, 24, 23, 23, 22, 22, 23, 23, 22, 22, 23, 22, 22, 22, 21, 22, 21, 23, 22, 22, 22, 23, 22, 22,
            21};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        myFunc();
        solution(Integer.parseInt(br.readLine()));
        br.close();
    }
    /*
    private static void myFunc() {
        for(int i=2; i<=1000; i++) {
            if(results[i] != solution2(i)) {
                System.out.println(i);
            }
        }
    }

    private static int solution2(int n) {
        final int MAX = 1001;
        LinkedList<EmoInfo> emoticon = new LinkedList<>();
        emoticon.offer(new EmoInfo(0,1,0)); // 초기
        boolean[][] visit = new boolean[MAX][MAX];
        int ans=0;

        while(!emoticon.isEmpty()) {
            EmoInfo now = emoticon.poll();

            int clipboard = now.clipboard;
            int screen = now.screen;
            int step = now.step;

            if(screen == n) {
                ans = step;
                break;
            }
            if(screen >= clipboard) {
                // 1. 클립보드 저장
                if (clipboard < screen && !visit[screen][screen]) {
                    emoticon.offer(new EmoInfo(screen, screen, step + 1));
                    visit[screen][screen] = true;
                }
                // 2. 클립보드에 있는 값 붙여넣기
                if(clipboard+screen <MAX) {
                    if (!visit[screen + clipboard][clipboard]) {
                        emoticon.offer(new EmoInfo(clipboard, screen + clipboard, step + 1));
                        visit[screen + clipboard][clipboard] = true;
                    }
                }
                // 3. 화면에 있는 스마일 하나 지우기
                if(screen-1 >=2) {
                    if (!visit[screen - 1][clipboard]) {
                        emoticon.offer(new EmoInfo(clipboard, screen - 1, step + 1));
                        visit[screen - 1][clipboard] = true;
                    }
                }
            }
        }
        return ans;
    }*/

    private static void solution(int n) {
        final int MAX = 1001;
        LinkedList<EmoInfo> emoticon = new LinkedList<>();
        emoticon.offer(new EmoInfo(0,1,0)); // 초기
        boolean[][] visit = new boolean[MAX][MAX];


        while(!emoticon.isEmpty()) {
            EmoInfo now = emoticon.poll();

            int clipboard = now.clipboard;
            int screen = now.screen;
            int step = now.step;

            if(screen == n) {
//                System.out.println(step + "\t" +screen + "\t" +clipboard);
//                System.out.println(results[n]);
                System.out.println(step);
                break;
            }
            if(screen >= clipboard) {
                // 1. 클립보드 저장
                if (clipboard < screen && !visit[screen][screen]) {
                    emoticon.offer(new EmoInfo(screen, screen, step + 1));
                    visit[screen][screen] = true;
                }
                // 2. 클립보드에 있는 값 붙여넣기
                if(clipboard+screen <MAX) {
                    if (!visit[screen + clipboard][clipboard]) {
                        emoticon.offer(new EmoInfo(clipboard, screen + clipboard, step + 1));
                        visit[screen + clipboard][clipboard] = true;
                    }
                }
                // 3. 화면에 있는 스마일 하나 지우기
                if(screen-1 >=2) {
                    if (!visit[screen - 1][clipboard]) {
                        emoticon.offer(new EmoInfo(clipboard, screen - 1, step + 1));
                        visit[screen - 1][clipboard] = true;
                    }
                }
            }
        }
    }

    public static int[] getResult(BufferedReader br) throws IOException {
        int[] abc = new int[1001];

        for(int i=2; i<=1000; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            abc[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }
        return abc;
    }
    // 행동
    // 1 : 현재 입력된 스마일 클립보드에 저장
    // 2 : 클립보드에 저장된 스마일 붙여넣기
    // 3 : 화면에 있는 스마일 하나 지우기
}

class EmoInfo {
    int clipboard; // 클립보으데 저장된 스마일
    int screen; // 화면에 표시되어있는 스마일
    int step;

    public EmoInfo(int clipboard, int screen, int step) {
        this.clipboard = clipboard;
        this.screen = screen;
        this.step = step;
    }

}
