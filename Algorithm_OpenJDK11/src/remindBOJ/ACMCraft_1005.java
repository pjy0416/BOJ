package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class ACMCraft_1005 {
    static boolean[][] parents;
    static int[] result;
    static HashMap<Integer, Integer> buildingTimeMap;

    final static int defaultValue = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        final String newLine = "\n";

        for(int i=0; i<testCases; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            // init building infos
            parents = new boolean[n+1][n+1];
            st = new StringTokenizer(br.readLine());
            buildingTimeMap = new HashMap<>();
            for(int j=1; j<=n; j++) {
                buildingTimeMap.put(j, Integer.parseInt(st.nextToken()));
            }

            // init build order
            for(int j=0; j<k; j++) {
                st = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());
                parents[parent][child] = true;
            }

            result = new int[n+1];
            Arrays.fill(result, defaultValue);

            int target = Integer.parseInt(br.readLine());
            int time = solution(n, target);
            sb.append(time).append(newLine);
        }
        br.close();
        System.out.println(sb.toString());
    }

    private static int solution(int n, int target) {
        int time =0;
        if(result[target] != defaultValue) {
            return result[target];
        }
        for(int i=1; i<=n; i++) {
            if(parents[i][target]) {
                time = Math.max(solution(n, i), time);
            }
        }
        result[target] = time + buildingTimeMap.get(target);
        return result[target];
    }
}
/*
1
5 10
100000 99999 99997 99994 99990
4 5
3 5
3 4
2 5
2 4
2 3
1 5
1 4
1 3
1 2
4

1
3 2
1 2 3
3 2
2 1
1

1
4 3
1 1 1 1
1 2
3 2
1 4
4

1
3 2
1 1 1
2 1
3 2
1

1
4 3
10 1 100 10
1 2
2 3
2 4
3 4
4

1
91 120
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 2
1 3
2 4
3 4
4 5
4 6
5 7
6 7
7 8
7 9
8 10
9 10
10 11
10 12
11 13
12 13
13 14
13 15
14 16
15 16
16 17
16 18
17 19
18 19
19 20
19 21
20 22
21 22
22 23
22 24
23 25
24 25
25 26
25 27
26 28
27 28
28 29
28 30
29 31
30 31
31 32
31 33
32 34
33 34
34 35
34 36
35 37
36 37
37 38
37 39
38 40
39 40
40 41
40 42
41 43
42 43
43 44
43 45
44 46
45 46
46 47
46 48
47 49
48 49
49 50
49 51
50 52
51 52
52 53
52 54
53 55
54 55
55 56
55 57
56 58
57 58
58 59
58 60
59 61
60 61
61 62
61 63
62 64
63 64
64 65
64 66
65 67
66 67
67 68
67 69
68 70
69 70
70 71
70 72
71 73
72 73
73 74
73 75
74 76
75 76
76 77
76 78
77 79
78 79
79 80
79 81
80 82
81 82
82 83
82 84
83 85
84 85
85 86
85 87
86 88
87 88
88 89
88 90
89 91
90 91
91

 */