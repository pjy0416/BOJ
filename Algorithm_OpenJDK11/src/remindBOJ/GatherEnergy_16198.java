package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class GatherEnergy_16198 {
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        ArrayList<Integer> energyList = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            energyList.add(Integer.parseInt(st.nextToken()));
        }

        solution(n,energyList);

        br.close();
    }

    private static void solution(int n, ArrayList<Integer> energyList) {
        answer =0;
        dfs(n,energyList, 0);
        System.out.println(answer);
    }

    private static void dfs(int n, ArrayList<Integer> energyList, int sum) {
        if(n ==2) {
            answer = Math.max(answer,sum);
            return;
        }

        for(int i=1; i<energyList.size()-1; i++) {
            int mul = energyList.get(i-1)*energyList.get(i+1);
            int energy = energyList.get(i);
            energyList.remove(i);
            dfs(n-1, energyList, sum+mul);
            energyList.add(i,energy);
        }
    }
}
