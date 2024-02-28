import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int K, width, height;
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static final int[][] monkey = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        int[][] squares = new int[height][width];
        for (int x = 0; x < height; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < width; y++) {
                squares[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][][] visited = new boolean[K + 1][height][width];
        Queue<N1600Node> queue = new LinkedList<>();
        queue.offer(new N1600Node(0, 0, K, 0));
        while (!queue.isEmpty()) {
            N1600Node pop = queue.poll();

            if (pop.x == (height - 1) && pop.y == (width - 1)) {
                System.out.println(pop.count);
                System.exit(0);
            }

            if (pop.remain > 0) {
                for (int[] ints : monkey) {
                    int newX = pop.x + ints[0];
                    int newY = pop.y + ints[1];

                    if (isIn(newX, newY) && squares[newX][newY] == 0 && !visited[pop.remain - 1][newX][newY]) {
                        visited[pop.remain - 1][newX][newY] = true;
                        queue.offer(new N1600Node(newX, newY, pop.remain - 1, pop.count + 1));
                    }
                }
            }

            for (int[] ints : direction) {
                int newX = pop.x + ints[0];
                int newY = pop.y + ints[1];

                if (isIn(newX, newY) && squares[newX][newY] == 0) {
                    if (!visited[pop.remain][newX][newY]) {
                        visited[pop.remain][newX][newY] = true;
                        queue.offer(new N1600Node(newX, newY, pop.remain, pop.count + 1));
                    }

                }
            }
        }
        System.out.println(-1);
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < height && y < width;
    }

    static class N1600Node {
        int x;
        int y;
        int remain;
        int count;

        public N1600Node(int x, int y, int remain, int count) {
            this.x = x;
            this.y = y;
            this.remain = remain;
            this.count = count;
        }
    }
}