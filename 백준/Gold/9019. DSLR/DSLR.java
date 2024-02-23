import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final char[] direction = {'D', 'S', 'L', 'R'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        main:
        for (int testCase = 0; testCase < T; testCase++) {
            st = new StringTokenizer(br.readLine());
            int original = Integer.parseInt(st.nextToken());
            int goal = Integer.parseInt(st.nextToken());
            boolean[] visited = new boolean[10001];

            Queue<N9019Node> queue = new LinkedList<>();
            visited[original] = true;
            queue.offer(new N9019Node(original, ""));
            while (!queue.isEmpty()) {
                N9019Node pop = queue.poll();
                if (pop.num == goal) {
                    builder.append(pop.command).append("\n");
                    continue main;
                }

                for (char action : direction) {
                    if (action == 'D') {
                        int newNum = pop.num * 2 % 10000;
                        if (visited[newNum]) continue;
                        visited[newNum] = true;
                        queue.offer(new N9019Node(newNum, pop.command + "D"));
                    } else if (action == 'S') {
                        int newNum = pop.num == 0 ? 9999 : pop.num - 1;
                        if (visited[newNum]) continue;
                        visited[newNum] = true;
                        queue.offer(new N9019Node(newNum, pop.command + "S"));
                    } else if (action == 'L') {
                        int newNum = commandL(pop.num);
                        if (visited[newNum]) continue;
                        visited[newNum] = true;
                        queue.offer(new N9019Node(newNum, pop.command + "L"));
                    } else {
                        int newNum = commandR(pop.num);
                        if (visited[newNum]) continue;
                        visited[newNum] = true;
                        queue.offer(new N9019Node(newNum, pop.command + "R"));
                    }
                }
            }
        }
        System.out.print(builder);
    }

    private static int commandL(int x) {
        if (x >= 1000) {
            return (x % 1000 * 10) + (x / 1000);
        }
        return x * 10;
    }

    private static int commandR(int x) {
        return ((x % 10) * 1000) + (x / 10);
    }

    static class N9019Node {
        int num;
        String command;

        public N9019Node(int num, String command) {
            this.num = num;
            this.command = command;
        }
    }
}