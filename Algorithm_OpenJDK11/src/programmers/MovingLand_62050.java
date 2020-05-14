package programmers;

import java.util.*;

public class MovingLand_62050 {
    static int[] dx = {0,1,0,-1};   // x축 이동    0 : 북, 1 : 동, 2 : 남, 3 : 서
    static int[] dy = {1,0,-1,0};   // y축 이동
    static int[] parents;
    static int[][] visit;
    static PriorityQueue<Node> ladderQueue;

    private static int solution(int[][] land, int height) {
        initVisit(land.length);
        int group =1;

        //grouping
        for(int y=0; y<land.length; y++) {
            for(int x=0; x<land.length; x++) {
                if(visit[y][x] ==0) {   //구역이 없는 경우
                    grouping(new Pos(x,y),land,height,group++);
                }
            }
        }

        //initialize parents
        inItParents(group);

        //initialize ladder PQ
        ladderQueue = new PriorityQueue<>();

        //save the ladder pq
        for(int y=0; y<land.length; y++) {
            for(int x=0; x<land.length; x++) {
                findOtherGroup(x,y,land);
            }
        }

        return getMinLadder(group-2);   // n-1개 연결하면 됨
    }

    private static int getMinLadder(int total) {
        int result =0;
        // find min ladder cost
        while(total >0) {
            Node node = ladderQueue.poll();
            if(find(node.group1) != find(node.group2)) {
                union(node.group1, node.group2);
                result += node.diff;
                total--;
            }
        }
        return result;
    }

    private static void findOtherGroup(int originX, int originY, int[][] land) {
        for(int i=0; i<4; i++) {
            int x = originX + dx[i];
            int y = originY + dy[i];
            if(isInArea(x,y,land.length)) { // out of idx 방지
                if(visit[originY][originX] != visit[y][x]) { // 다른 그룹이면
                    ladderQueue.offer(new Node(visit[originY][originX], visit[y][x], Math.abs(land[y][x] - land[originY][originX])));
                }
            }
        }
    }

    private static void grouping(Pos pos, int[][] land, int height, int group) {
        Queue<Pos> queue = new LinkedList<>();
        queue.offer(pos);
        visit[pos.y][pos.x] = group;
        while(!queue.isEmpty()) {
            Pos tmp = queue.poll();
            for(int i=0; i<4; i++) {
                int x = tmp.x+dx[i];
                int y = tmp.y+dy[i];
                if(isInArea(x,y, land.length)) {
                    if(visit[y][x] ==0 && canMove(land[tmp.y][tmp.x], land[y][x], height)) {
                        queue.offer(new Pos(x,y));
                        visit[y][x] = group;
                    }
                }
            }
        }
    }

    private static boolean canMove(int num1, int num2, int height) {    // move without ladder
        return Math.abs(num1-num2) <= height ? true : false;
    }

    private static boolean isInArea(int x, int y, int n) {
        return x>=0 && x<n && y>=0 && y<n ? true : false;
    }

    private static void initVisit(int n) {
        visit = new int[n][n];
    }

    private static void inItParents(int n) {
        parents = new int[n];
        for(int i=1; i<n; i++) {
            parents[i] = i;
        }
    }

    private static void union(int val1, int val2) {
        val1 = find(val1);
        val2 = find(val2);
        if(val1 < val2) {
            parents[val2] = val1;
        } else {
            parents[val1] = val2;
        }
    }

    private static int find(int value) {
        if(value == parents[value]) {
            return value;
        }
        return parents[value] = find(parents[value]);
    }

    public static void main(String[] args) {
//        int[][] land = {{1, 4, 8, 10}, {5, 5, 5, 5}, {10, 10, 10, 10}, {10, 10, 10, 20}};
//        int height = 3;
        int[][] land = {{10, 11, 10, 11}, {2, 21, 20, 10}, {1, 20, 21, 11}, {2, 1, 2, 1}};
        int height = 1;
        System.out.println(solution(land,height));
    }
}

class Pos {
    int x;
    int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Node implements Comparable<Node>{
    int group1;
    int group2;
    int diff;

    public Node(int group1, int group2, int diff) {
        this.group1 = group1;
        this.group2 = group2;
        this.diff = diff;
    }

    @Override
    public int compareTo(Node o) {
        if(this.diff > o.diff) {
            return 1;
        } else if (this.diff == o.diff) {
            return this.group1 > o.group1 ? 1 : -1;
        } else {
            return -1;
        }
    }
}
/*
https://programmers.co.kr/learn/courses/30/lessons/62050

지형 이동
문제 설명
N x N 크기인 정사각 격자 형태의 지형이 있습니다. 각 격자 칸은 1 x 1 크기이며, 숫자가 하나씩 적혀있습니다. 격자 칸에 적힌 숫자는 그 칸의 높이를 나타냅니다.

이 지형의 아무 칸에서나 출발해 모든 칸을 방문하는 탐험을 떠나려 합니다. 칸을 이동할 때는 상, 하, 좌, 우로 한 칸씩 이동할 수 있는데, 현재 칸과 이동하려는 칸의 높이 차가 height 이하여야 합니다. 높이 차가 height 보다 많이 나는 경우에는 사다리를 설치해서 이동할 수 있습니다. 이때, 사다리를 설치하는데 두 격자 칸의 높이차만큼 비용이 듭니다. 따라서, 최대한 적은 비용이 들도록 사다리를 설치해서 모든 칸으로 이동 가능하도록 해야 합니다. 설치할 수 있는 사다리 개수에 제한은 없으며, 설치한 사다리는 철거하지 않습니다.

각 격자칸의 높이가 담긴 2차원 배열 land와 이동 가능한 최대 높이차 height가 매개변수로 주어질 때, 모든 칸을 방문하기 위해 필요한 사다리 설치 비용의 최솟값을 return 하도록 solution 함수를 완성해주세요.

제한사항
land는 N x N크기인 2차원 배열입니다.
land의 최소 크기는 4 x 4, 최대 크기는 300 x 300입니다.
land의 원소는 각 격자 칸의 높이를 나타냅니다.
격자 칸의 높이는 1 이상 10,000 이하인 자연수입니다.
height는 1 이상 10,000 이하인 자연수입니다.
입출력 예
land	height	result
[[1, 4, 8, 10], [5, 5, 5, 5], [10, 10, 10, 10], [10, 10, 10, 20]]	3	15
[[10, 11, 10, 11], [2, 21, 20, 10], [1, 20, 21, 11], [2, 1, 2, 1]]	1	18
입출력 예 설명
입출력 예 #1

각 칸의 높이는 다음과 같으며, 높이차가 3 이하인 경우 사다리 없이 이동이 가능합니다.

land_ladder_5.png

위 그림에서 사다리를 이용하지 않고 이동 가능한 범위는 같은 색으로 칠해져 있습니다. 예를 들어 (1행 2열) 높이 4인 칸에서 (1행 3열) 높이 8인 칸으로 직접 이동할 수는 없지만, 높이가 5인 칸을 이용하면 사다리를 사용하지 않고 이동할 수 있습니다.

따라서 다음과 같이 사다리 두 개만 설치하면 모든 칸을 방문할 수 있고 최소 비용은 15가 됩니다.

높이 5인 칸 → 높이 10인 칸 : 비용 5
높이 10인 칸 → 높이 20인 칸 : 비용 10
입출력 예 #2

각 칸의 높이는 다음과 같으며, 높이차가 1 이하인 경우 사다리 없이 이동이 가능합니다.

land_ladder3.png

위 그림과 같이 (2행 1열) → (1행 1열), (1행 2열) → (2행 2열) 두 곳에 사다리를 설치하면 설치비용이 18로 최소가 됩니다.
 */
