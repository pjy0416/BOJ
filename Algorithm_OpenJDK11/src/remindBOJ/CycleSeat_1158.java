package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CycleSeat_1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        solution(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));

        br.close();
    }

    /*private static void solution(int n, int k) {  // 통과하기는 하지만 너무 오래걸림
        int[] table = getTable(n);
        int[] visit = new int[n];
        int idx =-1;
        StringBuffer sb = new StringBuffer("<");
        final String restSign = ", ";
        for(int i=0; i<n; i++) {
            int cnt=k;
            for(int j=0; j<cnt; j++) {
                idx=(idx+1)%n;
                if(visit[idx] ==1) {
                    cnt++;
                }
            }
            visit[idx] =1;
            sb.append(table[idx]).append(restSign);
        }
        sb.deleteCharAt(sb.length()-1);
        sb.setCharAt(sb.length()-1, '>');
        System.out.println(sb.toString());
    }

    private static int[] getTable(int n) {
        int[] result = new int[n];
        for(int i=0; i<n; i++) {
            result[i] = i+1;
        }
        return result;
    }*/

    private static void solution(int n, int k) {    // 훨씬 효율적
        ArrayList<Integer> tableList = new ArrayList<>();
        StringBuffer sb = new StringBuffer("<");
        final String restSign = ", ";

        for(int i=0; i<n; i++) {
            tableList.add(i+1);
        }

        int idx =0;
        while(!tableList.isEmpty()) {
            idx = (idx+k-1)%n;
            sb.append(tableList.get(idx)).append(restSign);
            tableList.remove(idx);
            n--;
        }
        sb.deleteCharAt(sb.length()-1);
        sb.setCharAt(sb.length()-1, '>');
        System.out.println(sb.toString());
    }
}
