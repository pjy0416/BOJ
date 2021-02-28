package kakao;

public class RecommendNewId_72410 {
  static final String NOTHING_STATEMENT = "", DEFAULT_STRING = "a", SINGLE_DOT = ".";
  static final int ZERO = 0, MAX_ID_LENGTH = 15, MIN_ID_LENGTH = 3;

  public static void main(String[] args) {
    String[] inputs = {"...!@BaT#*..y.abcdefghijklm", "z-+.^.", "=.=", "123_.def", "abcdefghijklmn.p", "ABCDEFGHIJKLMO*23"};

    for(String newId : inputs) {
      System.out.println(solution(newId));
    }
  }

  private static String solution(String newId) {
    // 1단계
    String recommendId = newId.toLowerCase();
    // 2단계
    recommendId = filter(recommendId);
    // 3단계
    recommendId = getSingleDotString(recommendId);
    // 4단계
    recommendId = getRemoveDotCondition(recommendId);
    // 5단계
    if(recommendId.length() == ZERO) {
      recommendId = DEFAULT_STRING;
    }
    // 6단계
    if(recommendId.length() > MAX_ID_LENGTH) {
      recommendId = recommendId.substring(ZERO, MAX_ID_LENGTH).replaceAll("[.]$", NOTHING_STATEMENT);
    }
    // 7단계
    recommendId = getMinCondition(recommendId);
    return recommendId;
  }

  private static String filter(String id) {
    return id.replaceAll("[^a-z0-9._-]", NOTHING_STATEMENT);
  }

  private static String getSingleDotString(String id) {
    return id.replaceAll("[.]{2,}", SINGLE_DOT);
  }

  private static String getRemoveDotCondition(String id) {
    return id.replaceAll("^[.]|[.]$", NOTHING_STATEMENT);
  }

  private static String getMinCondition(String id) {
    StringBuilder sb = new StringBuilder(id);
    while(sb.length() <MIN_ID_LENGTH) {
      sb.append(sb.charAt(sb.length()-1));
    }
    return sb.toString();
  }
}

/*
아이디의 길이는 3자 이상 15자 이하여야 합니다.
아이디는 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.) 문자만 사용할 수 있습니다.
단, 마침표(.)는 처음과 끝에 사용할 수 없으며 또한 연속으로 사용할 수 없습니다.

7단계의 순차적인 처리 과정을 통해 신규 유저가 입력한 아이디가 카카오 아이디 규칙에 맞는 지 검사하고
규칙에 맞지 않은 경우 규칙에 맞는 새로운 아이디를 추천

1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
     만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
 */
