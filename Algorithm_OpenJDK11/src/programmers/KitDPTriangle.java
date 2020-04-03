package programmers;

import java.util.Arrays;
import java.util.Comparator;

public class KitDPTriangle {
    private static int solution(int[][] triangle) {
        int len = triangle.length;
        System.out.println(triangle[0][0]);
        for(int depth =1; depth <len; depth++) {
            for(int idx=0; idx<triangle[depth].length; idx++) {
                int left = idx-1;
                int right = idx;
                if(left ==-1) {   // 0번 째 인덱스라는 말
                    triangle[depth][idx] += triangle[depth-1][idx];
                } else if(right == triangle[depth-1].length) {  // 마지막 인덱스
                    triangle[depth][idx] += triangle[depth-1][left];
                } else {    // 가운데 수들
                    triangle[depth][idx] += Math.max(triangle[depth-1][left], triangle[depth-1][right]);
                }
            }
            for(int num : triangle[depth]) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        /*if(triangle.length ==1) {
            return triangle[0][0];
        }
        if(triangle.length ==2) {
            return Math.max(triangle[0][0]+triangle[1][0],triangle[0][0]+triangle[1][1]);
        }
        findNum(triangle[0][0], 0, 0, triangle, triangle.length-1);*/
        Arrays.sort(triangle[len-1]);
        return triangle[len-1][len-1];
    }

    private static void findNum(int sum, int depth, int idx, int[][] triangle, int len) {
        if(depth == len) {
//            max = max < sum ? sum : max;
            return;
        }

        findNum(sum+triangle[depth+1][idx], depth+1, idx, triangle,len);
        if(idx+1 <triangle[depth+1].length) {
//            System.out.print("depth : " + depth + "\t idx : " + idx + " 에서\t");
//            System.out.println("다음 Depth : " + (depth+1) + "\t idx : " + (idx+1) +"로 넘어감");
            findNum(sum+triangle[depth+1][idx+1], depth+1, idx+1, triangle,len);
        }
    }
    public static void main(String[] args) {
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};

        System.out.println(solution(triangle));
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/43105

정수 삼각형

문제 설명
위와 같은 삼각형의 꼭대기에서 바닥까지 이어지는 경로 중,
거쳐간 숫자의 합이 가장 큰 경우를 찾아보려고 합니다.
아래 칸으로 이동할 때는 대각선 방향으로 한 칸 오른쪽 또는 왼쪽으로만 이동 가능합니다.
예를 들어 3에서는 그 아래칸의 8 또는 1로만 이동이 가능합니다.

삼각형의 정보가 담긴 배열 triangle이 매개변수로 주어질 때,
거쳐간 숫자의 최댓값을 return 하도록 solution 함수를 완성하세요.

제한사항
삼각형의 높이는 1 이상 500 이하입니다.
삼각형을 이루고 있는 숫자는 0 이상 9,999 이하의 정수입니다.
입출력 예
                        triangle	                          result
[[7], [3, 8], [8, 1, 0], [2, 7, 4, 4], [4, 5, 2, 6, 5]]	    30
 */
