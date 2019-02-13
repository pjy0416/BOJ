package bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_14501 {               // 문제 보류
    private static int size =0;
    private static int[][] bf;
    private static int[] duration;
    private static int[] price;

    public static int getMax() {
        for(int idx=1; idx<=size; idx++) {
//            System.out.print(bf[idx][0] +", " + bf[idx][1] + " 에서 ~~~~~~~~~~>>>>>");
            if (idx != size && idx + duration[idx-1] <= size+1) {  // 마지막 Index 전까지의 로직
                if (bf[idx][1]+price[idx-1] > bf[idx-1][0]) {
                    bf[idx][0] = bf[idx][1];
                    bf[idx][1] += price[idx - 1];
                } else {
                    if(duration[idx-1] ==1) {   // 작업기간이 하루라면
                        bf[idx][1] += price[idx-1];
                        bf[idx][0] += price[idx-1];
                    } else {
                        bf[idx][1] = bf[idx-1][0] + price[idx - 1];
                        bf[idx][0] = bf[idx-1][0];
                    }
                }

                if (bf[idx + duration[idx - 1]][1] < price[idx - 1]) { // 작업기간 이후 번 돈 저장
                    bf[idx + duration[idx - 1]][1] += bf[idx][1];
                }
                if (bf[idx + duration[idx - 1]][0] < price[idx - 1]) { // 작업기간 이후 번 돈 저장
                    bf[idx + duration[idx - 1]][0] += bf[idx][0];
                }
            } else {    // 마지막 Index
                if(duration[idx-1] ==1) {
                    bf[idx][1] += price[idx-1];
                    bf[idx][0] += price[idx-1];
                }
            }
//            System.out.println(bf[idx][0] + ", " + bf[idx][1] + "로 변함");
        }

        int max =0;
        for(int i=1; i<=size; i++) {
            max = Math.max(Math.max(bf[i][0], bf[i][1]), max);
        }

        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        size = Integer.parseInt(br.readLine());

        duration = new int[size];
        price = new int[size];

        for(int i=0; i<size; i++) {
            String str = br.readLine();
            duration[i] = Integer.parseInt(str.split(" ")[0]);
            price[i] = Integer.parseInt(str.split(" ")[1]);
        }

        bf = new int[size+2][size+2];

        System.out.println(getMax());

        br.close();
    }
}
