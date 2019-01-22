import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Test_9019 {    // BFS 관련 문제
    // Ref : https://virusworld.tistory.com/83
    private static char[] commandArr = {'D','S','L','R'};

    private static void calculator(String inputStr) {
        int origin = Integer.parseInt(inputStr.split(" ")[0]);
        int target = Integer.parseInt(inputStr.split(" ")[1]);

        Graph dslr = new Graph(origin, target);

        System.out.println(dslr.getCommand());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for(int i=0; i<testCase; i++) {
            String inputStr = br.readLine();

            calculator(inputStr);
        }
        br.close();
    }

    static class Graph {
        private int origin, target;
        private boolean[] visited;

        public Graph(int num1, int num2) {      // 생성자
            this.origin = num1;
            this.target = num2;
            this.visited = new boolean[10001];
        }

        private int calD(int num) {
            return (num*2)%10000;
        }

        private int calS(int num) {
            if(num == 0) {
                return 9999;
            }
            return num-1;
        }

        private int calL(int num) {
            return (num%1000)*10 + num/1000;
        }

        private int calR(int num) {
            return (num%10)*1000 + num/10;
        }

        public String getCommand() {
            Queue<Register> registers = new LinkedList<>();
            registers.offer(new Register(origin, ""));
            visited[origin] = true;

            while(!registers.isEmpty()) {
                Register register = registers.poll();

                if(register.getNum() == target) {
                    return register.getCommand();
                }

                if(!visited[calD(register.getNum())]) {         // 명령어 D 수행
                    int num = calD(register.getNum());
                    visited[num] = true;
                    String command = register.getCommand();
                    registers.offer(new Register(num, command+commandArr[0]));
                }

                if(!visited[calS(register.getNum())]) {
                    int num =  calS(register.getNum());
                    visited[num] = true;
                    String command = register.getCommand();
                    registers.offer(new Register(num, command+commandArr[1]));
                }

                if(!visited[calL(register.getNum())]) {
                    int num = calL(register.getNum());
                    visited[num] = true;
                    String command = register.getCommand();
                    registers.offer(new Register(num, command+commandArr[2]));
                }
                if(!visited[calR(register.getNum())]) {
                    int num = calR(register.getNum());
                    visited[num] = true;
                    String command = register.getCommand();
                    registers.offer(new Register(num,command+commandArr[3]));
                }
            }

            return null;
        }

    }

    static class Register {
        private int num;
        private String command;

        public Register(int num, String command) {
            this.num = num;
            this.command = command;
        }

        public int getNum() {
            return num;
        }

        public String getCommand() {
            return command;
        }
    }

}

/** 이 문제 1등 코드
 * import java.io.BufferedReader;
 * import java.io.IOException;
 * import java.io.InputStreamReader;
 * import java.util.LinkedList;
 * import java.util.StringTokenizer;
 *
 * public class Main {
 * 	int T, a, b;
 * 	int[] trace;
 * 	public static void main(String[] args) throws IOException {
 * 		Main main = new Main();
 * 		main.solve();
 *        }
 * 	void solve() throws IOException {
 * 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 * 		T = Integer.parseInt(br.readLine());
 * 		trace = new int[10000];
 * 		for(int i = 0; i < T; i++) {
 * 			StringTokenizer st = new StringTokenizer(br.readLine());
 * 			a = Integer.parseInt(st.nextToken());
 * 			b = Integer.parseInt(st.nextToken());
 * 			LinkedList<Integer> queue = new LinkedList<>();
 * 			for(int j = 0; j < 10000; j++) trace[j] = -1;
 * 			queue.add(a);
 * 			bfs(queue);
 * 			StringBuilder sb = new StringBuilder();
 * 			while(b != a) {
 * 				int prev = trace[b];
 * 				if(2*prev % 10000 == b) {
 * 					sb.append("D");
 *                } else if((prev+9999)%10000 == b) {
 * 					sb.append("S");
 *                } else if((prev*10)%10000 + prev/1000 == b) {
 * 					sb.append("L");
 *                } else {
 * 					sb.append("R");
 *                }
 * 				b = prev;
 *            }
 * 			System.out.println(sb.reverse());
 *        }
 *    }
 *
 * 	void bfs(LinkedList<Integer> queue) {
 * 		int len = queue.size();
 * 		if(len == 0) return;
 * 		for(int i = 0; i < len; i++) {
 * 			int c = queue.pollFirst();
 * 			int d = 2*c % 10000;
 * 			int s = (c+9999)%10000;
 * 			int l = (c*10)%10000 + c/1000;
 * 			int r = c/10 + (c%10)*1000;
 * 			if(trace[d] == -1) {
 * 				trace[d] = c;
 * 				queue.add(d);
 *            }
 * 			if(trace[s] == -1) {
 * 				trace[s] = c;
 * 				queue.add(s);
 *            }
 * 			if(trace[l] == -1) {
 * 				trace[l] = c;
 * 				queue.add(l);
 *            }
 * 			if(trace[r] == -1) {
 * 				trace[r] = c;
 * 				queue.add(r);
 *            }
 * 			if(d == b || s == b || l == b || r == b) return;
 *        }
 * 		bfs(queue);
 *    }
 * }

*/
