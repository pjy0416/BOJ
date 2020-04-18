package devMatching;

public class Test2 {
    private static int solution(int[][] office, int r, int c, String[] move) {
        int answer = 0;
        int direction =0;   // 0 = 북쪽, 1 = 동쪽, 2 = 남쪽, 3 = 서쪽

        if(office[r][c] >=0) {  // 먼지면
            answer+= office[r][c];
            office[r][c] =0;
        }
        for(String order : move) {
            int y = r;
            int x = c;
//            System.out.println("현재 좌표 : " + x + ", " + y);
            switch (order) {
                case "go" :
                    if(direction ==0) { //북
                        y--;
                    }else if(direction ==1) {   //동
                        x++;
                    } else if(direction ==2) {  //남
                        y++;
                    } else {    // 서
                        x--;
                    }

                    if(canMove(y,x,office)) {
                        answer+= office[y][x];
                        office[y][x] = 0;
                        r = y;
                        c = x;
                        /*System.out.println("청소 좌표 : " + x + ", " + y);
                        System.out.println("청소량 : " + answer);*/
                    }
                    break;
                case "right" :
                    direction++;
                    break;
                case "left" :
                    direction--;
                    break;
                default:
                    break;
            }
            if(direction <0) {
                direction =3;
            } else if(direction > 3) {
                direction =0;
            }
        }
        return answer;
    }

    private static boolean canMove(int r, int c, int[][] office) {
        if(r > office.length-1 || r<0 || c > office.length-1 || c<0) {
            return false;
        } else {
            return office[r][c] >=0 ? true : false;
        }
    }

    public static void main(String[] args) {
        int[][] office = {{5,-1,4}, {6,3,-1}, {2,-1,1}};
        int r = 1;
        int c = 0;
        String[] move = {"go","go","right","go","right","go","left","go"};

        System.out.println(solution(office,r,c,move));
    }
}