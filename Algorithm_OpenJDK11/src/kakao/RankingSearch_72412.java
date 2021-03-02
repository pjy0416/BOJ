package kakao;

import java.util.*;

public class RankingSearch_72412 {
  static final String DEFAULT = "-", QUERY_REGEX = " and ", SPACE = " ", NOTHING = "";
  static final int CONDITION_LENGTH = 4;
  static final int SCORE_INDEX = 4;

  public static void main(String[] args) {
    String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
    String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
    int[] expectedResult = {1,1,1,1,2,4};
    int[] receivedResult = solution(info, query);
    boolean isOk = true;

    for(int i=0; i<expectedResult.length; i++) {
      if(expectedResult[i] != receivedResult[i]) {
        isOk = false;
        break;
      }
    }
    System.out.println(isOk ? "맞음" : "틀림");
  }

  public static int[] solution(String[] info, String[] query) {
    Map<String, List<Integer>> map = getInfoMap(info);
    int len = query.length;
    int[] answer = new int[len];

    for(int i=0; i<len; i++) {
      answer[i] = getCounts(map, query[i]);
    }

    return answer;
  }

  private static Map<String, List<Integer>> getInfoMap(String[] info) {
    HashMap<String,List<Integer>> map = new HashMap<>();
    final int MAX_CONDITION = 1 << CONDITION_LENGTH;
    for(String applicant : info) {
      String[] data = getCondition(applicant);
      int score = Integer.parseInt(data[SCORE_INDEX]);

      for(int i=0; i<MAX_CONDITION; i++) {
        StringBuilder sb = new StringBuilder();
        for(int j=0; j<CONDITION_LENGTH; j++) {
          if((i&(1 <<j)) >0) { // i: 1 ~> j: 0, i: 2 ~> j: 1, i: 3 ~> j:0,1 , i: 4 ~> j: 1,2 
            sb.append(data[j]); // => 조합 가능한 모든 경우를 만들어 key로 생성
          }
        }
        String key = sb.toString();
        map.computeIfAbsent(key, mappingFunction -> new ArrayList<>()).add(score);
      }
    }

    for(Map.Entry<String, List<Integer>> entry : map.entrySet()) {
      entry.getValue().sort(null);
    }

    return map;
  }

  private static int getCounts(Map<String, List<Integer>> map, String query) {
    String[] queryCondition = getCondition(query.replaceAll(DEFAULT, NOTHING).replaceAll(QUERY_REGEX, SPACE));
    String key = getKey(queryCondition);
    int score = Integer.parseInt(queryCondition[SCORE_INDEX]);
    List<Integer> list = map.getOrDefault(key, new ArrayList<>());

    return list.size() - lowerBound(list, score);
  }

  private static String[] getCondition(String condition) {
    return condition.split(SPACE);
  }

  private static String getKey(String[] conditions) {
    StringJoiner sj = new StringJoiner(NOTHING);
    for(int i=0; i<CONDITION_LENGTH; i++) {
      sj.add(conditions[i]);
    }
    return sj.toString();
  }

  private static int lowerBound(List list, int score) {
    int left =0, right = list.size();

    while(left < right) {
      int mid = (left + right) /2;
      if((int)list.get(mid) >= score) {
        right = mid;
      } else {
        left = mid+1;
      }
    }
    return left;
  }

}
