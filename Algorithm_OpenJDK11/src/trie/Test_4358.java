package trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Test_4358 {
    private static final int MAX_TREE = 1000000;
    private static String[] trees;      //10000개 종, 1,000,000 최대 나무 수


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        trees = new String[MAX_TREE];



        br.close();
    }
}

class Node_4358 {
    char ch;
    HashMap<Character, Node_4358> children = new HashMap<>();
    boolean isEnd;
    int cnt;

    Node_4358(){}
    Node_4358(char ch){
        this.ch = ch;
    }
}

class Trie_4358 {
    private Node_4358 root;

    Trie_4358() {
        root = new Node_4358();
    }

    public void insert(String word) {
        Map<Character, Node_4358> children = root.children;

        for(int i=0; i<word.length(); i++) {
            Node_4358 tmp;
            char ch = word.charAt(i);

            if(children.containsKey(ch)) {
                tmp = children.get(ch);
            } else {
                tmp = new Node_4358(ch);
                children = tmp.children;
            }

            if(i == word.length()-1) {
                tmp.isEnd = true;
                tmp.cnt++;
            }
        }
    }

    public void search(String word) {
        Map<Character, Node_4358> children = root.children;


    }
}
