package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LanCutting_1654 {
    public static void main(String[] args) throws IOException { // 랜선 길이의 최대 값을 잘못보고 int형으로 설정해서 틀렸었음.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        long[] lanArr = new long[k];

        int n = Integer.parseInt(st.nextToken());
        for(int i=0; i<k; i++) {
            lanArr[i] = Long.parseLong(br.readLine());
        }

        solution(k,n,lanArr);

        br.close();
    }

    private static void solution(int k, int n, long[] lanArr) {
        System.out.println(binarySearch(k,n,lanArr));
    }

    private static long binarySearch(int k, int n, long[] lanArr) {
        Arrays.sort(lanArr);
        long left = 1, right = lanArr[k-1];
        long answer =0;

        while(left<= right) {
            long mid = (left+right)/2;
            if(getCnt(lanArr,mid) >= n) {
                answer = Math.max(answer, mid);
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

        return answer;
    }

    private static long getCnt(long[] lanArr, long mid) {
        long result =0;
        for(long lan : lanArr) {
            result += lan/mid;
        }
        return result;
    }
}
