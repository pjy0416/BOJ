package programmers;

public class MultiplyProcession_12949 {
    private static int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];

        for (int i = 0; i < answer.length; i++) {  // 행
            for (int j = 0; j < answer[i].length; j++) {   // 열
                //answer[i][j] = arr1은 행이 i, arr2는 열이 j 움직이는거는 둘이 공통되는 부분의 길이
                for (int idx = 0; idx < arr1[i].length; idx++) {
                    answer[i][j] += arr1[i][idx] * arr2[idx][j];
                }
            }
        }
        return answer;
    }
    public static void main(String[] args) {
        int[][] arr1= {{1, 4}, {3, 2}, {4, 1}};
        int[][] arr2= {{3,3},{3,3}};

        int[][] result = solution(arr1, arr2);
        for(int[] line : result) {
            for(int num : line) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
