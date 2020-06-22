package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NumberConnection_1790 {
    final static int MAX = 9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n =  Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        System.out.println(solution(n,k));

        br.close();
    }

    private static int solution(int n, int k) {
        int answer = -1;

        if(getMaxLen(n) >= k) {
            // answer 구해줘야함
            int len =1;
            int cnt =9;
            int sum = 0;
            for(int i=1; i<=MAX; i++) {
                long tmp = (long)cnt*len;
                if(sum+tmp >=k) {
                    break;
                }
                len++;
                cnt*=10;
                sum+=tmp;
            }
            k -=sum;
            answer = (int) (Math.pow(10,len-1) + k/len -1);
            int order = k%len;
            if(k%len !=0) {
                answer++;
                answer = String.valueOf(answer).charAt(order-1)-48;
            } else {    // 0인 경우 마지막 인덱스 반환
                answer = String.valueOf(answer).charAt(len-1)-48;
            }
        }

        return answer;
    }

    private static int getMaxLen(int n) {
        int result =0;
        int cnt = 9;
        for(int i=1; i<=MAX; i++) {
            if(Math.pow(10,i) > n) {
                if(i !=1) {
                    result += (n-Math.pow(10,i-1)+1)*i;
                } else {
                    result = n;
                }
                break;
            } else {
                result += cnt * i;
                cnt *=10;
            }
        }
        return result;
    }
}