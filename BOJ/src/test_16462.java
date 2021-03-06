import java.util.Scanner;

public class test_16462 {
	private static int convertInt(String textScore) {
		textScore = textScore.replaceAll("[0]", "9").replaceAll("[6]", "9");
		int result = Integer.parseInt(textScore);
		
		if(result > 100) {
			return 100;
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		short count = (short)scan.nextInt();

		String textScore = "";
		int sum =0;
		
		for(int i=0; i<count; i++) {
			textScore =scan.next();
			sum += convertInt(textScore);
		}
		float avg = (float)sum/count;
		
		
		System.out.println(String.format("%.0f", avg));
		
		scan.close();
	}
}
