package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BigGuys_7568 {
    private static class BodyInfo{
        int id;
        int weight;
        int height;

        public BodyInfo(int id, int weight, int height) {
            this.id = id;
            this.weight = weight;
            this.height = height;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        BodyInfo[] bodyInfos = new BodyInfo[n];

        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            bodyInfos[i] = new BodyInfo(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        solution(bodyInfos,n);
        br.close();
    }

    private static void solution(BodyInfo[] bodyInfos, int n) {
        StringBuffer sb = new StringBuffer();
        final String space = " ";

        for(int i=0; i<n; i++) {
            int cnt=1;
            BodyInfo origin = bodyInfos[i];
            for(int j=0; j<n; j++) {
                if(i!=j) {
                    BodyInfo target = bodyInfos[j];
                    if(origin.height < target.height && origin.weight < target.weight) {
                        cnt++;
                    }
                }
            }
            sb.append(cnt).append(space);
        }

        System.out.println(sb.toString());
    }
}

