package trie;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_5052{

    public static String[][] lists;
    public static Trie[] trie;

    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        lists = new String[testCase][];
        trie = new Trie[testCase];

        for(int i=0; i<testCase; i++) {    // 테스트 케이스만큼 입력
            int size = Integer.parseInt(br.readLine());
            lists[i] = new String[size];
            trie[i] = new Trie();

            for(int j=0; j<size; j++) {
                String word = br.readLine();
                lists[i][j] = word;
                trie[i].insert(word);
            }
        }

        getResult(testCase);

        br.close();
    }

    public static void getResult(int testCase) {
        for(int i=0; i<testCase; i++) {
            boolean isOverlap = false;

            for(String word : lists[i]) {
                if(trie[i].search(word)) {
                    isOverlap = true;
                }
            }

            if(isOverlap) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
    }
}

class Node {
    char ch;
    HashMap<Character,Node> children = new HashMap<>();
    boolean isEnd;

    Node(){}

    Node(char ch) {
        this.ch = ch;
    }
}

class Trie {
    private Node root;

    Trie() {
        root = new Node();
    }

    public void insert(String str) {
        HashMap<Character,Node> children = root.children;
        for(int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);
            Node tmp;

            if(children.containsKey(ch)) {
                tmp = children.get(ch);
            } else {
                tmp = new Node(ch);
                children.put(ch,tmp);
            }

            children = tmp.children;

            if(i==str.length()-1) {
                tmp.isEnd = true;
            }
        }
    }

    public boolean search(String str) {
        Map<Character,Node> children = root.children;
        Node result = null;

        for(int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);

            if(children.containsKey(ch)) {    //계속 들어가기
                result = children.get(ch);
                children = result.children;
            }
        }

        if(!children.isEmpty()) {    //비어있는게 아니면 단어가 있는거
            return true;
        }
        return false;
    }
}