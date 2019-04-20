package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 각각의 시험장에 총감독관은 오직 1명만 있어야 하고, 부감독관은 여러 명 있어도 된다.
 * 각 시험장마다 응시생들을 모두 감시해야 한다. 이때, 필요한 감독관 수의 최솟값을 구하는 프로그램을 작성하시오.
 *
 * 첫째 줄에 시험장의 개수 N(1 ≤ N ≤ 1,000,000)이 주어진다.
 * 둘째 줄에는 각 시험장에 있는 응시자의 수 Ai (1 ≤ Ai ≤ 1,000,000)가 주어진다.
 * 셋째 줄에는 B와 C가 주어진다. (1 ≤ B, C ≤ 1,000,000)
 *
 * 풀이))))
 * 시험장이 1,000,000개 이면서 응시자 수가 각 시험장마다 1,000,000명일 때, 총 감독관이 1명, 부감독관이 1명 씩만 감당 가능하면
 * 1,000,000 * 1,000,000 명의 감독관이 필요,
 * 결과는 long 형이 되야함.
 */
public class SuperVisor13458 {

    private static int[] examinee;
    private static int superMax, subMax;

    //////////////////// save datas
    static void saveMax(String str) {
        superMax = Integer.parseInt(str.split(" ")[0]);
        subMax = Integer.parseInt(str.split(" ")[1]);
    }

    static void saveExaminee(String str, int testSite) {
        String[] examineeStr = str.split(" ");

        for(int i=0; i<testSite; i++) {
            examinee[i] = Integer.parseInt(examineeStr[i]);
        }
    }
    ///////////////////////////

    static void getResult(int testSite) {
        long result =testSite;       // 총 감독관 시험장만큼 배치

        for(int people : examinee) {
            people -= superMax;       // 총 감독관이 한번에 감독 가능한 애들 제외


            if(people >0) {                       // 남은 애들이 있으면
                int div = people/subMax;
                int mod = people%subMax;

                result += div;                    // 부 감독관 필요한 만큼 배치
                if(mod >0) {                      // 배치하고 남은애 있으면
                    result += +1;                 // 부감독 한명 추가
                }

            }
        }
        System.out.println(result);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testSite = Integer.parseInt(br.readLine());
        examinee = new int[testSite];

        // save datas
        saveExaminee(br.readLine(), testSite);
        saveMax(br.readLine());

        getResult(testSite);

        br.close();
    }
}
