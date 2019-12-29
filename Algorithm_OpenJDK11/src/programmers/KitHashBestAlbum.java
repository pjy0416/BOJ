package programmers;

import java.util.*;

public class BestAlbum {    // TimeOver
    // 시간초과 같은데 시간 줄이는거 생각해보기 (3초 이내로 끊기)

    // 장르가 키가되고, 그 안에 노래 ID랑 플레이 횟수가 들어가게 만들자.
    // 저장될 때 내림차순으로 저장
    // 장르별로 ID와 플레이횟수를 저장,

    /*
    속한 노래가 많이 재생된 장르를 먼저 수록합니다.                                 <= play가 가장 높은 음악이 속한 장르를 우선적으로 수록
    장르 내에서 많이 재생된 노래를 먼저 수록합니다.                                 <= 해당 장르에서 재생된 순서대로 수록
    장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.    <= 재생횟수가 같으면 먼저 나온거 부터 수록
    */

    /*
//    ============================================  Solve 1
    public static int[] sol(String[] genres, int[] plays) {
        Map<String, int[] > songs = new HashMap<>();     // 전체 음악 저장
        Map<Integer, Integer> song= new HashMap<>();    // 음악별 플레이 횟수
        Map<String, Integer> maxPerGenre = new HashMap<>();

        song.put(-1, 0);                                // Exception 방지 (없는 key 불러오는 것)

        for(int i=0; i<genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];
            song.put(i, play);

            if(!songs.containsKey(genre)) { // 장르가 등록된 적이 없으면
                int[] tmp = { i,-1 ,-1};    // out of index 방지로 3번째 인덱스까지 생성
                songs.put(genre, tmp);
                maxPerGenre.put(genre, play);
            } else {    // 등록된 적 있으면
                int[] tmp = songs.get(genre);
                songs.replace(genre,saveNewData(song, tmp, i));
                maxPerGenre.replace(genre, Math.max(maxPerGenre.get(genre), play));
            }
        }

        List<String> keyList = getOrder(maxPerGenre);
        List<Integer> idList = new ArrayList<>();

        for(String key : keyList) {
            int[] num = songs.get(key);
            for(int i=0; i<2; i++) {
                if(num[i] != -1) {
                    idList.add(num[i]);
                }
            }
        }
        int[] answer = new int[idList.size()];
        int idx =0;

        for(int num : idList) {
            answer[idx++] = num;
            System.out.println(num);
        }

        return answer;
    }

    private static int[] saveNewData(Map<Integer, Integer> song, int[] num, int idx) {
        int temp = song.get(idx);

        for(int i=1; i>=0; i--) {
            if(temp > song.get(num[i]) ) {   // 바꿀게 더 크면
                num[i+1] = num[i];
                num[i] = idx;
            }
        }

        return num;
    }
//    ======================= Sovle1
*/
//   ======================== Solve 2
    public static int[] solution(String[] genres, int[] plays) {
        Map<String, Song> songs = new HashMap<>();   // 장르별 노래 리스트(고유 번호와 플레이 횟수) 저장 변수
        Map<String, Integer> titles = new HashMap<>();  // 장르 별 최고 재생횟수 저장 변수

        for(int i=0; i<genres.length; i++) {
            int play = plays[i];
            String genre = genres[i];

            Song tmp = new Song(i, play);       // id와 플레이 횟수 저장

            if(!songs.containsKey(genre)) {     // map에 장르가 없으면
                songs.put(genre, tmp);          // 장르별 노래 리스트 저장
                titles.put(genre, play);        // 장르에 플레이 횟수 저장
            } else {                                // 있으면
                songs.replace(genre, saveData(songs.get(genre), tmp));
                titles.replace(genre, titles.get(genre) + play);
            }
        }

        List<String> keySetList = getOrder(titles);
        List<Integer> idList = new ArrayList<>();

        for(String genre : keySetList) {
            Song tmp = songs.get(genre);

            for(int i=0; i<2; i++) {
                if(tmp.play[i] != 0) {
                    idList.add(tmp.id[i]);
                }
            }
        }

        int[] answer = new int[idList.size()];
        int idx =0;

        for(int tmp : idList) {
            answer[idx++] = tmp;
            System.out.println(tmp);
        }

        return answer;
    }

     private static Song saveData(Song prevSong, Song newSong) {
        for(int i=0; i<2; i++) {
            if(newSong.play[0] > prevSong.play[i]) {    // 이미 대소비교를 해줌
                prevSong.add(i, newSong);
                return prevSong;
            }
        }

        return prevSong;
    }
//    ==================== Solve2

    // 타이틀 별로 내림차순 해주기
    private static List<String> getOrder(Map<String, Integer> titles) {
        // 내림차순 //
        List<String> keySetList = new ArrayList<>(titles.keySet());
        Collections.sort(keySetList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return titles.get(o2).compareTo(titles.get(o1));
            }
        });

        return keySetList;
    }

    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

        /*
        String[] genres = {};
        int[] plays = {};
         */


        solution(genres, plays);
//        sol(genres,plays);
    }
}

class Song {
    private final int MAXSIZE = 100;
    int[] id;
    int[] play;

    Song(int id, int play) {
        this.id = new int[MAXSIZE];
        this.play = new int[MAXSIZE];
        this.play[0] = play;
        this.id[0] = id;
    }

    void add(int i, Song newSong) {
        if(i ==0) { // 하나 미뤄주기
            this.id[i+1] = this.id[i];
            this.play[i+1] = this.play[i];
        }
        this.id[i] = newSong.id[0];
        this.play[i] = newSong.play[0];
    }
}

//        ---------------- 통과 Test Case -------------------
//        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
//        int[] plays = {500, 600, 150, 800, 2500};

//        String[] genres = {"classic", "pop", "classic", "pop", "classic", "classic"};
//        int[] plays = {400, 600, 150, 2500, 500, 500};

//        String[] genres = {"a", "b", "c", "d", "e", "f"};
//        int[] plays = {1, 2, 3, 4, 5, 6};

//        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
//        int[] plays = {500, 600, 150, 800, 2500};

//        String[] genres = {"a", "a", "a"};
//        int[] plays = {1,1,1};

//        String[] genres = {"a", "b", "c", "b", "c", "d"};
//        int[] plays = {1000, 1, 2, 3, 4, 5};

//        String[] genres = {"a", "b", "a", "b", "c"};
//        int[] plays = {100, 200, 300, 400, 500};

//        String[] genres = {"classic", "pop", "classic", "classic", "pop", "zazz", "zazz"};
//        int[] plays = {500, 600, 150, 800, 2500, 2000, 6000};

//        String[] genres = {"classic", "pop", "classic", "classic", "pop", "zazz", "zazz"};
//        int[] plays = {500, 600, 150, 800, 2500, 2100, 1000};


//        ------------------ 실패 Test Case ----------------------

/*
문제 설명
스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다.
노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.

속한 노래가 많이 재생된 장르를 먼저 수록합니다.
장르 내에서 많이 재생된 노래를 먼저 수록합니다.
장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
                                                                            // 장르 ~> 노래 ~> 고유번호 낮은거 순으로 진행
노래의 장르를 나타내는 문자열 배열 genres와
노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때,
베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.

제한사항
genres[i]는 고유번호가 i인 노래의 장르입니다.
plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
장르 종류는 100개 미만입니다.
장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
모든 장르는 재생된 횟수가 다릅니다.

입출력 예시)
genres												plays				return
[classic, pop, classic, classic, pop]	[500, 600, 150, 800, 2500]	[4, 1, 3, 0]

입출력 예 설명
classic 장르는 1,450회 재생되었으며, classic 노래는 다음과 같습니다.

고유 번호 3: 800회 재생
고유 번호 0: 500회 재생
고유 번호 2: 150회 재생
pop 장르는 3,100회 재생되었으며, pop 노래는 다음과 같습니다.

고유 번호 4: 2,500회 재생
고유 번호 1: 600회 재생
따라서 pop 장르의 [4, 1]번 노래를 먼저, classic 장르의 [3, 0]번 노래를 그다음에 수록합니다.
*/