import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Test_3052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        HashSet<Integer> set = new HashSet<>();

        for(int i=0; i<10; i++) {
            int tmp = Integer.parseInt(br.readLine())%42;
            set.add(tmp);
        }

        System.out.println(set.size());

        br.close();
    }
}
