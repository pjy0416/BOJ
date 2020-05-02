package kakao;

import java.util.HashMap;

public class SearchLyrics {
    private static int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        Trie trie = getTrie(words); // 정방향 Trie
        Trie reverseTrie = getReverseTrie(words);   // 역방향 Trie

        for(int i=0; i<answer.length; i++) {
            answer[i] = getCount(trie, reverseTrie, queries[i]);
        }

        return answer;
    }

    private static int getCount(Trie trie, Trie reverseTrie, String query) {    // query 에 해당하는 문자열 cnt 반환
        int cnt =0;
        if(query.charAt(0) == '?') {    // reverse 트리에서 탐색
            StringBuffer sb = new StringBuffer(query);
            cnt += reverseTrie.search(sb.reverse().toString());
        } else {    // trie 탐색
            cnt+= trie.search(query);
        }
        return cnt;
    }

    private static Trie getTrie(String[] words) {   // 정방향 트리 초기화 및 반환
        Trie trie = new Trie();
        for(String word : words) {
            trie.insert(word);
        }
        return trie;
    }

    private static Trie getReverseTrie(String[] words) {    // 역방향 트리 초기화 및 반환
        Trie trie = new Trie();
        for(String word : words) {
            StringBuffer sb = new StringBuffer(word);
            trie.insert(sb.reverse().toString());
        }
        return trie;
    }

    public static void main(String[] args) {
        String[] words ={"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries ={"fro??", "????o", "fr???", "fro???", "pro?"};
        int[] result = solution(words, queries);

        for(int num : result) {
            System.out.println(num);
        }
    }
}

class Node {    // Node class
    char ch;
    HashMap<Character, Node> children;          // 자식 노드
    HashMap<Integer, Integer> childrenCnt;      // 와일드카드 ?를 위해, key에는 query의 길이, value에는 갯수가 들어감
    boolean isEnd;                              // 끝인지 판별하는 boolean 이지만 여기서는 필요 없을 것 같기도

    public Node() {
        this.children = new HashMap<>();
        this.childrenCnt = new HashMap<>();
    }
    public Node(char ch) {
        this.children = new HashMap<>();
        this.childrenCnt = new HashMap<>();
        this.ch = ch;
    }
}

class Trie {
    private Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {   // word 삽입
        HashMap<Character, Node> children = root.children;
        updateChildrenCnt(root, word.length()); // query 의 모든 글자가 ? 로 이루어진 경우를 위해, root에도 저장

        for(int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            Node tmp;
            if(children.containsKey(ch)) {  //이미 있으면, 들어가기
                tmp = children.get(ch);
                children = tmp.children;
            } else {    // 없으면 만들어서 삽입 후, children 으로 들어감
                tmp = new Node(ch);
                children.put(ch, tmp);
                children = tmp.children;
            }
            updateChildrenCnt(tmp, word.length());  //  각 depth 마다 word(query)의 length 만큼 가진 갯수 저장
            if(i == word.length()-1) {
                tmp.isEnd = true;
            }
        }
    }
    public void updateChildrenCnt(Node node, int len) { // length 갯수 저장
        if(node.childrenCnt.containsKey(len)) {
            node.childrenCnt.replace(len, node.childrenCnt.get(len)+1);
        } else {
            node.childrenCnt.put(len, 1);
        }
    }

    public int search(String word) {
        if(word.replaceAll("[?]","").length() ==0) {    // 모든 문자가 ?로 이루어진 경우
            if(root.childrenCnt.containsKey(word.length())) {   // 글자 수 만큼 있으면
                return root.childrenCnt.get((word.length()));   // 갯수 반환
            } else {    // 없으면
                return 0;   //0 반환
            }
        }
        HashMap<Character, Node> children = root.children;
        Node tmp = null;

        int cnt = 0;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ch != '?') {    // ?가 아닌 경우
                if (children.containsKey(ch)) {  // ch가 있으면
                    tmp = children.get(ch);     // 들어가자
                    children = tmp.children;
                } else {    // 다른 글자면
                    return 0;   // 맞는 조건 없음
                }
            } else {    // ?를 만났으면
                if (tmp.childrenCnt.containsKey(word.length())) {   // query 의 갯수 만큼 존재하는 cnt 반환
                    cnt = tmp.childrenCnt.get(word.length());
                    break;
                }
            }
        }
        return cnt;
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/60060

가사 검색
문제 설명
[본 문제는 정확성과 효율성 테스트 각각 점수가 있는 문제입니다.]

친구들로부터 천재 프로그래머로 불리는 프로도는 음악을 하는 친구로부터 자신이 좋아하는 노래 가사에 사용된 단어들 중에
특정 키워드가 몇 개 포함되어 있는지 궁금하니 프로그램으로 개발해 달라는 제안을 받았습니다.
그 제안 사항 중, 키워드는 와일드카드 문자중 하나인 '?'가 포함된 패턴 형태의 문자열을 뜻합니다.
와일드카드 문자인 '?'는 글자 하나를 의미하며, 어떤 문자에도 매치된다고 가정합니다.
예를 들어 "fro??"는 "frodo", "front", "frost" 등에 매치되지만 "frame", "frozen"에는 매치되지 않습니다.

가사에 사용된 모든 단어들이 담긴 배열 words와 찾고자 하는 키워드가 담긴 배열 queries가 주어질 때,
각 키워드 별로 매치된 단어가 몇 개인지 순서대로 배열에 담아 반환하도록 solution 함수를 완성해 주세요.

가사 단어 제한사항

words의 길이(가사 단어의 개수)는 2 이상 100,000 이하입니다.
각 가사 단어의 길이는 1 이상 10,000 이하로 빈 문자열인 경우는 없습니다.
전체 가사 단어 길이의 합은 2 이상 1,000,000 이하입니다.
가사에 동일 단어가 여러 번 나올 경우 중복을 제거하고 words에는 하나로만 제공됩니다.
각 가사 단어는 오직 알파벳 소문자로만 구성되어 있으며, 특수문자나 숫자는 포함하지 않는 것으로 가정합니다.

검색 키워드 제한사항

queries의 길이(검색 키워드 개수)는 2 이상 100,000 이하입니다.
각 검색 키워드의 길이는 1 이상 10,000 이하로 빈 문자열인 경우는 없습니다.
전체 검색 키워드 길이의 합은 2 이상 1,000,000 이하입니다.
검색 키워드는 중복될 수도 있습니다.
각 검색 키워드는 오직 알파벳 소문자와 와일드카드 문자인 '?' 로만 구성되어 있으며, 특수문자나 숫자는 포함하지 않는 것으로 가정합니다.
검색 키워드는 와일드카드 문자인 '?'가 하나 이상 포함돼 있으며, '?'는 각 검색 키워드의 접두사 아니면 접미사 중 하나로만 주어집니다.
예를 들어 "??odo", "fro??", "?????"는 가능한 키워드입니다.
반면에 "frodo"('?'가 없음), "fr?do"('?'가 중간에 있음), "?ro??"('?'가 양쪽에 있음)는 불가능한 키워드입니다.

입출력 예
                        words	                                                queries	                    result
["frodo", "front", "frost", "frozen", "frame", "kakao"]	["fro??", "????o", "fr???", "fro???", "pro?"]	 [3, 2, 4, 1, 0]

입출력 예에 대한 설명

"fro??"는 "frodo", "front", "frost"에 매치되므로 3입니다.
"????o"는 "frodo", "kakao"에 매치되므로 2입니다.
"fr???"는 "frodo", "front", "frost", "frame"에 매치되므로 4입니다.
"fro???"는 "frozen"에 매치되므로 1입니다.
"pro?"는 매치되는 가사 단어가 없으므로 0 입니다.
 */
