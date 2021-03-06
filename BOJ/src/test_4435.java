import java.util.Scanner;

public class test_4435 {
	private static void printWinner(int gan, int sau, int round) {
		System.out.print("Battle "+round);
				
		if(gan > sau) {
			System.out.println(": Good triumphs over Evil");
		}else if( gan < sau) {
			System.out.println(": Evil eradicates all trace of Good");
		}else {
			System.out.println(": No victor on this battle field");
		}
	}
	
	public static void main(String[] args) {
		int[] gandalf = {1, 2, 3, 3, 4, 10};
		int[] sauron = {1, 2, 2, 2, 3, 5, 10}; 
		
		Scanner scan = new Scanner(System.in);
		
		int warCount = scan.nextInt();
		int[] gandalfScore = new int[(warCount)]; 
		int[] sauronScore = new int[(warCount)];
		
		//input values
		for(int i=1; i<=warCount*2; i++) {	// ���� Ƚ������
			int hap=0;
			if(i%2 !=0) {	// Ȧ���� => ������
				for(int j=0; j<6; j++) {
					hap += gandalf[j] * scan.nextInt();
				}
				gandalfScore[(i-1)/2] = hap;
			}else {			// ¦���� => ����
				for(int j=0; j<7; j++) {
					hap += sauron[j] * scan.nextInt();
				}
				sauronScore[(i-1)/2] = hap;
			}
		}
		
		for(int i=0; i<warCount; i++) {
			int gan = gandalfScore[i];
			int sau = sauronScore[i];
			
			printWinner(gan, sau, i+1);
		}
		scan.close();
	}
}
