package lineTest;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Test5 {
    private static String[] solution(String[][] dataSource, String[] tags) {
        Documents[] docArr = new Documents[dataSource.length];
        PriorityQueue<Documents> pq = new PriorityQueue<>();
        //값 저장
        for(int i=0; i<dataSource.length; i++) {
            for (int j = 0; j < dataSource[i].length; j++) {
                if (j == 0) {
                    docArr[i] = new Documents();
                    docArr[i].setName(dataSource[i][j]);
                } else {
                    docArr[i].addTag(dataSource[i][j]);
                }
            }
        }

        //Tag 저장 및 카운팅
        for(String tag : tags) {
            for(Documents doc : docArr) {
                doc.addScore(tag);
            }
        }

        //우선 순위 큐에 저장
        for(Documents doc : docArr) {
            if(doc.getScore() !=0) {
                pq.offer(doc);
            }
        }


        int size = pq.size() > 10 ? 10 : pq.size(); // 10개 초과 시 10개 까지만 출력

        String[] answer = new String[size];

        for(int i=0; i<size; i++) { // 답 저장
            Documents doc = pq.poll();
            answer[i] = doc.getName();
        }

        return answer;
    }

    public static void main(String[] args) {
        String[][] dataSource = {
                {"doc1", "t1", "t2", "t3"},
                {"doc2", "t0", "t2", "t3"},
                {"doc3", "t1", "t6", "t7"},
                {"doc4", "t1", "t2", "t4"},
                {"dOc4", "t1", "t2", "t4"},
                {"doc44", "t1", "t2", "t4"},
                {"doca44", "t1", "t2", "t4"},
                {"doc5", "t6", "t100", "t8"}
        };
        String[] tags = {"t1", "t2", "t3"};

        String[] result = solution(dataSource, tags);

        for(String str : result) {
            System.out.println(str);
        }
    }
}

class Documents implements Comparable<Documents>{
    private String docName;
    private ArrayList<String> tagList;
    private int score;

    public Documents() {
        this.tagList = new ArrayList<>();
        score =0;
    }

    public void addTag(String tag) {
        this.tagList.add(tag);
    }

    public void addScore(String tag) {
        if(isContainTag(tag))   this.score++;
    }

    private boolean isContainTag(String tag) {
        return tagList.contains(tag) ? true : false;
    }

    public int getScore() {
        return this.score;
    }

    public String getName() {
        return this.docName;
    }

    public void setName(String name) {
        this.docName = name;
    }

    @Override
    public int compareTo(Documents o) {
        if(this.score < o.score) {
            return 1;
        } else if(this.score == o.score) {
            String originStr = this.docName.replaceAll("[0-9]","");
            String targetStr = o.getName().replaceAll("[0-9]", "");
            String originNum = this.docName.replaceAll("[^0-9]","");
            String targetNum = o.getName().replaceAll("[^0-9]", "");
            for(int i=0; i<originStr.length(); i++) {
                for(int j=0; j<targetStr.length(); j++) {
                    if(i == j) {
                        char originCh = originStr.charAt(i);
                        char targetCh = targetStr.charAt(j);
                        int originCategory = charOrder(originCh);
                        int targetCategory = charOrder(targetCh);

                        if(originCategory == targetCategory) {
                            if(originCh > targetCh) {
                                return 1;
                            } else if(originCh < targetCh) {
                                return -1;
                            }
                            // 같은 경우는 스킵
                        } else {    // 다른 경우는 바로 리턴
                            return originCategory > targetCategory ? 1 : -1;
                        }
                    }
                }
            }

            //위에서 결론이 안났다면 비교한 곳까지는 똑같다는 의미
            if(originStr.length() == targetStr.length()) {  // 길이가 같은 아이라면 뒤에 숫자 비교
                return Integer.parseInt(originNum) > Integer.parseInt(targetNum) ? 1 : -1;
            } else {
                return originStr.length() > targetStr.length() ? 1: -1;
            }
        }
        return -1;
    }

    private int charOrder(char ch) {
        if(ch >=97 && ch <= 122) {   // 소문자
            return 1;
        } else if(ch >=65 && ch <= 90) {
            return 2;
        } else if(ch >=48 && ch <=57) {
            return 3;
        } else {
            return 4;
        }
    }
}
