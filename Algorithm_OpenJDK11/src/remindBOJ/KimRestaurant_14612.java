package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class KimRestaurant_14612 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int n = Integer.parseInt(str.split(" ")[0]); // 주문 수
        int m = Integer.parseInt(str.split(" ")[1]); // 테이블 수
        String[] orderArr = new String[n];

        for(int i=0; i<n; i++) {
            orderArr[i] = br.readLine();
        }

        solution(orderArr, m);

        br.close();
    }

    private static void solution(String[] orderArr, int m) {
        ArrayList<int[]> tableList = new ArrayList<>();
        for(String order : orderArr) {
            String[] cmdArr = order.split(" ");
            if(cmdArr[0].equals("order")) {
                tableList.add(new int[]{Integer.parseInt(cmdArr[1]),Integer.parseInt(cmdArr[2])});
            } else if(cmdArr[0].equals("sort")) {
                Collections.sort(tableList, new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        if(o1[1] > o2[1]) {
                            return 1;
                        } else if(o1[1] == o2[1]) {
                            return o1[0] > o2[0] ? 1 : -1;
                        }
                        return -1;
                    }
                });
            } else {    // complete
                for(int i=0; i<tableList.size(); i++) {
                    if(tableList.get(i)[0] == Integer.parseInt(cmdArr[1])) {
                        tableList.remove(i);
                        break;
                    }
                }
            }
            if(tableList.size() >0) {
                StringBuffer sb = new StringBuffer();
                for(int i=0; i<tableList.size(); i++) {
                    sb.append(tableList.get(i)[0]);
                    if(i <tableList.size()-1) {
                        sb.append(" ");
                    }
                }
                System.out.println(sb.toString());
            } else {
                System.out.println("sleep");
            }
        }
    }
}
/*
https://www.acmicpc.net/problem/14612
 */