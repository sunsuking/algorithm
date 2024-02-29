import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int WEIGHT = 100_000;
    private static int N;
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int[][] squares = new int[N][N];
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < N; y++) {
                squares[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<N2146Position> startQueue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        int num = 1;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (squares[x][y] == 1 && !visited[x][y]) {
                    visited[x][y] = true;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{x, y});
                    startQueue.offer(new N2146Position(x, y, num, 1));
                    squares[x][y] = num * WEIGHT;

                    while (!queue.isEmpty()) {
                        int[] pop = queue.poll();

                        for (int[] ints : direction) {
                            int newX = pop[0] + ints[0];
                            int newY = pop[1] + ints[1];

                            if (isIn(newX, newY) && !visited[newX][newY]) {
                                if (squares[newX][newY] == 1) {
                                    squares[newX][newY] = num * WEIGHT;
                                    visited[newX][newY] = true;
                                    queue.offer(new int[]{newX, newY});
                                    startQueue.offer(new N2146Position(newX, newY, num, 1));
                                }
                            }
                        }
                    }
                    num++;
                }
            }
        }

        int min = Integer.MAX_VALUE;

        while (!startQueue.isEmpty()) {
//            visited = new boolean[N][N];
            int size = startQueue.size();
            for (int __x = 0; __x < size; __x++) {
                N2146Position pop = startQueue.poll();
                int prevDistance = squares[pop.x][pop.y] % WEIGHT;
                int n = pop.n;
//            System.out.println(pop);

                for (int[] ints : direction) {
                    int newX = pop.x + ints[0];
                    int newY = pop.y + ints[1];

                    if (isIn(newX, newY)) {
                        int newN = squares[newX][newY] / WEIGHT;
                        int distance = squares[newX][newY] % WEIGHT;

                        if (n != newN && newN != 0) {

                            min = Math.min(min, distance + prevDistance);
                            continue;
                        }

                        if (!visited[newX][newY]) {
                            squares[newX][newY] = n * WEIGHT + pop.size;
                            visited[newX][newY] = true;
                            startQueue.offer(new N2146Position(newX, newY, pop.n, pop.size + 1));
                        }
                    }
                }
            }

            if (min != Integer.MAX_VALUE) {
                System.out.println(min);
                System.exit(0);
            }
        }


    }

    static class N2146Position implements Comparable<N2146Position>{
        int x;
        int y;
        int n;
        int size;

        public N2146Position(int x, int y, int n, int size) {
            this.x = x;
            this.y = y;
            this.n = n;
            this.size = size;
        }

        @Override
        public int compareTo(N2146Position other) {
            return Integer.compare(this.n, other.n);
        }
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}