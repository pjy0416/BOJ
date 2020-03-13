package swMaestro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MockTest1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        int n = Integer.parseInt(input.split(" ")[0]);
        int k = Integer.parseInt(input.split(" ")[1]);

        String[] nums = br.readLine().split(" ");
        int cnt = 0; // 바꿔준 횟수
        int num = 0; // 1이 있는 IDX
        int total = n - 1; // 바꿔야하는 1이 아닌 갯수

        for (int i = 0; i < n; i++) {
            if (nums[i].equals("1")) {
                num = i;
            }
        }
        // 1 왼쪽으로 몇개가 있는지 판별하고, 그 숫자를 1로 만들 수 있는 최적을 찾아야함.
        // num-1 개가 있고, k-1 개씩 바꿀 수 있음.
        // n을 제외하면 n-1 개의 숫자가 남아있음

        cnt = num / (k - 1);  // i 왼쪽의 숫자를 바꿔줌

        total -= cnt * (k - 1);   // 바꿔준 만큼 갯수 빼줌

        cnt += total / (k - 1);  // 바꿔줄 애들 바꿔준 만큼 횟수 추가

        if(total % (k-1) !=0) {
            cnt++;
        }

        System.out.println(cnt);
    }
}