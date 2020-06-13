package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Shingle_13251 {
    public static void main(String[] args) throws IOException {
        solution(new BufferedReader(new InputStreamReader(System.in)));
    }

    private static void solution(BufferedReader br) throws IOException {
        int M = Integer.parseInt(br.readLine());
        int[] shingleArr = new int[M];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int total =0;

        for(int i=0; i<M; i++) {
            int num = Integer.parseInt(st.nextToken());
            shingleArr[i] = num;
            total += num;
        }

        int K = Integer.parseInt(br.readLine());
        br.close();

        printResult(M,shingleArr,K,total);
    }

    private static void printResult(int M, int[] shingleArr, int K, int total) {
        double result = 0.0;
        for(int i=0; i<M; i++) {
            // 조약돌 어레이를 돌면서 total까지
            double ratio = 1.0;
            if(shingleArr[i] >= K) { // 뽑을 갯수 이상이어야 확률이 나오기 때문에
                for(int j=0; j<K; j++) {
                    ratio *= (shingleArr[i]-j)/(double)(total-j);
                }
                result+=ratio;
            }
        }
        System.out.println(result);
    }
}
/*
https://www.acmicpc.net/problem/13251
 */