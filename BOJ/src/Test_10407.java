import java.util.Scanner;

public class Test_10407 {	// 초기 시도코드
	
	static void printResult(String str) {
		int h =0;
		if(str.length() > 1) {
			h =2;
		} else {
			h = Integer.parseInt(str);
		}
		
		int result =getValue(h);
		System.out.println(result);
	}

	private static int getValue(int h) {
		int value = 2;
		for(int i=1; i<h; i++) {
			value = (int) Math.pow(2, value);
		}
		
		return value%3;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String input = scan.nextLine();
		
		printResult(input);
		
		scan.close();
	}

}

/*		간단한 코드
 import java.util.Scanner;

public class Test_10407 {
	
	static void printResult(String str) {
		if(str.equals("1")) {
			System.out.println("2");
		} else {
			System.out.println("1");
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		printResult(str);
		scan.close();
	}

}
*/
