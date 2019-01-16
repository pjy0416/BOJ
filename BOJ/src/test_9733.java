import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class test_9733 {				// 마지막 테스트케이스에서 계속 틀림
	private final static int size = 7;
	private final static String[] workName = {"Re","Pt","Cc","Ea","Tb","Cm","Ex","Total"};
	
	private static HashMap<String,Integer> addCnt(String str) {
		String[] works = str.split(" ");

		HashMap<String, Integer> workList = new HashMap<>();
		for(String work : works) {
			if(workList.containsKey(work)) {
				int value = workList.get(work) + 1;
				workList.replace(work, value);
			} else {
				workList.put(work, 1);
			}
		}

		int total = works.length;
		workList.put(workName[size], total);

		return workList;
	}
	
	private static void calRatio(HashMap<String, Integer> workList) {
		float total = workList.get(workName[size]);
		for(String work : workName) {
			int cnt = 0;
			float ratio =0.00f;

			if(workList.containsKey(work)) {
				cnt = workList.get(work);
				ratio = cnt / total;
			}

			printWork(work, cnt, ratio);
		}
	}

	private static void printWork(String work, int cnt, float ratio) {
		System.out.println(work + " " + cnt + " " + String.format("%.2f", ratio));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = br.readLine() + " " + br.readLine();

		HashMap<String, Integer> workList =addCnt(inputStr);

		calRatio(workList);

		br.close();
	}

}
