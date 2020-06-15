package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Racing_2651 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int maxDist = Integer.parseInt(br.readLine());
        int repairs = Integer.parseInt(br.readLine())+2;  // 출발점과, 도착점 저장하기 위해 +2
        int[] repairSpots = new int[repairs];
        int[] timeArr = new int[repairs];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<repairs; i++) {
            repairSpots[i] = Integer.parseInt(st.nextToken()) + repairSpots[i-1];
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<repairs-1; i++) {
            timeArr[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        solution(maxDist,repairs,repairSpots,timeArr);
    }

    private static void solution(int maxDist, int repairs, int[] repairSpots, int[] timeArr) {
        int[] spotArr = new int[repairs];
        for(int i=1; i<repairs; i++) {
            int min = Integer.MAX_VALUE;
            for(int j=i-1; j>=0; j--) {
                if(repairSpots[i] - repairSpots[j] > maxDist) {
                    break;
                }
                if(min > timeArr[j]) {
                    min = timeArr[j];
                    spotArr[i] =j;
                }
            }
            if(min != Integer.MAX_VALUE) {
                timeArr[i] += min;
            }
        }
        System.out.println(timeArr[repairs-1]); // 걸린 시간

        int prev = spotArr[repairs-1];
        ArrayList<Integer> spotList = new ArrayList<>();

        while(prev !=0) {
            spotList.add(prev);
            prev = spotArr[prev];
        }
        System.out.println(spotList.size());
        for(int i = spotList.size()-1; i>=0; i--) {
            System.out.print(spotList.get(i) + " ");
        }
    }
}
/*
https://www.acmicpc.net/problem/2651

// 19초로 3번,4번에서 받아야함
140
4
100 30 10 140 40
10  3  9  10


//16초로 1,3,5번에서 받아야함
140
5
100 30 100 40 50 60
 5  10  4  11  7
 */