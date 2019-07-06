package step7;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.String;

public class StudyWord1157 {

    public static MyMap map;

    private static void printResult(String word) {
        int len = word.length();
        word = word.toUpperCase();

        for(int i=0; i<len; i++) {
            map.put(word.charAt(i),1);
        }

        map.print();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new MyMap();

        String word = br.readLine();

        printResult(word);

        br.close();
    }

}

class MyMap {
    private static final int MAX_LENGTH = 26;
    MyDiction[] map;
    int rear;
    boolean isDuple;

    MyMap() {
        map = new MyDiction[MAX_LENGTH];
        rear =0;
        isDuple = false;

        for(int i=0; i<MAX_LENGTH; i++) {
            map[i] = new MyDiction();
        }
    }

    void put(char key, int value) {
        map[containsIdx(key)].put(key, value);
        this.rear++;
    }

    int containsIdx(char key) {
        for(int i=0; i<rear; i++) {
            if(map[i].key == key) {
                this.rear--;
                return i;
            }
        }
        return this.rear;
    }

    char checkDuple() {
        int maxCnt =0;
        int result =0;

        for(int i=0; i<MAX_LENGTH; i++) {
            MyDiction diction = map[i];
            if(maxCnt < diction.value) {
                this.isDuple = false;
                maxCnt = diction.value;
                result = i;
            } else if(maxCnt == diction.value) {
                this.isDuple = true;
            }
        }

        return isDuple ? '?' : map[result].key;
    }

    void print() {
        System.out.println(checkDuple());
    }
}

class MyDiction {
    public char key;
    public int value;

    MyDiction() {
        this.value =0;
    }

    void put(char key, int value) {
        this.key = key;
        this.value += value;
    }

}