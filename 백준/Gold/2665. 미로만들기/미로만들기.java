import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        boolean[][] squares = new boolean[N][N];
        int[][] dp = new int[N][N];
        for (int x = 0; x < N; x++) {
            char[] chars = br.readLine().toCharArray();
            for (int y = 0; y < N; y++) {
                squares[x][y] = chars[y] == '0';
                dp[x][y] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(node -> node.n));
        pq.offer(new Node(0, 0, 0));
        dp[0][0] = 0;

        while (!pq.isEmpty()) {
            Node pop = pq.poll();

            if (pop.x == N - 1 && pop.y == N - 1) {
                System.out.println(pop.n);
                System.exit(0);
            }

            for (int[] direction : directions) {
                int newX = pop.x + direction[0];
                int newY = pop.y + direction[1];

                if (isIn(newX, newY, N)) {
                    int weight = dp[pop.x][pop.y] + (squares[pop.x][pop.y] ? 1 : 0);
                    if (weight < dp[newX][newY]) {
                        dp[newX][newY] = weight;
                        pq.offer(new Node(newX, newY, weight));
                    }
                }
            }
        }
    }

    private static boolean isIn(int x, int y, int N) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static class Node {
        int x;
        int y;
        int n;

        public Node(int x, int y, int n) {
            this.x = x;
            this.y = y;
            this.n = n;
        }
    }
}
