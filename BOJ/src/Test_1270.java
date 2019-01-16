import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Test_1270 {        // 런타임 에러

    static void numCountFunc(String[] arr, int size, HashMap<Integer,Integer> map) {
        for(int idx =1; idx<=size; idx++) {         // 해당 병사 number counting
            int key = Integer.parseInt(arr[idx]);
            if(map.containsKey(key)) {
                int count = map.get(key) +1;
                map.replace(key, count);
            }else {
                map.put(key, 1);
            }
        }
    }

    static String sortDesc (HashMap<Integer, Integer> map, int size) {
        ArrayList<Integer> keyArrays = new ArrayList<>(map.keySet());		// ArrayList에 key값을 저장

        Collections.sort(keyArrays, new Comparator<Integer>() {						// ArrayList 정렬

            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(map.get(o2), map.get(o1));				// 내림차 순 정렬
            }
        });

        Iterator<Integer> keys = keyArrays.iterator();								// key값 들을 Iteator에 저장

        String result ="";
        while(keys.hasNext()) {
            int key = keys.next();
            int value = map.get(key);			// Map에서 해당 숫자의 cnt를 가져옴

            if(value > size/2) {
                result = String.valueOf(key);
                break;
            } else {
                result = "SYJKGW";
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int fields = Integer.parseInt(br.readLine());
        String[] soldiers = new String[fields];

        for(int i=0; i<fields; i++) {
            HashMap<Integer, Integer> map= new HashMap<>();

            String input = br.readLine();

            String[] arr = input.split(" ");
            int size = Integer.parseInt(arr[0]);

            numCountFunc(arr, size, map);

            soldiers[i] = sortDesc(map, size);
        }

        for(int i=0; i<fields; i++) {
            System.out.println(soldiers[i]);
        }

        br.close();
    }
}
