package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PrinterQueue_1966 {
    private static class PrintInfo {
        int id, weight;
        public PrintInfo(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        for(int i=0; i<testCase; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());

            ArrayList<PrintInfo> printList = new ArrayList<>();
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<n; j++) {
                printList.add(new PrintInfo(j,Integer.parseInt(st.nextToken())));
            }
            solution(printList.get(target),printList);
        }
        br.close();
    }

    private static void solution(PrintInfo target, ArrayList<PrintInfo> printList) {
        int order=1;
        for(int i=0; i<printList.size(); i++) {
            PrintInfo origin = printList.get(i);
            boolean isMax = true;
            for(int j=i+1; j<printList.size(); j++) {
                if(origin.weight < printList.get(j).weight) {
                    isMax = false;
                    break;
                }
            }

            if(isMax) {
                if(origin == target) { break; }
                order++;
            } else { printList.add(origin); }

            printList.remove(i--);
        }

        System.out.println(order);
    }
}
