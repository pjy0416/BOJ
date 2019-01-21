import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test_11658 {			// time over code....	add �κп��� �ð��� �����ɸ��µ�...
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static String[][] initArr(int arrLen) throws IOException{
		String[][] result = new String[arrLen][];
		
		for(int i=0; i<arrLen; i++) {
			String inputStr = br.readLine();
			result[i] = inputStr.split(" ");
		}
		
		return result;
	}

	static void runQuery(String[][] arr, int querySize) throws IOException {

		for(int i=0; i<querySize; i++) {
			String str = br.readLine();
			String[] query = str.split(" ");
			int len = query.length;

			if(len == 4) {
				arrayModifier(arr, query);
			} else {
				addSection(arr, query);
			}
		}
	}
	
	static String[][] arrayModifier(String[][] arr, String[] query) {	// arr ����
		int x = Integer.parseInt(query[1])-1;
		int y = Integer.parseInt(query[2])-1;

		arr[x][y] = query[3];
		
		return arr;
	}
	
	static void addSection(String[][] arr, String[] query) {		// arr ����
		int startX = Integer.parseInt(query[1])-1;
		int startY = Integer.parseInt(query[2])-1;
		int endX = Integer.parseInt(query[3])-1;
		int endY = Integer.parseInt(query[4])-1;

		int result =0;
		for(int x=startX; x<=endX; x++) {
			for(int y=startY; y<=endY; y++) {
				result += Integer.parseInt(arr[x][y]);
			}
		}

		System.out.println(result);
	}

	public static void main(String[] args) throws IOException {

		String sizes = br.readLine();
		int arrSize = Integer.parseInt(sizes.split(" ")[0]);
		int querySize = Integer.parseInt(sizes.split(" ")[1]);
		
		String[][] array = initArr(arrSize);
		runQuery(array, querySize);

		br.close();
	}
}
