package remindBOJ;

import data_reader.DataReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Pattern;

public class SecretCode_2011 {
    private final static int MOD = 1000000, MIN = 1, MAX = 26, NUMBER_ASCII_START = 48, NUMBER_ASCII_END = 57;
    private final static String WRONG_INPUT = "0", CONTINUITY_ZERO = ".*0{2,}.*";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = br.readLine();
        StringBuilder sb = new StringBuilder();

        if(isValidInput(inputStr)) {
            sb.append(solution(inputStr.toCharArray()));
        } else {
            sb.append(WRONG_INPUT);
        }
        br.close();

        System.out.println(sb.toString());
//        check();
    }

    private static void check() {
        DataReader reader = new DataReader("2011");
        List<String> inputData = reader.getInputData();
        List<String> outputData = reader.getOutputData();
        int count =0;
        for(int i=0; i<outputData.size(); i++) {
            String input = inputData.get(i);
            int result = isValidInput(input) ? solution(input.toCharArray()) : 0;
            int output = (int) (Long.parseLong(outputData.get(i)) % MOD);
            if(result != output) {
                count++;
                System.out.println("=============================================");
                System.out.println("입력 값");
                System.out.println(input);
                System.out.println("내 값 : => " + result);
                System.out.println("기댓값 : => " + output);
            }
        }
        System.out.println("\n총 개수 =>" + outputData.size());
        System.out.println("인풋은?=> "+inputData.size());
        System.out.println("틀린 개수 => " + count);
    }

    private static int solution(char[] numArr) {
        int len = numArr.length;
        int[] dp = new int[len+1];
        dp[0] =1;

        if(len ==1) {
            return numArr[0] == NUMBER_ASCII_START ? 0 : dp[0];
        }

        for(int i=1; i<=len; i++) {
            int prev = i-2;
            int now = i-1;

            if(numArr[now] != '0') { // 현재 pointer가 0이라면, 만들 수 있는 암호(경우의 수)가 없음
                dp[i] = dp[now];
            }
            if(prev <0) { // out of range 방지
                continue;
            }
            if(isValidPermutation(numArr[prev], numArr[now])) { // 연속으로 더할 수 있는 경우
                dp[i] += dp[prev];
            }
            dp[i] %= MOD;
        }

        return dp[len];
    }

    private static boolean isValidPermutation(char left, char right) {
        if(!isInAsciiRange(left, NUMBER_ASCII_START+1, NUMBER_ASCII_END)) {
            return false;
        }

        int num = (left - NUMBER_ASCII_START) * 10 + (right - NUMBER_ASCII_START);
        
        return isInAsciiRange(num, MIN, MAX);
    }

    private static boolean isInAsciiRange(char target, int min, int max) {
        return target >=min && target<=max;
    }

    private static boolean isInAsciiRange(int target, int min, int max) {
        return target >=min && target<=max;
    }

    private static boolean isValidInput(String inputStr) { // 0으로 시작하는 수인지, 0이 연속 2번 이상 들어가는지 확인
        return !inputStr.startsWith(WRONG_INPUT) &&
                !Pattern.matches(CONTINUITY_ZERO, inputStr) &&
                inputStr.length() >0 &&
                isNumberString(inputStr);
    }

    private static boolean isNumberString(String inputStr) {
        String target = inputStr.replaceAll("[0-9]", "");
        return target.length() ==0;
    }
}
