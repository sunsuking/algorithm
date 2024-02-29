import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class N4485Position {
        int x;
        int y;
        int cost;

        public N4485Position(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "N4485Position{" +
                    "x=" + x +
                    ", y=" + y +
                    ", cost=" + cost +
                    '}';
        }
    }

    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static int N, min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        StringTokenizer st;
        int T = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            min = Integer.MAX_VALUE;
            if (N == 0) break;
            int[][] squares = new int[N][N];
            int[][] score = new int[N][N];
            for (int i = 0; i < N; i++) Arrays.fill(score[i], Integer.MAX_VALUE);

            for (int x = 0; x < N; x++) {
                st = new StringTokenizer(br.readLine());
                for (int y = 0; y < N; y++) {
                    squares[x][y] = Integer.parseInt(st.nextToken());
                }
            }

            Queue<N4485Position> queue = new LinkedList<>();
            score[0][0] = squares[0][0];
            queue.offer(new N4485Position(0, 0, squares[0][0]));
            while (!queue.isEmpty()) {
                N4485Position pop = queue.poll();

                if (pop.x == N - 1 && pop.y == N - 1) {
                    continue;
                }

                for (int[] ints : direction) {
                    int newX = pop.x + ints[0];
                    int newY = pop.y + ints[1];

                    if (isIn(newX, newY) && score[newX][newY] > score[pop.x][pop.y] + squares[newX][newY]) {
                        score[newX][newY] = score[pop.x][pop.y] + squares[newX][newY];
                        queue.offer(new N4485Position(newX, newY, pop.cost + squares[pop.x][pop.y]));
                    }
                }
            }
            builder.append("Problem").append(" ").append(T).append(":").append(" ").append(score[N - 1][N - 1]).append("\n");
            T++;
        }
        System.out.print(builder);
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}