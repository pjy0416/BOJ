package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Palindrome_10942 { // pass
    final static String Palindrome = "1", NoPalindrome = "0", NewLine = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numArr = new int[size];

        for(int i=0; i<size; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        int testCases = Integer.parseInt(br.readLine());
        int[][] testCaseArr = new int[testCases][2];

        for(int i=0; i<testCases; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<2; j++) testCaseArr[i][j] = Integer.parseInt(st.nextToken())-1;
        }
        br.close();

        solution(numArr,testCaseArr);
    }

    private static void solution(int[] numArr, int[][] testCaseArr) {
        StringBuffer sb = new StringBuffer();
        for(int[] testCase : testCaseArr)   sb.append(isPalindrome(numArr, testCase)).append(NewLine);

        System.out.println(sb.toString());
    }

    private static String isPalindrome(int[] numArr, int[] testCase) {
//        System.out.println("New test Case");
        // start == testCase[0], end == testCase[1]
        if(testCase[0] == testCase[1])  return Palindrome;  // 길이가 1인 경우 팰린드롬
        
        int mid = (testCase[0] + testCase[1]) /2;
        int left = mid-1, right = mid+1;
        if((testCase[0]+testCase[1]) %2 !=0)    left++;

        while(left >= testCase[0]) {
//            System.out.println("left => " + left + " , right => " + right + ", mid => " + mid);
            if(numArr[left--] != numArr[right++])   return NoPalindrome;
        }

        return Palindrome;
    }
}

