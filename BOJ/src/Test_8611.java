import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Test_8611 {
	static void getResult(BigInteger num) {
		String numStr = "" + num;
		int cnt =0;

		for(int sys=2; sys<10; sys++) {
			String str = convertSys(sys,num);
			if(isPalin(str)) {
				cnt ++;
				System.out.println(sys + " " + str);
			}
		}

		if(isPalin(numStr)) {
			System.out.println(10 + " " + numStr);
			cnt++;
		}

		if(cnt==0) {
			System.out.println("NIE");
		}
	}

	static boolean isPalin(String str) {
		int len = str.length();
		int end = str.length()-1;
		int center = len/2;

		for(int start =0; start <center; start++) {
			if(str.charAt(start) != str.charAt(end - start)) {
				return false;
			}
		}

		return true;
	}

	static String convertSys(int sys, BigInteger num) {
		String result ="";

		BigInteger temp = num;
		BigInteger param = new BigInteger("0");
		BigInteger bigSys = BigInteger.valueOf(sys);

		while(temp.compareTo(param) == 1) {
			result = temp.mod(bigSys) + result;
			temp = temp.divide(bigSys);
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BigInteger num = new BigInteger(bf.readLine());

		getResult(num);
		bf.close();
	}

}
