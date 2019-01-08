import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Test_2910 {
	// Ref) https://zoonvivor.tistory.com/132
	static void inputSeqCnt(HashMap<Integer,Integer> seqHash, int key) {
		boolean hasNum = seqHash.containsKey(key);
		
		if(hasNum) {
			int value = seqHash.get(key) +1;
			seqHash.replace(key, value);
		} else {
			seqHash.put(key, 1);
		}
	}
	
	static void printNum(Iterator<Integer> keys, HashMap<Integer, Integer> seqMap) {
		while(keys.hasNext()) {
			int key = keys.next();
			int numCnt = seqMap.get(key);			// Map에서 해당 숫자의 cnt를 가져옴
			for(int i=0; i<numCnt; i++) {			// count 만큼 loop
				System.out.print(key + " ");
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int seqSize = scan.nextInt();
		int maxNum = scan.nextInt();
		
		HashMap<Integer, Integer> seqMap = new LinkedHashMap<>();		// 입력 받은 순서가 중요하기 때문에 LinkedHashMap으로 선언
		
		for(int i=0; i<seqSize; i++) {
			int key = scan.nextInt();
			inputSeqCnt(seqMap, key);
		}
		
		ArrayList<Integer> keyArrays = new ArrayList<Integer>(seqMap.keySet());		// ArrayList에 key값을 저장
		
		Collections.sort(keyArrays, new Comparator<Integer>() {						// ArrayList 정렬

			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(seqMap.get(o2), seqMap.get(o1));				// 내림차 순 정렬
			}
		});

		Iterator<Integer> keys = keyArrays.iterator();								// key값 들을 Iteator에 저장
		
		while(keys.hasNext()) {
			int key = keys.next();
			int numCnt = seqMap.get(key);			// Map에서 해당 숫자의 cnt를 가져옴
			for(int i=0; i<numCnt; i++) {			// count 만큼 loop
				System.out.print(key + " ");
			}
		}
		
		scan.close();
	}
}