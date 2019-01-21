import java.util.Scanner;

public class Algjobs {
    static Scanner scan = new Scanner(System.in);

    private static int[] func(String inputValue, int testCase) {
        int ySize = Integer.parseInt(inputValue.split(" ")[1]);
        int robotX = Integer.parseInt(inputValue.split(" ")[2]) -1;
        int robotY = Integer.parseInt(inputValue.split(" ")[3]) -1;

        // 지도 좌표
        String[][] map = new String[ySize][];

        for(int i=0; i<ySize; i++) {
            String inputStr = scan.nextLine();
            map[i] = inputStr.split(" ");
        }

        //
        int[] total = new int[testCase];

        for(int test=0; test< testCase; test++) {
            // controller (돌림판)
            int[] roller = makeRoller();
            int rollerSize = roller.length;
            int rollerTop = 0;

            int orderNum = scan.nextInt();
            scan.nextLine();
            // 명령 수행
            for (int i = 0; i < orderNum; i++) {
                String order = scan.nextLine();
                String mapSide = order.split(" ")[0];
                String rollerSide = order.split(" ")[1];

                int rollerMove = Integer.parseInt(order.split(" ")[2]);
                if (rollerSide.equals("1")) {
                    rollerTop = (rollerTop + rollerMove) % rollerSize;
                } else {
                    rollerTop = Math.abs(rollerTop - rollerSize - rollerMove) % rollerSize;
                }
                int move = roller[rollerTop];

                // 동서남북
                switch (mapSide) {
                    case "N":
                        for (int y = 1; y <= move; y++) {
                            robotY -= 1;
                            total[test] += Integer.parseInt(map[robotY][robotX]);
                        }
                        break;
                    case "E":
                        for (int x = 1; x <= move; x++) {
                            robotX += 1;
                            total[test] += Integer.parseInt(map[robotY][robotX]);
                        }
                        break;
                    case "W":
                        for (int x = 1; x <= move; x++) {
                            robotX -= 1;
                            total[test] += Integer.parseInt(map[robotY][robotX]);
                        }
                        break;
                    case "S":
                        for (int y = 1; y <= move; y++) {
                            robotY += 1;
                            total[test] += Integer.parseInt(map[robotY][robotX]);
                        }
                        break;
                    default:
                        break;
                }

            }
        }

        return total;
    }

    private static int[] makeRoller() {
        int rollingSize = scan.nextInt();
        scan.nextLine();
        int[] roller = new int[rollingSize];

        String inputStr = scan.nextLine();

        for(int i=0; i<rollingSize; i++) {
            roller[i] = Integer.parseInt(inputStr.split(" ")[i]);
        }

        return roller;
    }

    public static void main(String[] args) {
        int testCase = scan.nextInt();
        scan.nextLine();
        String inputValue = scan.nextLine();
        int[] total = func(inputValue, testCase);

        for(int i=0; i< testCase; i++) {
            System.out.println(total[i]);
        }

        scan.close();
    }
}
