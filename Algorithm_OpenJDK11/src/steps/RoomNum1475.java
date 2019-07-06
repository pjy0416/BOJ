package steps;

import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.IOException;

public class RoomNum1475 {
    // 음 6과 9는 하나의 숫자로 취급하고
    // 나머지 숫자들에 대해서 몇 개가 필요한지 하면 되지 않을까??
    public static int[] cnts;

    private static void printResult(int num) {
        if(num ==0) {
            cnts[0] =1;
        }

        while(num !=0) {
            int tmp = num%10;
            cnts[tmp]++;
            num /=10;
        }

        int max =0;

        for(int i=0; i<10; i++) {

            if(i ==6 || i ==9) {
                int tmp = cnts[6] + cnts[9];
                tmp = tmp%2==0 ? tmp/2 : tmp/2 +1;
                max =Math.max(max, tmp);
            } else {
                max = Math.max(max, cnts[i]);
            }
        }
        System.out.println(max);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int roomNum = Integer.parseInt(br.readLine());
        cnts = new int[10];

        printResult(roomNum);

        br.close();
    }
}
