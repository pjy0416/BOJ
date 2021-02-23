package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FBI_2857 {
  static final int AGENT_LEN = 5;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    char[][] agents = new char[AGENT_LEN][];

    for(int i=0; i<5; i++) {
      agents[i] = br.readLine().toCharArray();
    }

    br.close();
    solution(agents);
  }

  private static void solution(char[][] agents) {
    StringBuilder sb = new StringBuilder();
    final String NO_AGENT = "HE GOT AWAY!";
    final char F = 'F', B = 'B', I = 'I', SPACE = ' ';
    final int MAX_LEN = 10;

    for(int i=0; i<AGENT_LEN; i++) {
      int len = Math.min(MAX_LEN, agents[i].length);
      for(int j=2; j<len; j++) {
        if(agents[i][j-2] == F && agents[i][j-1] == B && agents[i][j] == I) {
          sb.append((i+1)).append(SPACE);
          break;
        }
      }
    }
    if(sb.length() ==0) {
      sb.append(NO_AGENT);
    }
    System.out.println(sb.toString());
  }
}
/*
FBI
A-0
A-0
A-0
fbi

FBIFBIFBIFBI
ABCDEFGHIJFBI
A
A
A
 */