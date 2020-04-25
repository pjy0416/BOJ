package programmers;

import java.util.LinkedList;

public class KitGraphRemoveCycle {
    private static int solution(int n, int[][] edges) {
        LinkedList<Integer>[] nodes = inItNodes(n);
        nodes = connectNodes(edges, nodes);

        return getCnt(nodes, n);
    }

    private static int getCnt(LinkedList<Integer>[] nodes, int n) {   //
        int result =0;

        for(int i=1; i<=n; i++) {
//            System.out.println(i+"번 째");
            int[] visit = new int[nodes.length];
            result += i;
            for(int j=1; j<=n; j++) {
                if(i!=j && visit[j] !=1) {
                    if (isCycle(i, j, -1, visit, nodes)) {
                        result -= i;
                        break;
                    }
                }
            }
        }

        return result;
    }

    private static boolean isCycle(int expt, int current, int parent, int[] visit, LinkedList<Integer>[] nodes) {
        visit[current] =1;

        for(int next : nodes[current]) {
            if(next != expt) {
                if(visit[next] != 1) {
                    if(isCycle(expt, next, current, visit, nodes)) {
                        return true;
                    }
                } else if(next != parent){
//                    System.out.println("Except = " + expt + "\t curr = " + current + "\t next = "+ next+ "\t parent = " + parent);
                    return true;
                }
            }
        }

        return false;
    }


    private static LinkedList<Integer>[] inItNodes(int n) {
        LinkedList<Integer>[] result = new LinkedList[n+1];

        for(int i=1; i<=n; i++) {
            result[i] = new LinkedList<>();
        }
        return result;
    }

    private static LinkedList<Integer>[] connectNodes(int[][] edges, LinkedList<Integer>[] nodes) {
        for(int[] edge : edges) {
            nodes[edge[0]].offer(edge[1]);
            nodes[edge[1]].offer(edge[0]);
        }
        return nodes;
    }

    /*private static LinkedList<Integer> getCycles(LinkedList<Integer>[] nodes) {
        LinkedList<Integer> result = new LinkedList<>();
        for(int i=1; i<nodes.length; i++) {
            if(nodes[i].size() >2) {
                result.offer(i);
            }
        }
        return result;
    }*/

    public static void main(String[] args) {
        int n=8;
        int[][] edges = {{1,2},{2,3},{3,4},{4,5},{5,6},{6,7},{7,8},{8,1},{2,7},{3,6}};
//        int n=4;
//        int[][] edges = {{1,2},{1,3},{2,3},{2,4},{3,4}};
        System.out.println(solution(n,edges));
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/49188
사이클 제거
문제 설명
n개의 노드로 구성된 그래프에서 하나의 노드만을 제거해서 사이클이 없도록 만들고 싶습니다.

노드의 개수 n, 노드간의 연결 edges가 매개변수로 주어질 때,
노드를 딱 하나 제거해서 그래프를 사이클이 없도록 만들 수 있다면 해당 노드의 번호를 return 하도록 solution 함수를 완성하세요.
단, 그런 노드가 없다면 0을 return하고, 여러 개라면 노드의 번호의 합을 return하세요.

제한사항

노드 번호는 1 부터 시작합니다.
주어진 그래프에 반드시 하나 이상의 사이클은 있습니다.
노드간의 연결에는 방향이 없습니다.
주어지는 노드의 수는 2개 이상 5,000개 이하입니다.
연결의 수는 1개 이상 n(n + 1)/2개 이하입니다.
입출력 예

n	                            edges	                                 result
4	            [[1,2],[1,3],[2,3],[2,4],[3,4]]	                        5
8	[[1,2],[2,3],[3,4],[4,5],[5,6],[6,7],[7,8],[8,1],[2,7],[3,6]]          0
입출력 예 설명

예제 #1

아래 그림과 같이 표현할 수 있으며 2번 또는 3번 노드를 제거하면 사이클이 없어지므로 2+3인 5를 리턴하면 됩니다.

예제 #2

아래 그림과 같이 표현할 수 있으며 어떤 노드를 제거하더라도 사이클이 남아있으므로 0을 리턴하면 됩니다.
 */