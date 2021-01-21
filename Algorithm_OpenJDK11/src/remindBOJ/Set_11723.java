package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Set_11723 {
    final static String NEW_LINE = "\n", ALL = "all", EMPTY = "empty", ADD ="add", REMOVE ="remove", CHECK = "check", TOGGLE = "toggle";
    final static char TRUE = '1', FALSE = '0';
    final static int FULL_NUMBER = (1 << 21) -1, EMPTY_NUMBER =0;

    static int num;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());
        num =0;
        sb  = new StringBuilder();

        while(m-- >0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            solution(st);
        }
        br.close();
        System.out.println(sb.toString());
    }

    private static void solution(StringTokenizer st) {
        String cmd = st.nextToken();
        int x = st.hasMoreTokens() ? Integer.parseInt(st.nextToken()) : EMPTY_NUMBER;

        switch (cmd) {
            case ALL:
                num = FULL_NUMBER;
                break;
            case EMPTY:
                num = EMPTY_NUMBER;
                break;
            case ADD :
                num |= (1 << x); // |(OR) 연산을 통해 해당 값을 넣어줌
                break;
            case REMOVE:
                num &= ~(1 << x); // 전체 비트를 ~(NOT) 를 통해 역으로 바꿔주고 &(AND) 연산을 통해 해당 값을 지워줌
                break;
            case CHECK:
                sb.append( (num&(1 << x)) != EMPTY_NUMBER ? TRUE : FALSE).append(NEW_LINE); // &(AND) 연산을 통해 값이 0이 아니면 TRUE를, 아니면 FALSE를 추가
                break;
            case TOGGLE:
                num ^= (1 << x); // ^(XOR) 연산(대응되는 비트가 서로 다르면 1을 반환)을 통해 있으면 제거, 없으면 추가를 해준다.
                break;
            default :
                break;
        }
    }
}
