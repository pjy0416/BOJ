package programmers;

public class KitHeapDiskController_42627 { // 시간이 없다... 나중에 수정하기
}
/*
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

class Solution { // 우선순위 큐 1개, ArrayList 1개 (2020.06.05)
    public int solution(int[][] jobs) {
        int answer = 0;

        ArrayList<int[]> waitList = new ArrayList<>();
        PriorityQueue<Task> taskQ = new PriorityQueue<>();

        for(int[] job : jobs) {
            waitList.add(job);
        }

        Collections.sort(waitList, new Comparator<int[]>() {
            @Override
            public int compare(int[] v1, int[] v2) {
                if(v1[0] > v2[0]) {
                    return 1;
                } else if(v1[0] == v2[0]) {
                    return v1[1] >= v2[1] ? 1: -1;
                }
                return -1;
            }
        });
        int[] start = waitList.get(0);
        answer = 0;
        taskQ.offer(new Task(start[0],start[1]));
        waitList.remove(0);
        int nowTime = start[0]; // 일을 진행할 시간

        while(!taskQ.isEmpty()) {
            Task task = taskQ.poll();
            answer += Math.abs(nowTime-task.start) + task.time;
            nowTime +=  task.time;

            int min=1001;
            int idx=0;
            for(int i=0; i<waitList.size(); i++) {
                int[] target = waitList.get(i);
                if(target[0] <= nowTime) { // 시작 시점이 현재 시간보다 빠른 경우
                    if(min > target[1]) {
                        min = target[1];
                        idx = i;
                    }
                }
            }
            if(waitList.size() !=0) {
                int[] tmp = waitList.get(idx);
                if(min == 1001) {
                    nowTime = tmp[0];
                }
                taskQ.offer(new Task(tmp[0],tmp[1]));
                waitList.remove(idx);
            }
        }
        return answer/jobs.length;
    }
}

class Task implements Comparable<Task>{
    int start;
    int time;

    public Task(int start, int time) {
        this.start = start;
        this.time = time;
    }

    @Override
    public int compareTo(Task t) {
        return this.time >= t.time ? 1 : -1;
    }
}
 */

/*
import java.util.PriorityQueue; // 우선순위 큐 2개

class Solution {
    public int solution(int[][] jobs) {
        int answer =0;
        int jobCnt = jobs.length;

        PriorityQueue<WaitJob> waitQueue = new PriorityQueue<>();
        PriorityQueue<Job> jobQueue = new PriorityQueue<>();

        for(int[] job : jobs) {
            waitQueue.offer(new WaitJob(job[0], job[1]));
        }

        WaitJob startJob = waitQueue.poll();    // 기준
        jobQueue.offer(new Job(startJob));
        while(!waitQueue.isEmpty()) {
            WaitJob wait = waitQueue.poll();
            if(startJob.st == wait.st) {
                jobQueue.offer(new Job(wait));
            } else {
                waitQueue.offer(wait);
                break;
            }
        }

        int time = 1000;  // 최대 1000초 까지 작업이 들어올 수 있음
        int prevEnd = 0;

        for(int i=0; i<=time; i++) {

            while(!waitQueue.isEmpty()) {
                WaitJob wait = waitQueue.poll();
                if(wait.st <= i) {
                    jobQueue.offer(new Job(wait));
                } else {
                    waitQueue.offer(wait);
                    break;
                }
            }

            while(!jobQueue.isEmpty()) {
                Job job = jobQueue.poll();

                if(prevEnd <= i) {
                    // 중간에 작업이 뜨는 기간이 아닐때는 대기시간 구해주고, 아닐 때는 대시기간 0으로 초기화
                    int watingTime = (prevEnd == i && job.startTime < i) ? prevEnd - job.startTime : 0;

                    time += job.workTime;
                    answer += job.workTime + watingTime; // 진행 시간에 대기시간 더해주기
                   prevEnd =prevEnd < job.startTime ? job.startTime + job.workTime : i+job.workTime; // 진행시작한 작업이 끝나는 시점 저장

                    // prevEnd = i+job.workTime;
                } else {
                    jobQueue.offer(job);
                    break;
                }
            }
        }

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
 */