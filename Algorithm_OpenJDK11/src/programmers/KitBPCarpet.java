package programmers;

public class KitBPCarpet {

    private static int[] solution(int brown, int red) {
        int[] answer = findPos(brown, red);

        return answer;
    }

    private static int[] findPos(int brown, int red) {
        // Logic
        // 2x + 2y +4 = brown +red
        // 1. x + y = (brown + red -4) / 2
        // 2. x*y = red
        // 3. 최종 좌표는 x +2, y+2

        int[] result = new int[2];
        int sumPos = (brown-4) / 2;
//        System.out.println(sumPos);
        for(int i=1; i<=sumPos/2; i++) {
            int x = i;
            int y = sumPos -x;
//            System.out.println("x => " +x + "\t y = " +y);
            if(x*y == red) {
                result[0]=y +2;
                result[1]=x +2;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        /*int brown = 24;
        int red = 24;*/
/*        int brown = 10;
        int red = 2;*/
        int brown = 8;
        int red = 1;
        int[] result = solution(brown, red);

        System.out.println(result[0] +", " + result[1]);
    }
}
/*
문제 설명
Leo는 카펫을 사러 갔다가 아래 그림과 같이 중앙에는 빨간색으로 칠해져 있고 테두리 1줄은 갈색으로 칠해져 있는 격자 모양 카펫을 봤습니다.

Leo는 집으로 돌아와서 아까 본 카펫의 빨간색과 갈색으로 색칠된 격자의 개수는 기억했지만, 전체 카펫의 크기는 기억하지 못했습니다.

Leo가 본 카펫에서 갈색 격자의 수 brown, 빨간색 격자의 수 red가 매개변수로 주어질 때 카펫의 가로, 세로 크기를 순서대로 배열에 담아 return 하도록 solution 함수를 작성해주세요.

제한사항
갈색 격자의 수 brown은 8 이상 5,000 이하인 자연수입니다.
빨간색 격자의 수 red는 1 이상 2,000,000 이하인 자연수입니다.
카펫의 가로 길이는 세로 길이와 같거나, 세로 길이보다 깁니다.
입출력 예
brown	red	return
10	     2	[4, 3]
8	     1	[3, 3]
24	     24	[8, 6]
출처

※ 공지 - 2020년 2월 3일 테스트케이스가 추가되었습니다.
 */
