package lineTest;

import java.util.Stack;

public class Test1 {
    private static int solution(String inputString) {
        // 검증해야하는 괄호 (),<>,{},[]
        int answer = 0;
        Stack<Character>[] stack = new Stack[4];    // 0 : (), 1: <>, 2 : {}, 3 :[]
        for(int i=0; i<4; i++) {
            stack[i] = new Stack<>();
        }

        for(int i=0; i<inputString.length(); i++) {
            char ch = inputString.charAt(i);
            if(ch =='(') {
                stack[0].push(ch);
            } else if(ch =='<') {
                stack[1].push(ch);
            } else if(ch =='{') {
                stack[2].push(ch);
            } else if(ch =='[') {
                stack[3].push(ch);
            } else if(ch ==')') {
                if(stack[0].isEmpty()) {
                    return -1;
                } else {
                    stack[0].pop();
                    answer++;
                }
            } else if(ch =='>') {
                if(stack[1].isEmpty()) {
                    return -1;
                } else {
                    stack[1].pop();
                    answer++;
                }
            } else if(ch =='}') {
                if(stack[2].isEmpty()) {
                    return -1;
                } else {
                    stack[2].pop();
                    answer++;
                }
            } else if(ch ==']') {
                if(stack[3].isEmpty()) {
                    return -1;
                } else {
                    stack[3].pop();
                    answer++;
                }
            }
        }

        for(int i=0; i<4; i++) {
            if(!stack[i].isEmpty()) {
                return -1;
            }
        }
        return answer;
    }
    public static void main(String[] args) {
        String inputString = "line [plus]";
        System.out.println(solution(inputString));
    }
}