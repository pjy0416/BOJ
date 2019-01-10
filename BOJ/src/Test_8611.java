import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_8611 {				// �Է� ������ 10^1000 �����ε� �ٿ��� ó���ϴ°� ����..
										// double���� �Ҽ��� ����
	static final short max =10;
	static void getResult(Double num) {
		int cnt =0;
		
		for(int sys=2; sys<max; sys++) {
			String str = convertSys(sys,num);
			if(isPalin(str)) {
				cnt ++;
				System.out.println(sys + " " + str);
			}
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
	
	static String convertSys(int sys, Double num) {
		String result ="";
		double temp = num;
		while(temp > 0.0) {
			System.out.println(temp);
			result = String.valueOf(temp%sys).concat(result);
			temp /= sys;
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		Double num = Double.parseDouble(bf.readLine());
		
	
		getResult(num);
		
		bf.close();
	}

}
