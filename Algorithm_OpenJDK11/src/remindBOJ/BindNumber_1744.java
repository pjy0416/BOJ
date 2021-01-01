package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class BindNumber_1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> negativeNumber = new PriorityQueue<>();
        PriorityQueue<Integer> positiveNumber = new PriorityQueue<>(Collections.reverseOrder());
        int oneCount =0;

        for(int i=0; i<n; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num ==1) {
                oneCount++;
            }else if(num <=0) {
                negativeNumber.offer(num);
            } else {
                positiveNumber.offer(num);
            }
        }

        br.close();
        System.out.println(solution(oneCount, negativeNumber, positiveNumber));
    }

    private static int solution(int oneCount, PriorityQueue<Integer> negativeNumber, PriorityQueue<Integer> positiveNumber) {
        int answer = oneCount;

        while(!positiveNumber.isEmpty()) {
            int num = positiveNumber.poll();
            if(!positiveNumber.isEmpty()) {
                num *= positiveNumber.poll();
            }
            answer+= num;
        }

        while(!negativeNumber.isEmpty()) {
            int num = negativeNumber.poll();
            if(!negativeNumber.isEmpty()) {
                num *= negativeNumber.poll();
            }
            answer += num;
        }

        return answer;
    }
}
