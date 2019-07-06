package steps;

import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.IOException;

public class Fraction1193 {    // 1193 분수찾기

    public static final int LOOP_MIN = 2;            // 판별식에 들어갈 n의 최솟값과 최대값
    public static final int LOOP_MAX = 18300;

    private static void printResult(int num) {        // 결과값 print
        if(num ==1) {
            System.out.println("1/1");
        } else {
            System.out.println(getResult(num));
        }
    }

    private static String getResult(int num) {            // 몇번움직이는지 return
        for(int n=LOOP_MIN; n<=LOOP_MAX; n++) {
            if(isRange(n,num)) {                    // 입력받은 수가 움직임 횟수에 해당하는 범위 안에 존재하면
                return getString(n,num);                            // 이동 count 리턴
            }
        }
        return "null";    // 의미없음
    }

    private static boolean isRange(int n, int num) {    // 최소 최대 범위 확인
        int min = 1+ n*(n-1)/2;
        int max = min + n-1;        // == n*(n+1)/2

        return num >= min && num <= max ? true : false;    // 범위 안에 있으면 true, else false 리턴
    }

    private static String getString(int n, int num) {
        String result;
        // n번째 확인 및 분수 return
        int startNum = n*(n-1)/2 +1;
        int x;
        int y;
        int cnt = num - startNum;

        // 이거 판별이 중요한데,,, 숫자가 커지면 안되네
        if(n %2 != 0) { // x축에서 시작할때, x축 감소
            x = n;
            y = 1;
            for(int i=0; i<cnt; i++) {
                x--;
                y++;
            }
            result = String.valueOf(x)+ "/" + String.valueOf(y);

        } else { // y축에서 시작할 때, y축 감소
            x = 1;
            y = n;

            for(int i=0; i<cnt; i++) {
                x++;
                y--;
            }
            result = String.valueOf(x)+ "/" + String.valueOf(y);
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        printResult(num);

        br.close();
    }
}