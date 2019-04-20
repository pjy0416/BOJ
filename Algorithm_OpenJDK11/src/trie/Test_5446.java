package trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
문제
팀포2 덕후 연수는 팀포2를 다운받던 도중 하드 용량이 부족하다는 것을 알았다.
이때는 침착하게 팀포2를 하지 말거나 하드를 새로 사면 되지만 불가능했고,결국 하드에서 쓸모없는 파일을 지워야만 한다.
연수는 또한 턱스 덕후여서 리눅스를 사용중이다. 리눅스에서 현재 디렉토리의 특정 파일을 지우려면 "rm 파일명"의 형식을 갖춰 명령하면 된다.
그러나 파일 개수가 너무 많을 경우 일일이 다 칠 수 없기에, 와일드카드 '*'를 사용할 수도 있다. "rm 문자열*" 형식으로 명령하면
현재 디렉토리에서 파일 이름이 "문자열"이거나 "문자열"로 시작하는 모든 파일이 한번에 삭제된다!
그러나 지워서는 안 되는 파일들 또한 존재한다.
rm 명령어를 잘못 사용하여 이러한 파일들을 지우지 않도록 조심해야 할 것이다.
때에 따라서 "rm *"도 사용할 수 있긴 하다. 현재 디렉토리의 모든 파일을 지우고 싶을 때만...
모든 파일이 디렉토리 하나에 존재하고 연수가 그 디렉토리에 있을 때, 지우고 싶은 파일들을 모두 지우는 데 필요한 최소 rm 명령 횟수를 구하시오.
단, 사용할 수 있는 와일드카드는 '*' 뿐이며 반드시 제일 끝에만 사용해야 한다. 예를 들면 "rm *.txt"는 사용할 수 없다.

입력
입력은 여러 개의 테스트 케이스로 주어지며, 첫째 줄에 테스트 케이스의 개수가 주어진다. 각 테스트 케이스는 다음과 같은 형식이다.

첫째 줄에 지워야 하는 파일의 개수 N1이 주어진다. (1 ≤ N1 ≤ 1000)
이어서 N1개의 줄에 지워야 하는 파일명이 줄마다 하나씩 주어진다.
이어서 지우면 안 되는 파일의 개수 N2가 주어진다. (0 ≤ N2 ≤ 1000)
이어서 N2개의 줄에 지우면 안 되는 파일명이 줄마다 하나씩 주어진다.
파일 이름은 모두 1글자 이상 20글자 이하이며, 영문 대소문자, 숫자, 점(.)으로만 이루어져 있다. 하나의 테스트 케이스에 등장하는 모든 파일 이름은 서로 다르다.
*/

/*
Input
1
11
BAPC.in
BAPC.out
BAPC.tex
filter
filename
filenames
clean
cleanup.IN1
cleanup.IN2
cleanup.out
problem.tex
5
BAPC
files
cleanup
cleanup.IN
cleaning

Output
8
*/


public class Test_5446 {
    private static Trie_5446[] listTrie, warnTrie, trieALl;
    private static String[][] listArr, warnArr;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int testCase;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        testCase = Integer.parseInt(br.readLine());

        listTrie = new Trie_5446[testCase];
        warnTrie = new Trie_5446[testCase];
        listArr = new String[testCase][];
        warnArr = new String[testCase][];

        for(int i=0; i<testCase; i++) {
            listTrie[i] = new Trie_5446();
            int listLen = Integer.parseInt(br.readLine());
            listArr[i] = new String[listLen];

            for(int j=0; j<listLen; j++) {
                listArr[i][j] = br.readLine();
            }

            warnTrie[i] = new Trie_5446();
            int warnLen = Integer.parseInt(br.readLine());
            warnArr[i] = new String[listLen];

            for(int j=0; j<warnLen; j++) {
                warnArr[i][j] = br.readLine();
            }
            warnTrie[i] = new Trie_5446();
        }

        getResult();

        br.close();
    }

    private static void getResult() {

    }

    private static void removeWarn() {

    }
}

class Node_5446 {
    char ch;
    HashMap<Character, Node_5446> children = new HashMap<>();
    boolean isEnd;

    Node_5446() {}          // 기본 생성자
    Node_5446(char ch) {
        this.ch = ch;
    }
}

class Trie_5446 {
    private Node_5446 root;

    Trie_5446() {
        root = new Node_5446();
    }

    public void insert(String word) {
        Map<Character, Node_5446> children = root.children;

        for(int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            Node_5446 tmp;

            if(children.containsKey(ch)) {  //이미 있으면
                tmp = children.get(ch);
            } else {
                tmp = new Node_5446(ch);
                children = tmp.children;
            }
            if(i == word.length()-1) {
                tmp.isEnd = true;
            }
        }
    }

    public void search(String word) {
        Map<Character, Node_5446> children = root.children;
        Node_5446 tmp;

        for(int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);

            if(children.containsKey(ch)) {  // 있니
                tmp = children.get(ch);     // 들어가자
                children = tmp.children;
            }
        }

    }
}
