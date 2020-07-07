package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Editor_1406 {
    public static void main(String[] args) throws IOException {
        solution(new BufferedReader(new InputStreamReader(System.in)));
    }

    private static void solution(BufferedReader br) throws IOException {
        final String L = "L", D = "D", B = "B";
        char[] chArr = br.readLine().toCharArray();
        LinkedList<Character> left = new LinkedList<>(), right = new LinkedList<>();

        for(char ch : chArr) {
            left.offer(ch);
        }

        int n = Integer.parseInt(br.readLine());

        for(int i=0; i<n; i++) {
            String input =br.readLine();
            switch (input) {
                case L :
                    if(!left.isEmpty()) {
                        right.offer(left.pollLast());
                    }
                    break;
                case D :
                    if(!right.isEmpty()) {
                        left.offer(right.pollLast());
                    }
                    break;
                case B :
                    if(!left.isEmpty()) {
                        left.pollLast();
                    }
                    break;
                default:
                    left.offer(input.charAt(2));
                    break;
            }
        }
        StringBuffer sb = new StringBuffer();
        while(!left.isEmpty()) {
            sb.append(left.poll());
        }
        while(!right.isEmpty()) {
            sb.append(right.pollLast());
        }
        System.out.println(sb.toString());
        br.close();
    }
}
