import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_1000 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		short a = (short) (bf.read() -48);
		bf.read();
		short b = (short) (bf.read() -48);
		
		System.out.println(a+b);
	}

}
