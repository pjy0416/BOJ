package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class StandInOneLine_1138 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] persons = new int[n+1];
        for(int i=1; i<=n; i++) persons[i] = Integer.parseInt(st.nextToken());
        br.close();
        solution(n,persons);
    }

    private static void solution(int n, int[] persons) {
        ArrayList<Integer> line = new ArrayList<>();
        for(int i=n; i>0; i--) {
            line.add(persons[i],i);
        }

        StringBuffer sb = new StringBuffer();
        final String space = " ";
        for(int person : line)  sb.append(person).append(space);
        System.out.println(sb.toString());
    }
}

