package swMaestro;

public class SecondTest3 {
    public static void main(String[] args) {
        int N = 5;
        long[][] skillArea = {{1,3},{2,4},{1,5},{3,5},{2,8}};    //s,e

        int[] result = solution(N,skillArea);
        // 이거 아니면 2차원 배열로 서로 승리 체크해도 ㄱㅊ
        for(int num : result) {
            System.out.println(num);
        }
    }

    private static int[] solution(int n, long[][] skillArea) {
        int[] answer = new int[n];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(i != j) {
                    if(isWin(skillArea[i],skillArea[j])) {
                        answer[i]++;
                    }
                }
            }
        }

        return answer;
    }

    private static boolean isWin(long[] player1, long[] player2) {    // s2 < s1 < e2
        return player1[0] > player2[0] && player1[0] < player2[1] ? true : false;
    }
}
