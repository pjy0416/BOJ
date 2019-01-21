import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Test_6986 {

    private static double trimMean(double sum, int all, int exp) {
        int sampleNum = all-exp*2;
        double result = sum / sampleNum;

        return result;
    }

    private static double adjMean(double sum, double[] numArr, int all, int exp) {
        double smallNum = numArr[exp];
        double largeNum = numArr[all-exp-1];

        sum += smallNum*exp + largeNum*exp;

        return sum/all;
    }

    private static void printMean(double[] numArr, int all, int exp) {
        double sum =0.0d;

        for(int i=exp; i<all-exp; i++) {
            sum += numArr[i];
        }

        double trim = trimMean(sum, all, exp);
        double adj = adjMean(sum, numArr, all, exp);

        System.out.println(String.format("%.2f", trim));
        System.out.println(String.format("%.2f", adj));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputStr = br.readLine();

        int all = Integer.parseInt(inputStr.split(" ")[0]);
        int exp = Integer.parseInt(inputStr.split(" ")[1]);

        double[] numArr = new double[all];

        for(int i=0; i<all; i++) {
            numArr[i] = Double.parseDouble(br.readLine());
        }

        Arrays.sort(numArr);

        printMean(numArr, all, exp);

        br.close();
    }
}
