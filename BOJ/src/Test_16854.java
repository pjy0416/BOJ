import java.util.Scanner;

public class Test_16854 {
	
	static int getResult(String str) {				// �𸣰���..
		int cnt =0;
		int stack =0;
		
		for(char ch : str.toCharArray()) {
			if(ch == '(') {
				stack++;
			}else {
				stack-= 1;
				if(stack >=0) {
					cnt++;
					System.out.println("\t\t\t ) ���� ī����");
				}
				if(stack ==0) {
					cnt++;
					System.out.println("\t\t\t\t �߰� ī����");
				}
			}
			System.out.println("( ���� ���� : " + stack + "\t ī���� : " +cnt);
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
