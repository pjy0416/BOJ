package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SmallNum_16471 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());

        int[] myArr = saveNumArr(size, new StringTokenizer(br.readLine()));
        int[] enemyArr = saveNumArr(size, new StringTokenizer(br.readLine()));

        br.close();
        solution(size, myArr, enemyArr);
    }

    private static void solution(int n, int[] myArr, int[] enemyArr) {
        Arrays.sort(myArr);
        Arrays.sort(enemyArr);

        int winPoint = (n+1)/2;
        int myPoint =0;

        int enemyIdx=0;
        for(int i=0; i<n; i++) {
            for(int j=enemyIdx; j<n; j++) {
                if(myArr[i] <enemyArr[j]) {
                    myPoint++;
                    enemyIdx = j+1;
                    break;
                }
            }
            if(myPoint == winPoint) break;
        }

        if(myPoint == winPoint)     System.out.println("YES");
        else                        System.out.println("NO");
//        (N+1)/2점 이상의 점수를 획득한 사람이 승리
    }

    private static int[] saveNumArr(int size, StringTokenizer st) {
        int[] arr = new int[size];
        for(int i=0; i<size; i++) arr[i] = Integer.parseInt(st.nextToken());
        return arr;
    }
}
/*
각자 N장의 카드를 받는다. (N은 홀수다)
각자 카드를 1장씩 골라서 카드에 적힌 수의 크기를 비교한다. (각 카드에 적힌 수는 0이상, 100,000이하의 정수다)
더 작은 수가 적힌 카드를 낸 사람이 1점을 얻고, 승부에 사용된 카드는 버린다. (무승부의 경우, 둘 다 점수를 획득하지 못한다)
총 N번의 승부 후, (N+1)/2점 이상의 점수를 획득한 사람이 승리한다.
(N+1)/2점 이상의 점수를 획득한 사람이 없을 경우, 승자가 없는 것으로 게임이 끝난다. */
