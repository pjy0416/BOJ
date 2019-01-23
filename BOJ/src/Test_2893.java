import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test_2893 {        // wrong code
    private static void getResult(String[][] triangles, int size) {
        double area =0.0d;
        for(int i=0; i<size; i++) {
            int originX = getX(triangles[i]);
            int originY = getY(triangles[i]);
            int side = getSide(triangles[i]);

            area += Math.pow((double)side, 2)/2.0;
            for(int j=0; j<size; j++) {
                if(i==j) {
                    continue;
                }
                int targetX = getX(triangles[j]);
                int targetY = getY(triangles[j]);
                int targetSide = getSide(triangles[j]);

                if(originX < targetX && originX+side - targetX >0 ) {       // 기준과 타겟의 X가 겹치는 부분
                    if(linearEquation(originX, originY, side, targetX)-originY == originX+side-targetX) {    // Y가 겹치는 부분
                        System.out.println("여기라고..?");
                        area -= Math.pow(originX+side-targetX,2);
                    }
                } else if (originX > targetX && originX+side <targetX + targetSide) {   // x길이가 아예 먹혀있을때
                    System.out.println("여기 오냐");
                    if(targetY >= originY) {// 기준보다 타겟이 위 or 같은 위치에서 시작할 때
                        if(side > targetSide) { // 기준의 길이가 더 크면 아예 포함
                            area -= Math.pow((double)targetSide, 2);
                        }
                    }
                }

            }
        }
        System.out.println(String.format("%.1f", area));
    }

    private static int linearEquation(int originX, int originY, int side, int x) {
        int a = -1;     // a= (y2-y1)/(x2-x1) 인데 둘 다 주어진 side 의 길이이고 때문에 우하향 직선을 가지기 때문에
        int b = originY - a*(originX+side);
        int y = a*x + b;

        return y;
    }
    private static int getX(String[] triangle) {
        return Integer.parseInt(triangle[0]);
    }
    private static int getY(String[] triangle) {
        return Integer.parseInt(triangle[1]);
    }
    private static int getSide(String[] triangle) {
        return Integer.parseInt(triangle[2]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        String triangles[][] = new String[size][];

        for(int i=0; i<size; i++) {
            triangles[i] = br.readLine().split(" ");
        }

        getResult(triangles, size);

        br.close();
    }
}
