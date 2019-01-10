import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_13458 {

    static void getResult(int rooms, String examinee, int headSV, int subSV) {
        long svNum =rooms;   // 감시 인원의 수가 int형을 넘어 갈 수 있기때문에 long으로 해줘야한다.

        String[] examNums = examinee.split(" ");

        for(int i=0; i< rooms; i++) {
            int examNum = Integer.parseInt(examNums[i]) - headSV;

            if(examNum >0) {
                int div = examNum/subSV;
                int mod = examNum%subSV;

                if(mod >0) {
                    svNum += div +1;
                } else {
                  svNum += div;
                }
            }
        }

        System.out.println(svNum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int rooms = Integer.parseInt(br.readLine());    // 시험장 갯수

        String examinee = br.readLine();                // 시험장 당 시험 응시자

        String sv = br.readLine();                      // 감독관 (Supervisor)이 감시 가능한 인원

        int headSV = Integer.parseInt(sv.split(" ")[0]);    // 메인 감독관
        int subSV = Integer.parseInt(sv.split(" ")[1]);     // 부 감독관

        getResult(rooms, examinee, headSV, subSV);

        br.close();
    }

}
