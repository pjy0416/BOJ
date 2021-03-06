import java.util.Scanner;

public class test_14579 {
	
	private static int multiSig(int min, int max) {
		int multiSum =1;
		
		for(int i=min; i<= max; i++) {
			int multi = (int) ((Math.pow(i, 2)+i)/2);
			multiSum = multiSum * multi %14579;
		}
		
		return multiSum;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int minNum = scan.nextInt();
		int maxNum = scan.nextInt();
		
		System.out.println(multiSig(minNum, maxNum));
	}

}
