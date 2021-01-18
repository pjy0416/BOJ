package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PhoneGame_2847 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        br.close();
        solution(N, arr);
    }

    private static void solution(int N, int[] arr) {
        int answer =0;
        for(int i=N-2; i>=0; i--) {
            int next = i+1;
            if(arr[i] >= arr[next]) {
                int decreaseNum = arr[i] - arr[next] +1;
                answer += decreaseNum;
                arr[i] -= decreaseNum;
            }
        }
        System.out.println(answer);
    }
}
