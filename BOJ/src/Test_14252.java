import java.util.Arrays;
import java.util.Scanner;

public class Test_14252 {		// 테스트 데이터가 이상함...
	
	private static int gcd(int a, int b) {
		while (b != 0) {
			int temp = a % b;
			a = b;
			b = temp;
		}
		return Math.abs(a);
	}
	
	private static int getResult(int[] arr, int arrSize) {
		int cnt =0;
		for(int i=0; i<arrSize-1; i++) {
			int nextIdx = i+1;
			int comdiv = gcd(arr[i], arr[nextIdx]);
			
			if(judge(comdiv)) {
				cnt++;
			}
		}
		
		return cnt;
	}
	
	private static boolean judge(int num) {
		if(num >1) {
			return true;
		}
		return false;
	}
	  
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int arrSize = scan.nextInt();
		
		int[] arr = new int[arrSize];
		
		for(int i=0; i<arrSize; i++) {
			arr[i] = scan.nextInt();
		}
		Arrays.sort(arr);
		System.out.println(getResult(arr, arrSize));
		scan.close();
	}

}
