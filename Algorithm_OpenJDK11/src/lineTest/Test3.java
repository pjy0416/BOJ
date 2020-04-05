package lineTest;

public class Test3 {
    static int max =0;
    private static int solution(String road, int n) {
        int answer = 0;
        max =0;
        StringBuilder sb = new StringBuilder();

        int sum =0;

        for(int i=0; i<road.length(); i++) {
            if(road.charAt(i) == '1') {
                sum += 1;
            } else {    // 0인 경우
                if(sum !=0) {
                    sb = sb.append(String.valueOf(sum)).append("0");
                } else {
                    sb = sb.append("0");
                }
                sum=0;
            }
        }

        String newRoad = sb.toString();
        if(newRoad.equals(newRoad.replace("0", ""))) {  // 공사 구간이 없으면
            for(int i=0; i<newRoad.length(); i++) {
                answer += Character.getNumericValue(newRoad.charAt(i));
            }
        } else {
            dp(newRoad, 0, n);
            answer = max;
        }

        return answer;
    }

    private static int getScore(String road) {
        int result =0;
        String[] roadArr = road.split("0");
        for(int i=0; i<roadArr.length; i++) {
            if(!roadArr[i].equals("")) {
                int score =0;
                for(int j=0; j<roadArr[i].length(); j++) {
                    score+= Character.getNumericValue(roadArr[i].charAt(j));
                }
                result = score > result ? score : result;
            }
        }
        return result;
    }

    private static void dp(String road, int idx, int cnt) {
        if(cnt ==0) {
            int score = getScore(road);
            max = score > max ? score : max;
            return;
        }
        if(idx == road.length()) {
            int score = getScore(road);
            max = score > max ? score : max;
            return;
        }
        if(road.charAt(idx) =='0') {
            dp(road, idx+1, cnt);   // 안바꾸거나
            StringBuilder sb = new StringBuilder(road);
            sb.setCharAt(idx,'1');
            dp(sb.toString(), idx+1, cnt-1);    // 바꾸거나
        } else {
            dp(road, idx+1, cnt);   // 넘기기
        }
    }
    public static void main(String[] args) {
//        String road = "111011110011111011111100011111";
//        int n = 3;
        String road = "001100";
        int n = 5;

        System.out.println(solution(road, n));
    }
}
