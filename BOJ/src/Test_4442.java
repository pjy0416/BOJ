import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Test_4442 {        // wrong code
    /** 시도
     *  1. 둘 다 Array로 Split하여 프로도는 prodoArr, 샘은 samArr로 선언
     *  loop 돌면서 prodo Array 의 index와 옆 index 두 가지가 sam Array의 같은 index에 존재하지 않으면
     *  counting 하는 방식
     *
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~> 틀림
     *
     *  2. Array를 Arrays.asList를 통해 두 명의 좌석표 ArrayList를 선언해주고
     *     순서가 맞지 않은 경우 .lastIndexOf(이름)을 통해 index를 찾아서 Array와 List 둘 다 두 명의 좌석을 바꿔줬음
     *     (Array의 값을 index로 접근하는 것이 List의 get(element) 메소드 보다 빠르기 때문에
     *
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~> over time
     *
     *  3. Array에 대해서 binarySearch(Array, element) 로 인덱스를 찾고 바꿔주는 방식
     *
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~> 틀림
     *
     *  4. Sam의 좌석표를 HashMap<이름, 인덱스> </>으로 선언해주고 loop를 돌며 Prodo의 좌석표 사람과 다르면
     *     HashMap 에서 get을 통해 인덱스를 받아와서 List와 HashMap 둘 다 바꿔줬음
     *
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~> 틀림
     * */
    private static int diffSeat(String prodo, String sam, int num) {
        int cnt =0;

        String[] prodoArr = prodo.split(" ");
        String[] samArr = sam.split(" ");

        HashMap<String, Integer> samMap = new HashMap<>();

        for(int i=0; i<num; i++) {
            String prodoSeat = prodoArr[i];
            String samSeat = samArr[i];
            boolean isSame = prodoSeat.equals(samSeat);

            if(!isSame) {
                int idx = samMap.get(prodoSeat);

                samArr[i] = prodoSeat;
                samArr[idx] = samSeat;

                samMap.replace(prodoSeat, i);
                samMap.replace(samSeat, idx);
                cnt++;
            }
        }


        return cnt;
    }

    private static int getIndex(String[] strArr, String target, int start) {
        int len = strArr.length;

        for(int i=start+1; i<len; i++) {
            if(strArr[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String hobitNum = br.readLine();

            if(hobitNum.equals("0")) {
                break;
            } else {
                int num = Integer.parseInt(hobitNum);

                String prodo = br.readLine();
                String sam = br.readLine();

                System.out.println(diffSeat(prodo, sam, num));
            }
        }

        br.close();
    }
}
