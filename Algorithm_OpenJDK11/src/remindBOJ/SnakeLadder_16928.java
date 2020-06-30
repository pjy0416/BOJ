package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SnakeLadder_16928 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ladders = Integer.parseInt(st.nextToken());
        int snakes = Integer.parseInt(st.nextToken());
        HashMap<Integer, Integer> snakeMap = new HashMap<>();
        HashMap<Integer, Integer> ladderMap = new HashMap();

        while(snakes >0 || ladders >0) {
            st = new StringTokenizer(br.readLine());
            if(ladders-- >0) {
                ladderMap.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            } else if(snakes-- >0){
                snakeMap.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
        }

        solution(snakeMap,ladderMap);

        br.close();
    }

    private static void solution(HashMap<Integer, Integer> snakeMap, HashMap<Integer, Integer> ladderMap) {
        final int MAX = 100;
        final int DICE = 6;
        boolean[][] visit = new boolean[MAX+1][MAX+1];

        LinkedList<MoveInfo> moveList = new LinkedList();

        moveList.offer(new MoveInfo(1,0));

        while(!moveList.isEmpty()) {
            MoveInfo moveInfo = moveList.poll();
            int now = moveInfo.idx;
            int step = moveInfo.step;

            if(now == MAX) {
                System.out.println(step);
                break;
            }
            step++;

            for(int i=1; i<=DICE; i++) {
                int next = now+i;
                if(next <=MAX) { // out of idx 방지
                    if(!visit[next][step]) { // 방문 확인
                        visit[next][step] = true;
                        if(ladderMap.containsKey(next)) {  // 사다리가 있는 경우
                            next = ladderMap.get(next);
                            visit[next][step] = true;
                        } else if(snakeMap.containsKey(next)) { // 뱀이 있는 경우
                            next = snakeMap.get(next);
                            visit[next][step] = true;
                        }
                        moveList.offer(new MoveInfo(next, step));
                    }
                }
            }
        }
    }
}

class MoveInfo {
    int idx;
    int step;

    public MoveInfo(int idx, int step) {
        this.idx = idx;
        this.step = step;
    }
}