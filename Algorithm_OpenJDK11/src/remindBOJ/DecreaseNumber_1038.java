package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class DecreaseNumber_1038 {
  static final int MAX_NUMBER =9, MAX_DIGIT =10;
  static final int DEFAULT_ANSWER = -1;
  
  static long answer;
  static int remain;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    br.close();

    solution(n);
  }

  private static void solution(int n) {
    answer = DEFAULT_ANSWER; // 못찾는 경우로 초기화
    if(n <=MAX_NUMBER) {
      answer = n;
    }
    remain = n-MAX_NUMBER;
    for(int digit=2; digit<=MAX_DIGIT; digit++) {
      for(int startNumber=1; startNumber<=MAX_NUMBER; startNumber++) {
        LinkedList list = new LinkedList();
        list.offer(startNumber);
        findNumber(startNumber, digit, list);
      }
    }
    System.out.println(answer);
  }

  private static void findNumber(int max, int digit, LinkedList<Integer> list) {
    if(answer != DEFAULT_ANSWER) {
      return;
    }
    if(list.size() == digit) { // 찾았다 요놈
      if(--remain == 0) {
        answer = getNumber(digit, list);
      }
      return;
    }

    for(int i=0; i<max; i++) {
      if(!list.isEmpty() && list.peekLast() >i) {
        list.offer(i);
        findNumber(i, digit, list);
        list.pollLast();
      }
    }
  }

  private static long getNumber(int digit, LinkedList<Integer> list) {
    long result =0;

    for(int i=0; i<digit; i++) {
      result += list.get(i)*Math.pow(10,digit-i-1);
    }
    return result;
  }
}
