import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class test_13545 {			// run time error code
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int getMaxLine(int[] seqs, int start, int end) {
		int maxLine =0;
		Stack<Integer> stack = new Stack<>();
		for(int i=start; i<=end; i++) {
			int num = seqs[i];

			if(stack.empty()) {		//맨 처음의 경우만
				stack.push(num);
				continue;
			}

			int peek = stack.peek();

			if(peek+num ==0) {	// 뽑은 것과 현재 idx가 더해서 0이면 push
				maxLine = Math.max(maxLine,2);	// 0이 되는 경우 최댓값을 비교해서 0이면 2로 바꿈
			}

			stack.push(num);

			Stack<Integer> doubleStack = (Stack<Integer>)stack.clone();
			int sum =0;
			int cnt =0;

			while(!doubleStack.empty()) {
				sum += doubleStack.pop();
				cnt ++;
				if(sum ==0) {
					maxLine = Math.max(maxLine, cnt);
				}
			}
		}

		return maxLine;
	}
	
	private static void runQuery(int querySize, int[] seqs, int seqSize) throws IOException{
		int[] result = new int[querySize];
		for(int i=0; i<querySize; i++) {
			String str = br.readLine();
			int startLine = Integer.parseInt(str.split(" ")[0]) -1;
			int endLine = Integer.parseInt(str.split(" ")[1]) -1;
			
			result[i] = getMaxLine(seqs, startLine, endLine);
		}
		
		printResult(result, querySize);
	}
	
	private static int[] saveSeqs(int seqSize) throws IOException{
		int[] result = new int[seqSize];
		String inputStr = br.readLine();

		for(int i=0; i<seqSize; i++) {
			result[i] = Integer.parseInt(inputStr.split(" ")[i]);    // 값 저장
		}

		return result;
	}
	
	static void printResult(int[] queryResults, int querySize) {
		for(int i=0; i<querySize; i++) {
			System.out.println(queryResults[i]);
		}
	}

	public static void main(String[] args) throws IOException {
		int[] seqs;	// 수열을 담을 array 선언
		
		int seqSize = br.read() - 48;		// input a array's size
		br.readLine();		// 개행문자 제거
		seqs = saveSeqs(seqSize);

		int querySize = br.read() -48;
		br.readLine();		// 개행문자 제거
		runQuery(querySize, seqs, seqSize);

		br.close();
	}
}
