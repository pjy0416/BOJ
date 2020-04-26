package swMaestro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SecondTest2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        int N = Integer.parseInt(input.split(" ")[0]);
        int M = Integer.parseInt(input.split(" ")[1]);// for문돌거
        ArrayList<Participant> partList = new ArrayList<>();
        int[][] relations = new int[N+1][N+1];

        for(int i=1; i<=N; i++) {   // 정보 저장
            String inputStr = br.readLine();
            StringTokenizer st = new StringTokenizer(inputStr);
            partList.add(new Participant(Long.parseLong(st.nextToken()),Long.parseLong(st.nextToken())));
        }
        for(int i=0; i<M; i++) {    // 관계 저장
            String inputStr = br.readLine();
            StringTokenizer st = new StringTokenizer(inputStr);
            int part1 = Integer.parseInt(st.nextToken());
            int part2 = Integer.parseInt(st.nextToken());

            relations[part1][part1] =1;
            relations[part2][part1] =1;
        }

        System.out.println(solution(partList, relations, N));
    }

    private static ArrayList<ArrayList<Integer>> inItTeam() {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> tmp = new ArrayList<>();
        tmp.add(1);
        result.add(tmp);

        return result;
    }

    private static int searchNum(ArrayList<ArrayList<Integer>> networks, int num) {
        int result =-1;

        for(int idx=0; idx<networks.size(); idx++) {
            if(networks.get(idx).contains(num)) {
                result = idx;
                break;
            }
        }

        return result;
    }

    private static long solution(ArrayList<Participant> partList, int[][] relations, int N) {
        ArrayList<ArrayList<Integer>> teams = makeTeam(N, relations);
        long result = getMax(teams, partList);

        return result;
    }

    private static long getMax(ArrayList<ArrayList<Integer>> teams, ArrayList<Participant> partList) {
        long max =0;
        for(ArrayList<Integer> team : teams) {
            if (team.size() != 1) {
                long maxX = 0;
                long maxY = 0;
                long minX = 100000000;
                long minY = 100000000;
                for (int id : team) {
                    Participant pt = partList.get(id - 1);
                    maxX = maxX < pt.x ? pt.x : maxX;
                    maxY = maxY < pt.y ? pt.y : maxY;
                    minX = minX > pt.x ? pt.x : minX;
                    minY = minY > pt.y ? pt.y : minY;
                }
                if(maxX - minX !=0 && maxY - minY !=0) {
                    long num = (maxX - minX) * 2 + (maxY - minY) * 2;
                    max = Math.max(max, num);
                }
            }
        }

        return max;
    }


    private static ArrayList<ArrayList<Integer>> makeTeam(int N, int[][] relations) {
        ArrayList<ArrayList<Integer>> teams = inItTeam();
        for(int i=1; i<=N; i++) {
            int idx = searchNum(teams,i);    // i선수가 포함된 팀 탐색
            ArrayList<Integer> origin;
            if(idx ==-1) {  // 없으면 팀 생성
                origin = new ArrayList<>();
                origin.add(i);
            } else {    // 있으면 팀 가져오기
                origin = teams.get(idx);
                teams.remove(idx);   // 해당 팀은 목록에서 제거
            }

            for(int j=1; j<=N; j++) {
                if(i !=j && relations[i][j] ==1) {  // i참가자와 연결된 j선수
                    int targetIdx = searchNum(teams, j); // target이 만들어진 팀에 있는지 확인
                    if(targetIdx != -1) {   // target이 포함된 팀이 존재하면
                        ArrayList<Integer> team = teams.get(targetIdx);   // 받아와서
                        for(int num : origin) {
                            if(!team.contains(num)) {
                                team.add(num);   // 팀을 합쳐주고
                            }
                        }
                        origin = team;   // origin 교체
                        teams.remove(targetIdx); // network 목록에서 제거
                    } else {
                        origin.add(j);  // i에 j 연결
                    }
                }
            }
            teams.add(origin);   // 팀 리스트에 완성된 팀 추가
        }
        return teams;
    }
}

class Participant {
    long x;
    long y;

    public Participant(long x, long y) {
        this.x = x;
        this.y = y;
    }
}
