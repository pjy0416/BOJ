package programmers;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class KitGraphFarthestNode {

    private static int solution(int n, int[][] edge) {
        LinkedList<Integer>[] nodes = inItList(n);
        nodes = makeNode(edge, nodes);

        return search(nodes);
    }

    private static LinkedList<Integer>[] makeNode(int[][] edge, LinkedList<Integer>[] nodes) {  // node를 이어준다.
        for(int[] info : edge) {
            nodes[info[0]-1].offer(info[1]-1);    // 1~n 까지의 수 => 0~n-1까지 idx
            nodes[info[1]-1].offer(info[0]-1);    // 역방향은 이어주지 말자
        }
        return nodes;
    }

    private static LinkedList<Integer>[] inItList(int n) {  // node를 만들어준다.
        LinkedList<Integer>[] result= new LinkedList[n];
        for(int i=0; i<n; i++) {
            result[i] = new LinkedList<>();
        }
        return result;
    }

    private static int search(LinkedList<Integer>[] nodes) {    // 최단거리 node 찾아서 갯수 세는 메소드
        PriorityQueue<MyNode> orderPQ = new PriorityQueue<>();
        orderPQ.offer(new MyNode(0,0));
        int[] visit = new int[nodes.length];
        visit[0] = 1;
        int depth =0;
        int result=0;

        while(!orderPQ.isEmpty()) {
            MyNode node = orderPQ.poll();
            int cnt=0;
            if(depth != node.depth) {
//                System.out.println("초기화?");
                result =0;
                depth = node.depth;
            }
            while(!nodes[node.idx].isEmpty()) {
                int num = nodes[node.idx].poll();
                if(visit[num] ==0) {
                    visit[num] = 1;
                    orderPQ.offer(new MyNode(num, node.depth+1));
                    cnt++;
//                    System.out.println(idx + "에서 " + num + "으로");
//                    System.out.println((node.idx+1) + "에서 " + (num+1) +" 입력");
//                    System.out.println("depth는 " + node.depth + "에서 " + (node.depth+1));
                }
            }
            if(cnt ==0) {
                result++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int n =6;
        int[][] edge ={{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};

        System.out.println(solution(n,edge));
    }
}

class MyNode implements Comparable<MyNode>{
    int idx;
    int depth;

    public MyNode(int idx, int depth) {
        this.idx= idx;
        this.depth = depth;
    }

    @Override
    public int compareTo(MyNode o) {
        return this.depth > o.depth ? 1 : -1;
    }
}
/*
https://programmers.co.kr/learn/courses/30/lessons/49189
가장 먼 노드

문제 설명
n개의 노드가 있는 그래프가 있습니다.
각 노드는 1부터 n까지 번호가 적혀있습니다. 1번 노드에서 가장 멀리 떨어진 노드의 갯수를 구하려고 합니다.
가장 멀리 떨어진 노드란 최단경로로 이동했을 때 간선의 개수가 가장 많은 노드들을 의미합니다.

노드의 개수 n, 간선에 대한 정보가 담긴 2차원 배열 vertex가 매개변수로 주어질 때,
1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지를 return 하도록 solution 함수를 작성해주세요.

제한사항
노드의 개수 n은 2 이상 20,000 이하입니다.
간선은 양방향이며 총 1개 이상 50,000개 이하의 간선이 있습니다.
vertex 배열 각 행 [a, b]는 a번 노드와 b번 노드 사이에 간선이 있다는 의미입니다.

입출력 예
n	                            vertex	                            return
6	[[3, 6], [4, 3], [3, 2], [1, 3], [1, 2], [2, 4], [5, 2]]	       3

입출력 예 설명
예제의 그래프를 표현하면 아래 그림과 같고, 1번 노드에서 가장 멀리 떨어진 노드는 4,5,6번 노드입니다.
 */
