package remindBOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CircleArea_3053 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        solution(Integer.parseInt(br.readLine()));
        br.close();
    }

    private static void solution(int r) {
        double taxi = 2 * r * r;
        double euclid = Math.PI*r*r;

        printArea(euclid);
        printArea(taxi);
    }

    private static void printArea(double num) {
        System.out.println(String.format("%.6f", num));
    }
}
