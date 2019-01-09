/*import java.util.Scanner;

public class Test_1598 {			// 14252KB 108ms
	static final int height =4;
	
	static int calDist(int num1, int num2) {
		// pos1 ��ǥ
		int x1 =getXPos(num1);
		int y1 =getYPos(num1);
		
		// pos2 ��ǥ
		int x2 =getXPos(num2);
		int y2 =getYPos(num2);
		
		int dist = getDist(x1,y1,x2,y2);
		
		return dist;
	}
	
	static int getXPos(int num) {
		int result = num/height +1;
		if(num%height ==0) {
			result -=1;
		}
		return result;
	}
	
	static int getYPos(int num) {
		int result = num%height;
		if(result ==0) {
			result =4;
		}
		return result;
	}
	
	static int getDist(int x1, int y1, int x2, int y2) {
		int xDist = Math.abs(x2-x1);
		int yDist = Math.abs(y2-y1);
		
		return xDist+yDist;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int num1 = scan.nextInt();
		int num2 = scan.nextInt();
		
		int dist = calDist(num1,num2);
		System.out.println(dist);
		
		scan.close();

	}
}*/


import java.util.Scanner;

public class Test_1598 {			// 14296 KB 104MS		
	// scanner�� �Է¹޴� �Ŵ� �ּ�ȭ ��Ű�°� ���� ����. nextLine���� �ް� Parsing �ϴ� ���� �ִ���!!
	// �׸��� Array�� ������ ���� ���� ��ü�� �ּ�ȭ ��Ű�°� ����.
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		final int height =4; 
		
		String str = scan.nextLine();
		int num1 = Integer.parseInt(str.split(" ")[0]);
		int num2 = Integer.parseInt(str.split(" ")[1]);
		
		int xDist = Math.abs((num1-1)/height - (num2-1)/height);
		int yDist = Math.abs((num1-1)%height - (num2-1)%height);
		
		int result = xDist+yDist;
		
		System.out.println(result);
		
		scan.close();
	}
}
