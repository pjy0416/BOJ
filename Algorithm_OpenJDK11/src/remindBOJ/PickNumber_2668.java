package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class PickNumber_2668 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] numArr = new int[n+1];
        for(int i=1; i<=n; i++)  numArr[i] = Integer.parseInt(br.readLine());
        br.close();
        solution(n,numArr);
    }

    private static void solution(int n, int[] numArr) {
        ArrayList<Integer> ansList = new ArrayList<>();
        for(int i=1; i<=n; i++) {
            LinkedList<Integer> moveList = new LinkedList<>();
            moveList.offerFirst(numArr[i]);
            boolean[] visit = new boolean[n+1];
            while(!moveList.isEmpty()) {
                int now = moveList.poll();
                if(now == i) {
                    ansList.add(i);
                    break;
                }
                if(!visit[numArr[now]]) {
                    moveList.offer(numArr[now]);
                    visit[numArr[now]] = true;
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        final String NEWLINE = "\n";
        sb.append(ansList.size()).append(NEWLINE);
        for(int num : ansList) sb.append(num).append(NEWLINE);
        System.out.println(sb.toString());
    }
}
