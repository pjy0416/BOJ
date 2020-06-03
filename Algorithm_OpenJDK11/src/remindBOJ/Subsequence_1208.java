package remindBOJ;

import java.io.*;
import java.util.*;

class Subsequence_1208 {
    static ArrayList<Integer> left;
    static ArrayList<Integer> right;

    private static long solution(int S, int[] numArr) {
        left = new ArrayList<>();
        right = new ArrayList<>();

        permutation(0,numArr.length/2, numArr,S,0, left);    // 시작점, 배열, Target number
        permutation(numArr.length/2,numArr.length, numArr,S,0, right);    // 시작점, 배열, Target number

        return getAnswer(S);
    }

    private static long getAnswer(int S) {
        long answer =0;

        Collections.sort(left);
        Collections.sort(right);

        int leftIdx = 0;
        int rightIdx = right.size() - 1;

        while(leftIdx < left.size() && rightIdx >= 0) {
            int leftSum = left.get(leftIdx);
            int rightSum = right.get(rightIdx);

            if(leftSum+rightSum == S) { // left + right 가 S일 때
                long leftCnt = 0;
                while(leftIdx<left.size() && left.get(leftIdx) == leftSum) {    // leftSum 값이 같은 부분 갯수 세주기
                    leftCnt++;
                    leftIdx++;
                }

                long rightCnt = 0;
                while(rightIdx>=0 && right.get(rightIdx) == rightSum) {     // rightSum 값이 같은 부분 갯수 세주기
                    rightCnt++;
                    rightIdx--;
                }

                answer += leftCnt*rightCnt; // count 누적
            }

            if(leftSum+rightSum > S) {  // S보다 값이 클 경우, rightIdx 증가 (최대 값 줄여주기)
                rightIdx--;
            }
            if(leftSum+rightSum < S) { // S보다 값이 작을 경우, leftIdx 증가 (left는 -가 포함됐을 경우가 있음)
                leftIdx++;
            }
        }
        if(S ==0) { // 공집합이 두개 저장되므로 하나 빼주기
            answer--;
        }
        return answer;
    }

    private static void permutation(int idx, int end, int[] numArr, int S, int sum, ArrayList<Integer> list) {
        if(idx >= end) {
            list.add(sum);
            return;
        }

        permutation(idx+1, end, numArr, S, sum+numArr[idx],list);
        permutation(idx+1, end, numArr, S, sum,list);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = br.readLine();
        int N = Integer.parseInt(inputStr.split(" ")[0]);
        int S = Integer.parseInt(inputStr.split(" ")[1]);
        int[] numArr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution(S,numArr));

        br.close();
    }
}