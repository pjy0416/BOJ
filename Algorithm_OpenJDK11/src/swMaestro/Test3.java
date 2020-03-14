package swMaestro;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Test3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int n = Integer.parseInt(input.split(" ")[0]);
        int k = Integer.parseInt(input.split(" ")[1]);

        String inStr = br.readLine();
        StringTokenizer st = new StringTokenizer(inStr);
        LinkedList<Integer> nums = new LinkedList<>();

        while(st.hasMoreTokens()) {
            nums.offer(Integer.parseInt(st.nextToken()));
        }

        solution(n,k,nums);
    }

    private static void solution(int n, int k, LinkedList<Integer> numQueue) {
        int answer =0;
        if(k ==1) {
            answer = numQueue.pollLast() - numQueue.poll();
        } else {
            if (k == n) {
                answer = 0;
            } else {
                int cnt =n;
                while(cnt !=k) {

                    cnt--;
                }
            }
        }

        System.out.println(answer);
    }
}
/*
유일하게 가진 테케
6 3
4 8 15 16 23 42
문제 : 오름차순으로 정렬된 하나의 숫자 집합을, k 개의 부분 집합으로 자를 때,
각 부분집합의 최대값 - 최소값을 더했을 때 최소가 되는 부분집합으로 만들어라.

입력 :
숫자 개수  부분 집합 개수
   n           k
오름차순 정렬된 숫자

 2 <= n <= 3000;
 1 <= k <= n

 출력 : 12
 {4,8,15,16},{23},{42} => 12 + 0 + 0 => 12

 */

class MyNum {
    int min, max;

    public static void MyNum(int min, int max) {

    }
}

