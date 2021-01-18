package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TreeTraversal_1991 {
    private static char NULL = '.';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Tree tree = new Tree(st.nextToken().charAt(0), st.nextToken().charAt(0), st.nextToken().charAt(0));
        while(--n >0) {
            st = new StringTokenizer(br.readLine());
            tree.add(st.nextToken().charAt(0), st.nextToken().charAt(0), st.nextToken().charAt(0));
        }

        br.close();
        solution(tree);
    }

    private static void solution(Tree tree) {
        tree.print();
    }

    private static class Tree {
        Node root;
        private StringBuilder preSB, inSB, postSB;

        public Tree(char vertex, char left, char right) {
            this.root = new Node(vertex);
            if(left != NULL) {
                this.root.left = new Node(left);
            }
            if(right != NULL) {
                this.root.right = new Node(right);
            }
        }

        public void add(char vertex, char left, char right) {
            this.append(this.root, vertex, left, right);
        }

        private void append(Node node, char vertex, char left, char right) {
            if(node == null) {
                return;
            } else if(node.vertex == vertex) {
                if(left != NULL) {
                    node.left = new Node(left);
                }
                if(right != NULL) {
                    node.right = new Node(right);
                }
            } else {
                append(node.left, vertex, left, right);
                append(node.right, vertex, left, right);
            }
        }

        public void print() {
            preSB = new StringBuilder();
            inSB = new StringBuilder();
            postSB = new StringBuilder();
            this.traversal(this.root);
            System.out.println(preSB.toString());
            System.out.println(inSB.toString());
            System.out.println(postSB.toString());
        }

        private void traversal(Node node) {
            if(node != null && node.vertex != NULL) {
                preSB.append(node.vertex);
                traversal(node.left);
                inSB.append(node.vertex);
                traversal(node.right);
                postSB.append(node.vertex);
            }
        }
    }

    private static class Node {
        char vertex;
        Node left, right;

        public Node(char vertex) {
            this.vertex = vertex;
        }
    }
}
