package programmers;

import java.util.HashMap;
import java.util.LinkedList;

public class OpenChat_42888 {
    final static String ENTERMENT = "님이 들어왔습니다.";
    final static String LEAVEMENT = "님이 나갔습니다.";
    final static String ENTER = "Enter" , CHANGE = "Change";

    private static String[] solution(String[] record) {
        HashMap<String, String> userMap = new HashMap<>();
        LinkedList<String[]> cmdList = new LinkedList<>();

        for(String str : record) {
            String[] strArr = str.split(" ");   //0 : CMD, 1 : User ID, 2 : NickName
            if(strArr[0].equals(CHANGE)) {  // 닉네임 변경
                userMap.replace(strArr[1], strArr[2]); // 닉네임 변경
            } else {
                if(strArr[0].equals(ENTER)) {   // 대화방 입장
                    if(userMap.containsKey(strArr[1])) { // 등록된 유저면
                        userMap.replace(strArr[1],strArr[2]); // 닉네임 변경
                    } else {
                        userMap.put(strArr[1],strArr[2]); // 정보 추가
                    }
                }
                cmdList.offer(new String[] {strArr[0], strArr[1]}); // 명령 추가
            }
        }
        String[] answer = new String[cmdList.size()];
        for(int i=0; i<answer.length; i++) {
            String[] cmdArr = cmdList.poll();
            StringBuffer sb = new StringBuffer(userMap.get(cmdArr[1]));
            if(cmdArr[0].equals(ENTER)) {   // 입장
                sb.append(ENTERMENT);
            } else {    // 퇴장
                sb.append(LEAVEMENT);
            }
            answer[i] = sb.toString();
        }
        return answer;
    }
    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        String[] result = solution(record);
        for(String str : result) {
            System.out.println(str);
        }
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/42888
 */