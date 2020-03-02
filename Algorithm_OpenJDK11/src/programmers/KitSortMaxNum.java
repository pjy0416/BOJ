package programmers;


import java.util.PriorityQueue;

public class KitSortMaxNum {

    private static String solution(int[] numbers) {
        String answer = "";
        PriorityQueue<MyNum> candidates = new PriorityQueue<>();
//        int total =0;

        for(int num : numbers) {
            MyNum myNum = new MyNum((num));
            candidates.offer(myNum);
//            total += myNum.getDigit(myNum);
        }

//        System.out.println(total);

        while(!candidates.isEmpty()) {
            MyNum tmp = candidates.poll();
            answer += tmp.getNum(tmp);
        }
        if(answer.replace("0", "").equals("")) {
            return "0";
        }

        return answer;
    }

    public static void main(String[] args) {
//        int[] numbers = {0, 0, 0, 0};
//        int[] numbers = {12, 121};
//        int[] numbers = {21, 212};
        int[] numbers = {3, 30, 34, 5, 9};
//        int[] numbers = {6, 10, 2};

        System.out.println(solution(numbers));
    }
}

class MyNum implements Comparable<MyNum> {
    private int first;
    private int digit;
    int num;

    public MyNum(int num) {
        int tmp = num;
        int cnt =0;
        while(tmp /10 !=0) {
            tmp /= 10;
            cnt++;
        }
        this.first = tmp;
        this.num = num;
        this.digit = cnt;
    }

    public int getDigit(MyNum mynum) {
        return mynum.digit;
    }

    public String getNum(MyNum mynum) {
        String result = "";
//        System.out.println(mynum.num +" 겟 넘버 \t\t Digit = " + mynum.digit);
        int num = mynum.num;
        for(int i = mynum.digit; i >=0; i--) {
            int div = (int) Math.pow(10,i);
            result += String.valueOf(num/div);
            num %= div;
//            System.out.println(result + " 진행 중");
        }
//        System.out.println(result +" 완성");
        return result;
    }

    @Override
    public int compareTo(MyNum mynum) {
        if(this.first < mynum.first) {
            return 1;
        } else if (this.first == mynum.first) {
            // 한자리 수가 아닌 경우
            if(this.digit !=0) {
                int digitOrigin = this.digit;
                int origin = this.num;

                // 자리수를 줄여가면서 비교
                while (digitOrigin != 1) {
                    origin /= 10;
                    digitOrigin--;

                    int digitTarget = mynum.digit;
                    int target = mynum.num;
                    while (digitTarget != 0) {
                        target %= 10;
                        digitTarget--;

                        if (origin < target) {
                            return 1;
                        } else if (origin > target) {
                            return -1;
                        }
                    }

                    // target의 자리수가 0일 경우가 있으니까
                    if(origin <= target) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            } else {
//                System.out.println(mynum.num + "일 때 들어왔어요");
//                System.out.println("Origin = " + this.num);
                int digitTarget = mynum.digit;
                int target = mynum.num;
                while (digitTarget != 0) {
//                    System.out.println("while 들어왔음");
                    target %= 10;
                    digitTarget--;

                    if (this.first < target) {
                        return 1;
                    } else if (this.first> target) {
                        return -1;
                    }
                }
            }
        } else {
            return -1;
        }
        return this.first <= mynum.first ? 1 : -1;
    }
}

/*
문제 설명
0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.

예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고, 이중 가장 큰 수는 6210입니다.

0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.

제한 사항
numbers의 길이는 1 이상 100,000 이하입니다.
numbers의 원소는 0 이상 1,000 이하입니다.
정답이 너무 클 수 있으니 문자열로 바꾸어 return 합니다.
입출력 예
    numbers	            return
  [6, 10, 2]	         6210
[3, 30, 34, 5, 9]       9534330
 */