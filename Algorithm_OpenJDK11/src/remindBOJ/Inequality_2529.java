package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Inequality_2529 {
    final static int NUMMAX = 10;
    final static String LEFT = ">";
    static String[] ineqArr;
    static long minAns, maxAns;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ineqArr = br.readLine().split(" ");

        solution(n);

        br.close();
    }

    private static void solution(int n) {
        minAns = Long.MAX_VALUE;
        maxAns = 0;
        visit = new boolean[NUMMAX];
        for(int i=0; i<NUMMAX; i++) {
            visit[i] = true;
            getAns(i, 0, n, i);
            visit[i] = false;
        }

        System.out.println(maxAns);
        StringBuffer sb = new StringBuffer("%0").append(n+1).append("d");
        System.out.println(String.format(sb.toString(),minAns));
    }

    private static void getAns(int now, int idx, int n, long num) {
        if(idx == n) {
            maxAns = Math.max(maxAns, num);
            minAns = Math.min(minAns, num);
            return;
        }

        if(ineqArr[idx].equals(LEFT)) {  // >
            for(int i=0; i<now; i++) {
                if(!visit[i]) {
                    visit[i] = true;
                    getAns(i, idx + 1, n, num * 10 + i);
                    visit[i] = false;
                }
            }
        } else {                    // <
            for(int i=NUMMAX-1; i>now; i--) {
                if(!visit[i]) {
                    visit[i] = true;
                    getAns(i, idx + 1, n, num * 10 + i);
                    visit[i] = false;
                }
            }
        }
    }
}
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
    final static int NUMMAX = 10;
    final static String LEFT = ">";
    static String[] ineqArr;
    static long minAns, maxAns;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ineqArr = br.readLine().split(" ");

        solution(n);

        br.close();
    }

    private static void solution(int n) {
        minAns = Long.MAX_VALUE;
        maxAns = 0;
        getAns(new boolean[NUMMAX], 0, n, new LinkedList<>());
        printNum(maxAns,n);
        printNum(minAns,n);
    }

    private static void printNum(long num, int n) {
        long div = (long) Math.pow(10,n);
        StringBuffer sb = new StringBuffer();

        for(int i=0 ;i<=n; i++) {
            sb.append(num/div);
            num%=div;
            div/=10;
        }
        System.out.println(sb.toString());
    }

    private static void getAns(boolean[] visit, int cnt, int n, LinkedList<Integer> list) {
        if(n <cnt) {
            if(list.size()>n) {
                StringBuffer sb = new StringBuffer();
                int leftIdx =0;
                int rightIdx =1;
                for(int i=0; i<n; i++) {
                    int leftNum = list.get(leftIdx++);
                    int rightNum = list.get(rightIdx++);
                    if (ineqArr[i].equals(LEFT)) {     // >
                        if (leftNum < rightNum) {
                            return;
                        }
                    } else {                    // <
                        if (leftNum > rightNum) {
                            return;
                        }
                    }
                    sb.append(leftNum);
                }
                sb.append(list.get(leftIdx));
                maxAns = Math.max(Long.parseLong(sb.toString()),maxAns);
                minAns = Math.min(Long.parseLong(sb.toString()), minAns);
            }
            return;
        }
        for(int i=0; i<NUMMAX; i++) {
            if(!visit[i]) {
                visit[i] = true;
                list.offer(i);
                getAns(visit,cnt+1,n,list);
                visit[i] = false;
                list.pollLast();
            }
        }

    }
}
 */
