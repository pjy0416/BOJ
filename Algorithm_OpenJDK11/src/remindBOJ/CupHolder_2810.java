package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CupHolder_2810 {
  static final char COUPLE = 'C';

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    String inputStr = br.readLine();

    br.close();
    solution(n, inputStr);
  }

  private static void solution(int n, String inputStr) {
    char[] seats = inputStr.replaceAll("[LL]{2}",String.valueOf(COUPLE)).toCharArray();
    int len = seats.length;
    boolean[] occupation = new boolean[2*len+1]; // 좌석 양 옆에 홀더

    for(int i=0; i<len; i++) {
      int index = 2*i+1; // 팔걸이를 인덱스 하나로 받기 위해, 좌석에 대한 인덱스 재정의
      int leftHolder = index-1, rightHolder = index+1;
      if(seats[i] == COUPLE) { // 양 옆을 다 차지해야함
        if(occupation[leftHolder]) { // 좌측 홀더 자리 없음
          n--;
        }
        if(occupation[rightHolder]) { // 우측 홀더 자리 없음
          n--;
        }
        occupation[leftHolder] = true;
        occupation[rightHolder] = true;
      } else { // SOLO는 양 옆 중 남는거 하나 차지
        if(!occupation[leftHolder]) { // left 홀더 자리가 있으면
          occupation[leftHolder] = true;
        } else if(!occupation[rightHolder]) { // 오른쪽 자리 남아있으면
          occupation[rightHolder] = true;
        } else { // 둘 다 없으면
          n--;
        }
      }
    }
    System.out.println(n);
  }
}
