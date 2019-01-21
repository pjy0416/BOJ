import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_15318 {       // over time
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());
        String[] arr = br.readLine().split(" ");

        for(int i=0; i<size; i++) {
            int result =0;
            for(int k=0; k<size; k++) {
                int idx = (i+k)%size;
                int tmp = (k+1) * Integer.parseInt(arr[idx]);
                if(k%2 !=0) {
                    tmp *=-1;
                }
                result += tmp;
            }
            System.out.print(result + " ");
        }

        br.close();
    }
}
