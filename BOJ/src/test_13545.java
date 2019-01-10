import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class test_13545 {			// over time code
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int getMaxLine(String[] seqs, int start, int end) {
		int maxLine =0;
		Stack<Integer> stack = new Stack<>();

		for(int i=start; i<=end; i++) {
			int num = Integer.parseInt(seqs[i]);

			if(stack.empty()) {		//맨 처음의 경우만
				stack.push(num);
				continue;
			}

			stack.push(num);

			maxLine = maxInStack(stack);
		}

		return maxLine;
	}

	static int maxInStack(Stack<Integer> stack) {
		int maxLine =0;

		Stack<Integer> doubleStack = (Stack<Integer>)stack.clone();
		int sum =0;
		int cnt =0;

		while(!doubleStack.empty()) {
			sum += doubleStack.pop();
			cnt++;
			if(sum ==0) {
				maxLine = Math.max(maxLine, cnt);
			}
		}

		return maxLine;
	}
	
	private static void runQuery(int querySize, String[] seqs) throws IOException{
		int[] result = new int[querySize];

		for(int i=0; i<querySize; i++) {
			String str = br.readLine();
			int startIdx = Integer.parseInt(str.split(" ")[0]) -1;
			int endIdx = Integer.parseInt(str.split(" ")[1]) -1;
			
			result[i] = getMaxLine(seqs, startIdx, endIdx);
		}
		
		printResult(result, querySize);
	}
	
	private static String[] saveSeqs(int seqSize) throws IOException{
		String[] result = br.readLine().split(" ");

		return result;
	}
	
	static void printResult(int[] queryResults, int querySize) {
		for(int i=0; i<querySize; i++) {
			System.out.println(queryResults[i]);
		}
	}

	public static void main(String[] args) throws IOException {
		String[] seqs;	// 수열을 담을 array 선언
		
		int seqSize = Integer.parseInt(br.readLine());		// input a array's size
		seqs = saveSeqs(seqSize);

		int querySize = Integer.parseInt(br.readLine());
		runQuery(querySize, seqs);

		br.close();
	}
}
