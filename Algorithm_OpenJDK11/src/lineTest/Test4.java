package lineTest;

import java.util.*;

public class Test4 {

    private static String[][] solution(String[][] snapshots, String[][] transactions) {
        Bank bank = new Bank();

        for(String[] snaps : snapshots) {
            bank.add(new SnapShot(snaps));
        }   // 초기 정보 저장

        Set<String> ids = new HashSet<>();
        ArrayList<Transaction> trsList = new ArrayList<>();

        for(String[] trs : transactions) {
            if(!ids.contains(trs[0])) {
                trsList.add(new Transaction(trs));
                ids.add(trs[0]);
            }
        }

        for(Transaction trans : trsList) {
            bank.doTransaction(trans);
        }

        return bank.getResult();
    }

    public static void main(String[] args) {
        String[][] snapshots = {
                {"ACCOUNT1", "100"},
                {"ACCOUNT2", "150"},
                {"ACCOUNT7", "150"},
                {"ACCOUNT5", "150"},
                {"account5", "150"}
        };
        String[][] transcations = {
                {"1", "SAVE", "ACCOUNT2", "100"},
                {"2", "WITHDRAW", "ACCOUNT1", "50"},
                {"1", "SAVE", "ACCOUNT2", "100"},
                {"4", "SAVE", "ACCOUNT3", "500"},
                {"3", "WITHDRAW", "ACCOUNT2", "30"}
        };

        String[][] result = solution(snapshots, transcations);
        for(String[] info : result) {
            System.out.println(info[0] + ", " + info[1]);

        }
    }
}

class Bank {
    private HashMap<String,Integer> info;
    public Bank() {
        info = new HashMap<>();
    }

    public void add (SnapShot snapshot) {
        info.put(snapshot.name, snapshot.money);
    }

    public void doTransaction(Transaction tr) {
        switch (tr.action) {
            case "SAVE":
                if(info.containsKey(tr.person)) {
                    info.replace(tr.person, info.get(tr.person)+ tr.money);
                } else {
                    info.put(tr.person, tr.money);
                }
                break;
            case "WITHDRAW":
                if(info.containsKey(tr.person)) {
                    info.replace(tr.person, info.get(tr.person) - tr.money);
                }
                break;
            default:
                break;
        }
    }

    public String[][] getResult() {
        String[][] strArr = new String[info.size()][2];
        PriorityQueue<SnapShot> pq = new PriorityQueue<>();
        Iterator<String> it = info.keySet().iterator();
        while(it.hasNext()) {
            String name = it.next();
            pq.offer(new SnapShot(name, info.get(name)));
        }

        for(int i=0; i<info.size(); i++) {
            SnapShot ss = pq.poll();
            strArr[i][0] = ss.name;
            strArr[i][1] = String.valueOf(ss.money);
        }

        return strArr;
    }
}

class SnapShot implements Comparable<SnapShot>{
    String name;
    int money;

    public SnapShot(String[] info) {
        this.name = info[0];
        this.money = Integer.parseInt(info[1]);
    }

    public SnapShot(String name, Integer money) {
        this.name = name;
        this.money = money;
    }

    @Override
    public int compareTo(SnapShot o) {
        int originLen = this.name.length();
        int targetLen = o.name.length();

        int min = Math.min(originLen, targetLen);

        for(int i=0; i<min; i++) {
            if(this.name.charAt(i) > o.name.charAt(i)) {
                return 1;
            } else if(this.name.charAt(i) < o.name.charAt(i)) {
                return -1;
            }
        }
        return originLen == min ? 1 : -1;
    }
}

class Transaction {
    String id;
    String action;
    String person;
    int money;

    public Transaction(String[] info) {
        this.id = info[0];
        this.action = info[1];
        this.person = info[2];
        this.money = Integer.parseInt(info[3]);
    }
}
