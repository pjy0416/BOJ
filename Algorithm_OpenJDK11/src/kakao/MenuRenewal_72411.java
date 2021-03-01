package kakao;

import java.util.*;

public class MenuRenewal_72411 {
  static final char EXIST = '1';
  static final int ASCII_UPPER_START = 'A';
  static final int MIN_MENU =2;
  static final int PERMUTATION_START_INDEX =0, DEFAULT_BITMASK =0, DEFAULT_COUNT =0;

  static List<String> permutationList;
  static Map<Integer, List<Menu>> answerMap;

  public static void main(String[] args) {
    String[][] orders = {{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"},
            {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"},
            {"XYZ", "XWY", "WXA"},
            {"ABCDE", "ABC", "ABD", "AE"}}; // AB, AE, ABC, ABD 나와야함
    int[][] course = {{2,3,4}, {2,3,5}, {2,3,4},{2,3}};
    for(int i=0; i<orders.length; i++) {
      String[] answer = solution(orders[i], course[i]);
      for(String menu : answer) {
        System.out.print(menu + " ");
      }
      System.out.println();
    }
  }

  private static String[] solution(String[] orders, int[] course) {
    initAnswerMap(course);
    permutationList = new ArrayList<>(); // 초기화
    for(String order : orders) {
      char[] orderArr = order.toCharArray();
      permutation(orderArr,PERMUTATION_START_INDEX, DEFAULT_BITMASK, DEFAULT_COUNT); // 메뉴 조합 생성
      setAnswerMap(orders, course); // permutation에 있는 메뉴 세트가 다른 order와 몇 개나 일치하는지 확인
      permutationList.clear();
    }

    return getAnswerArr(course);
  }

  private static void initAnswerMap(int[] course) { // 여기가 잘못됨, 동길이일때, 가장 많이 주문된 것을 출력해야함 (가장 많은 것 중 가장 긴 길이가 아니라)
    answerMap = new HashMap<>();
    for(int courseNumber : course) {
      answerMap.put(courseNumber, new ArrayList<>());
    }
  }

  private static void permutation(char[] order, int index, int bitMask, int count) {
    if(count >= MIN_MENU) {
      String menu = parseUpperString(bitMask);
      if(!permutationList.contains(menu)) {
        permutationList.add(menu);
      }
    }
    if(index == order.length) {
      return;
    }

    permutation(order, index+1, bitMask|(1<<order[index]-ASCII_UPPER_START), count+1);
    permutation(order, index+1, bitMask, count);
  }

  private static void setAnswerMap(String[] orders, int[] course) {
    for(String menu : permutationList) { // 각 메뉴가 몇개나 일치하는지 카운팅
      int len = menu.length();
      if(!isCourse(len, course)) {
        continue;
      }

      int count =0;
      int origin = parseBitMask(menu);
      for(String order : orders) { // 다른 메뉴들이랑 비교
        int target = parseBitMask(order);
        if((origin&target) == origin) { // 되는 메뉴 카운팅
          count++;
        }
      }

      if(count >=MIN_MENU) {
        List<Menu> list = answerMap.get(len);
        list.add(new Menu(menu, count));
        answerMap.replace(len, list);
      }
    }
  }

  private static String parseUpperString(int bitMask) {
    String menuBinary = Integer.toBinaryString(bitMask);
    int len = menuBinary.length();
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<len; i++) {
      if(menuBinary.charAt(len-i-1) == EXIST) {
        sb.append(getChar(i));
      }
    }
    return sb.toString();
  }

  private static boolean isCourse(int count, int[] course) {
    for(int courseNumber : course) {
      if(count == courseNumber) {
        return true;
      }
    }
    return false;
  }

  private static int parseBitMask(String order) {
    char[] orderArr = order.toCharArray();
    int bitMask =0;
    for(char ch : orderArr) {
      bitMask |= (1 << ch-ASCII_UPPER_START);
    }
    return bitMask;
  }

  private static char getChar(int ascii) {
    return (char)(ascii+ASCII_UPPER_START);
  }


  private static String[] getAnswerArr(int[] course) {
    List<String> answerList = new ArrayList<>();

    for(int courseNumber : course) {
      List<Menu> menuList = answerMap.get(courseNumber);
      Collections.sort(menuList);
      int prev = menuList.size() ==0 ? 0 : menuList.get(0).count;
      for(Menu menu : menuList) {
        if(prev != menu.count) {
          break;
        }
        if(!answerList.contains(menu.menus)) {
          answerList.add(menu.menus);
        }
      }
    }

    String[] answerArr = new String[answerList.size()];
    for(int i=0; i<answerList.size(); i++) {
      answerArr[i] = answerList.get(i);
    }
    Arrays.sort(answerArr);
    return answerArr;
  }

  private static class Menu implements Comparable<Menu> {
    String menus;
    int count;

    public Menu(String menus, int count) {
      this.menus = menus;
      this.count = count;
    }

    @Override
    public int compareTo(Menu menu) {
      return menu.count - this.count;
    }
  }
}
