package programmers;

import java.util.ArrayList;

public class KitDBFSNetwork {
    private static int solution(int n, int[][] computers) {
        ArrayList<ArrayList<Integer>> networks = inItNetwork();

        for(int i=0; i<n; i++) {
            int idx = searchNum(networks,i);    // i번째 컴퓨터가 포함된 네트워크 탐색
            ArrayList<Integer> origin;
            if(idx ==-1) {  // 없으면 네트워크 생성
                origin = new ArrayList<>();
                origin.add(i);
            } else {    // 있으면 네트워크 가져오기
                origin = networks.get(idx);
                networks.remove(idx);   // 해당 네트워크는 목록에서 제거
            }

            for(int j=0; j<computers[i].length; j++) {
                if(i !=j && computers[i][j] ==1) {  // i번째 컴퓨터에 연결된 j(target) 컴퓨터
                    int targetIdx = searchNum(networks, j); // target이 다른 네트워크랑 연결됐는지 확인
                    if(targetIdx != -1) {   // target이 연결된 네트워크가 존재하면
                        ArrayList<Integer> network = networks.get(targetIdx);   // 받아와서
                        for(int num : origin) {
                            if(!network.contains(num)) {
                                network.add(num);   // 네트워크를 합쳐주고
                            }
                        }
                        origin = network;   // origin 교체
                        networks.remove(targetIdx); // network 목록에서 제거
                    } else {
                        origin.add(j);  // i에 j 연결
                    }
                }
            }
                networks.add(origin);   // 네트워크 리스트에 완성된 네트워크 추가
        }

        return networks.size();
    }

    private static ArrayList<ArrayList<Integer>> inItNetwork() {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> tmp = new ArrayList<>();
        tmp.add(0);
        result.add(tmp);

        return result;
    }

    private static int searchNum(ArrayList<ArrayList<Integer>> networks, int num) {
        int result =-1;

        for(int idx=0; idx<networks.size(); idx++) {
            if(networks.get(idx).contains(num)) {
                result = idx;
                break;
            }
        }

        return result;
    }
    /*
    class Solution {    // dfs
    int[] visit;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visit = new int[n];
        for(int i=0; i<computers.length; i++) {
            if(visit[i] ==0) {
                dfs(i, computers);
                answer++;
            }
        }
        return answer;
    }

    private void dfs(int idx, int[][] computers) {
        if(visit[idx] == 0) {
            visit[idx] =1;
            for(int i=0; i<computers[idx].length; i++) {
                if(computers[idx][i] ==1 && visit[i] ==0) {
                    dfs(i, computers);
                }
            }
        }
    }
}
     */

    /*
    import java.util.HashSet;

class Solution { // find - union
    int[] parents;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        inItParents(n);
        for(int i=0; i<computers.length; i++) {
            for(int j=0; j<computers[i].length; j++) {
                if(computers[i][j] ==1) {
                    if(find(i) != find(j)) {
                        union(i,j);
                    }
                }
            }
        }

        HashSet<Integer> set = new HashSet<>();
        for(int num : parents) {
            set.add(find(num));
        }
        return set.size();
    }

    private void inItParents(int n) {
        parents = new int[n];
        for(int i=0; i<n; i++) {
            parents[i] = i;
        }
    }

    private int find(int val) {
        if(val == parents[val]) {
            return val;
        }
        return parents[val] = find(parents[val]);
    }

    private void union(int val1, int val2) {
        val1 = find(val1);
        val2 = find(val2);
        if(val1 != val2) {
            parents[val2] = val1;
        }
    }
}
     */

    public static void main(String[] args) {
        int n = 3;
        int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
//        int n = 3;
//        int[][] computers = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        System.out.println(solution(n, computers));
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/43162
네트워크

문제 설명
네트워크란 컴퓨터 상호 간에 정보를 교환할 수 있도록 연결된 형태를 의미합니다.
예를 들어, 컴퓨터 A와 컴퓨터 B가 직접적으로 연결되어있고,
컴퓨터 B와 컴퓨터 C가 직접적으로 연결되어 있을 때 컴퓨터 A와 컴퓨터 C도 간접적으로 연결되어 정보를 교환할 수 있습니다.
따라서 컴퓨터 A, B, C는 모두 같은 네트워크 상에 있다고 할 수 있습니다.

컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열 computers가 매개변수로 주어질 때,
네트워크의 개수를 return 하도록 solution 함수를 작성하시오.

제한사항
컴퓨터의 개수 n은 1 이상 200 이하인 자연수입니다.
각 컴퓨터는 0부터 n-1인 정수로 표현합니다.
i번 컴퓨터와 j번 컴퓨터가 연결되어 있으면 computers[i][j]를 1로 표현합니다.
computer[i][i]는 항상 1입니다.

입출력 예

n	            computers	             return
3	[[1, 1, 0], [1, 1, 0], [0, 0, 1]]	    2
3	[[1, 1, 0], [1, 1, 1], [0, 1, 1]]	    1
 */
