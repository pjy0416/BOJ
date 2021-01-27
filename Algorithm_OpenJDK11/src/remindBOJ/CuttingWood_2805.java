package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CuttingWood_2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        int[] woods = new int[n];
        st = new StringTokenizer(br.readLine());

        int max =0;
        for(int i=0; i<n; i++) {
            woods[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, woods[i]);
        }

        br.close();
        solution(woods, m, max);
    }

    private static void solution(int[] woods, int m, int right) {
        int answer =0;
        int left =0;

        while(left <= right) {
            int height = (left+right)/2;
            if(isEnoughWood(woods, m, height)) { // 충분하니까 높이를 올려
                left = height+1;
                answer = height;
            } else { // 충분하지 못함
                right = height-1;
            }
        }
        System.out.println(answer);
    }

    private static boolean isEnoughWood(int[] woods, int m, int height) {
        long sum =0;
        for(int wood : woods) {
            if(wood > height) {
                sum += wood-height;
            }
            if(sum >= m) {
                break;
            }
        }
        return sum >= m;
    }
}
