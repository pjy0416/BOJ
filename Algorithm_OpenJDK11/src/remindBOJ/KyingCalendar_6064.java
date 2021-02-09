package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KyingCalendar_6064 {
  public static void main(String[] args) throws IOException {
    final String NEW_LINE = "\n";
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int testCases = Integer.parseInt(br.readLine());
    while(testCases-- >0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int m = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
      sb.append(solution(m,n,x,y)).append(NEW_LINE);
    }
    System.out.println(sb.toString());
  }

  private static int solution(int m, int n, int x, int y) {
    final int MAX = m*n;
    int xCount =1, yCount =1, year =1, answer = m==1 && n ==1 ? 1 : -1;
    while(!(xCount == m && yCount ==n)) {
      if(xCount != x) {
        int increment = xCount > x ? m - xCount + x : x - xCount;
        year += increment;
        xCount = x;
        yCount = (yCount + increment) %n ==0 ? n : (yCount + increment) % n;
      }
      if(yCount != y) {
        int increment = yCount > y ? n - yCount + y : y - yCount;
        year += increment;
        xCount = (xCount + increment) % m ==0 ? m : (xCount + increment) % m;
        yCount = y;
      }

      if(year > MAX) {
        break;
      }
      if(xCount == x && yCount == y) {
        answer = year;
        break;
      }
    }
    return answer;
  }
}
/*
만일 x < M 이면 x' = x + 1이고, 그렇지 않으면 x' = 1이다.
같은 방식으로 만일 y < N이면 y' = y + 1이고, 그렇지 않으면 y' = 1이다.
<M:N>은 그들 달력의 마지막 해로서, 이 해에 세상의 종말이 도래한다는 예언이 전해 온다.

15
40000 39999 39999 39998
40000 39999 40000 39999
40000 40000 40000 39999
40000 39998 40000 39997
39999 2 39998 2
3 40000 3 39999
40000 3 40000 3
8 2 4 2
10 12 2 12
12 10 12 10
12 10 1 1
12 6 12 6
10 1 5 1
1 10 1 5
1 1 1 1

답
1599959999
1599960000
-1
-1
39998
39999
120000
4
12
60
1
12
5
5
1

3
10 12 3 9
10 12 7 2
13 11 5 6

답
33
-1
83
 */