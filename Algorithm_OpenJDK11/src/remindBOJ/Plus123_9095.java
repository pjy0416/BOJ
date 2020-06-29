package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Plus123_9095 {
    final static int[] numArr = {1,2,3};
    static int answer;

    public static void main(String[] args) throws IOException {
        solution(new BufferedReader(new InputStreamReader(System.in)));
    }

    private static void solution(BufferedReader br) throws IOException {
        int testCase = Integer.parseInt(br.readLine());

        for(int i=0; i<testCase; i++) {
            answer =0;
            getCount(Integer.parseInt(br.readLine()));
            System.out.println(answer);
        }
    }

    private static void getCount(int n) {
        if(n <=0) {
            if(n ==0) {
                answer++;
            }
            return;
        }
        for(int i=0; i<3; i++) {
            getCount(n-numArr[i]);
        }
    }
}
/*
//예전에 풀었더 풀이, 메모리 시간 더 훌륭함

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
private static int getResult(int num) {
    if(num ==1) {
        return 1;
    } else if(num ==2) {
        return 2;
    } else if(num ==3) {
        return 4;
    }

    int[] caseNum = new int[11];
    caseNum[0] =0;
    caseNum[1] =1;
    caseNum[2] =2;
    caseNum[3] =4;

    for(int i =4; i<=num; i++) {
        caseNum[i] = caseNum[i-3] + caseNum[i-2] + caseNum[i-1];
    }

    return caseNum[num];
}

    private static void printCase(int num) {
        System.out.println(getResult(num));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        for(int i=0; i<testCase; i++) {
            int num = Integer.parseInt(br.readLine());
            printCase(num);
        }

        br.close();
    }
}
 */
