package programmers;

import java.util.Arrays;
import java.util.Comparator;

public class FindRouteGame_42892 {
    static int ansIdx;
    static int[][] answer;

    private static int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];
        Node_42892[] nodes = inItNodes(nodeinfo); // 입력받은 node 정보 저장

        Node_42892 root = nodes[0];
        for(int i=1; i<nodes.length; i++) { // Node 이어주기
            makeNode(root, nodes[i]);
        }
        ansIdx =0;  // 답 배열의 idx 초기화
        findPreorder(root);     // 전위 순회
        ansIdx =0;  // 답 배열의 idx 초기화
        findPostorder(root);    // 후위 순회

        return answer;
    }

    private static Node_42892[] inItNodes(int[][] nodeinfo) {
        Node_42892[] nodes = new Node_42892[nodeinfo.length];
        for(int i=0; i<nodeinfo.length; i++) {
            nodes[i] = new Node_42892((i+1), nodeinfo[i][0], nodeinfo[i][1]);
        }
        // sorting
        Arrays.sort(nodes, new Comparator<Node_42892>() {
            public int compare(Node_42892 n1, Node_42892 n2) {
                return n2.y == n1.y ? n1.x-n2.x : n2.y -n1.y;
            }
        });
        return nodes;
    }

    private static void makeNode(Node_42892 root, Node_42892 child) {
        if(root.x > child.x) {
            if(root.left != null) {
                makeNode(root.left, child);
            } else {
                root.left = child;
            }
        } else {
            if(root.right != null) {
                makeNode(root.right, child);
            } else {
                root.right = child;
            }
        }
    }
    private static void findPreorder(Node_42892 root) {
        answer[0][ansIdx++] = root.idx;
        if(root.left != null) {
            findPreorder(root.left);
        }
        if(root.right != null) {
            findPreorder(root.right);
        }
    }

    private static void findPostorder(Node_42892 root) {
        if(root.left != null) {
            findPostorder(root.left);
        }
        if(root.right != null) {
            findPostorder(root.right);
        }
        answer[1][ansIdx++] = root.idx;
    }

    public static void main(String[] args) {
        int[][] nodeinfo = {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}};
        int[][] result = solution(nodeinfo);

        for(int[] arr : result) {
            for(int num : arr) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}

class Node_42892 {
    int idx;
    int x;
    int y;
    Node_42892 left;
    Node_42892 right;

    public Node_42892(int idx, int x, int y) {
        this.idx = idx;
        this.x = x;
        this.y = y;
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/42892
 */