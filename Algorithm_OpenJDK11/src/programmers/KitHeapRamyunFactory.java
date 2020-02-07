package programmers;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class KitHeapRamyunFactory {     // 40.0/100.0 

    /*  1. K일 까지 필요한 밀가루 양 = K-1톤
        2.

     */
    private static int solution(int stock, int[] dates, int[] supplies, int k) {
        int answer = 0;

        int total = stock;

        PriorityQueue<Integer> dateQueue = new PriorityQueue<>();
        Queue<Integer> supplyQueue = new LinkedList<>();

        for(int date : dates) {
            dateQueue.offer(date);
        }
        for(int sup : supplies) {
            supplyQueue.offer(sup);
        }

        int day =0;
        int idx =0;

        while(total < k) {
            int max =0;
            int date = 0;
            day++;

//            System.out.println(day +"일 차 Stock = " + stock + "\t Total = " + total + "\t K = " + k);

//            System.out.println("IDX = " + idx);
            int prev =0;
            Queue<Integer> tmp = new LinkedList<>();
            while(!dateQueue.isEmpty()) {
//                System.out.println(day + "일 차에 와일문");
                if(stock >= dateQueue.peek()) {
                    date = dateQueue.poll();
                    int supply = supplyQueue.poll();
                    if(max < supply) {
//                        System.out.println(max + "와 " + supply + " 비교 결과 새로운게 더 큼");
                        max = supply;
                    } else {
//                        System.out.println(max + "와 " + supply + " 비교 결과 더 작음");
                        tmp.offer(date);
                        supplyQueue.offer(supply);
                    }
                } else {
//                    System.out.println(dateQueue.peek() + "에서 와일문 종료");
                    break;
                }
            }

            for(int i=0; i< tmp.size(); i++) {
//                System.out.println("추가 돌아가는중");
                supplyQueue.offer(((LinkedList<Integer>) supplyQueue).pollLast());
                dateQueue.offer(tmp.poll());
            }

            if(max != 0) {
//                System.out.println(date +"에서 " + max + "만큼 더해줌");
                stock += max;
                total += max;
                answer++;
            }
//            k--;
            if(day ==40) {
                break;
            }

            stock--;


            /*
            if(stock <=0) {
                //재고가 떨어졌을 경우
                int max = 0;

                for (int i = idx; i < dates.length; i++) {
                    if (dates[i] <= total-1) { // 기간안에 받을 수 있는 날이 있었다면
//                        System.out.println(i+"번 째 탐색중");
                        if (max < supplies[i]) {
                            max = supplies[i];
                        }
                    } else {
                        idx = i-1;
                        break;
                    }
                }

                if(max !=0) {
//                    System.out.println(idx + "에서 Max = " + max + " 추가함" );
                    stock += max;
                    total += max;
                    idx++;
                    answer++;
                }
            }
            */

        }
        return answer;
    }

    public static void main(String[] args) {
        int stock = 4;
        int[] date = {1,2,3,4};
        int[] supplies = {1,1,1,1};
        int k = 6;

        System.out.println(solution(stock,date,supplies,k));
    }
}

/*
문제 설명
라면 공장에서는 하루에 밀가루를 1톤씩 사용합니다.
원래 밀가루를 공급받던 공장의 고장으로 앞으로 k일 이후에야 밀가루를 공급받을 수 있기 때문에 해외 공장에서 밀가루를 수입해야 합니다.

해외 공장에서는 향후 밀가루를 공급할 수 있는 날짜와 수량을 알려주었고, 라면 공장에서는 운송비를 줄이기 위해 최소한의 횟수로 밀가루를 공급받고 싶습니다.

현재 공장에 남아있는 밀가루 수량 stock,
밀가루 공급 일정(dates)과 해당 시점에 공급 가능한 밀가루 수량(supplies),
원래 공장으로부터 공급받을 수 있는 시점 k가 주어질 때,
밀가루가 떨어지지 않고 공장을 운영하기 위해서 최소한 몇 번 해외 공장으로부터 밀가루를 공급받아야 하는지를 return 하도록 solution 함수를 완성하세요.

dates[i]에는 i번째 공급 가능일이 들어있으며, supplies[i]에는 dates[i] 날짜에 공급 가능한 밀가루 수량이 들어 있습니다.

제한사항
stock에 있는 밀가루는 오늘(0일 이후)부터 사용됩니다.
stock과 k는 2 이상 100,000 이하입니다.
dates의 각 원소는 1 이상 k 이하입니다.
supplies의 각 원소는 1 이상 1,000 이하입니다.
dates와 supplies의 길이는 1 이상 20,000 이하입니다.

k일 째에는 밀가루가 충분히 공급되기 때문에 k-1일에 사용할 수량까지만 확보하면 됩니다.
dates에 들어있는 날짜는 오름차순 정렬되어 있습니다.
dates에 들어있는 날짜에 공급되는 밀가루는 작업 시작 전 새벽에 공급되는 것을 기준으로 합니다.
예를 들어 9일째에 밀가루가 바닥나더라도, 10일째에 공급받으면 10일째에는 공장을 운영할 수 있습니다.
밀가루가 바닥나는 경우는 주어지지 않습니다.

입출력 예
stock	    dates	    supplies	    k	    result
  4	      [4,10,15]	    [20,5,10]	    30	      2

입출력 예 설명
현재 밀가루가 4톤 남아 있기 때문에 오늘과 1일 후~3일 후까지 사용하고 나면 모든 밀가루를 다 사용합니다. 따라서 4일 후에는 반드시 밀가루를 공급받아야 합니다.
4일째 공급받고 나면 15일 이후 아침에는 9톤의 밀가루가 남아있게 되고, 이때 10톤을 더 공급받으면 19톤이 남아있게 됩니다.
15일 이후부터 29일 이후까지 필요한 밀가루는 15톤이므로 더 이상의 공급은 필요 없습니다.
따라서 총 2회의 밀가루를 공급받으면 됩니다.
※ 공지 - 2019년 2월 28일 테스트케이스가 추가되었습니다.
 */