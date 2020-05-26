package programmers;

public class FileSort_17686 {
    private static String[] solution(String[] files) {
        SortedFiles sf = new SortedFiles(files.length);

        for(String file : files) {
            sf.addFile(file);
        }
        return sf.getResult();
    }

    public static void main(String[] args) {
        String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        String[] result = solution(files);

        for(String file : result) {
            System.out.print(file + " ");
        }
    }
}

class SortedFiles {
    private String[] result;
    private FileInfo[] fileArr;
    int top;

    public SortedFiles(int n) {
        result = new String[n];
        fileArr = new FileInfo[n];
        top = -1;
    }

    public void addFile(String name) {
        FileInfo file = new FileInfo(name);
        // System.out.println(file.name + "\t" + file.head +  file.number);
        if (top == -1) {
            fileArr[++top] = file;
        } else {
            for (int i = top; i >= 0; i--) {
                if (isFast(fileArr[i], file)) {  // origin이 더 빠르면
                    fileArr[i + 1] = file;
                    break;
                } else {    // target이 더 빠르면
                    fileArr[i + 1] = fileArr[i];
                    fileArr[i] = file;
                }
            }
            top++;
        }
    }

    private boolean isFast(FileInfo origin, FileInfo target) {
        int len = Math.min(origin.head.length(), target.head.length());
        for (int i = 0; i < len; i++) {
            char originCh = origin.head.charAt(i);
            char targetCh = target.head.charAt(i);
            if (originCh < targetCh) {   // origin이 더 빠름
                return true;
            } else if (originCh > targetCh) {    // target이 더 빠름
                return false;
            }
        }
        if (origin.head.length() != len) {    // target이 더 빠름
            return false;
        } else if (target.head.length() != len) { // origin이 더 빠름
            return true;
        }
        if (origin.number < target.number) { // origin이 더 빠름
            return true;
        } else if (origin.number > target.number) {  // target이 더 빠름
            return false;
        }

        return true;    // 모두 같은경우 origin이 더 빨라야함
    }

    public String[] getResult() {
        for (int i = 0; i < this.result.length; i++) {
            result[i] = this.fileArr[i].name;
            // System.out.println(this.fileArr[i].name + "\t" + this.fileArr[i].head + "\t" + this.fileArr[i].number);
        }
        return this.result;
    }
}

class FileInfo {
    String name;
    String head;
    int number;

    public FileInfo(String file) {
        name = file;
        int lastIdx =0;
        for(int i=0; i<file.length(); i++) {
            char ch = file.charAt(i);
            if(ch >=48 && ch <=57) {
                break;
            }
            lastIdx=i;
        }
        this.head = getStandard(file.substring(0,lastIdx+1));

        for(int i=lastIdx+1; i<file.length(); i++) {
            char ch = file.charAt(i);
            if(ch >57 || ch <48) {// 숫자가 아닌 경우
                break;
            }
            lastIdx = i;
        }

        this.number = Integer.parseInt(file.substring(this.head.length(), lastIdx+1));
    }

    private String getStandard(String str) {
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);
            if(ch >=97 && ch <=122) {
                ch-=32;
            }
            sb.append(ch);
        }
        return sb.toString();
    }
}
/*
https://programmers.co.kr/learn/courses/30/lessons/17686#
 */