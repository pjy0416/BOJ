package kakao;

public class test2 {
    static MyStack countStack = new MyStack();

    public static int solution(String[] user_id, String[] banned_id) {

        for(int banIdx=0; banIdx < banned_id.length; banIdx++) {
            String bannedUser = banned_id[banIdx];

            for(int userIdx=0; userIdx < user_id.length; userIdx++) {
                String user = user_id[userIdx];

                if(bannedUser.length() != user.length()) {      // 길이 다르면 검증할 필요 없음
                    break;
                }

                checkUser(bannedUser, user);

            }
        }


        int answer = 0;
        return answer;
    }

    private static void checkUser(String bannedUser, String user) {


    }

    public static void main (String[] args) {
        String str = "{{2},{2,1},{2,1,3},{2,1,3,4}}";

//        solution(str);

    }
}

class MyStack {
    private static int MAXSIZE = 10001;
    private int[] countList;
    private int top = -1;

    void push(int num) {
        if(top >= MAXSIZE) {
            top = 0;
        }
        countList[++top] = num;
    }

    int pop() {
        return top <0 ? -1 : countList[top--];
    }

}

/*
부분 함수가 주어지는데, 모든 부분함수를 만족시키는 함수를 만들어라
*/

/*
Test3
[제한 사항]
user_id 배열의 크기는 1 이상 8 이하입니다.
user_id 배열 각 원소들의 값은 길이가 1 이상 8 이하인 문자열입니다.
응모한 사용자 아이디들은 서로 중복되지 않습니다.
응모한 사용자 아이디는 알파벳 소문자와 숫자로만으로 구성되어 있습니다.
banned_id 배열의 크기는 1 이상 user_id 배열의 크기 이하입니다.
banned_id 배열 각 원소들의 값은 길이가 1 이상 8 이하인 문자열입니다.
불량 사용자 아이디는 알파벳 소문자와 숫자, 가리기 위한 문자 '*' 로만 이루어져 있습니다.
불량 사용자 아이디는 '*' 문자를 하나 이상 포함하고 있습니다.
불량 사용자 아이디 하나는 응모자 아이디 중 하나에 해당하고 같은 응모자 아이디가 중복해서 제재 아이디 목록에 들어가는 경우는 없습니다.
제재 아이디 목록들을 구했을 때 아이디들이 나열된 순서와 관계없이 아이디 목록의 내용이 동일하다면 같은 것으로 처리하여 하나로 세면 됩니다.
*/
