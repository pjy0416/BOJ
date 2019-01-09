import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_8611 {
	static final short max =10;
	static void getResult(int num) {
		
		for(int sys=2; sys<max; sys++) {
			String str = convertSys(sys,num);
			if(isPalin(str)) {
				System.out.println(sys + " " + str);
			}
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
	
	static String convertSys(int sys, int num) {
		switch(sys) {
		case 2:
			return Integer.toBinaryString(num);
		case 8:
			return Integer.toOctalString(num);
		default :
			String result ="";
			int temp = num;
			while(temp != 0) {
				result = String.valueOf(temp%sys).concat(result);
				temp /= sys;
			}
			return result;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(bf.readLine());
		
		getResult(num);
		
		bf.close();
	}

}
