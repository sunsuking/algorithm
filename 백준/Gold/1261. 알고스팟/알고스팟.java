import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        boolean[][] squares = new boolean[N][M];
        int[][] breaks = new int[N][M];
        for (int x = 0; x < N; x++) {
            char[] chars = br.readLine().toCharArray();
            for (int y = 0; y < M; y++) {
                squares[x][y] = chars[y] == '1';
                breaks[x][y] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(node -> node.n));
        breaks[0][0] = 0;
        pq.offer(new Node(0, 0, 0));

        while (!pq.isEmpty()) {
            Node pop = pq.poll();

            if (pop.x == N - 1 && pop.y == M - 1) {
                System.out.println(pop.n);
                break;
            }

            for (int[] direction : directions) {
                int newX = pop.x + direction[0];
                int newY = pop.y + direction[1];

                if (isIn(newX, newY, N, M)) {
                    int newBreaks = pop.n + (squares[newX][newY] ? 1 : 0);
                    if (newBreaks < breaks[newX][newY]) {
                        breaks[newX][newY] = newBreaks;
                        pq.offer(new Node(newX, newY, newBreaks));
                    }
                }
            }
        }

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

    private static boolean isIn(int x, int y, int N, int M) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
