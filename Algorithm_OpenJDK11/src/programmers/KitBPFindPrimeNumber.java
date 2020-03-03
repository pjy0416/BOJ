package programmers;

import java.util.*;

public class KitBPFindPrimeNumber {
    static LinkedList<String> numList = new LinkedList<>();

    private static int solution(String numbers) {
        int answer = 0;
        int size = numbers.length();
        char[] chArr = numbers.toCharArray();

        Set<Integer> numSet = new HashSet<>();
        LinkedList<String> tmp = new LinkedList<>();

        for(int i=1; i<=size; i++) {
            int[] checked = new int[size];
            permutation(tmp, checked, chArr, size, i);
            for(String num : numList) {
//                System.out.println("솔루션거 "+ num);
                numSet.add(Integer.parseInt(num));
            }
            numList.clear();
        }

        // 소수 판정하기
        Iterator<Integer> it = numSet.iterator();
        while(it.hasNext()) {
            int num = it.next();
            if(isPrime(num)) {
                answer++;
                System.out.println("소수 => " + num);
            }
        }
        numSet.clear();

        return answer;
    }

    private static boolean isPrime(int num) {
        if(num < 2) {
            return false;
        }
        for(int i=2; i<num; i++) {
            if(num%i ==0) {
                return false;
            }
        }
        return true;
    }

    private static void permutation(LinkedList<String> list, int[] check, char[] chArr, int n, int r) {
        if(list.size() == r){
            String str = "";
            for(String i : list){
                str += i;
            }
            numList.add(str);
//            System.out.println(str);
//            System.out.println(r + "에서 끝  n => " + n);
            return;
        }
        for(int i=0; i<n; i++){//**중복 순열과 다른 점
            if(check[i] == 0){//자기자신을 못뽑게 해야지 중복이 안됨(이미 뽑은 것은 뽑지 않도록 체크)
                check[i] = 1;//자기자신을 못뽑게 해야지 중복이 안됨
                list.add(Character.toString(chArr[i]));
//                System.out.println(chArr[i] + "추가했음");
                permutation(list, check, chArr, n, r);
                list.removeLast();//해당 넘버를 다시 제거 (즉,뽑지 않고 다음 번호 뽑기위함)
//                System.out.println(chArr[i] + "제거하고 다시");
                check[i] = 0;
            }
        }

    }

    public static void main(String[] args) {
        String numbers = "17";
//        String numbers = "011";
        System.out.println(solution(numbers));

    }
}

/*
문제 설명
한자리 숫자가 적힌 종이 조각이 흩어져있습니다.
흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.

각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때,
종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 완성해주세요.

제한사항
numbers는 길이 1 이상 7 이하인 문자열입니다.
numbers는 0~9까지 숫자만으로 이루어져 있습니다.
013은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져있다는 의미입니다.

입출력 예
numbers	    return
  17	       3
  011	       2

입출력 예 설명
예제 #1
[1, 7]으로는 소수 [7, 17, 71]를 만들 수 있습니다.

예제 #2
[0, 1, 1]으로는 소수 [11, 101]를 만들 수 있습니다.

11과 011은 같은 숫자로 취급합니다.
출처
*/
