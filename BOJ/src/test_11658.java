import java.util.Arrays;
import java.util.Scanner;

public class test_11658 {			// time over code....	아마 String Array를 int Array로 parse하는 부분에서 시간이 걸리는듯....
	
	static int[] parseIntArr(String[] strArr, int[] intArr) {
		intArr = Arrays.stream(strArr).mapToInt(Integer::parseInt).toArray();
		
		return intArr;
	}
	
	static int[][] initArr(int arrLen, Scanner scan) {
		int[][] result = new int[arrLen][];
		
		for(int i=0; i<arrLen; i++) {
			String inputStr = scan.nextLine();
			String[] inputArr = inputStr.split(" ");
			
			result[i] = parseIntArr(inputArr, result[i]);
		}
		
		return result;
	}
	
	static void arrayOperator(int[][] arr, int[][] queries, int arrSize, int querySize) {
		for(int i=0; i<querySize; i++) {
			int[] query = queries[i];
			int len = query.length;
			if(len == 4) {	
				arr = arrayModifier(arr, query, arrSize, querySize);
			}else if(len == 5) {
				System.out.println(addSection(arr, query, arrSize, querySize));
			}	
		}
	}
	
	static int[][] arrayModifier(int[][] arr, int[] query, int arrSize, int querySize) {	// arr 수정
		int x = query[1]-1;
		int y = query[2]-1;
		int value = query[3];
		
		arr[x][y] = value;
		
		return arr;
	}
	
	static int addSection(int[][] arr, int[] query, int arrSize, int querySize) {		// arr 연산
		int result =0;
		
		int startX = query[1]-1;
		int startY = query[2]-1;
		int endX = query[3]-1;
		int endY = query[4]-1;
		
		for(int x=startX; x<=endX; x++) {
			for(int y=startY; y<=endY; y++) {
				System.out.print(arr[x][y] +"\t");
				result += arr[x][y];
			}
		}
		
		return result;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int arrSize = scan.nextInt();
		int querySize = scan.nextInt();
		
		scan.nextLine(); //개행문자 제거
		
		int[][] array = initArr(arrSize, scan);
		int[][] queries = initArr(querySize, scan);
		
		arrayOperator(array, queries, arrSize, querySize);
		
		scan.close();
	}
}
