import java.util.Scanner;

public class Test_16854 {
	
	static int getResult(String str) {				// 모르겠음..
		int cnt =0;
		int stack =0;
		
		for(char ch : str.toCharArray()) {
			if(ch == '(') {
				stack++;
			}else {
				stack-= 1;
				if(stack >=0) {
					cnt++;
					System.out.println("\t\t\t ) 제거 카운팅");
				}
				if(stack ==0) {
					cnt++;
					System.out.println("\t\t\t\t 추가 카운팅");
				}
			}
			System.out.println("( 누적 갯수 : " + stack + "\t 카운팅 : " +cnt);
		}
		
		return cnt;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String str = scan.nextLine();
		
		System.out.println(getResult(str));
		
		scan.close();
	}

}
