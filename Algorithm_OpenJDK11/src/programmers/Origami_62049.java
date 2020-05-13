package programmers;

public class Origami_62049 {
    static int[][] answer;
    private static int[] solution(int n) {
        answer = new int[n][];
        makeArr(n);
        return answer[n-1];
    }

    private static void makeArr(int n) {
        for(int i=1; i<=n; i++) {
            answer[i-1] = new int[(int)Math.pow(2,i)-1];
            if(i !=1) {
                setAnswer(i);
            }
        }
    }

    private static void setAnswer(int num) {
        int addIdx = (int)Math.pow(2,num-1);
        int standard = (int)Math.pow(2, num-1) -1; // 기준점, 중간

        for(int i=0; i<standard; i++) { // 이 전 값 저장
            answer[num-1][i] = answer[num-2][i];
        }

        for(int i=1; i<=standard; i++) {
            answer[num-1][standard+i] = getOpposNum(answer[num-1][standard-i]);
        }
        /*for(int i=0; i<answer[num-2].length; i++) {
//            System.out.println("num : " + num + " \t i : " + i + " \t addIdx +i : " + (addIdx+i));
            answer[num-1][i] = answer[num-2][i];    // 그대로

            if(answer[num-2][i] == 1) { // 1이면
                answer[num-1][addIdx+i] = 0;    // 다음거는 0
            } else {    // 0이면
                answer[num-1][addIdx+i] = 1;    // 다음거는 1
            }
//            answer[num-1][addIdx+i] = answer[num-2][i]; // 반대로
        }*/

        /*if(num != 1) {  // 마지막 IDX
            answer[num-1][(int)Math.pow(2,num)-2] =1;
        }*/
    }

    private static int getOpposNum(int num) {
        return num==0 ? 1 : 0;
    }

    public static void main(String[] args) {
        int n = 4;
        int[] result = solution(n);
        for(int num : result) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
