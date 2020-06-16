package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CourseBasket_17488 {
    final static String FAILED = "망했어요";
    static CourseInfo[] courseArr;

    public static void main(String[] args) throws IOException { // 시간 초과 ~> 25%에서 틀렸습니다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] infoArr = br.readLine().split(" ");

        int studentNum = Integer.parseInt(infoArr[0]);
        int courseNum = Integer.parseInt(infoArr[1]);

        courseArr = new CourseInfo[courseNum];
        inItCourseArr(new StringTokenizer(br.readLine()), courseNum);

        ArrayList<Integer>[] studentList = inItStudentList(studentNum);

        for(int i=0; i<2; i++) {    // 수강신청, 저장
            setCourseArr(br, studentNum);
            studentList = setStudentList(studentList, courseNum);
        }

        for(ArrayList<Integer> student : studentList) {
            if(student.size() ==0) {
                System.out.println(FAILED);
            } else {
                for(int num : student) {
                    System.out.print((num+1) + " ");
                }
                System.out.println();
            }
        }

        br.close();
    }

    private static ArrayList<Integer>[] inItStudentList(int studentNum) {
        ArrayList<Integer>[] result = new ArrayList[studentNum];
        for(int i=0; i<studentNum; i++) {
            result[i] = new ArrayList<>();
        }
        return result;
    }

    private static ArrayList<Integer>[] setStudentList(ArrayList<Integer>[] studentList, int courseNum) {
        for(int i=0; i<courseNum; i++) {    // 넣기
            if(courseArr[i].isEnrolled && courseArr[i].isChanged) {
                for(int j=courseArr[i].start; j<=courseArr[i].top; j++) {
                    int stdIdx =courseArr[i].students[j];
                    studentList[stdIdx].add(i);

                }
                courseArr[i].setStart();
            }
        }
        return studentList;
    }

    private static void inItCourseArr(StringTokenizer st, int courseNum) {
        for(int i=0; i<courseNum; i++) {
            courseArr[i] = new CourseInfo(Integer.parseInt(st.nextToken()));
        }
    }

    private static void setCourseArr(BufferedReader br, int studentNum) throws IOException{
        StringTokenizer st;
        for(int i=0; i<studentNum; i++) { // 1차 수강신청
            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()) {
                int num = Integer.parseInt(st.nextToken());
                if(num != -1) {
                    courseArr[num - 1].add(i);
                }
            }
        }
    }
}

class CourseInfo {
    int top;
    int start;
    int[] students;
    boolean isEnrolled;
    boolean isChanged;

    public CourseInfo(int size) {
        this.top =-1;
        this.students = new int[size];
        this.isEnrolled = true;
        this.isChanged = false;
        this.start =0;
    }

    public void add(int idx) {
        if(top+1 < students.length) {
            students[++top] = idx;
            this.isChanged = true;
        } else {    // 더이상 추가 못하는 경우
            isEnrolled = false;
        }
    }

    public void setStart() {
        this.start = this.top+1;
        this.isChanged = false;
    }
}