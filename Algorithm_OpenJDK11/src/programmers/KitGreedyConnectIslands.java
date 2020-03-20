package programmers;

import java.util.PriorityQueue;

public class KitGreedyConnectIslands {  // 최소 신장트리 Kruskal Algorithm
    public static int[] parent;
    public static PriorityQueue<Island> islandPQ;

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x != y) {
            parent[y] = x;
        }
    }

    public static int find(int x) {
        if(parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    public static boolean isSameParent(int x, int y) {
        return find(x) == find(y) ? true : false;
    }

    private static int[] inItParent(int n) {
        int[] result = new int[n];

        for(int i=0; i<n; i++) {
            result[i] = i;
        }

        return result;
    }


    private static int solution(int n, int[][] costs) {
        int answer = 0;
        islandPQ = new PriorityQueue<>();
        parent = inItParent(n);

        for(int[] info : costs) {
            islandPQ.add(new Island(info[0], info[1], info[2]));
        }


        while(!islandPQ.isEmpty()) {
            Island island = islandPQ.poll();

            if(!isSameParent(island.origin, island.target)) {
                answer += island.cost;
                union(island.origin, island.target);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
//        int n = 4;
//        int[][] costs = { {0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8} };
        int n = 5;
        int[][] costs = { {0,1,1},{3,4,1},{1,2,2},{2,3,4} };

        System.out.println(solution(n, costs));
    }
}

class Island implements Comparable<Island> {
    int origin;
    int target;
    int cost;

    public Island(int origin, int target, int cost) {
        this.origin = origin;
        this.target = target;
        this.cost = cost;
    }

    @Override
    public int compareTo(Island island) {
        if(this.cost < island.cost) {
            return -1;
        } else if( this.cost == island.cost) {
            return 0;
        } else {
            return 1;
        }
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/42861

섬 연결하기
문제 설명
n개의 섬 사이에 다리를 건설하는 비용(costs)이 주어질 때,
최소의 비용으로 모든 섬이 서로 통행 가능하도록 만들 때 필요한 최소 비용을 return 하도록 solution을 완성하세요.

다리를 여러 번 건너더라도, 도달할 수만 있으면 통행 가능하다고 봅니다.
예를 들어 A 섬과 B 섬 사이에 다리가 있고,
B 섬과 C 섬 사이에 다리가 있으면 A 섬과 C 섬은 서로 통행 가능합니다.

제한사항

섬의 개수 n은 1 이상 100 이하입니다.
costs의 길이는 ((n-1) * n) / 2이하입니다.
임의의 i에 대해, costs[i][0] 와 costs[i] [1]에는 다리가 연결되는 두 섬의 번호가 들어있고,
costs[i] [2]에는 이 두 섬을 연결하는 다리를 건설할 때 드는 비용입니다.
같은 연결은 두 번 주어지지 않습니다.
또한 순서가 바뀌더라도 같은 연결로 봅니다.
즉 0과 1 사이를 연결하는 비용이 주어졌을 때, 1과 0의 비용이 주어지지 않습니다.
모든 섬 사이의 다리 건설 비용이 주어지지 않습니다.
이 경우, 두 섬 사이의 건설이 불가능한 것으로 봅니다.
연결할 수 없는 섬은 주어지지 않습니다.

입출력 예

n	                    costs	                     return
4	[[0,1,1],[0,2,2],[1,2,5],[1,3,1],[2,3,8]]       	4
입출력 예 설명

costs를 그림으로 표현하면 다음과 같으며, 이때 초록색 경로로 연결하는 것이 가장 적은 비용으로 모두를 통행할 수 있도록 만드는 방법입니다.
 */
