package programmers;

import java.util.PriorityQueue;

public class KtHeapDiskController {
    public static void main(String[] args) {
        int[][] jobs = {{0, 3}, {1, 9}, {500, 6}};
//        int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};
        System.out.println(solution(jobs));
    }

    private static int solution(int[][] jobs) { // 40.0 / 100.0
        int answer =0;
        int jobCnt = jobs.length;

        PriorityQueue<WaitJob> waitQueue = new PriorityQueue<>();
        PriorityQueue<Job> jobQueue = new PriorityQueue<>();

        for(int[] job : jobs) {
            waitQueue.offer(new WaitJob(job[0], job[1]));
        }

        /*while(!waitQueue.isEmpty()) {
            WaitJob job = waitQueue.poll();
            System.out.println(job.st + " \t\t" + job.wt);
            jobQueue.offer(new Job(job));
        }
        while(!jobQueue.isEmpty()) {
            Job job = jobQueue.poll();
            System.out.println(job.startTime + " \t\t " + job.workTime);
        }*/

        WaitJob startJob = waitQueue.poll();
        int time = 1000 + startJob.wt;  // 최대 1000초 까지 작업이 들어올 수 있음
        int prevEnd = startJob.wt;
        answer += startJob.wt;

        for(int i=0; i<=time; i++) {
            while(!waitQueue.isEmpty()) {
                WaitJob wait = waitQueue.poll();
                if(wait.st <= i) {
//                    System.out.println(wait.st + "시작 " + wait.wt + "만큼 일하는거 추가~~");
                    jobQueue.offer(new Job(wait));
                } else {
//                    System.out.println(wait.st + "에 시작 해서 " + wait.wt +"만큼 일하는 작업 빠꾸");
                    waitQueue.offer(wait);
                    break;
                }
            }

            while(!jobQueue.isEmpty()) {
                Job job = jobQueue.poll();

                if(prevEnd <= i) {
                    // 중간에 작업이 뜨는 기간이 아닐때는 대기시간 구해주고, 아닐 때는 대시기간 0으로 초기화
                    int watingTime = prevEnd == i ? prevEnd - job.startTime : 0;

//                    System.out.println(i +"일 때 들어왔어요");
//                    System.out.println("대기시간 => " + watingTime);
//                    System.out.println("요청부터 종료까지 ~> " + ( job.workTime + watingTime) + "걸려요");
                    time += job.workTime;
                    answer += job.workTime + watingTime; // 진행 시간에 대기시간 더해주기
//                    prevEnd = prevEnd == i ? prevEnd + job.workTime : i+job.workTime;
                    prevEnd = i+job.workTime;
                } else {
                    jobQueue.offer(job);
                    break;
                }
            }
        }

//        System.out.println("Time => " + time);
//        System.out.println("Ans => " + answer);
        /*
        // 처음 작업은 바로 시작해주기
        jobQueue.offer(new Job(waitQueue.poll()));
        int prevEnd =0;

        while(!jobQueue.isEmpty()) {
            Job job = jobQueue.poll();
            answer += job.elapsedTime + (prevEnd - job.startTime);
            prevEnd += job.endTime;     // 작업마다 EndTime이 밀리기 때문에 더해줌
//            System.out.println(job.startTime +"에 들어와서 " + job.elapsedTime +"동안 진행");
//            System.out.println("대기시간 = " + (prevEnd - job.startTime));
//            System.out.println("Answer = > " + answer);

            while(!waitQueue.isEmpty()) {
                WaitJob wait = waitQueue.poll();
//                System.out.println("Wait st => " + wait.st);
                if(wait.st <= answer) {
                    jobQueue.offer(new Job(wait));
                } else {
                    waitQueue.offer(wait);
                    break;
                }
            }
        }
        */

        return answer/jobCnt;
    }
}

class WaitJob implements Comparable<WaitJob> {
    int st;
    int wt;

    public WaitJob(int st, int wt) {
        this.st = st;
        this.wt = wt;
    }

    @Override
    public int compareTo(WaitJob job) {
        return this.st >= job.st ? 1 : -1;
    }
}

class Job implements Comparable<Job> {
    int startTime;
    int workTime;

    public Job (WaitJob job) {
        this.startTime = job.st;
        this.workTime = job.wt;
    }

    @Override
    public int compareTo(Job job) {
        return this.workTime >= job.workTime ? 1 : -1;
    }
}
/*
문제 설명
하드디스크는 한 번에 하나의 작업만 수행할 수 있습니다.
디스크 컨트롤러를 구현하는 방법은 여러 가지가 있습니다.
가장 일반적인 방법은 요청이 들어온 순서대로 처리하는 것입니다.

예를들어
- 0ms 시점에 3ms가 소요되는 A작업 요청
- 1ms 시점에 9ms가 소요되는 B작업 요청
- 2ms 시점에 6ms가 소요되는 C작업 요청
와 같은 요청이 들어왔습니다.

한 번에 하나의 요청만을 수행할 수 있기 때문에 각각의 작업을 요청받은 순서대로 처리하면 다음과 같이 처리 됩니다.

- A: 3ms 시점에 작업 완료 (요청에서 종료까지 : 3ms)
- B: 1ms부터 대기하다가, 3ms 시점에 작업을 시작해서 12ms 시점에 작업 완료(요청에서 종료까지 : 11ms)
- C: 2ms부터 대기하다가, 12ms 시점에 작업을 시작해서 18ms 시점에 작업 완료(요청에서 종료까지 : 16ms)
이 때 각 작업의 요청부터 종료까지 걸린 시간의 평균은 10ms(= (3 + 11 + 16) / 3)가 됩니다.

하지만 A → C → B 순서대로 처리하면
Screen Shot 2018-09-13 at 6.41.42 PM.png

- A: 3ms 시점에 작업 완료(요청에서 종료까지 : 3ms)
- C: 2ms부터 대기하다가, 3ms 시점에 작업을 시작해서 9ms 시점에 작업 완료(요청에서 종료까지 : 7ms)
- B: 1ms부터 대기하다가, 9ms 시점에 작업을 시작해서 18ms 시점에 작업 완료(요청에서 종료까지 : 17ms)
이렇게 A → C → B의 순서로 처리하면 각 작업의 요청부터 종료까지 걸린 시간의 평균은 9ms(= (3 + 7 + 17) / 3)가 됩니다.

각 작업에 대해 [작업이 요청되는 시점, 작업의 소요시간]을 담은 2차원 배열 jobs가 매개변수로 주어질 때, 작업의 요청부터 종료까지 걸린 시간의 평균을 가장 줄이는 방법으로 처리하면 평균이 얼마가 되는지 return 하도록 solution 함수를 작성해주세요. (단, 소수점 이하의 수는 버립니다)

제한 사항
jobs의 길이는 1 이상 500 이하입니다.
jobs의 각 행은 하나의 작업에 대한 [작업이 요청되는 시점, 작업의 소요시간] 입니다.
각 작업에 대해 작업이 요청되는 시간은 0 이상 1,000 이하입니다.
각 작업에 대해 작업의 소요시간은 1 이상 1,000 이하입니다.
하드디스크가 작업을 수행하고 있지 않을 때에는 먼저 요청이 들어온 작업부터 처리합니다.

입출력 예
          jobs	             return
[[0, 3], [1, 9], [2, 6]]	    9
입출력 예 설명
문제에 주어진 예와 같습니다.

0ms 시점에 3ms 걸리는 작업 요청이 들어옵니다.
1ms 시점에 9ms 걸리는 작업 요청이 들어옵니다.
2ms 시점에 6ms 걸리는 작업 요청이 들어옵니다.
 */