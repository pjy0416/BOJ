package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GreatContest_14610 {
    public static void main(String[] args) throws IOException {
        System.out.println(solution(new BufferedReader(new InputStreamReader(System.in))));
    }

    private static String solution(BufferedReader br) throws IOException{
        final String YES = "YES";
        final String NO = "NO";

        String str = br.readLine();
        int N = Integer.parseInt(str.split(" ")[0]);   // 참가자 수
        int M = Integer.parseInt(str.split(" ")[1]);   // 문제 수
        int[] problems = new int[M]; // 문제 풀이 여부를 담을 배열

        Arrays.fill(problems,0);   // 모두 못푼 상태로 초기화
        LinkedList<Participant> remainList = new LinkedList<>();

        loop:
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Participant person = new Participant();
            for(int j=0; j<=M; j++) {
                if(j ==0) { // 푼 문제 수 담기
                    int solve = Integer.parseInt(st.nextToken());
                    if(solve ==0) { // 1번 조건
                        return NO;
                    } else if(solve == M) { // 3번 조건
                        return NO;
                    }
                    person.setRemain(solve);
                } else { // 문제에 대한 풀이 여부
                    int prob = Integer.parseInt(st.nextToken());
                    if(prob ==1) { // 풀었을 때
                        problems[j-1] = prob; // 풀었다고 체크
                        person.remain--;
                    } else if(prob ==-1) {
                        if(problems[j-1] == 0) { // 못 푼 상태일 때만
                            problems[j-1] = prob; // 못풀었다고 체크
                        }
                        person.addList(j-1);
                    }
                }
            }
            if(person.remain !=0) {
                remainList.add(person);
            }
        }

        while(!remainList.isEmpty()) {
            Participant person = remainList.pollLast();
            for(int idx : person.list) {
                if(problems[idx] ==-1 && person.remain-- >0) {
                    problems[idx] =1;
                }
            }
        }

        for(int prob : problems) {
            if(prob != 1) { // 2번 조건
                return NO;
            }
        }


        br.close();
        return YES;
    }
}

class Participant {
    int remain;
    LinkedList<Integer> list;

    public Participant() {
        this.remain =0;
        list = new LinkedList<>();
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }

    public void addList(int problem) {
        list.offer(problem);
    }
}

/*
https://www.acmicpc.net/problem/14610

1. 모든 참가자가 한 문제 이상을 풀어야 한다.
2. 모든 문제는 한 명 이상의 참가자에게 풀려야 한다.
3. 모든 문제를 푼 참가자는 없어야 한다.

5 7         //4 문제
4 1 1 1 - - - -      // 1
3 1 1 1 0 0 0 0      // 0
3 1 1 1 0 0 0 0      // 0
2 0 0 0 0 0 - -      // 2
1 0 0 0 0 0 - -      // 1

5 7
4 1 1 1 -1 -1 -1 -1
3 1 1 1 0 -1 -1 -1
3 1 1 1 0 -1 -1 -1
2 0 0 0 0 0 -1 -1
1 0 0 0 0 0 -1 -1
 */