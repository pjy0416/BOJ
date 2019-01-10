import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Test_12893 {               // 모르겠음... failed code

    static void makeEnemy(Set<Integer>[] enemySet, String enemyStr, int personNum) {
        int major = Integer.parseInt(enemyStr.split(" ")[0]);
        int target = Integer.parseInt(enemyStr.split(" ")[1]);

        enemySet[major].add(target);
    }

    static void makeParty(Set<Integer>[] partySet, Set<Integer>[] enemySet) {
        /**
         * 조건 1 => 나랑 적이 아니어야 한다.
         * 조건 2 => 나와 적인 사람의 적이 나를 적으로 생각하지 않아야한다.
         * */
        int len = enemySet.length;

        for(int i=1; i<len; i++) { // 사람의 수만큼 loop 진행
            Iterator keys =enemySet[i].iterator();  // 해당 사람이 가진 적들을 불러온다.
            while(keys.hasNext()) {                 // 적이 존재할 때 까지 loop
                int target = (int)keys.next();      // 적이 누군지 number를 가져온다.
                Iterator targetKey = enemySet[target].iterator();   // 자신의 적의 적들을 불러온다.
                while(targetKey.hasNext()) {        // 적의 적들이 존재하는한 loop
                    int targetEnemy = (int)targetKey.next();    // 적의 적인 사람의 number를 가져온다.
                    if(!enemySet[i].contains(targetEnemy) && !enemySet[targetEnemy].contains(i)) {  // 조건 1 2 적용부분
                        partySet[i].add(targetEnemy);
                    }
                }
            }
        }
    }

    static int isParty(Set<Integer>[] partySet) {
        int size = partySet.length;
        for(int i=1; i<size; i++) {
            if(partySet[i].size() > 0) {
                return 1;       // 친구가 된 경우하 하나라도 존재하면 리턴 1
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String relation = br.readLine();

        int personNum = Integer.parseInt(relation.split(" ")[0]);
        int enemyNum = Integer.parseInt(relation.split(" ")[1]);

        Set<Integer>[] partySet = new HashSet[personNum+1];
        Set<Integer>[] enemySet = new HashSet[personNum+1];

        for(int i=0; i<=personNum; i++) {   // 초기화
            partySet[i] = new HashSet<>();
            enemySet[i] = new HashSet<>();
        }

        for(int i=1; i<=enemyNum; i++) {     // 적대 관계 형성
            String enemyStr = br.readLine();
            makeEnemy(enemySet, enemyStr, personNum);
        }

        makeParty(partySet, enemySet);
        System.out.println(isParty(partySet));

        br.close();
    }
}
