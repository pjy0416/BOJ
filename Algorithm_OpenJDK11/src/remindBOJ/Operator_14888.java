package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Operator_14888 {
    final static int operatorSize = 4;
    static int n, numArr[];
    static long min, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        numArr = new int[n];
        int[] operators = new int[operatorSize];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<n; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<operatorSize; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        solution(operators);

        br.close();
    }

    private static void solution(int[] operators) {
        min = 1000000000;
        max = -1000000000;

        dfs(1, numArr[0], operators);
        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(int next, int sum, int[] operators) {
        if(next == n) {
            max = Math.max(max,sum);
            min = Math.min(min,sum);
            return;
        }

        for(int i=0; i<operatorSize; i++) {
            if(operators[i] >0) {
                operators[i]--;
                dfs(next+1,getNum(i,sum,numArr[next]), operators);
                operators[i]++;
            }
        }
    }

    private static int getNum(int cmd, int left, int right) {
        int result =0;
        switch (cmd) {
            case 0:
                result = left+right;
                break;
            case 1:
                result = left-right;
                break;
            case 2:
                result = left*right;
                break;
            case 3:
                result = left/right;
                break;
            default:
                break;
        }
        return result;
    }
}
