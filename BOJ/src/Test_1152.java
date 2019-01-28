import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_1152 {        // 28696KB 208MS
                                // 왜 for문 없이 strArr[0]에 접근하면 컴파일 에러 뜨는지 모르겠음..
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputStr = br.readLine();
        String[] strArr = inputStr.split(" ");
        int size = strArr.length;

        int none =0;

        for(int i=0; i<size; i++) {
            if(strArr[i].equals("")) {
                none++;
            }
        }
        System.out.println(size -none);
        br.close();
    }
}
