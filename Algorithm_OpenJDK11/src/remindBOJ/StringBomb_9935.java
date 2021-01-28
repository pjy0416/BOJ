package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringBomb_9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String origin = br.readLine();
        String bomb = br.readLine();

        br.close();
        solution(origin.toCharArray(), bomb.toCharArray());
    }

    private static void solution(char[] origin, char[] bomb) {
        final int BOMB_LENGTH = bomb.length, ORIGIN_LENGTH = origin.length, DEFAULT_INDEX = -1;
        final String NOTHING_STATEMENT = "FRULA";

        char[] result = new char[ORIGIN_LENGTH];
        int idx = DEFAULT_INDEX;
        for(char ch : origin) {
            result[++idx] = ch;
            if(isBomb(idx, result, bomb, BOMB_LENGTH)) {
                idx -= BOMB_LENGTH;
            }
        }

        StringBuilder sb = new StringBuilder();
        if(idx == DEFAULT_INDEX) {
            sb.append(NOTHING_STATEMENT);
        } else {
            for(int i=0; i<=idx; i++) {
                sb.append(result[i]);
            }
        }
        System.out.println(sb.toString());
    }

    private static boolean isBomb(int idx, char[] target, char[] bomb, int BOMB_LENGTH) {
        int targetIdx = idx - BOMB_LENGTH +1;
        if(targetIdx< 0) {
            return false;
        }
        for(int i=0; i<BOMB_LENGTH; i++) {
            if(bomb[i] != target[targetIdx+i]) {
                return false;
            }
        }
        return true;
    }

    /* //stack을 활용한 풀이 ~> 공간, 시간 효율 안좋음
    private static void solution(char[] origin, char[] bomb) {
        int bombLen = bomb.length;
        Stack<Character> stack = new Stack<>();

        for(char ch : origin) {
            if(ch == bomb[bombLen-1]) {
                Stack<Character> removedCharStack = new Stack<>();
                for(int i=bombLen-2; i>=0; i--) {
                    if(!stack.isEmpty() && stack.peek() == bomb[i]) {
                        removedCharStack.push(stack.pop());
                    } else {
                        while(!removedCharStack.isEmpty()) {
                            stack.push(removedCharStack.pop());
                        }
                        stack.push(ch);
                        break;
                    }
                }
            } else {
                stack.push(ch);
            }
        }

        StringBuilder sb = new StringBuilder();
        final String NOTHING_STATEMENT = "FRULA";
        if(stack.isEmpty()) {
            sb.append(NOTHING_STATEMENT);
        } else {
            while(!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            sb = new StringBuilder(sb.reverse());
        }

        System.out.println(sb.toString());
    }
     */
}

/*
mirkovC4nizCC44
C4

12ab112ab2ab
12ab
 */