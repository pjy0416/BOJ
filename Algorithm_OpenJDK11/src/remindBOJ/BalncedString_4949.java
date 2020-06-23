package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BalncedString_4949 {
    final static String YES = "yes", NO = "no", END = ".";
    final static char[] openBracket = {'[','('}, closeBracket = {']',')'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr ="";

        while(!(inputStr = br.readLine()).equals(END)) {
            inputStr = inputStr.replaceAll("[^\\[\\]\\(\\)]","");
            Stack<Character> bracket = new Stack<>();

            loop:
            for(int i=0; i<inputStr.length(); i++) {
                char ch = inputStr.charAt(i);
                if(ch == openBracket[0] || ch == openBracket[1]) {  // open
                    bracket.push(ch);
                } else if(ch == closeBracket[0]) { // ]
                    if(bracket.empty()) {
                        bracket.push(openBracket[0]);
                        break loop;
                    } else if(bracket.peek() == openBracket[0]){
                        bracket.pop();
                    } else {   // (가 먼저 나옴
                        break loop;
                    }
                } else {    // )
                    if(bracket.empty()) {
                        bracket.push(openBracket[1]);
                        break loop;
                    } else if(bracket.peek() == openBracket[1]){
                        bracket.pop();
                    } else {   // [가 먼저 나옴
                        break loop;
                    }
                }
            }
            if(bracket.size() ==0) {
                System.out.println(YES);
            } else {
                System.out.println(NO);
            }
        }
    }
}