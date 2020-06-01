package programmers;

public class Stations_12979 {
    private static int solution(int n, int[] stations, int w) {
        int answer = 0;
        int prev = -w;   // 없는 것

        for(int spot : stations) {
            answer += getStations(prev, spot, w);
            prev = spot;
        }
        answer += getStations(prev,n+w+1,w);  // 마지막 아파트까지 체크

        return answer;
    }

    private static int getStations(int prev, int spot, int w) {
        int result =0;

        int apartments = (spot-w) - (prev+w) -1; // 아파트 수
        int cover = 2*w+1;  // 기지국이 커버 가능한 세대 수
        if(apartments >0) {
            result = apartments/cover;
            if(apartments%cover !=0) {  // 남은 아파트 수
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int N = 11;
        int[] stations = {4,11};
        int W = 1;

        System.out.println(solution(N,stations,W));
    }
}

/*
https://programmers.co.kr/learn/courses/30/lessons/12979#
 */
